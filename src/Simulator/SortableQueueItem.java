package Simulator;

/**
 * SortableQueueItem class
 * Basic class for events that holds a long value representing the
 * simulation time.
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-10-22
 */
public class SortableQueueItem
{
	/**
	 * Attribute: Representing the simulation time at which the event occurs
	 */
	public long value;
	/**
	 * Constructor of the SortableQueueItem
	 * Sets the initial value to zero.
	 */
	public SortableQueueItem ()
	{
		value = 0;
	}
	/**
	 * Constructor that uses the given argument
	 *@param _value Representing the simulation time at which the event occurs
	 */
	public SortableQueueItem (long _value)
	{
		value = _value;
	}
}