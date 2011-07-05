import Analysis.ContinuousHistogram;
import Analysis.CounterCollection;
import Analysis.DiscreteHistogram;
import Analysis.Histogram;
import RandVar.Exponential;
import RandVar.Geometric;
import RandVar.Normal;
import RandVar.RandVar;
import Simulator.CustomerArrival;
import Simulator.SimEvent;
import Simulator.SimState;
import Simulator.SimulationTermination;

/**
 * Exercise 5 Discrete Event Simulation SS 2011
 * 
 * DES05 class Main class performing the simulation.
 * 
 * @author Bader, D�lle, P�hlmann
 * @version 1.0.0
 * @since 2011-06-21
 */
public class DES05 {
	private static final int STRATEGY_FIFO = 0;
	private static final int STRATEGY_EDF = 1;

	/**
	 * main. Application entry point for DES exercise 5
	 * 
	 * @param args
	 *            if no arguments are given, all subsections will run. Otherwise
	 *            give a number from 1 to 3 to indicate which subsection to run.
	 */
	public static void main(String[] args) {
		//Ex 1
//				testExercise1a();
				
				//		
//				exercise1d();
//				exercise1e();
				
		// Ex 2
//		exercise2(STRATEGY_EDF);
				exercise2(STRATEGY_FIFO);

		//Initialize CounterCollection
		CounterCollection.cc = new CounterCollection();

		//Insert the first and the last event
		SimState.s.ec.insert(new CustomerArrival(0));
		SimState.s.ec.insert(new SimulationTermination(SimState.s.simulationDuration));

		// ----- Setup histograms -------
		CounterCollection.cc.customerWaitingTimeHistogram = new DiscreteHistogram("customer waiting time", 0, 20, 100);

		// The wql histogram should probably be a continous histogram because the cc_qo counter is
		// also a time-based counter. (see CounterCollection.ss.cc_qo)
		CounterCollection.cc.waitingQueueLengthHistogram = new ContinuousHistogram("waiting queue length", 0, 20, 100);

		/**
		 * Simulation is done here
		 */
		while (SimState.s.stop != true) {
			// Get the next SimEvent from the EventChain
			SimEvent e = SimState.s.ec.removeOldestEvent();

			if (e != null) {
				if (SimState.s.now > e.value) {
					System.out.println("_____________________________________________________");
					System.out.println("ERROR");
					System.out.println("Event_Time" + e.value);
					System.out.println("System_Time" + SimState.s.now);
					System.out.println("EVENT SHOULD BE ALREADY EXECUTED IN THE PAST");
					System.out.println("_____________________________________________________");
				}

				SimState.s.now = e.value;

				e.process();
			} else {
				System.out.println("EventChain is empty! Simulation abort!");
				SimState.s.stop = true;
			}

			// Updating the min and the max attribute of the SimState
			SimState.s.min = (SimState.s.queue.size() < SimState.s.min ? SimState.s.queue.size() : SimState.s.min);
			SimState.s.max = (SimState.s.queue.size() > SimState.s.max ? SimState.s.queue.size() : SimState.s.max);
		}

		// reporting
		System.out.println("E[inter-arrival time] = " + SimState.s.iat.getMean() / SimState.s.real_time_to_sim_time);
		System.out.println("E[service completion time] = " + SimState.s.sct.getMean() / SimState.s.real_time_to_sim_time);
		System.out.println("simulation duration = " + SimState.s.simulationDuration / SimState.s.real_time_to_sim_time);
		System.out.println("minimum queue length = " + SimState.s.min);
		System.out.println("maximum queue length = " + SimState.s.max);
		System.out.println("___________________________________________________________________");

		// ---- Print the histograms -------
		CounterCollection.cc.waitingQueueLengthHistogram.report();
		CounterCollection.cc.customerWaitingTimeHistogram.report();

		CounterCollection.cc.report();
	}

	private static void exercise1d() {
		RandVar randVar = new Normal(10, 2);
		Histogram hist = new DiscreteHistogram("normal distributed randVar", 0, 20, 100);

		for (int i = 0; i < 1e7; i++) {
			hist.count(randVar.getRV());
		}

		//Ex 1c !?
		hist.report();
	}

	private static void exercise1e() {
		RandVar randVar = new Geometric(0.1);
		Histogram hist = new DiscreteHistogram("geometric distributed randVar", 0, 20, 20);

		for (int i = 0; i < 1e7; i++) {
			hist.count(randVar.getRV());
		}

		hist.report();
	}

	private static void testExercise1a() {
		Histogram hist = new DiscreteHistogram("testVar", 0, 10, 10);
		hist.count(1.4);
		hist.count(1.6);
		hist.count(2.1);
		hist.count(15);

		hist.report();
	}

	private static void exercise2(int strategy) {
		long real_time_to_sim_time = 100;

		RandVar iat = new Exponential(9.5 / real_time_to_sim_time); // 9.5/s
		iat.setSeed(100);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time); // 10/s
		sct.setSeed(200);
		RandVar cet = new Exponential((double) 200 * real_time_to_sim_time); // 200s
		cet.setSeed(300);

		long simulation_duration = (long)1e7 * real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = strategy; // 0 == FIFO, 1 == EDF
		SimState.s = new SimState(iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy,
				real_time_to_sim_time);
	}
}
