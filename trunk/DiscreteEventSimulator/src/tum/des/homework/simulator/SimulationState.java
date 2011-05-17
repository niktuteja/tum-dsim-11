package tum.des.homework.simulator;

import java.util.LinkedList;
import java.util.Queue;

import tum.des.homework.simulator.events.EventBase;

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
		System.out.println("ticks = " + this.ticks);

	}

	public long getResolution() {
		return discreteEventSimulator.getResolution();
	}

	public int getWaitingQueueLength() {
		return this.waitingQueue.size();
	}

	public void addToWaitingQueue(EventBase event) {
		this.waitingQueue.add(event);
	}

	public boolean isServerBusy() {
		return this.serverIsBusy;
	}

	public void setServerBusy(boolean b) {
		this.serverIsBusy = b;
	}

	public EventBase dequeueWaitingEvent() {
		return waitingQueue.poll();
	}
}
