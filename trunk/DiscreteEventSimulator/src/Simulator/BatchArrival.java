package Simulator;

import Analysis.CounterCollection;

/**
 * BatchArrival is a special SimEvent. At least one CustomerArrival in the
 * EventChain is needed to start the simulation.
 * 
 * @author Alexander Klein
 * @version 1.0.1
 * @since 2011-07-070
 */
public class BatchArrival extends SimEvent {
	/**
	 * Constructor that uses the given argument
	 * 
	 * @param time
	 *            Representing the simulation time at which the event occurs
	 */
	public BatchArrival(long time) {
		value = time;
	}

	/**
	 * Function describes the system behavior depending on the serverBusy
	 * attribute if a CustomArrival occurs.
	 */
	@Override
	public void process() {
		/**
		 * If server is not busy insert the CustomerArrival in the server and
		 * set him to busy state
		 */
		long biat = Math.round(SimState.s.biat.getRV());

		//Insert the next BatchArrival in the EventChain
		SimState.s.ec.insert(new BatchArrival(SimState.s.now + biat));

		//Remove expired customers first! Otherwise expired customers can block new ones
		if (SimState.s.cet != null) {
			SimState.s.removeExpiredCustomers();
		}

		double batchSize;
		if (SimState.s.biat != null)
			batchSize = SimState.s.batchSize.getRV();
		else
			batchSize = 0;

		long iatS = 0;
		for (int i = 0; i < batchSize; i++) {
			long iat = 0;
			if (i < batchSize -1)
				iat += Math.round(SimState.s.iat.getRV());
			
			SimState.s.ec.insert(new CustomerArrival(SimState.s.now + iatS + iat));
			CounterCollection.cc.dc_ciat.count((double) iat / SimState.s.real_time_to_sim_time);
			iatS += iat;
		}
	}

	// unused now, since we need to create real events
	public void insertCustomer(double iat) {
		Customer c = new Customer();
		c.arrivalTime = SimState.s.now;
		if (SimState.s.cet != null) {
			c.deadline = SimState.s.now + Math.round(SimState.s.cet.getRV());
		}

		if (SimState.s.numBusyServers < SimState.s.numServers) {
			//Insert the ServiceCompletion event in the EventChain
			SimState.s.ec.insert(new ServiceCompletion(SimState.s.now + Math.round(SimState.s.sct.getRV()), c));

			SimState.s.numBusyServers++;
			c.serviceInitTime = SimState.s.now;

			CounterCollection.cc.dc_bp.count(0);
		}
		/**
		 * If server is busy try to insert the CustomerArrival in the queue
		 */
		else {
			if (SimState.s.queue.size() < SimState.s.maxQueueSize) {
				SimState.s.queue.add(c);
				CounterCollection.cc.dc_bp.count(0);
			} else {
				CounterCollection.cc.dc_bp.count(1);
			}
		}
		CounterCollection.cc.dc_ciat.count(iat / SimState.s.real_time_to_sim_time);

		// measurement	
		CounterCollection.cc.cc_su.count(1);
		CounterCollection.cc.cc_qo.count(SimState.s.queue.size());
		CounterCollection.cc.ch_qo.count(SimState.s.queue.size());
	}
}
