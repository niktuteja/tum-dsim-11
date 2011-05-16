package tum.des.homework.simulator;

import tum.des.homework.simulator.events.EventBase;

/**
 * 
 * Holds information about the current system state
 *
 */
public class SimulationState {

	private final DiscreteEventSimulator discreteEventSimulator;
	private long ticks = 0;
	
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
	
	public void enqueueEvent(EventBase evt)	{
		// TODO check if time < time_now ?
		discreteEventSimulator.getEventQueue().enqueueEvent(evt);
	}

	public void setTicks(long time) {
		this.ticks = time;
		System.out.println("ticks = " + this.ticks);
		
	}
}
