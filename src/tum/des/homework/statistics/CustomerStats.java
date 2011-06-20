package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.simulator.events.CustomerArrival;
import tum.des.homework.simulator.events.CustomerDeadline;

public class CustomerStats {
	private final long initialArrivalTime;
	private long serviceInitTime;
	private long serviceCompletionTime;
	private final SimulationState state;
	public CustomerDeadline deadlineEvent;

	public CustomerStats(long initialArrivalTime, SimulationState state) {
		this.initialArrivalTime = initialArrivalTime;
		this.state = state;
		this.deadlineEvent = null;
	}

	public void setServiceStarted() {
		serviceInitTime = state.getTicks();
		state.waitingTime.count(serviceInitTime - initialArrivalTime);
	}

	public void setServiceCompleted() {
		serviceCompletionTime = state.getTicks();
		state.processingTime.count(serviceCompletionTime - serviceInitTime);

		// retentionTime = Verweildauer = Wartezeit + Bedienzeit
		state.retentionTime.count(serviceCompletionTime - initialArrivalTime);

		state.utilization.count(state.getWaitingQueueLength() > 0 ? 1 : 0);

		state.satisfiedCustomers.count(1);

		state.eventQueue.removeEvent(deadlineEvent);
	}

	public long getInitialArrivalTime() {
		return initialArrivalTime;
	}

	public long getServiceCompletionTime() {
		return serviceCompletionTime;
	}

	public long getServiceInitTime() {
		return serviceInitTime;
	}

	public void cancelByDeadline(CustomerArrival arrivalEvent) {
		state.satisfiedCustomers.count(0);

		// remove the CustomerArrival from the waiting queue
		state.waitingQueue.remove(arrivalEvent);
	}
}
