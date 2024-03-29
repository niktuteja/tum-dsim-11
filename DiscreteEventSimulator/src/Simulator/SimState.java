package Simulator;

/**
 * SimState class
 * The SimState holds the whole information of the current simulation.
 * 
 * @author Alexander Klein
 * @version 1.0.1 
 * @since 2005-11-06
 */
import java.util.Vector;

import Analysis.CounterCollection;
import RandVar.Constant;
import RandVar.RandVar;

public class SimState {
	/**
	 * Attribute: EventChain that holds all pending SimEvents of the simulation.
	 */
	public EventChain ec;
	/**
	 * Attribute: representing the current simulation time.
	 */
	public long now;
	/**
	 * Attribute: boolean used to indicate the termination of the simulation
	 */
	public boolean stop;
	/**
	 * Attribute: time between CustomArrivals
	 */
	public RandVar iat;
	/**
	 * Attribute: time between ServiceCompletions
	 */
	public RandVar sct;
	/**
	 * Attribute: CustomerExpirationTime
	 */
	public RandVar cet = null;
	/**
	 * Attribute: time between batch arrivals
	 */
	public RandVar biat = null;
	/**
	 * Attribute: time between batch arrivals
	 */
	public RandVar batchSize = null;
	/**
	 * Attribute: Duration of the Simulation
	 */
	public long simulationDuration;

	/**
	 * Attribute: The number of busy servers
	 */
	int numBusyServers = 0;

	// The total number of servers
	public int numServers = 1;

	/**
	 * Attribute: minimum of waiting customers
	 */
	public long min = 0;
	/**
	 * Attribute: maximum of waiting customers
	 */
	public long max = 0;
	/**
	 * Attribute: maximum allowed queue size
	 */
	long maxQueueSize = Long.MAX_VALUE;
	/**
	 * Attribute: Dry waiting places
	 */
	long preferablePlaces = -1;
	/**
	 * Attribute: lazy threshold The lazy cashier works faster if more the
	 * waiting queue gets longer than the lazyThreshold
	 */
	public long lazyThreshold = -1;
	/**
	 * Attribute: speedFactor Defines the speed-up of the cashier if more than
	 * lazyThreshold customers are waiting
	 */
	public double speedFactor = 0;
	/**
	 * Attribute: 1s in real time corresponds real_time_to_sim_time ticks in the
	 * simulation
	 */
	public long real_time_to_sim_time = 1;
	/**
	 * Attribute: Stores the waiting Customers
	 */
	public Vector<Customer> queue;

	//	Customer customerInServer;

	public int queueingStrategy = 0;

	public final int FIFO = 0;
	public final int EDF = 1;

	public static SimState s;

	/**
	 * SimState constructor Initializes the simulation with default parameters
	 */
	public SimState() {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		iat = new Constant(1);
		sct = new Constant(1);
		cet = null;
		simulationDuration = 1;
		//		serverBusy = false;
		min = 0;
		max = 0;
		queueingStrategy = FIFO;
	}

