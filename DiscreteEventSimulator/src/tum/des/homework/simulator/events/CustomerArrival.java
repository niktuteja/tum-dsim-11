package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.statistics.Customer;

public class CustomerArrival extends EventBase {

	// customer object for statistics tracking
	private final Customer customer;

	public CustomerArrival(long executionTime, SimulationState state) {
		super(executionTime, state);
		customer = new Customer(executionTime, state);
	}

	@Override
	public void process() {
		if (executionTime == state.getTicks()) {
			// executed for the first time, create a new customer
			long arrivalTime = state.interArrivalTimes.getLong();
			state.enqueueEvent(new CustomerArrival(this.getExecutionTime() + arrivalTime, state));
		}

		//		System.out.println("customer arrives");		
		if (!state.isServerBusy()) {
			System.out.println("Queue is empty. Customer can be processed.");

			customer.setServiceStarted();

			long serviceTime = state.serviceTimes.getLong();

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
