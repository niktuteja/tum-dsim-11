package Analysis;

/**
 * Counter class
 * Basic class for all counting objects
 * 
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-06
 */
abstract class Counter
{
	/**
	 * Attribute: Sum
	 */
	double sumPowerOne;
	/**
	 * Attribute: SumPowerTwo
	 */
	double sumPowerTwo;
	/**
	 * Attribute: min counted value
	 */
	double min;
	/**
	 * Attribute: max counted value
	 */
	double max;
	/**
	 * Attribute: Name of the observed variable
	 */
	String observedVariable = "";
	/**
	 * Constructor uses the reset() methode to initialize
	 */
	public Counter ()
	{
		reset();
	}
	/**
	 * Constructor 
	 *@param name of the observed variable
	 */
	public Counter (String name)
	{
		reset();
		observedVariable = name;
	}
	
	/**
	 * Abstract methode getMean () used to define the interface of all counters
	 */
	public abstract double getMean ();
	/**
	 * Abstract methode getVariance () used to define the interface of all counters
	 */
	public abstract double getVariance ();
	/**
	 * Function returns the minimum of all counted values
	 */
	public double getMin ()
	{
		return min;
	}
	/**
	 * Function returns the maximum of all counted values
	 */
	public double getMax()
	{
		return max;
	}
	/**
	 * Function calculates and returns the standard deviation by using
	 * the getVariance() function.
	 *@return the standard deviation
	 */
	public double getStdDeviation ()
	{
		return Math.sqrt(getVariance());
	}
	/**
	 * Function calculates and returns the variation coefficient by using
	 * the getStdDeviation() and getMean() functions.
	 *@return the variation coefficient
	 */
	public double getCvar() 
	{
		if (getMean() ==0 )
		{
			return ( getStdDeviation() == 0 ? 0 : Double.MAX_VALUE);
		}
		else
		{
			return (getStdDeviation() / getMean());
		}
	}
	/**
	 * Function resets all attributes needed to calculate the statistic values
	 */
	public void reset ()
	{
		sumPowerOne = 0;
		sumPowerTwo = 0;
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
	}
	/**
	 * Function only sets the min and the max counted value.
	 * Extending classes have to implement additional functionality.
	 * Hint: Time dependent counting differs from time independent counting 
	 *@param x the value to count
	 */
	public void count (double x)
	{
		min = (x < min ? x : min);
		max = (x > max ? x : max);
	}
	/**
	 * Function prints all statistic values
	 */
	public void report ()
	{
		System.out.println("observed random variable: " + observedVariable);
		System.out.println("mean:                     " + getMean());
		System.out.println("variance:                 " + getVariance());
		System.out.println("standard deviation:       " + getStdDeviation());
		System.out.println("coefficient of variation: " + getCvar());
		System.out.println("minimum:                  " + getMin());
		System.out.println("maximum:                  " + getMax());
	}
}
