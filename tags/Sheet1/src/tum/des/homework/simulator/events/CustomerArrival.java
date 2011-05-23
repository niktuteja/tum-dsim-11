package tum.des.homework.simulator.events;

import tum.des.homework.simulator.DiscreteEventSimulator;
import tum.des.homework.simulator.SimulationState;
import tum.des.homework.simulator.Utils;

public class CustomerArrival extends EventBase {

	public CustomerArrival(long executionTime, SimulationState state) {
		super(executionTime, state);
	}

	@Override
	public void process() {
		//		System.out.println("customer arrives");

		if (!state.isServerBusy()) {
			System.out.println("Queue is empty. Customer can be processed.");

			long serviceTime = Utils.getRandomNumberBetween(DiscreteEventSimulator.getInstance().getMinWaitingTime(),
					DiscreteEventSimulator.getInstance().getMaxWaitingTime());

			System.out.printf("serviceTime is %d ticks.\n", serviceTime);

			ServiceCompletion completionEvent = new ServiceCompletion(this.getExecutionTime() + serviceTime, state);
			System.out.println("CustomerArrival created new event: " + completionEvent);
			state.setServerBusy(true);
			state.enqueueEvent(completionEvent);
			return;
		} else {
			System.out.println("Queue is not empty. Customer starts waiting.");
			state.addToWaitingQueue(this);
		}

	}
}
