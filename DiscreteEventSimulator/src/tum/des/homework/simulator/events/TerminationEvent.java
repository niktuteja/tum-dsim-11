package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

public class TerminationEvent extends EventBase {

	public TerminationEvent(long executionTime, SimulationState state) {
		super(executionTime, state);
	}
	
	@Override
	public void process() {
		System.out.println("stopping simulation...");
		state.stopSimulation();
	}
	
	protected long getServiceTime()	{
		return 0;
	}
}
