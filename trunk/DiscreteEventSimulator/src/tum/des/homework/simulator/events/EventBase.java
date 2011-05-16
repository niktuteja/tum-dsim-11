package tum.des.homework.simulator.events;

import tum.des.homework.simulator.SimulationState;

/**
 * 
 * Base class for events.
 * 
 */
public abstract class EventBase implements Comparable<EventBase>{
	
	// holds information about the current system state
	protected final SimulationState state;
	
	// when will the event be executed? 
	private final long executionTime;
	
	// how long needs the event to be processed?
	protected long serviceTime;

	public EventBase(long executionTime, SimulationState state)
	{
		this.executionTime = executionTime;
		this.state = state;
		
	}
	
	public abstract void process();
	
	abstract long getServiceTime();
	
	@Override
	public int compareTo(EventBase evt) {
		if (evt == null)
			throw new NullPointerException();
		
		if (this.executionTime < evt.executionTime)
			return -1;
		
		if (this.executionTime > evt.executionTime)
			return 1;
		
		return 0;
	}

	public long getExecutionTime() {
		return this.executionTime;
	}
}
