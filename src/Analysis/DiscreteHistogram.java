package Analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * DiscreteHistogram class
 * 
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-06
 */
public class DiscreteHistogram extends Histogram
{
	/**
	 * Attribute: Number of samples taken
	 */
	public long numSamples;
	/**
	 * Constructor that uses the given arguments to initialize the histogram 
	 *@param oVariable name of the observed variable
	 *@param lb lower bound of the histogram
	 *@param uB upper bound of the histogram
	 */
	public DiscreteHistogram (String oVariable, double lB, double uB)
	{
		super(oVariable, lB, uB);
		type = "discrete";
		numSamples = 0;
	}
	/**
	 * Function sets all attributes to initial value
	 */
	public void reset()
	{
		super.reset();
		numSamples = 0;
	}
	/**
	 * Function count the given argument and sets the attributes according to
	 * the SimState
	 *@ param x the argument to count
	 */
	public void count (double x)
	{
		if (numIntervals > 0)
		{
			numSamples++;
			double newValue = ((Double) bins.get(getBinNumber(x))).doubleValue() + 1;
			bins.set (getBinNumber(x), Double.valueOf(newValue));
		}
		if (file != null)
			write(x);
	}
	
	/**
	 * Function returns the divisor() for statistic calculation
	 * In this simple case the function only has to return the numSamples attribute
	 *@return the numSamples attribute
	 */
	public double divisor ()
	{
		return numSamples;
	}
}