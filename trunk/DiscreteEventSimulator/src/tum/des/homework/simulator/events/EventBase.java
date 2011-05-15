package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

public abstract class EventBase implements Comparable<EventBase>{
	
	protected final SimulationState state;
	private final long exectutionTime;

	public EventBase(long exectutionTime, SimulationState state)
	{
		this.exectutionTime = exectutionTime;
		this.state = state;
		
	}
	
	public void process()
	{
		state.tick(this.getProcessingTime());
	}
	
	abstract long getProcessingTime();
	
	@Override
	public int compareTo(EventBase evt) {
		if (evt == null)
			throw new NullPointerException();
		
		if (this.exectutionTime < evt.exectutionTime)
			return -1;
		
		if (this.exectutionTime > evt.exectutionTime)
			return 1;
		
		return 0;
	}
}
