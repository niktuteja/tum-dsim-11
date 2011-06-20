package Simulator;

/**
 * SimEvent abstract class
 * SimEvent class adds the global attribute SimState to all SimEvents and 
 * describes the interface of all SimEvents.
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-10-22
 */
public abstract class SimEvent extends SortableQueueItem
{
	/**
	 * Interface of SimEvents. This function has to be overwritten
	 * by all extending classes.
	 */
	public abstract void process ();
}