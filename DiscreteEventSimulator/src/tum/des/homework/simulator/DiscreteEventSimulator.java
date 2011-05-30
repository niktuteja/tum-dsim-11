package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.simulator.events.CustomerArrival;
import tum.des.homework.simulator.events.EventBase;
import tum.des.homework.simulator.events.TerminationEvent;
import tum.des.homework.statistics.DCounter;

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

	public RandVarExp interArrivalTimes;
	public RandVarExp serviceTimes;

	private DiscreteEventSimulator() {

	}

	public void init(Properties props) {

		state = new SimulationState(this);

		long terminationTime = (long)Double.parseDouble(props.getProperty("terminationTime"));
		resolution = Long.parseLong(props.getProperty("resolution"));
		terminationTime = Utils.secondsToTicks(terminationTime, state);

		minServiceTime = Long.parseLong(props.getProperty("minServiceTime"));
		maxServiceTime = Long.parseLong(props.getProperty("maxServiceTime"));

		eventQueue.enqueueEvent(new TerminationEvent(terminationTime, this.state));
				
		interArrivalTimes = new RandVarExp(Utils.secondsToTicks(1.0/9.5, state), 100);
		serviceTimes = new RandVarExp(Utils.secondsToTicks(1.0/10.0, state), 200);

		// Add the first customer to start the simulation.
		eventQueue.enqueueEvent(new CustomerArrival(interArrivalTimes.getLong(), state));
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
