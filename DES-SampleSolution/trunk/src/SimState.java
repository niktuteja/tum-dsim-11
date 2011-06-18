/**
 * SimState class
 * The SimState holds the whole information of the current simulation.
 * 
 * @author Alexander Klein
 * @version 1.0.1 
 * @since 2005-11-06
 */
import java.util.Vector;
public class SimState
{
	/**
	 * Attribute: EventChain that holds all pending SimEvents of the simulation.
	 */
	public EventChain ec;
	/**
	 * Attribute: representing the current simulation time.
	 */
	long now;
	/**
	 * Attribute: boolean used to indicate the termination of the simulation
	 */
	boolean stop;
	/**
	 * Attribute: time between CustomArrivals
	 */
	public RandVar iat;
	/**
	 * Attribute: time between ServiceCompletions
	 */
	public RandVar sct;
	/**
	 * Attribute: Duration of the Simulation
	 */
	long simulationDuration;
	/**
	 * Attribute: Number of waiting customers 
	 */
	long queueSize;
	/**
	 * Attribute: status of the server
	 */
	boolean serverBusy;
	/**
	 * Attribute: minimum of waiting customers 
	 */
	long min;
	/**
	 * Attribute: maximum of waiting customers 
	 */
	long max;
	/**
	 * Attribute: maximum allowed queue size 
	 */	
	long maxQueueSize;
	/**
	 * Attribute: Stores the waiting Customers 
	 */
	Vector<Customer> queue;

	Customer customerInServer;
	public static SimState s;
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
		simulationDuration = 1;
		queueSize = 0;
		serverBusy = false;
		min = 0;
		max = 0;
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
		simulationDuration = sd;
		queueSize = 0;
		serverBusy = false;
		min = 0;
		max = 0;
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
		simulationDuration = sd;
		queueSize = 0;
		serverBusy = false;
		min = 0;
		max = 0;
		this.maxQueueSize = maxQueueSize;
	}
}