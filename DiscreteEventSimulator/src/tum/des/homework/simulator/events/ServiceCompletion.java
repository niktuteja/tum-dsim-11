package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.statistics.CustomerStats;

public class ServiceCompletion extends EventBase {

	private final CustomerStats customer;

	public ServiceCompletion(long executionTime, CustomerStats customer, SimulationState state) {
		super(executionTime, state);
		this.customer = customer;
	}

	@Override
	public void process() {
		state.setServerBusy(false);
		customer.setServiceCompleted();
		// TODO process stats
		
		if (state.getWaitingQueueLength() > 0) {
			EventBase event = state.dequeueWaitingEvent();
			event.executionTime = this.executionTime;
			state.enqueueEvent(event);
		}
		//		super.process();
	}

}
