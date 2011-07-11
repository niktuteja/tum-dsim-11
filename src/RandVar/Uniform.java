package RandVar;

/**
 * Discrete Event Simulation SS2011
 *
 * Discrete class
 * Generates exponential distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2010-11-08
 */
public class Uniform extends RandVar
{
	public double lowerBound;
	public double upperBound;
	
	/**
	 * Constructor of the Uniform class
	 * lowerBound is set to 0 and upperBound is set to 1 representing the uniform values of the
	 * java Random.nextDouble() method with seed set to zero
	 */
	public Uniform ()
	{
		lowerBound = 0;
		upperBound = 1;
	}

	/**
	 * Constructor of the Uniform class
	 * Sets the bounds to the values of the given arguments by using the setBounds method.
	 * If lb > uB no Values are set.  The seed of the java random number generator is 0.
	 *@param lB lowerBound value
	 *@param uB upperBound value
	 */
	public Uniform (double lB, double uB)
	{
		setBounds(lB, uB);
	}

	/**
	 * Returns the name of the distribution
	 */
	public String type()
	{
		return "uniform distribution";
	}
	
	/**
	 * Function calculates a double between lowerBound and upperBound by using
	 * the java random number generator
	 *@return the sample
	 */
	public double getRV()
	{
		return (lowerBound + rng.nextDouble() * (upperBound - lowerBound));
	}

	/**
	 * Function sets the bounds of the sample values according to the given parameters
	 *@param lb the lower bound
	 *@param uB the upper bound
	 */
	public void setBounds(double lB, double uB)
	{
		if (lB < uB)
		{
			lowerBound = lB;
			upperBound = uB;
		}
	}

	/**
	 * Method return the mean of the distribution as double value
	 *@return the mean
	 */
	public double getMean() 
	{ 
		return (lowerBound + upperBound) / 2; 
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 *@return the variance
	 */
	public double getVariance() 
	{
		return (upperBound - lowerBound) * (upperBound - lowerBound) / 12;
	}

	/**
	 * Function sets the lowerBound and the upperBound to create samples with the mean and the standardDeviation
	 * given by the arguments. However, this function shall not be used.
	 *@param m the new mean value
	 *@param s the new standard deviation value
	 */
	public void setMeanAndStdDeviation(double m, double s)
	{
		lowerBound = m - Math.sqrt((double) 3 ) * s;
		upperBound = m + Math.sqrt((double) 3 ) * s;
	}

	/**
	 * Setting the mean makes no sense for uniform distribution. Change bounds to get the
	 * desired mean.
	 */
	public void setMean (double m)
	{
		System.out.println("Set bounds to change the mean!");
	}

	/**
	 * Function changes the lowerBound and the upperBound to create samples with the standardDeviation
	 * given by the arguments. However, this function shall not be used.
	 *@param s the new standard deviation value
	 */
	public void setStdDeviation (double s)
	{
		setMeanAndStdDeviation(getMean(), s);
	}

	/**
	 * Function prints the statistical characteristics of the random variable to the console
	 */
	public void report()
	{
		System.out.println(type());
		System.out.println("parameters:  " );
		System.out.println("lowerBound = " + lowerBound);		
		System.out.println("upperBound = " + upperBound);
		super.report();
	}
}
