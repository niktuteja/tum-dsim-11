import Analysis.*;
import RandVar.*;
import Simulator.*;

/**
 * Exercise 3		Discrete Event Simulation SS2011
 *
 * DES03 class
 * Main class reading the arguments and performing the simulation.
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2011-06-10
 */
public class  DES03
{
	/**
	  * Main executing function
	  *@param args console parameters
	 */
	public static void main(String[] args) 
	{
		// Initialize SimState with the given arguments
		
		//exercise1a ();
		//exercise1b ();
		//exercise1c ();
		//exercise2a ();
		//exercise2b ();
		//exercise2c ();
		//exercise3 (0);//FIFO
		exercise3 (1);//EDF
				
		//Initialize CounterCollection
		CounterCollection.cc = new CounterCollection();

		//Insert the first and the last event
		SimState.s.ec.insert (new CustomerArrival (0));
		SimState.s.ec.insert (new SimulationTermination(SimState.s.simulationDuration));
		
		/**
		 * Simulation is done here
		 */
		while (SimState.s.stop != true)
		{	
			// Get the next SimEvent from the EventChain
			SimEvent e = SimState.s.ec.removeOldestEvent();
			
			if (e != null)
			{
				if (SimState.s.now > e.value)
				{
					System.out.println("_____________________________________________________");
					System.out.println("ERROR");
					System.out.println("Event_Time" + e.value);
					System.out.println("System_Time" + SimState.s.now);
					System.out.println("EVENT SHOULD BE ALREADY EXECUTED IN THE PAST"); 
					System.out.println("_____________________________________________________");
				}
				
				SimState.s.now = e.value;
				
				e.process();
			}
			else
			{
				System.out.println("EventChain is empty! Simulation abort!");
				SimState.s.stop = true;
			}
			
			// Updating the min and the max attribute of the SimState
			SimState.s.min = (SimState.s.queue.size() < SimState.s.min ? SimState.s.queue.size():SimState.s.min);
			SimState.s.max = (SimState.s.queue.size() > SimState.s.max ? SimState.s.queue.size():SimState.s.max);
		}
		
		// reporting
		System.out.println("E[inter-arrival time] = " + SimState.s.iat.getMean()/ SimState.s.real_time_to_sim_time);
		System.out.println("E[service completion time] = " + SimState.s.sct.getMean()/ SimState.s.real_time_to_sim_time);
		System.out.println("simulation duration = " + SimState.s.simulationDuration/ SimState.s.real_time_to_sim_time);
		System.out.println("minimum queue length = " + SimState.s.min);
		System.out.println("maximum queue length = " + SimState.s.max);
		System.out.println("___________________________________________________________________");
		
		CounterCollection.cc.report();

	}
	static void exercise1a ()
	{
		//System A:----------------------------------------------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 10 / real_time_to_sim_time);
		RandVar sct = new Exponential ((double) 10 / real_time_to_sim_time);
		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = 1;
		SimState.s = new SimState ( iat, sct, simulation_duration, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void exercise1b ()
	{
		//System B:----------------------------------------------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 10 / real_time_to_sim_time);
		RandVar sct = new Exponential ((double) 10 / real_time_to_sim_time);
		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = 5;
		SimState.s = new SimState ( iat, sct, simulation_duration, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void exercise1c ()
	{
		//System C:----------------------------------------------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 10 / real_time_to_sim_time);
		RandVar sct = new Exponential ((double) 10 / real_time_to_sim_time);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = 10;
		SimState.s = new SimState ( iat, sct, simulation_duration, maxQueueSize, preferablePlaces, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void exercise2a ()
	{
		//System A:-------------- GI / GI / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Uniform ((double) 10 * real_time_to_sim_time, (double) 200 * real_time_to_sim_time);
		RandVar sct = new Uniform ((double) 30 * real_time_to_sim_time, (double) 150 * real_time_to_sim_time);
		RandVar cet = null;
		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = 0;//FIFO
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void exercise2b ()
	{
		//System B:-------------- GI / GI / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Uniform ((double) 10 * real_time_to_sim_time, (double) 200 * real_time_to_sim_time);
		RandVar sct = new Uniform ((double) 30 * real_time_to_sim_time, (double) 150 * real_time_to_sim_time);
		RandVar cet = new Constant ((double) 200 * real_time_to_sim_time);
		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = 0;//FIFO
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void exercise2c ()
	{
		//System C:-------------- GI / GI / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Uniform ((double) 10 * real_time_to_sim_time, (double) 200 * real_time_to_sim_time);
		RandVar sct = new Uniform ((double) 30 * real_time_to_sim_time, (double) 150 * real_time_to_sim_time);
		RandVar cet = new Uniform ((double) 200 * real_time_to_sim_time, (double) 600 * real_time_to_sim_time);
		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = 0;//FIFO
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void exercise3 (int strategy)
	{
		//System C:-------------- GI / GI / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Uniform ((double) 10 * real_time_to_sim_time, (double) 200 * real_time_to_sim_time);
		iat.setSeed(100);
		RandVar sct = new Uniform ((double) 30 * real_time_to_sim_time, (double) 150 * real_time_to_sim_time);
		sct.setSeed(200);
		RandVar cet = new Uniform ((double) 200 * real_time_to_sim_time, (double) 600 * real_time_to_sim_time);
		cet.setSeed(300);

		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = strategy;
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
	static void test (int strategy)
	{
		//System C:-------------- GI / GI / 1 - Infty -----------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential((double) 1 / 10 / real_time_to_sim_time);
		iat.setSeed(100);
		RandVar sct = new Exponential((double) 1 / 10 / real_time_to_sim_time);
		sct.setSeed(200);
		RandVar cet = new Exponential((double) 1 / 100 / real_time_to_sim_time);
		cet.setSeed(300);

		long simulation_duration = 1000000*real_time_to_sim_time;
		long maxQueueSize = Long.MAX_VALUE;
		long preferablePlaces = -1;
		int queueingStrategy = strategy;
		SimState.s = new SimState ( iat, sct, cet, simulation_duration, maxQueueSize, preferablePlaces, queueingStrategy, real_time_to_sim_time);
		//-------------------------------------------------------------------
	}
}
