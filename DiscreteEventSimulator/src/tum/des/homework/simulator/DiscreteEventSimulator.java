package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.util.Log;

public class DiscreteEventSimulator {
	SimulationState state;

	public void init(Properties props) {
		state = new SimulationState(props);
	}

	/**
	 * Starts the simulation.
	 */
	public void start() {

		System.out.println("simulation started.");

		while (true) {
			if (state.stopped == true)
				break;

			EventBase evt = state.eventQueue.dequeueNextEvent();

			if (evt == null)
				throw new IllegalStateException("EventQueue empty");

			state.setTicks(evt.getExecutionTime());

			Log.v("DiscreteEventSimulator", "Now = %d ticks: Processing event %s\n", state.getTicks(), evt);

			evt.process();

		}

		System.out.println("simulation stopped.");
		System.out.println(state);

		// TODO stats

	}
}
