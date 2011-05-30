package tum.des.homework.simulator;

import java.util.LinkedList;
import java.util.Queue;

import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.statistics.DCounter;
import tum.des.homework.statistics.TDCounter;

/**
 * 
 * Holds information about the current system state
 * 
 */
public class SimulationState {

	private final DiscreteEventSimulator discreteEventSimulator;
	private long ticks = 0;
	private final Queue<EventBase> waitingQueue = new LinkedList<EventBase>();
	private boolean serverIsBusy = false;
	private long minQueueOccupation = Long.MAX_VALUE;
	private long maxQueueOccupation = 0;

	DCounter waitingQueueLength = new DCounter();
	DCounter waitingTime = new DCounter();
	DCounter processingTime = new DCounter();
	DCounter retentionTime = new DCounter();
	DCounter customerBlocked = new DCounter();
	TDCounter utilization = new TDCounter();

	public SimulationState(DiscreteEventSimulator discreteEventSimulator) {
		this.discreteEventSimulator = discreteEventSimulator;
	}

	public void stopSimulation() {
		discreteEventSimulator.stop();
	}

	/**
	 * Increases the simulation time.
	 */
	public void addTicks(long ticks) {
		this.ticks += ticks;

	}

	public void enqueueEvent(EventBase evt) {
		// TODO check if time < time_now ?
		discreteEventSimulator.getEventQueue().enqueueEvent(evt);
	}

	public void setTicks(long time) {
		this.ticks = time;
	}

	public long getResolution() {
		return discreteEventSimulator.getResolution();
	}

	public int getWaitingQueueLength() {
		return this.waitingQueue.size();
	}

	public void addToWaitingQueue(EventBase event) {
		this.waitingQueue.add(event);

		if (waitingQueue.size() < minQueueOccupation) {
			minQueueOccupation = waitingQueue.size();
		}

		if (waitingQueue.size() > maxQueueOccupation) {
			maxQueueOccupation = waitingQueue.size();
		}
	}

	public boolean isServerBusy() {
		return this.serverIsBusy;
	}

	public void setServerBusy(boolean b) {
		this.serverIsBusy = b;
	}

	public EventBase dequeueWaitingEvent() {
		EventBase event = waitingQueue.poll();

		if (waitingQueue.size() < minQueueOccupation) {
			minQueueOccupation = waitingQueue.size();
		}

		return event;
	}

	public long getTicks() {
		return ticks;
	}

	public long getMinWaitingQueueOccupation() {
		if (maxQueueOccupation < minQueueOccupation)
			return maxQueueOccupation;
		return minQueueOccupation;
	}

	public long getMaxWaitingQueueOccupation() {
		return maxQueueOccupation;
	}

}
