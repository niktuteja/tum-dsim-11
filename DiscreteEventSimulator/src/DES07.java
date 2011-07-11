import Analysis.CounterCollection;
import RandVar.Constant;
import RandVar.Exponential;
import RandVar.RandVar;
import RandVar.Rng;
import Simulator.BatchArrival;
import Simulator.CustomerArrival;
import Simulator.SimEvent;
import Simulator.SimState;
import Simulator.SimulationTermination;

/**
 * Exercise 6 Discrete Event Simulation SS 2011
 * 
 * DES06 class Main class performing the simulation.
 * 
 * @author Alexander Klein
 * @version 1.0.0
 * @since 2011-07-06
 */
public class DES07 {
	static long real_time_to_sim_time = 100;
	static Rng a = new Rng(100, 16807, 0, (1 << 31) - 1);
	static Rng b = new Rng(200, 16807, 0, (1 << 31) - 1);
	static Rng c = new Rng(300, 16807, 0, (1 << 31) - 1);
	static long simulation_duration = (long) (Math.pow(10, 5) * real_time_to_sim_time);

	/**
	 * main. Application entry point for DES exercise 6
	 * 
	 * @param args
	 *            args[0] indicates the exercise 1 or 2 Exercise 1: args[1] =
	 *            lazyThreshold Exercise 1: args[2] = speedFactor Exercise 2:
	 *            args[1] specifies the simulated system
	 */
	public static void main(String[] args) {
		// Exercise 1
		exercise1H();
	}

	private static void exercise1A() {
		RandVar iat, sct;
		iat = new Exponential(9.0 / real_time_to_sim_time);
		sct = new Exponential(10.0 / real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 1;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1B() {
		RandVar iat, sct;
		iat = new Exponential(9.0 / real_time_to_sim_time);
		sct = new Exponential(1.0 / real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 10;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1C() {
		RandVar iat, sct;
		iat = new Exponential(9.0 / real_time_to_sim_time);
		sct = new Exponential(20.0 / real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 1;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1D() {
		RandVar iat, sct;
		iat = new Exponential(9.0 / real_time_to_sim_time);
		sct = new Exponential(2.0 / real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 10;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1E() {
		RandVar iat, sct;
		iat = new Constant(1.0 / 9.0 * real_time_to_sim_time);
		sct = new Exponential(10.0 / real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 1;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1F() {
		RandVar iat, sct;
		iat = new Constant(1.0 / 9.0 * real_time_to_sim_time);
		sct = new Exponential(1.0 / real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 10;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1G() {
		RandVar iat, sct;
		iat = new Exponential(9.0 / real_time_to_sim_time);
		sct = new Constant(1.0 / 20.0 * real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 1;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	private static void exercise1H() {
		RandVar iat, sct;
		iat = new Exponential(9.0 / real_time_to_sim_time);
		sct = new Constant(1.0 / 2.0 * real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		int numServers = 10;

		SimState.s = new SimState(iat, sct, simulation_duration, maxQueueSize);
		SimState.s.numServers = numServers;
		runSimulation("1");
	}

	static void runSimulation(String args) {
		//Initialize CounterCollection
		CounterCollection.cc = new CounterCollection();

		//Insert the first event:
		//CustomerArrival for exercise 1 or
		//BatchArrival for exercise 2
		if (args.equals("1"))
			SimState.s.ec.insert(new CustomerArrival(0));
		else
			SimState.s.ec.insert(new BatchArrival(0));

		//Insert termination event
		SimState.s.ec.insert(new SimulationTermination(SimState.s.simulationDuration));

		/**
		 * Simulation is done here
		 */

		while (SimState.s.stop != true) {
			// Get the next SimEvent from the EventChain
			SimEvent e = SimState.s.ec.removeOldestEvent();

			if (e != null) {
				if (SimState.s.now > e.value) {
					System.out.println("_____________________________________________________");
					System.out.println("ERROR: Time warp detected");
					System.out.println("Event Time" + e.value);
					System.out.println("System Time" + SimState.s.now);
					System.out.println("_____________________________________________________");
				}
				SimState.s.now = e.value;

				e.process();
			} else {
				System.out.println("EventChain is empty! Simulation aborted!");
				SimState.s.stop = true;
			}

			// Updating the min and the max attribute of the SimState
			SimState.s.min = (SimState.s.queue.size() < SimState.s.min ? SimState.s.queue.size() : SimState.s.min);
			SimState.s.max = (SimState.s.queue.size() > SimState.s.max ? SimState.s.queue.size() : SimState.s.max);

		}

		// report general simulation stats - stats are chosen depending on the exercise
		if (args.equals("1")) {
			System.out.println("E[inter-arrival time] = " + SimState.s.iat.getMean());
			System.out.println("E[service completion time] = " + SimState.s.sct.getMean());
			System.out.println("simulation duration = " + SimState.s.simulationDuration);
			System.out.println("minimum queue length = " + SimState.s.min);
			System.out.println("maximum queue length = " + SimState.s.max);
		} else {
			System.out.println("E[batch-inter-arrival time] = " + SimState.s.biat.getMean());
			System.out.println("E[batch-size] = " + SimState.s.batchSize.getMean());
			System.out.println("E[inter-arrival time] = " + SimState.s.biat.getMean() / SimState.s.batchSize.getMean());
			System.out.println("E[service completion time] = " + SimState.s.sct.getMean());
			System.out.println("simulation duration = " + SimState.s.simulationDuration);
			System.out.println("minimum queue length = " + SimState.s.min);
			System.out.println("maximum queue length = " + SimState.s.max);
		}
		//Report includes histograms
		CounterCollection.cc.report();
		System.out.println("___________________________________________________________________");

	}
}
