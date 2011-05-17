package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.simulator.Utils;

public class CustomerArrival extends EventBase {

	public CustomerArrival(long executionTime, SimulationState state) {
		super(executionTime, state);
	}

	@Override
	public void process() {
		System.out.println("customer arrives");

		if (!state.isServerBusy()) {
			System.out.println("Queue is empty. Customer can be processed.");
			ServiceCompletion completionEvent = new ServiceCompletion(this.getExecutionTime() + Utils.secondsToTicks(11, state), state);
			state.setServerBusy(true);
			state.enqueueEvent(completionEvent);
			return;
		} else {
			System.out.println("Queue is not empty. Customer starts waiting.");
			state.addToWaitingQueue(this);
		}

	}

	@Override
	public String toString() {
		return "CustomerArrival";
	}

}
