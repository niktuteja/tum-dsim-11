package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;
import tum.des.homework.statistics.CustomerStats;
import tum.des.homework.util.Log;

public class CustomerDeadline extends EventBase {

	private final CustomerStats customerStats;
	private final CustomerArrival customerArrivalEvent;

	public CustomerDeadline(long executionTime, CustomerStats customerStats, CustomerArrival customerArrivalEvent, SimulationState state) {
		super(executionTime, state);
		this.customerArrivalEvent = customerArrivalEvent;
		this.customerStats = customerStats;
	}

	@Override
	public void process() {
		customerStats.cancelByDeadline(customerArrivalEvent);
		Log.v("Deadline", "customer removed because of deadline");
	}

}
