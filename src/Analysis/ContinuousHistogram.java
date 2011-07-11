package Analysis;

import Simulator.SimState;

/**
 * Class Continuous Histogram
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-06
 */
public class ContinuousHistogram extends Histogram
{
	/**
	 * Attribute: Simulation time of the last sample
	 */
	long lastSampleTime;
	/**
	 * Attribute: Simulation time of the first sample
	 */
	long firstSampleTime;
	/**
	 * Attribute: Number of samples
	 */
	double lastSampleSize;
	/**
	 * Constructor that uses the given arguments to initialize the histogram 
	 *@param oVariable name of the observed variable
	 *@param lb lower bound of the histogram
	 *@param uB upper bound of the histogram
	 */
	public ContinuousHistogram (String oVariable, double lB, double uB)
	{
		super (oVariable, lB, uB);
		type = "continuous";
		lastSampleTime = 0;
		firstSampleTime = 0;
		lastSampleSize = 0;
	}
	/**
	 * Function sets all attributes to initial value
	 */
	public void reset()
	{
		super.reset();
		lastSampleTime = 0;
		firstSampleTime = 0;
		lastSampleSize = 0;
	}
	/**
	 *Function counts the given argument and sets the attributes according to
	 *the SimState
	 *@param x the argument to count
	 */
	public void count(double x)
	{
		if (numIntervals > 0)
		{
			double newValue = ((Double) bins.get(getBinNumber(lastSampleSize))).doubleValue() + ((SimState.s.now - lastSampleTime));
			bins.set (getBinNumber(lastSampleSize), Double.valueOf(newValue));
			lastSampleTime = SimState.s.now;
			lastSampleSize = x;
		}
		if (file != null)
			write(x);
	}
	/**
	 * Method calculates the divisor required for histogram calculations
	 *@return calculate divisor
	 */
	public double divisor ()
	{
		return lastSampleTime - firstSampleTime;
	}
	/**
	 * String representing the type of the histogram
	 *@return type of the histogram
	 */
	public String type()
	{
		return "continuous";
	}
}
