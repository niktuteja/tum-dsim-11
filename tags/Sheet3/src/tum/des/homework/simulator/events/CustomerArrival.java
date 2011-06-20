package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.statistics.CustomerStats;
import tum.des.homework.util.Log;

public class CustomerArrival extends EventBase {

	// customer object for statistics tracking
	final CustomerStats customerStats;

	private boolean didReproduce = false;

	public CustomerArrival(long executionTime, SimulationState state) {
		super(executionTime, state);

		customerStats = new CustomerStats(executionTime, state);

		if (state.waitingQueueUseDeadlines)
		{
			long deadline = executionTime + state.deadlines.getLong();
			CustomerDeadline deadlineEvent = new CustomerDeadline(deadline, customerStats, this, state);
	
			customerStats.deadlineEvent = deadlineEvent;
			state.enqueueEvent(deadlineEvent);
		}

		state.numCustomers++;
	}

	@Override
	public void process() {
		// If executed for the first time, create a new customer
		if (!didReproduce) {
			didReproduce = true;
			long arrivalTime = state.interArrivalTimes.getLong();
			state.enqueueEvent(new CustomerArrival(state.getTicks() + arrivalTime, state));
		}

		if (!state.isServerBusy()) {

			state.customerBlocked.count(0);
			Log.v("CustomerArrival", "Queue is empty. Customer can be processed.");
			customerStats.setServiceStarted();

			long serviceTime = state.serviceTimes.getLong();

			Log.v("CustomerArrival", "serviceTime is %d ticks.\n", serviceTime);

			ServiceCompletion completionEvent = new ServiceCompletion(state.getTicks() + serviceTime, customerStats, state);
			Log.v("CustomerArrival", "created new event: " + completionEvent);
			state.setServerBusy(true);
			state.enqueueEvent(completionEvent);
		} else {
			Log.v("CustomerArrival", "Queue is not empty. Customer starts waiting.");
			state.addToWaitingQueue(this);
		}

	}

	@Override
	public String toString() {
		return String.format("CustomerArrival(Texec=%d)", this.getExecutionTime());
	}
}
