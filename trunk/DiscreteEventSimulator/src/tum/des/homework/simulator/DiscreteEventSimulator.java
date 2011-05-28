package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.simulator.events.CustomerArrival;
import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.simulator.events.TerminationEvent;

public class DiscreteEventSimulator {
	private static final DiscreteEventSimulator instance = new DiscreteEventSimulator();

	public static DiscreteEventSimulator getInstance() {
		return instance;
	}

	// flag for determining if the machine is (or should be) stopped.
	private boolean stopped = false;

	// simulation event queue
	private final EventQueue eventQueue = new EventQueue();

	private long minServiceTime;

	private long maxServiceTime;

	private SimulationState state;

	private long resolution;

    RandVar interArrivalTimes = new RandVar(50, 100); // TODO: choose proper mean value
    RandVar serviceTimes = new RandVar(200, 200);     // TODO: setup the mean


	private DiscreteEventSimulator() {

        // Test the RandVar class
        for(int i = 0; i < 100; i++) {
            System.out.printf("iat = %d, sct = %d\n", interArrivalTimes.getLong(), serviceTimes.getLong());
        }
	}

	public void init(Properties props) {

		state = new SimulationState(this);

		long terminationTime = Long.parseLong(props.getProperty("terminationTime"));
		resolution = Long.parseLong(props.getProperty("resolution"));
		terminationTime = Utils.secondsToTicks(terminationTime, state);

		minServiceTime = Long.parseLong(props.getProperty("minServiceTime"));
		maxServiceTime = Long.parseLong(props.getProperty("maxServiceTime"));

		eventQueue.enqueueEvent(new TerminationEvent(terminationTime, this.state));

		int numCustomers = Integer.parseInt(props.getProperty("numCustomers"));

//		for (int i = 0; i < numCustomers; i++) {
//			eventQueue.enqueueEvent(new CustomerArrival(i * Utils.secondsToTicks(10, state), state));
//		}

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

			System.out.printf("Now = %d ticks: Processing event %s\n", state.getTicks(), evt);

			evt.process();

			//			System.out.printf("Waiting queue occupation: %d (min = %d, max = %d)\n", state.getWaitingQueueLength(), state
			//					.getMinWaitingQueueOccupation(), state.getMaxWaitingQueueOccupation());

			System.out.println();

		}

		System.out.println("simulation stopped.");

		System.out.printf("Waiting queue occupation: %d (min = %d, max = %d)\n", state.getWaitingQueueLength(), state
				.getMinWaitingQueueOccupation(), state.getMaxWaitingQueueOccupation());

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

	public long getMinWaitingTime() {
		return minServiceTime;
	}

	public long getMaxWaitingTime() {
		return maxServiceTime;
	}

}
