package Analysis;
import Simulator.SimState;

/**
 * TDCounter class
 * Class counts the given values REGARDING the simulation time
 * 
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-06
 */
public class TDCounter extends Counter
{
	/**
	 * Attribute: Simulation time of the last sample
	 */
	public long lastSampleTime;
	/**
	 * Attribute: Simulation time of the first sample
	 */
	public long firstSampleTime;
	/**
	 * Attribute: Number of samples
	 */
	public double lastSampleSize;
	/**
	 * Constructor that uses the given argument and the super constructor 
	 * to initialize the TDCounter
	 */
	public TDCounter ()
	{
		super ();
		lastSampleTime = 0;
		firstSampleTime = 0;
		lastSampleSize = 0;
	}
	/**
	 * Constructor that uses the given argument and the super constructor 
	 * to initialize the TDCounter
	 *@param oVariable name of the observed variable
	 */
	public TDCounter (String oVariable)
	{
		super (oVariable);
		lastSampleTime = 0;
		firstSampleTime = 0;
		lastSampleSize = 0;
	}
	/**
	 * Method resets all attributes
	 */
	public void reset ()
	{
		super.reset();
		lastSampleTime = 0;
		firstSampleTime = 0;
		lastSampleSize = 0;
	}
	/**
	 * Method calculates and returns the mean of the observed variable
	 * regarding the counting time
	 *@return the calculated time dependent mean
	 */
	public double getMean() 
	{
		long tmp = lastSampleTime - firstSampleTime;
		return ( tmp > 0 ? sumPowerOne / (double) tmp : 0);
	}
	/**
	 * Method calculates and returns the var of the observed variable
	 * regarding the counting time
	 *@return the calculated time dependent var
	 */
	public double getVariance()
	{
		long tmp = lastSampleTime - firstSampleTime;
		return ( tmp > 0 ? (sumPowerTwo/tmp - getMean()*getMean()) : 0);
	}
	/**
	 * Method counts the given argument (regarding the time the system remained in this state)
	 * by extending the "original" counting method.
	 *@param x the double value to count
	 */
	public void count (double x)
	{
		super.count(x);
		double tmp = SimState.s.now - lastSampleTime;
		// If this statement is true there must be an error in the simulation
		// => abort
		if( tmp < 0 )
		{
			System.out.println("last = " + lastSampleTime + " now = " + SimState.s.now);
			System.exit(-1);
		}
		sumPowerOne+= (lastSampleSize*tmp);
		sumPowerTwo+= (lastSampleSize*lastSampleSize*tmp);
		lastSampleTime = SimState.s.now;
		lastSampleSize = x;
	}
	/**
	 * Method visualizes the calculated statistics by using the report method
	 * of the super class.
	 */
	public void report() 
	{
		System.out.println("continuous counter\n");
		super.report();
		System.out.println("interval length: " + (lastSampleTime - firstSampleTime));
	}
}
