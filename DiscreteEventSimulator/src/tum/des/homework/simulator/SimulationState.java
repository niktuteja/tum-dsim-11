package tum.des.homework.simulator;

import tum.des.homework.simulator.events.EventBase;

public class SimulationState {

	private final DiscreteEventSimulator discreteEventSimulator;
	private long ticks = 0;
	private long resolution = 1000;

	public SimulationState(DiscreteEventSimulator discreteEventSimulator) {
		this.discreteEventSimulator = discreteEventSimulator;
	}
	
	public void stopSimulation()
	{
		discreteEventSimulator.stop();
	}

	/**
	 * Increases the simulation time. TODO use ms instead of ticks?
	 */
	public void tick(long processingTicks) {
		ticks += processingTicks;
		
	}
	
	public void enqueueEvent(EventBase evt)
	{
		// TODO check if time < time_now ?
		discreteEventSimulator.getEventQueue().enqueueEvent(evt);
	}
}
