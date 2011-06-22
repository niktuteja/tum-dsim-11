package tum.des.homework.simulator;

import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import tum.des.homework.simulator.events.CustomerArrival;
import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.simulator.events.TerminationEvent;
import tum.des.homework.statistics.DCounter;
import tum.des.homework.statistics.TDCounter;

/**
 * 
 * Holds information about the current system state
 * 
 */
public class SimulationState {
	// flag for determining if the machine is (or should be) stopped.
	boolean stopped = false;

	// simulation event queue
	final EventQueue eventQueue;

	long resolution;

	public long numCustomers;

	public RandVar interArrivalTimes;
	public RandVar serviceTimes;

	long ticks = 0;
	final Queue<EventBase> waitingQueue = new LinkedList<EventBase>();
	boolean serverIsBusy = false;

	public DCounter waitingQueueLength = new DCounter(this);
	public DCounter waitingTime = new DCounter(this);
	public DCounter processingTime = new DCounter(this);
	public DCounter retentionTime = new DCounter(this);
	public DCounter customerBlocked = new DCounter(this);
	public TDCounter utilization = new TDCounter(this);

	private long waitingQueueMaxSize = Long.MAX_VALUE;

	public SimulationState(Properties props) {
		resolution = Long.parseLong(props.getProperty("resolution"));

		String maxSize = props.getProperty("waitingQueue.maxSize");
		if (maxSize != null) {
			try {
				this.waitingQueueMaxSize = Long.parseLong(maxSize);
			} catch (NumberFormatException e) {
				// swallow
			}
		}

		long terminationTime = (long) Double.parseDouble(props.getProperty("terminationTime"));
		//ensure this.resolution is set
		terminationTime = Utils.secondsToTicks(terminationTime, this);

		eventQueue = new EventQueue(props);

		eventQueue.enqueueEvent(new TerminationEvent(terminationTime, this));

		interArrivalTimes = DistributionFactory.getDistribution("interArrivalTimes", props);
		serviceTimes = DistributionFactory.getDistribution("serviceTimes", props);

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

	public void addToWaitingQueue(EventBase event) {
		waitingQueueLength.count(waitingQueue.size());
		if (waitingQueue.size() < waitingQueueMaxSize) {
			this.waitingQueue.add(event);
		} else {
			// FIXME add blocking counter
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

		s.append("|| x        || " + waitingTime.getMean() + "||" + processingTime.getMean() + "|| " + retentionTime.getMean() + "|| "
				+ waitingQueueLength.getMean() + "|| " + customerBlocked.getMean() + "|| " + utilization.getMean() + "||");

		return s.substring(0);
	}
}