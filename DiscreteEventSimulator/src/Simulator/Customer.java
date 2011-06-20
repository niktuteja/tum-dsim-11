package Simulator;

/**
 * Customer class
 * Class holds additional elements for advanced statistic collection
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2011-06-10
 */
public class  Customer extends SortableQueueItem
{
	/**
	 * Attribute: Simulation time the customer enters the system 
	 * (queue or serviceUnit depending on the system state)
	 */
	public long arrivalTime;
	/**
	 * Attribute: Simulation time the customer enters the serviceUnit
	 */
	public long serviceInitTime;
	/**
	 * Attribute: Time of the serviceCompletion (in SimTime)
	 */
	public long serviceCompletionTime;
	/**
	 * Attribute: Customers are removed from the waiting queue if 
	 * the simulation time (now) is larger than the deadline
	 */
	public long deadline;
}
