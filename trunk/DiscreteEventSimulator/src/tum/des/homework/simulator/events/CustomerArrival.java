package tum.des.homework.simulator.events;

import tum.des.homework.simulator.DiscreteEventSimulator;
import tum.des.homework.simulator.SimulationState;
import tum.des.homework.simulator.Utils;
import tum.des.homework.statistics.Customer;

public class CustomerArrival extends EventBase {

	// customer object for statistics tracking
	private Customer customer;

	public CustomerArrival(long executionTime, SimulationState state) {
		super(executionTime, state);
		customer = new Customer(executionTime, state);
	}

	@Override
	public void process() {
		//		System.out.println("customer arrives");

		if (!state.isServerBusy()) {
			System.out.println("Queue is empty. Customer can be processed.");
			
			customer.setServiceStarted();

			long serviceTime = Utils.getRandomNumberBetween(DiscreteEventSimulator.getInstance().getMinWaitingTime(),
					DiscreteEventSimulator.getInstance().getMaxWaitingTime());

			System.out.printf("serviceTime is %d ticks.\n", serviceTime);

			ServiceCompletion completionEvent = new ServiceCompletion(this.getExecutionTime() + serviceTime, customer, state);
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
