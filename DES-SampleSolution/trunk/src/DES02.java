/**
 * Exercise 2		Discrete Event Simulation SS2011
 *
 * DES02 class
 * Mainclass reading the arguments and performing the simulation.
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2010-11-08
 */
public class  DES02
{
	/**
	  * Main executing function
	  *@param args console parameters
	 */
	public static void main(String[] args) 
	{
		// Initialize SimState with the given arguments
		
		//System A:----------------------------------------------------------
		long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 9.5 / real_time_to_sim_time);
		iat.setSeed(100);
		RandVar sct = new Exponential ((double) 10 / real_time_to_sim_time);
		sct.setSeed(200);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = 100000000;
		//-------------------------------------------------------------------
		//System B:----------------------------------------------------------
		/*long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 9.5 / real_time_to_sim_time);
		RandVar sct = new Exponential ((double) 10 / real_time_to_sim_time);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = 3;*/
		//-------------------------------------------------------------------
		//System C:----------------------------------------------------------
		/*long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 9.5 / real_time_to_sim_time);
		RandVar sct = new Exponential ((double) 10 / real_time_to_sim_time);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = 10;*/
		//-------------------------------------------------------------------
		//System D:----------------------------------------------------------
		/*long real_time_to_sim_time = 100;
		RandVar iat = new Constant ((double) 1/9 *  real_time_to_sim_time);
		RandVar sct = new Constant ((double) 1/10 *  real_time_to_sim_time);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = 1000000;*/
		//-------------------------------------------------------------------
		//System E:----------------------------------------------------------
		/*long real_time_to_sim_time = 100;
		RandVar iat = new Exponential ((double) 9 / real_time_to_sim_time);
		RandVar sct = new Constant ((double) 1/10 *  real_time_to_sim_time);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = 1000000;*/
		//-------------------------------------------------------------------
		//System F:----------------------------------------------------------
		/*long real_time_to_sim_time = 100;
		RandVar iat = new Constant ((double) 1/9 *  real_time_to_sim_time);
		RandVar iat = new Exponential ((double) 10 / real_time_to_sim_time);
		long simulation_duration = 100000*real_time_to_sim_time;
		long maxQueueSize = 1000000;*/
		//-------------------------------------------------------------------
		
			
		SimState.s = new SimState ( iat, sct, simulation_duration, maxQueueSize);
		
		//Initialize CounterCollection
		CounterCollection.c = new CounterCollection();

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
			SimState.s.min = (SimState.s.queueSize < SimState.s.min ? SimState.s.queueSize:SimState.s.min);
			SimState.s.max = (SimState.s.queueSize > SimState.s.max ? SimState.s.queueSize:SimState.s.max);
		}
		
		// reporting
		System.out.println("E[inter-arrival time] = " + SimState.s.iat.getMean());
		System.out.println("E[service completion time] = " + SimState.s.sct.getMean());
		System.out.println("simulation duration = " + SimState.s.simulationDuration);
		System.out.println("minimum queue length = " + SimState.s.min);
		System.out.println("maximum queue length = " + SimState.s.max);
		System.out.println("___________________________________________________________________");
		
		CounterCollection.c.report();
	}
}
