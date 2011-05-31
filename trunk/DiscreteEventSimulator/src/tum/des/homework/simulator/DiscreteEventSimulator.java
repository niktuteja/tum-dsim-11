package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.simulator.events.EventBase;

public class DiscreteEventSimulator {
	SimulationState state;

	public void init(Properties props) {
		state = new SimulationState(props);
	}

	/**
	 * Starts the simulation.
	 */
	public void start() {

		while (true) {
			if (state.stopped == true)
				break;

			EventBase evt = state.eventQueue.dequeueNextEvent();

			if (evt == null)
				throw new IllegalStateException("EventQueue empty");

			state.setTicks(evt.getExecutionTime());

			System.out.printf("Now = %d ticks: Processing event %s\n", state.getTicks(), evt);

			evt.process();

			System.out.println();

		}

		System.out.println("simulation stopped.");
		System.out.println(state);

		// TODO stats

	}
}
