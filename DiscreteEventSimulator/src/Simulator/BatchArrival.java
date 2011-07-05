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
		for (int i = 0; i < numArrivals; i++)
		{
			SimState.s.ec.insert ((SimEvent) new CustomerArrival (value));
			CounterCollection.cc.dc_ciat.count((double) value / SimState.s.real_time_to_sim_time);
		}
	}
}
