package tum.des.homework.simulator;

import java.util.Date;
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
	@SuppressWarnings("deprecation")
	public void start() {

		System.out.println("simulation started.");

		while (true) {
			if (state.stopped == true)
				break;

			EventBase evt = state.eventQueue.dequeueNextEvent();

			if (evt == null)
				throw new IllegalStateException("EventQueue empty");

			state.setTicks(evt.getExecutionTime());

			// Show that we are still alive by printing a message every 10 realtime second.
			if (state.getTicks() % (10000*state.getResolution()) == 0) {
				Log.d("DiscreteEventSimulator", "Heartbeat: [now = %d ticks]", state.getTicks());
			}

			Log.v("DiscreteEventSimulator", "Now = %d ticks: Processing event %s\n", state.getTicks(), evt);

			evt.process();

		}

		System.out.println("simulation stopped.");
		System.out.println(state);

		// TODO stats

	}
}
