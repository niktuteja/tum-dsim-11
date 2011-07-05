package Simulator;

import Analysis.*;

public class BatchArrival extends SimEvent
{
	private final long numArrivals;
	
	public BatchArrival (long time, long numArrivals)
	{
		value = time;
		this.numArrivals = numArrivals;
	}
	/**
	 * Function describes the system behavior depending on the serverBusy attribute 
	 * if a CustomArrival occurs.
	 */
	public void process ()
	{
		long iat = Math.round(SimState.s.iat.getRV());
		long na = Math.round(SimState.s.na.getRV());
		SimState.s.ec.insert (new BatchArrival(SimState.s.now + iat, na));
		
		for (int i = 0; i < numArrivals; i++)
		{
			SimState.s.ec.insert ((SimEvent) new CustomerArrival (SimState.s.now));
			CounterCollection.cc.dc_ciat.count((double) SimState.s.now / SimState.s.real_time_to_sim_time);
		}
	}
}
