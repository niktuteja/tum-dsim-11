/**
 * SimulationTermination is a special SimEvent.
 * This class is used to terminate the simulation.
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-10-22
 */
public class SimulationTermination extends SimEvent
{
	/**
	 * Constructor that uses the given argument
	 *@param time Representing the simulation time at which the event occurs.
	 */
	public SimulationTermination (long time)
	{
		value = time;
	}
	/**
	 * Function sets the stop boolean in the SimState to true which terminates
	 * the while-loop in the DES - mainclass
	 */
	public void process ()
	{
		SimState.s.stop = true;
	}
}