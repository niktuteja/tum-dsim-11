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
		
		if (state.getWaitingQueueLength() > 0) {
			EventBase event = state.dequeueWaitingEvent();
			event.process();
		}
	}

//	@Override
//	public int compareTo(EventBase evt) {
//		if (evt == null)
//			throw new NullPointerException();
//
//		// HACK service completion events are more important than anything else 
//		// if (!(evt instanceof ServiceCompletion) && this.getExecutionTime() == evt.getExecutionTime())
//		//	return -1;
//		
//		return super.compareTo(evt);
//
//	}
	
}
