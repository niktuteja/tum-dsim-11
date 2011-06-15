package tum.des.homework.simulator;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;

import tum.des.homework.simulator.events.CustomerArrival;
import tum.des.homework.simulator.events.DeadlineComparator;
import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.simulator.events.TerminationEvent;
import tum.des.homework.statistics.DCounter;
import tum.des.homework.statistics.TDCounter;
import tum.des.homework.util.Log;

/**
 * 
 * Holds information about the current system state
 * 
 */
public class SimulationState {

	// flag for determining if the machine is (or should be) stopped.
	boolean stopped = false;

	// simulation event queue
	public final EventQueue eventQueue;

	Properties props;

	long resolution;

	public long numCustomers;

	public RandVar interArrivalTimes;
	public RandVar wetInterArrivalTimes;
	public RandVar serviceTimes;
	public RandVar deadlines;
	public boolean waitingQueueUseDeadlines = false;

	long ticks = 0;
	public final Queue<CustomerArrival> waitingQueue;
	boolean serverIsBusy = false;

	public DCounter waitingQueueLength = new DCounter(this);
	public DCounter waitingTime = new DCounter(this);
	public DCounter processingTime = new DCounter(this);
	public DCounter retentionTime = new DCounter(this);
	public DCounter customerBlocked = new DCounter(this);
	public TDCounter utilization = new TDCounter(this);
	public DCounter satisfiedCustomers = new DCounter(this);
	
	public long dryQueueSlots = Long.MAX_VALUE;
	public boolean dryQueueSlotsFull = false;

	private long waitingQueueMaxSize = Long.MAX_VALUE;

	private final RandVar dryInterArrivalTimes;

	public long terminationTime;

	public SimulationState(Properties props) {
		this.props = props;

		resolution = Long.parseLong(props.getProperty("resolution"));

		String maxSize = props.getProperty("waitingQueue.maxSize");
		if (maxSize != null) {
			try {
				this.waitingQueueMaxSize = Long.parseLong(maxSize);
			} catch (NumberFormatException e) {
				// swallow
			}
		}
		
		String drySlots = props.getProperty("waitingQueue.drySlots");
		if (maxSize != null) {
			try {
				this.dryQueueSlots = Long.parseLong(drySlots);
			} catch (NumberFormatException e) {
				// swallow
			}
		}
		
		String wqdl = props.getProperty("waitingQueue.deadlines");
		if (maxSize != null) {
			try {
				waitingQueueUseDeadlines = Boolean.parseBoolean(wqdl);
			} catch (NumberFormatException e) {
				// swallow
			}
		}
		
		@SuppressWarnings("unchecked")
		Comparator<CustomerArrival> comp = (Comparator<CustomerArrival>) EventComparatorFactory.getComparator("waitingQueue", props);
		if (comp != null)
			waitingQueue = new PriorityQueue<CustomerArrival>(1, comp);
		else
			waitingQueue = new PriorityQueue<CustomerArrival>(1);

		terminationTime = (long) Double.parseDouble(props.getProperty("terminationTime"));
		//ensure this.resolution is set
		terminationTime = Utils.secondsToTicks(terminationTime, this);

		eventQueue = new EventQueue(props);

		eventQueue.enqueueEvent(new TerminationEvent(terminationTime, this));

		dryInterArrivalTimes = DistributionFactory.getDistribution("interArrivalTimes", props);
		wetInterArrivalTimes = DistributionFactory.getDistribution("wetInterArrivalTimes", props);
		serviceTimes = DistributionFactory.getDistribution("serviceTimes", props);
		deadlines = DistributionFactory.getDistribution("deadlines", props);

		interArrivalTimes = dryInterArrivalTimes;


		// Add the first customer to start the simulation.
		eventQueue.enqueueEvent(new CustomerArrival(interArrivalTimes.getLong(), this));
	}

	public void stopSimulation() {
		stopped = true;
	}

	/**
	 * Increases the simulation time.
	 */
	public void addTicks(long ticks) {
		this.ticks += ticks;
	}

	public void enqueueEvent(EventBase evt) {
		// TODO check if time < time_now ?
		eventQueue.enqueueEvent(evt);
	}

	public void setTicks(long time) {
		this.ticks = time;
	}

	public long getResolution() {
		return resolution;
	}

	public int getWaitingQueueLength() {
		return this.waitingQueue.size();
	}

	public void addToWaitingQueue(CustomerArrival event) {
		waitingQueueLength.count(waitingQueue.size());
		
		if (waitingQueue.size() < waitingQueueMaxSize) {
			this.waitingQueue.add(event);
			customerBlocked.count(0);

			// Sheet 3, Ex. 1, addition (dry & wet waiting slots)
			if (!dryQueueSlotsFull && waitingQueue.size() > dryQueueSlots) {
				interArrivalTimes = wetInterArrivalTimes;
				dryQueueSlotsFull = true;
				Log.v("simstate", "now wet");
			}
			if (dryQueueSlotsFull && waitingQueue.size() <= dryQueueSlots) {
				interArrivalTimes = dryInterArrivalTimes;
				dryQueueSlotsFull = false;
				Log.v("simstate", "now dry");
			}

		} else {
			customerBlocked.count(1);
		}
	}

	public boolean isServerBusy() {
		return this.serverIsBusy;
	}

	public void setServerBusy(boolean b) {
		this.serverIsBusy = b;
	}

	public EventBase dequeueWaitingEvent() {
		//		waitingQueueLength.count(waitingQueue.size());
		EventBase event = waitingQueue.poll();
		return event;
	}

	public long getTicks() {
		return ticks;
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("numCustomers = " + numCustomers + "\n");
		s.append("waitingQueueLength = " + waitingQueueLength + "\n");
		s.append("waitingTime = " + waitingTime + "\n");
		s.append("processingTime = " + processingTime + "\n");
		s.append("retentionTime = " + retentionTime + "\n");
		s.append("customerBlocked = " + customerBlocked + "\n");
		s.append("utilization = " + utilization + "\n");
		s.append("satisfiedCustomers = " + satisfiedCustomers + "\n");

		s.append("\n");
		String fmt = "|| %6.6s || %20.20s || %20.20s || %20.20s || %20.20s ||\n";
		s.append(String.format(fmt, "system","avg waiting time","avg waiting queue length","avg utilization", "satisfied customers %"));
		s.append(String.format(fmt, "x",waitingTime.getMean(),waitingQueueLength.getMean(),utilization.getMean(), satisfiedCustomers.getMean() * 100.0f));

		return s.toString();
	}
}
