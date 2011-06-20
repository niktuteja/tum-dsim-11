package Simulator;

/**
 * EventChain class
 * The EventChain holds all SimEvents in an attribute queue which
 * is a SortableQueue.
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-10-22
 */
public class EventChain
{
	/**
	 * Queue that contains the stored SimEvents
	 */
	public SortableQueue queue;
	/**
	 * Constructor of an empty EventChain
	 * Initializes its SortibleQueue attribute
	 */
	public EventChain ()
	{
		queue = new SortableQueue ();
	}
	/**
	 * Inserts the given SortableQueueItem argument at the correct position
	 * in the queue.
	 *@param e SimEvent that has to be inserted at the correct position.
	 */
	public void insert (SimEvent e)
	{
		queue.insert ((SortableQueueItem) e);
	}
	/**
	 * Removes the smallest/oldest item from the queue and returns it.
	 *@return The smallest/oldest item in the queue
	 */
	public SimEvent removeOldestEvent ()
	{
		return (SimEvent) queue.removeSmallestItem();
	}
}
