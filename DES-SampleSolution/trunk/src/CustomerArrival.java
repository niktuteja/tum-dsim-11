/**
 * CustomerArrival is a special SimEvent.
 * At least one CustomerArrival in the EventChain is needed to 
 * start the simulation.
 * @author Alexander Klein
 * @version 1.0.1 
 * @since 2011-06-09
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
			CounterCollection.c.dc_bp.count(0);
		}
		/**
		 * If server is busy try to insert the CustomerArrival in the queue
		 */
		else
		{
			if (SimState.s.queueSize < SimState.s.maxQueueSize)
			{
				SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now + iat));
				SimState.s.queueSize ++;
				SimState.s.queue.add(c);
				CounterCollection.c.dc_bp.count(0);
			}
			else
			{
				SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now + iat));
				CounterCollection.c.dc_bp.count(1);
			}
		}
		
		// measurement	
		CounterCollection.c.cc_su.count(1);
		CounterCollection.c.cc_qo.count(SimState.s.queueSize);	
		CounterCollection.c.dc_ciat.count((double) iat);

	}
}
