package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public class CustomerStats {
	private final long initialArrivalTime;
	private long serviceInitTime;
	private long serviceCompletionTime;
	private final SimulationState state;
	public long deadline;

	public CustomerStats(long initialArrivalTime, SimulationState state, long deadline) {
		this.initialArrivalTime = initialArrivalTime;
		this.state = state;
		this.deadline = deadline;
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

		state.utilization.count(serviceCompletionTime - serviceInitTime);

		// check wheter it's a happy customer or an unsatisfied customer
		if (serviceCompletionTime <= deadline) {
			// a satisfied customer
			state.satisfiedCustomers.count(1);
		} else {
			// an unsatisfied customer
			state.satisfiedCustomers.count(0);
		}

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
}
