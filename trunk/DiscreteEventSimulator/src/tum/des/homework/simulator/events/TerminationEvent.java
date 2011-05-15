package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

public class TerminationEvent extends EventBase {

	public TerminationEvent(long exectutionTime, SimulationState state) {
		super(exectutionTime, state);
	}
	
	@Override
	public void process() {
		super.process();
		
		state.stopSimulation();
	}
	
	protected long getProcessingTime()
	{
		return 0;
	}
}
