import Analysis.CounterCollection;
import RNG.Rng;
import RandVar.Erlang;
import RandVar.Exponential;
import RandVar.RandVar;
import Simulator.CustomerArrival;
import Simulator.SimEvent;
import Simulator.SimState;
import Simulator.SimulationTermination;

/**
 * Exercise 4 Discrete Event Simulation SS2011
 * 
 * DES04 class Main class reading the arguments and performing the simulation.
 * 
 * @author Bader, D�lle, P�hlmann
 * @version 1.0.0
 * @since 2011-06-10
 */
public class DES04 {
	
	private static Rng CONFIG_A = new Rng(16807, 0, 1, 2147483647);
	private static Rng CONFIG_B = new Rng(65539, 1, 0, 201); 
	private static Rng CONFIG_C = new Rng(65539, 19, 0, 201);
	
	
	/**
	 * Main executing function
	 * 
	 * @param args
	 *            console parameters
	 */
	public static void main(String[] args) {
		// Initialize SimState with the given arguments

//		testRNG();
//		exercise1d();
//		exercise2a();
		//exercise2b();
		//exercise2c();
		exercise3b();

		//Initialize CounterCollection
		CounterCollection.cc = new CounterCollection();

		//Insert the first and the last event
		SimState.s.ec.insert(new CustomerArrival(0));
		if (SimState.s.simulationDuration >= 0)
			SimState.s.ec.insert(new SimulationTermination(SimState.s.simulationDuration));

		/**
		 * Simulation is done here
		 */
		while (SimState.s.stop != true) {
			// Get the next SimEvent from the EventChain
			SimEvent e = SimState.s.ec.removeOldestEvent();
			
//			if (SimState.s.now > 0 && SimState.s.simulationDuration % SimState.s.now == 10000){
//				System.out.println(".");
//			}
			
			if (e != null) {
				if (SimState.s.now > e.value) {
					System.err.println("_____________________________________________________");
					System.err.println("ERROR");
					System.err.println("Event_Time = " + e.value);
					System.err.println("System_Time = " + SimState.s.now);
					System.err.println("EVENT SHOULD BE ALREADY EXECUTED IN THE PAST");
					System.err.println("_____________________________________________________");
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

		CounterCollection.cc.report();

	}

	private static void calculateAutoCorrelations(Rng rng, int maxLag, int sampleSize) {
		for (int lag = 0; lag < maxLag; lag++) {
			for (int i = 0; i < sampleSize; i++) {
				rng.nextDouble();
			}
			System.out.printf("\t%s autoCorrelation(lag=%d) = %f\n", rng, lag, rng.autoCorrelation(lag));
		}
	}

	private static void exercise1d() {
		System.out.println("Exercise 1d: autocorrelations");

		System.out.println("Config A");
		calculateAutoCorrelations(CONFIG_A, 10, 10000);

		System.out.println("Config B:");
		calculateAutoCorrelations(CONFIG_B, 10, 10000);

		System.out.println("Config C:");
		calculateAutoCorrelations(CONFIG_C, 10, 10000);
	}

	private static void exercise2a() {
		//System:-------------- M / M / 1 - INF -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 8 / real_time_to_sim_time, CONFIG_A);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time, CONFIG_B);
		long simulation_duration = 10000000 * real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		SimState.s = new SimState (iat, sct, simulation_duration, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	
	private static void exercise2b() {
		//System:-------------- M / M / 1 - INF -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 8 / real_time_to_sim_time, CONFIG_B);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time, CONFIG_C);
		long simulation_duration = 10000000 * real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		SimState.s = new SimState (iat, sct, simulation_duration, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	
	private static void exercise2c() {
		//System:-------------- M / M / 1 - INF -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Erlang(10, (double) 9/10 / real_time_to_sim_time, CONFIG_B);
		RandVar sct = new Erlang(10, (double) 1 / real_time_to_sim_time, CONFIG_C);
		long simulation_duration = 10000000 * real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		SimState.s = new SimState (iat, sct, simulation_duration, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	
	private static void exercise3a() {
		//System:-------------- M / M / 1 - INF -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 8 / real_time_to_sim_time, CONFIG_A);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time, CONFIG_B);
		long endTransient = 100 * real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		SimState.s = new SimState (iat, sct, 1e-4, endTransient, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	
	private static void exercise3b() {
		//System:-------------- M / M / 1 - INF -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 8 / real_time_to_sim_time, CONFIG_B);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time, CONFIG_C);
//		long simulation_duration = 10000000 * real_time_to_sim_time;
		long endTransient = 100 * real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		SimState.s = new SimState (iat, sct, 1e-6, endTransient, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}	
	private static void testRNG() {
		//Test the RNG by printing some values
		Rng r = CONFIG_A;
		System.out.println(r);
		for (int i = 0; i < 100; i++) {
			System.out.println(r.nextDouble());
		}
	}
}
