package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.statistics.CustomerStats;
import tum.des.homework.util.Log;

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
		
//		Log.e("ServiceCompletion", "processed customer");
		
		if (state.getWaitingQueueLength() > 0) {
			EventBase event = state.dequeueWaitingEvent();
// (Martin) I think this is a bad idea, cause we have no clue whether the event arrived earlier or not.
//			event.executionTime = this.executionTime;
//			state.enqueueEvent(event);
			event.process();
		}
//				super.process();
	}

}
