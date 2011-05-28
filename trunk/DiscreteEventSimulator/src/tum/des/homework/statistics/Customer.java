package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public class Customer {
	private long initialArrivalTime;
	private long serviceInitTime;
	private long serviceCompletionTime;
	private final SimulationState state;
	
	public Customer(long initialArrivalTime, SimulationState state)
	{
		this.initialArrivalTime = initialArrivalTime;
		this.state = state;
	}
	
	public void setServiceStarted()
	{
		serviceInitTime = state.getTicks();
	}
	
	public void setServiceCompleted()
	{
		serviceCompletionTime = state.getTicks();
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
