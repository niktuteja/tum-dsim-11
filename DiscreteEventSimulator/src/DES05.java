import Analysis.*;
import Simulator.*;
import RandVar.*;

/**
 * Exercise 5		Discrete Event Simulation SS 2011
 *
 * DES05 class
 * Main class performing the simulation.
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2011-06-21
 */
public class  DES05
{
	/**
	 * main. Application entry point for DES exercise 5
	 * 
	 * @param args if no arguments are given, all subsections will run. 
	 */
	public static void main(String[] args) 
	{
		// 1d
		if (args.length == 0 || args[0].equals("1d")) {
			exercise1d();
		}
		// 1d
		if (args.length == 0 || args[0].equals("1e")) {
			exercise1e();
		}
		// 2a
		if (args.length == 0 || args[0].equals("2a")) {
			exercise2a();
		}
		// 2b
		if (args.length == 0 || args[0].equals("2b")) {
			exercise2b();
		}
			
	}
	
	private static void exercise1d() 
	{
		DiscreteHistogram dh = new DiscreteHistogram ("Normal(mu=10, std=2)", 0, 20);
		dh.setupNumIntervals(100);
		int numberOfSamples = (int) Math.pow(10, 5);
		
		//Java based
		//Normal norm = new Normal (10, 2);
		
		//Script
		NormalAcc norm = new NormalAcc (10,2);
		
		for(int i = 0; i < numberOfSamples;i++)
		{
			dh.count(norm.getRV());
		}
		dh.report();
	}

	private static void exercise1e() 
	{
		DiscreteHistogram dh = new DiscreteHistogram ("Geom(p=0.1)", 0, 20);
		dh.setupNumIntervals(20);
		int numberOfSamples = (int) Math.pow(10, 5);
		
		Geom geom = new Geom (0.1);
		
		for(int i = 0; i < numberOfSamples;i++)
		{
			dh.count(geom.getRV());
		}
		dh.report();
	}

	private static void exercise2a() 
	{
		//System A:-------------- M / M / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 9.5 / real_time_to_sim_time);
		iat.setSeed(100);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time);
		sct.setSeed(200);
		//Note: The MEAN of the deadline is given in the exercise!
		RandVar cet = new Exponential((double) 1 / 200 / real_time_to_sim_time);
		cet.setSeed(300);

		long simulation_duration = (long) (Math.pow(10,6)*real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = 0;//FIFO
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		runSimulation();
	}
	private static void exercise2b() 
	{
		//System B:-------------- M / M / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 9.5 / real_time_to_sim_time);
		iat.setSeed(100);
		RandVar sct = new Exponential((double) 10 / real_time_to_sim_time);
		sct.setSeed(200);
		//Note: The MEAN of the deadline is given in the exercise!
		RandVar cet = new Exponential((double) 1 / 200 / real_time_to_sim_time);
		cet.setSeed(300);

		long simulation_duration = (long) (Math.pow(10,6)*real_time_to_sim_time);
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = 1;//EDF
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		runSimulation();
	}
	

	static void runSimulation() 
	{
		//Initialize CounterCollection
		CounterCollection.cc = new CounterCollection();

		//Insert the first and the last event
		SimState.s.ec.insert (new CustomerArrival (0));
		SimState.s.ec.insert (new SimulationTermination(SimState.s.simulationDuration));
		
		/**
		 * Simulation is done here
		 */
		
		while (SimState.s.stop != true) {	
			// Get the next SimEvent from the EventChain
			SimEvent e = (SimEvent) SimState.s.ec.removeOldestEvent ();
			
			if (e != null) {
				if (SimState.s.now > e.value) 
				{
					System.out.println("_____________________________________________________");
					System.out.println("ERROR: Time warp detected");
					System.out.println("Event Time" + e.value);
					System.out.println("System Time" + SimState.s.now);
					System.out.println("_____________________________________________________");
				}
				
				SimState.s.now = e.value;
				
				e.process();
			} 
			else 
			{
				System.out.println("EventChain is empty! Simulation aborted!");
				SimState.s.stop = true;
			}
			
			// Updating the min and the max attribute of the SimState
			SimState.s.min = (SimState.s.queue.size() < SimState.s.min ? SimState.s.queue.size():SimState.s.min);
			SimState.s.max = (SimState.s.queue.size() > SimState.s.max ? SimState.s.queue.size():SimState.s.max);
					
		}
		
			// report general simulation stats
			System.out.println("E[inter-arrival time] = " + SimState.s.iat.getMean());
			System.out.println("E[service completion time] = " + SimState.s.sct.getMean());
			System.out.println("simulation duration = " + SimState.s.simulationDuration);
			System.out.println("minimum queue length = " + SimState.s.min);
			System.out.println("maximum queue length = " + SimState.s.max);
			//Report includes histograms
			CounterCollection.cc.report();
			System.out.println("___________________________________________________________________");
			
		
	}
}
