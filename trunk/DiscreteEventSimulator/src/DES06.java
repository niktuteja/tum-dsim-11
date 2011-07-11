import Analysis.*;
import Simulator.*;
import RandVar.*;

/**
 * Exercise 6		Discrete Event Simulation SS 2011
 *
 * DES06 class
 * Main class performing the simulation.
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2011-07-06
 */
public class  DES06
{
	/**
	 * main. Application entry point for DES exercise 6
	 * 
	 * @param args 
	 * args[0] indicates the exercise 1 or 2
	 * Exercise 1: args[1] = lazyThreshold
	 * Exercise 1: args[2] = speedFactor
	 * Exercise 2: args[1] specifies the simulated system
	 */
	public static void main(String[] args) 
	{
		Rng a = new Rng (1, 16807, 0, (1 << 31) - 1);
		Rng b = new Rng (1, 16807, 15, (1 << 31) - 1);
		Rng c = new Rng (1, 16807, 100, (1 << 31) - 1);
		RandVar iat, sct, biat = null, batchSize = null;
				
		// Exercise 1
		if (args.length == 3 || args[0].equals("1")) {
			long   lazyThreshold = new Long(args[1]).longValue();
			double speedFactor = new Double(args[2]).doubleValue();
			
			//System A:-------------- M / M / 1 - Infty -----------------------
			long real_time_to_sim_time = 100;
			iat = new Exponential((double) 10 / real_time_to_sim_time);
			iat.rng = a;
			sct = new Exponential((double) 8 / real_time_to_sim_time);
			sct.rng = b;
			
			long simulation_duration = (long) (Math.pow(10,5)*real_time_to_sim_time);
			long maxQueueSize = Long.MAX_VALUE;
			
			SimState.s = new SimState ( iat, sct, simulation_duration, maxQueueSize, lazyThreshold, speedFactor, real_time_to_sim_time);
			runSimulation(args[0]);
		}
		
		
		
		// Exercise 2 - args[0] Exercise - args[1] - system
		if (args.length == 2 || args[0].equals("2")) {
			
			long real_time_to_sim_time = 100;
			
			//System A
			if (args[1].equals("a"))
			{
				biat = new Exponential((double) 9 / real_time_to_sim_time);
				batchSize = new Constant ((double) 1);
			}
			//System B
			if (args[1].equals("b"))
			{
				biat = new Exponential((double) 4.5 / real_time_to_sim_time);
				batchSize = new Constant ((double) 2);
			}
			//System C
			if (args[1].equals("c"))
			{
				biat = new Exponential((double) 3 / real_time_to_sim_time);
				batchSize = new Constant ((double) 3);
			}
			//System D
			if (args[1].equals("d"))
			{
				biat = new Exponential((double) 9 / (double) 4 / real_time_to_sim_time);
				batchSize = new Constant ((double) 4);
			}
			//System E
			if (args[1].equals("e"))
			{
				biat = new Exponential((double) 9 / (double) 5 / real_time_to_sim_time);
				batchSize = new Constant ((double) 5);
			}
			//System F
			if (args[1].equals("f"))
			{
				biat = new Exponential((double) 9 / (double) 5 / real_time_to_sim_time);
				batchSize = new Uniform ((double) 1, (double) 10);
			}
			//System Test
			if (args[1].equals("test"))
			{
				biat = new Exponential((double) 9 / (double) 5 / real_time_to_sim_time);
				batchSize = new Uniform ((double) 10, (double) 20);
			}
			sct = new Exponential((double) 10 / real_time_to_sim_time);
			
			//Set Rng's
			biat.rng = a;
			sct.rng = b;
			batchSize.rng = c;
			
			long simulation_duration = (long) (Math.pow(10,5)*real_time_to_sim_time);
			long maxQueueSize = Long.MAX_VALUE;
			
			SimState.s = new SimState (null, sct, biat, batchSize, simulation_duration, maxQueueSize, -1, 0, real_time_to_sim_time);
			
			runSimulation(args[0]);
		}
	}
		
	static void runSimulation(String args) 
	{
		//Initialize CounterCollection
		CounterCollection.cc = new CounterCollection();

		//Insert the first event:
		//CustomerArrival for exercise 1 or
		//BatchArrival for exercise 2
		if (args.equals("1"))
			SimState.s.ec.insert (new CustomerArrival (0));
		else
			SimState.s.ec.insert (new BatchArrival (0));
		
		//Insert termination event
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
		
		// report general simulation stats - stats are chosen depending on the exercise
		if (args.equals("1"))
		{
			System.out.println("E[inter-arrival time] = " + SimState.s.iat.getMean());
			System.out.println("E[service completion time] = " + SimState.s.sct.getMean());
			System.out.println("simulation duration = " + SimState.s.simulationDuration);
			System.out.println("minimum queue length = " + SimState.s.min);
			System.out.println("maximum queue length = " + SimState.s.max);
		}
		else
		{
			System.out.println("E[batch-inter-arrival time] = " + SimState.s.biat.getMean());
			System.out.println("E[batch-size] = " + SimState.s.batchSize.getMean());
			System.out.println("E[inter-arrival time] = " + SimState.s.biat.getMean()/SimState.s.batchSize.getMean());
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
