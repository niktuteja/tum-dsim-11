package Simulator;

import Analysis.*;

/**
 * CustomerArrival is a special SimEvent.
 * At least one CustomerArrival in the EventChain is needed to 
 * start the simulation.
 * @author Alexander Klein
 * @version 1.0.1 
 * @since 2011-06-10
 */
public class CustomerArrival extends SimEvent
{
	/**
	 * Constructor that uses the given argument
	 *@param time Representing the simulation time at which the event occurs
	 */
	public CustomerArrival (long time)
	{
		value = time;
	}
	/**
	 * Function describes the system behavior depending on the serverBusy attribute 
	 * if a CustomArrival occurs.
	 */
	public void process ()
	{
		Customer c = new Customer ();
		c.arrivalTime = SimState.s.now;
		
		//Remove expired customers first! Otherwise expired customers can block new ones
		if (SimState.s.cet != null) 
		{
			c.deadline = SimState.s.now+Math.round(SimState.s.cet.getRV());
			SimState.s.removeExpiredCustomers ();
		}
		
		/**
		 * If server is not busy insert the CustomerArrival in the server and set him to busy state
		 */
		long iat = Math.round(SimState.s.iat.getRV());
		
		if (SimState.s.serverBusy == false) 
		{
			//Insert the next CustomerArrival in the EventChain
			SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now + iat));
			
			//Insert the ServiceCompletion event in the EventChain
			SimState.s.ec.insert ((SimEvent) new ServiceCompletion (SimState.s.now + Math.round(SimState.s.sct.getRV())));

			SimState.s.serverBusy = true;

			c.serviceInitTime = SimState.s.now;
			
			SimState.s.customerInServer = c;
			CounterCollection.cc.dc_bp.count(0);
			CounterCollection.cc.dc_ciat.count((double) iat / SimState.s.real_time_to_sim_time);
		}
		/**
		 * If server is busy try to insert the CustomerArrival in the queue
		 */
		else
		{
			if (SimState.s.queue.size() < SimState.s.maxQueueSize)
			{
				//Check waiting queue size in order to determine the arrival rate
				//which depends on the current queue size
				if ((SimState.s.preferablePlaces == -1) || (SimState.s.queue.size() < SimState.s.preferablePlaces))
				{
					//Preferable (dry) places are still available => high arrival rate
					SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now + iat));
					CounterCollection.cc.dc_ciat.count((double) iat / SimState.s.real_time_to_sim_time);
				}
				else
				{
					//No preferable places available => decrease arrival rate by 50%
					SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now + 2*iat));
					CounterCollection.cc.dc_ciat.count((double) 2*iat/ SimState.s.real_time_to_sim_time);
				}

				SimState.s.queue.add(c);
				CounterCollection.cc.dc_bp.count(0);
			}
			else
			{
				SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now + iat));
				CounterCollection.cc.dc_ciat.count((double) iat / SimState.s.real_time_to_sim_time);
				CounterCollection.cc.dc_bp.count(1);
			}
		}
		
		// measurement	
		CounterCollection.cc.cc_su.count(1);
		CounterCollection.cc.cc_qo.count(SimState.s.queue.size());	
		CounterCollection.cc.ch_qo.count(SimState.s.queue.size());
	}
}
