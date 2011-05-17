package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

public class ServiceCompletion extends EventBase {

	public ServiceCompletion(long executionTime, SimulationState state) {
		super(executionTime, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() {
		state.setServerBusy(false);
		if (state.getWaitingQueueLength() > 0) {
			EventBase event = state.dequeueWaitingEvent();
			event.executionTime = this.executionTime;
			state.enqueueEvent(event);
		}
		//		super.process();
		// TODO Auto-generated method stub

	}

}
