package Simulator;

import Analysis.CounterCollection;

/**
 * ServiceCompletion is a special SimEvent. Changes the SimState according to
 * the server state if a ServiceCompletion event occurs
 * 
 * @author Alexander Klein
 * @version 1.0.2
 * @since 2011-06-10
 */
public class ServiceCompletion extends SimEvent {
	Customer c;

	/**
	 * Constructor that uses the given argument
	 * 
	 * @param time
	 *            Representing the simulation time at which the event occurs
	 */
	public ServiceCompletion(long time, Customer c) {
		value = time;
		this.c = c;
	}

	/**
	 * Function describes the system behavior depending on the queueSize if a
	 * ServiceCompletion occurs.
	 */
	@Override
	public void process() {
		if (c != null) {
			c.serviceCompletionTime = SimState.s.now;

			CounterCollection.cc.dc_cwt.count((double) (c.serviceInitTime - c.arrivalTime) / SimState.s.real_time_to_sim_time);
			CounterCollection.cc.dc_cst.count((double) (c.serviceCompletionTime - c.serviceInitTime) / SimState.s.real_time_to_sim_time);

			CounterCollection.cc.dh_cwt.count((double) (c.serviceInitTime - c.arrivalTime) / SimState.s.real_time_to_sim_time);

			if ((double) (c.serviceInitTime - c.arrivalTime) < 60 * SimState.s.real_time_to_sim_time) {
				CounterCollection.cc.dc_sc.count(1);
			} else {
				CounterCollection.cc.dc_sc.count(0);
			}
			CounterCollection.cc.dc_uc.count(0);
		} else {
			System.out.println("ServiceCompletionTime scheduled for non-existing customer!");
			System.exit(-1);
		}
		if (SimState.s.cet != null) {
			SimState.s.removeExpiredCustomers();
		}
		/**
		 * If queueSize greater than zero decrease queue size by one and trigger
		 * the next ServiceCompletion event.
		 */
		if (SimState.s.queue.size() > 0) {
			ServiceCompletion newServiceCompletionEvent = null;
			if ((SimState.s.queue.size() < SimState.s.lazyThreshold) && (SimState.s.lazyThreshold != -1))
				newServiceCompletionEvent = new ServiceCompletion(SimState.s.now + Math.round(SimState.s.sct.getRV()), null);

			else
				newServiceCompletionEvent = new ServiceCompletion(SimState.s.now
						+ Math.round((1 - SimState.s.speedFactor) * SimState.s.sct.getRV()), null);

			SimState.s.ec.insert(newServiceCompletionEvent);

			switch (SimState.s.queueingStrategy) {
			case 0: {
				newServiceCompletionEvent.c = SimState.s.queue.remove(0);
				break;
			}
			case 1: {
				newServiceCompletionEvent.c = SimState.s.getEdfCustomer();
				break;
			}
			default: {
				//Apply FIFO by default
				newServiceCompletionEvent.c = SimState.s.queue.remove(0);
				break;
			}
			}

			newServiceCompletionEvent.c.serviceInitTime = SimState.s.now;

		}
		/**
		 * If queueSize is equal to zero set the server to the idle state.
		 */
		else {
			SimState.s.numBusyServers--;
			//			SimState.s.customerInServer = null;
		}

		//SimState measurements below
		double util = (SimState.s.numBusyServers < SimState.s.numServers ? 1 : 0);

		CounterCollection.cc.cc_su.count(util);
		CounterCollection.cc.cc_qo.count(SimState.s.queue.size());
		CounterCollection.cc.ch_qo.count(SimState.s.queue.size());
	}
}