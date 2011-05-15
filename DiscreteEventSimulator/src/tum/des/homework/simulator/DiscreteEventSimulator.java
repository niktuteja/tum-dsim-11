package tum.des.homework.simulator;

import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.simulator.events.TerminationEvent;

public class DiscreteEventSimulator {

	// flag for determining if the machine is (or should be) stopped.
	private boolean stopped = false;
	
	// simulation event queue
	private EventQueue eventQueue = new EventQueue();

	/**
	 * Starts the simulation.
	 */
	public void start() {
		SimulationState state = new SimulationState(this);
		
		while (true) {
			if (stopped == true)
				break;
			
			EventBase evt = eventQueue.dequeueNextEvent();
			
			if (evt == null)
				throw new IllegalStateException("EventQueue empty");
			
			evt.process();
		}
		
		// TODO stats
	}
	
	/**
	 * Stops the simulation
	 */
	public void stop() {
		this.stopped = true;
	}

	public EventQueue getEventQueue() {
		return eventQueue;
	}

}