	/**
	 * SimState constructor Using the given arguments to initialize the SimState
	 * 
	 * @param iat
	 *            <-> interArrivalTime
	 *@param sct
	 *            <-> serviceCompletionTime
	 *@param sd
	 *            <-> simulationDuration
	 */
	public SimState(long iat, long sct, long sd) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = new Constant(iat);
		this.sct = new Constant(sct);
		this.cet = null;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		queueingStrategy = FIFO;
	}

	/**
	 * SimState constructor Using the given arguments to initialize the SimState
	 * 
	 * @param iat
	 *            <-> RandVar interArrivalTime
	 *@param sct
	 *            <-> RandVar serviceCompletionTime
	 *@param sd
	 *            <-> simulationDuration
	 *@param maxQueueSize
	 *            <-> maximum queue size
	 */
	public SimState(RandVar iat, RandVar sct, long sd, long maxQueueSize) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		queueingStrategy = FIFO;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param lazyThreshold - Service Unit works faster if more than
	 *lazyThreshold customers are waiting
	 *@param speedFactor - speed-up factor of the lazy cashier
	 */
	public SimState(RandVar iat, RandVar sct, RandVar biat, RandVar batchSize, long sd, long maxQueueSize, long lazyThreshold,
			double speedFactor, long real_time_to_sim_time) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		this.biat = biat;
		this.batchSize = batchSize;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = Long.MAX_VALUE;
		this.preferablePlaces = -1;
		this.lazyThreshold = lazyThreshold;
		this.speedFactor = speedFactor;
		queueingStrategy = FIFO;
		this.real_time_to_sim_time = real_time_to_sim_time;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param lazyThreshold - Service Unit works faster if more than
	 *lazyThreshold customers are waiting
	 *@param speedFactor - speed-up factor of the lazy cashier
	 */
	public SimState(RandVar iat, RandVar sct, long sd, long maxQueueSize, long lazyThreshold, double speedFactor, long real_time_to_sim_time) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = Long.MAX_VALUE;
		this.preferablePlaces = -1;
		this.lazyThreshold = lazyThreshold;
		this.speedFactor = speedFactor;
		queueingStrategy = FIFO;
		this.real_time_to_sim_time = real_time_to_sim_time;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param preferablePlaces Attribute is used in Exercise 3 to modify state
	 *dependent customer arrivals
	 */
	public SimState(RandVar iat, RandVar sct, long sd, long maxQueueSize, long preferablePlaces) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		queueingStrategy = FIFO;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param preferablePlaces Attribute is used in Exercise 3 to modify state
	 *@param real_time_to_sim_time used to modify the statistics
	 *dependent customer arrivals
	 */
	public SimState(RandVar iat, RandVar sct, long sd, long maxQueueSize, long preferablePlaces, long real_time_to_sim_time) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		queueingStrategy = FIFO;
		this.real_time_to_sim_time = real_time_to_sim_time;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param cet <-> RandVar customerExpirationTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param preferablePlaces Attribute is used in Exercise 3 to modify state
	 *dependent customer arrivals
	 */
	public SimState(RandVar iat, RandVar sct, RandVar cet, long sd, long maxQueueSize, long preferablePlaces) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = cet;
		simulationDuration = sd;
		//serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		queueingStrategy = FIFO;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param cet <-> RandVar customerExpirationTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param preferablePlaces Attribute is used in Exercise 3 to modify state
	 *@param queueingStrategy FIFO / EDF
	 *dependent customer arrivals
	 */
	public SimState(RandVar iat, RandVar sct, RandVar cet, long sd, long maxQueueSize, long preferablePlaces, int queueingStrategy) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = cet;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		this.queueingStrategy = queueingStrategy;
	}

	/* SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param cet <-> RandVar customerExpirationTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 *@param preferablePlaces Attribute is used in Exercise 3 to modify state
	 *@param queueingStrategy FIFO / EDF
	 *dependent customer arrivals
	 */
	public SimState(RandVar iat, RandVar sct, RandVar cet, long sd, long maxQueueSize, long preferablePlaces, int queueingStrategy,
			long real_time_to_sim_time) {
		ec = new EventChain();
		queue = new Vector<Customer>(10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = cet;
		simulationDuration = sd;
		//		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		this.queueingStrategy = queueingStrategy;
		this.real_time_to_sim_time = real_time_to_sim_time;
	}

	//Function returns the customer with the closest deadline and removes it from the queue.
	//Bad implementation! Use something sorted.
	public Customer getEdfCustomer() {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < queue.size(); i++) {
			if (min > queue.get(i).deadline) {
				min = (int) queue.get(i).deadline;
				index = i;
			}
		}
		return queue.remove(index);
	}

	//Function removes expired customers from the waiting queue
	public void removeExpiredCustomers() {
		for (int i = 0; i < queue.size(); i++) {
			if (s.queue.get(i).deadline < s.now) {
				s.queue.remove(i);
				i--;
				CounterCollection.cc.dc_uc.count(1);
				CounterCollection.cc.dc_sc.count(0);
			}
		}
	}
}