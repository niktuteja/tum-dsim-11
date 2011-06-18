/**
 * ServiceCompletion is a special SimEvent.
 * Changes the SimState according to the server state if a
 * ServiceCompletion event occurs
 * @author Alexander Klein
 * @version 1.0.1 
 * @since 2005-11-06
 */
public class ServiceCompletion extends SimEvent
{
	/**
	 * Constructor that uses the given argument
	 *@param time Representing the simulation time at which the event occurs
	 */
	public ServiceCompletion (long time)
	{
		value = time;
	}
	/**
	 * Function describes the system behavior depending on the queueSize
	 * if a ServiceCompletion occurs.
	 */
	public void process ()
	{
		Customer c = SimState.s.customerInServer;

		if (c!= null)
		{
			c.serviceCompletionTime = SimState.s.now;
			
			CounterCollection.c.dc_cwt.count((double) (c.serviceInitTime - c.arrivalTime));
			CounterCollection.c.dc_cst.count(c.serviceCompletionTime - c.serviceInitTime);
		}
		else
		{
			System.out.println("ServiceCompletionTime scheduled for non-existing customer!");
			System.exit(-1);
		}
		/**
		 * If queueSize greater than zero decrease queue size by one and trigger the next 
		 * ServiceCompletion event.
		 */
		if (SimState.s.queueSize > 0) 
		{
			SimState.s.ec.insert (new ServiceCompletion (SimState.s.now + Math.round(SimState.s.sct.getRV())));
			SimState.s.queueSize--;
			
			SimState.s.customerInServer = (Customer) SimState.s.queue.firstElement();
			SimState.s.customerInServer.serviceInitTime = SimState.s.now;

			SimState.s.queue.remove(0);
		}
		/**
		 * If queueSize is equal to zero set the server to the idle state.
		 */
		else
		{
			SimState.s.serverBusy = false;
			SimState.s.customerInServer = null;
		}

		//SimState measurements below
		double util = (SimState.s.serverBusy ? 1 : 0);
		
		CounterCollection.c.cc_su.count(util);
		CounterCollection.c.cc_qo.count(SimState.s.queueSize);	
	}
}