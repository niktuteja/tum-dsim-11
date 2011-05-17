package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.simulator.events.CustomerArrival;
import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.simulator.events.TerminationEvent;

public class DiscreteEventSimulator {

	// flag for determining if the machine is (or should be) stopped.
	private boolean stopped = false;

	// simulation event queue
	private final EventQueue eventQueue = new EventQueue();

	private long minWaitingTime;

	private long maxWaitingTime;

	private SimulationState state;

	private long resolution;

	public void init(Properties props) {

		state = new SimulationState(this);

		long terminationTime = Long.parseLong(props.getProperty("termination-time"));
		resolution = Long.parseLong(props.getProperty("resolution"));
		terminationTime = Utils.secondsToTicks(terminationTime, state);

		eventQueue.enqueueEvent(new TerminationEvent(terminationTime, this.state));

		int numCustomers = Integer.parseInt(props.getProperty("num-customers"));

		for (int i = 0; i < numCustomers; i++) {
			eventQueue.enqueueEvent(new CustomerArrival(i * Utils.secondsToTicks(10, state), state));
		}

	}

	/**
	 * Starts the simulation.
	 */
	public void start() {

		while (true) {
			if (stopped == true)
				break;

			EventBase evt = eventQueue.dequeueNextEvent();

			if (evt == null)
				throw new IllegalStateException("EventQueue empty");

			state.setTicks(evt.getExecutionTime());

			evt.process();

		}

		System.out.println("simulation stopped.");

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

	public long getResolution() {
		return resolution;
	}

}
