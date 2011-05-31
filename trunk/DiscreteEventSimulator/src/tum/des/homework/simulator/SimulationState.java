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
	final EventQueue eventQueue = new EventQueue();

	long resolution;

	public long numCustomers;

	public RandVarExp interArrivalTimes;
	public RandVarExp serviceTimes;

	long ticks = 0;
	final Queue<EventBase> waitingQueue = new LinkedList<EventBase>();
	boolean serverIsBusy = false;

	public DCounter waitingQueueLength = new DCounter(this);
	public DCounter waitingTime = new DCounter(this);
	public DCounter processingTime = new DCounter(this);
	public DCounter retentionTime = new DCounter(this);
	public DCounter customerBlocked = new DCounter(this);
	public TDCounter utilization = new TDCounter(this);

	public SimulationState(Properties props) {
		long terminationTime = (long) Double.parseDouble(props.getProperty("terminationTime"));
		resolution = Long.parseLong(props.getProperty("resolution"));
		terminationTime = Utils.secondsToTicks(terminationTime, this);

		eventQueue.enqueueEvent(new TerminationEvent(terminationTime, this));

// FIXME		
//		interArrivalTimes = new RandVarExp(Utils.secondsToTicks(1.0 / 9.5, this), 100);
//		serviceTimes = new RandVarExp(Utils.secondsToTicks(1.0 / 10.0, this), 200);
		//		interArrivalTimes = new RandVarUni(0, 500, 100);
		//		serviceTimes = new RandVarUni(0, 50, 200);

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
		this.waitingQueue.add(event);
		waitingQueueLength.count(waitingQueue.size());
	}

	public boolean isServerBusy() {
		return this.serverIsBusy;
	}

	public void setServerBusy(boolean b) {
		this.serverIsBusy = b;
	}

	public EventBase dequeueWaitingEvent() {
		EventBase event = waitingQueue.poll();
		waitingQueueLength.count(waitingQueue.size());
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
		return s.substring(0);
	}
}
