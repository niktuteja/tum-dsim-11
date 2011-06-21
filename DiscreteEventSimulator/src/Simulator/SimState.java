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
import RNG.Rng;
import RandVar.*;
public class SimState
{
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
	public RandVar cet;
	/**
	 * Attribute: Duration of the Simulation
	 */
	public long simulationDuration;
	/**
	 * Attribute: status of the server
	 */
	boolean serverBusy;
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
	 * Attribute: maximum allowed queue size 
	 * (Ex3: number of dry waiting slots)
	 */		
	long preferablePlaces = -1;
	/**
	 * Attribute: 1s in real time corresponds real_time_to_sim_time
	 * ticks in the simulation 
	 */	
	public long real_time_to_sim_time = 1;
	/**
	 * Attribute: Stores the waiting Customers 
	 */
	public Vector<Customer> queue;

	Customer customerInServer;
	
	public int queueingStrategy = 0;
	
	public final int FIFO = 0;
	public final int EDF = 1;
	public double deltaSteadyState = 0;
	
	public static SimState s;
	public long endTransientPhase = -1;
	/**
	 * SimState constructor
	 * Initializes the simulation with default parameters
	 */
	public SimState ()
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		iat = new Constant(1);
		sct = new Constant(1);
		cet = null;
		simulationDuration = 1;
		serverBusy = false;
		min = 0;
		max = 0;
		queueingStrategy = FIFO;
	}
	/**
	 * SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> interArrivalTime
	 *@param sct <-> serviceCompletionTime
	 *@param sd <-> simulationDuration
	 */
	public SimState (long iat, long sct, long sd)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = new Constant(iat);
		this.sct = new Constant(sct);
		this.cet = null;
		simulationDuration = sd;
		serverBusy = false;
		min = 0;
		max = 0;
		queueingStrategy = FIFO;
	}
	/**
	 * SimState constructor
	 * Using the given arguments to initialize the SimState
	 *@param iat <-> RandVar interArrivalTime 
	 *@param sct <-> RandVar serviceCompletionTime
	 *@param sd <-> simulationDuration
	 *@param maxQueueSize <-> maximum queue size
	 */
	public SimState (RandVar iat, RandVar sct, long sd, long maxQueueSize)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		serverBusy = false;
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
	 *@param preferablePlaces Attribute is used in Exercise 3 to modify state
	 *dependent customer arrivals
	 */
	public SimState (RandVar iat, RandVar sct, long sd, long maxQueueSize, long preferablePlaces)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		serverBusy = false;
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
	public SimState (RandVar iat, RandVar sct, long sd, long maxQueueSize, long preferablePlaces, long real_time_to_sim_time)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = sd;
		serverBusy = false;
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
	public SimState (RandVar iat, RandVar sct, RandVar cet, long sd, long maxQueueSize, long preferablePlaces)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = cet;
		simulationDuration = sd;
		serverBusy = false;
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
	public SimState (RandVar iat, RandVar sct, RandVar cet, long sd, long maxQueueSize, long preferablePlaces, int queueingStrategy)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = cet;
		simulationDuration = sd;
		serverBusy = false;
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
	public SimState (RandVar iat, RandVar sct, RandVar cet, long sd, long maxQueueSize, 
			long preferablePlaces, int queueingStrategy, long real_time_to_sim_time)
	{
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = cet;
		simulationDuration = sd;
		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		this.queueingStrategy = queueingStrategy;
		this.real_time_to_sim_time = real_time_to_sim_time;
	}
	
	public SimState (RandVar iat, RandVar sct, double deltaSteadyState, long endTransientPhase, long maxQueueSize, long preferablePlaces, long real_time_to_sim_time)
	{
		this.endTransientPhase  = endTransientPhase;
		ec = new EventChain ();
		queue = new Vector<Customer> (10);
		stop = false;
		this.iat = iat;
		this.sct = sct;
		this.cet = null;
		simulationDuration = -1;
		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
		this.preferablePlaces = preferablePlaces;
		queueingStrategy = FIFO;
		this.real_time_to_sim_time = real_time_to_sim_time;
		this.deltaSteadyState = deltaSteadyState;
	}
	//Function returns the customer with the closest deadline and removes it from the queue.
	//Bad implementation! Use something sorted.
	public Customer getEdfCustomer ()
	{
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < queue.size();i++)
		{
			if (min > queue.get(i).deadline)
			{
				min = (int) queue.get(i).deadline;
				index = i;
			}
		}
		return queue.remove(index);
	}
	//Function removes expired customers from the waiting queue
	public void removeExpiredCustomers ()
	{
			for (int i = 0; i < queue.size();i++)
			{
				if (s.queue.get(i).deadline < s.now )
				{
					s.queue.remove(i);
					i--;
					CounterCollection.cc.dc_uc.count((double) 1);
					CounterCollection.cc.dc_sc.count((double) 0);
				}
			}
	}
}