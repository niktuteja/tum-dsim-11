package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

public class CustomerArrival extends EventBase {

	
	
	public CustomerArrival(long executionTime, SimulationState state) {
		super(executionTime, state);
	}
	
	
	public void setServiceTime(long time){
		this.serviceTime = time;
	}
	
	@Override
	public long getServiceTime() {
		return this.serviceTime;
	}
	
	@Override
	public void process() {
		System.out.println("customer arrives");
	}

}
