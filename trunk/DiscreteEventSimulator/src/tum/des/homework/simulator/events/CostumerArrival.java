package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

public class CostumerArrival extends EventBase {

	public CostumerArrival(long exectutionTime, SimulationState state) {
		super(exectutionTime, state);
	}
	
	@Override
	long getProcessingTime() {
		return 10000; // TODO randomize
	}
	
	@Override
	public void process() {
		super.process();
		
		// TODO modify queue.
	}

}
