package Simulator;

import java.util.Vector;
/**
 * SortableQueue class
 * Representing a sortable queue by using the java.util.Vector class for storing 
 * the SortableQueueItems.
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-10-22
 */
public class SortableQueue 		
{
	/**
	 * Attribute: Stores all SortableQueueItems
	 */
	public Vector<SortableQueueItem> vector;
	
	/**
	 * Constructor initializes the vector to a maximum basicsize of 10
	 */
	public SortableQueue ()
	{
		vector = new Vector<SortableQueueItem> (10);
	}
	/**
	 * Removes the smallest item from the queue and returns it.
	 *@return The smallest item in the queue
	 */
	public SortableQueueItem get ()
	{
		return removeSmallestItem();
	}
	/**
	 * Removes the smallest item from the queue and returns it.
	 *@return The smallest item in the queue
	 */
	public SortableQueueItem removeSmallestItem ()
	{
		
		if (vector.size() > 0)
		{
			SortableQueueItem e = (SortableQueueItem) vector.firstElement();
			vector.remove(0);
			return e;
		}
		return null;
	}
	/**
	 * Inserts the given SortableQueueItem argument at the correct position
	 * in the queue (vector).
	 *@param e SortableQueueItem that has to be inserted at the correct position.
	 */
	public void insert (SortableQueueItem e)
	{
		if (vector.size() == 0)
		{
			vector.insertElementAt((SortableQueueItem) e , 0);
			return;
		}
		int temp = vector.size();
		for (int i = 0; i < temp;i++ )
		{
			if (e.value < ((SortableQueueItem) vector.get(i)).value)
			{
				vector.insertElementAt((SortableQueueItem) e , i);
				return;
			}
		}
		//If SortableQueueItem e is not inserted yet, it has to be the new last element in the queue
		vector.add((SortableQueueItem) e);
		
	}
}
