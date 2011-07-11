package RandVar;

import java.util.Random;

/**
 * Discrete Event Simulation WS2010/2011
 *
 * Normal class
 * Generates exponential distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2010-11-21
 */

public class Normal extends RandVar
{
	public double mu;
	public double sigma;
	
	public Random rng;
		
	/**
	 * Constructor of the Normal class without parameters
	 * initializes with mu = 0 and sigma = 1 representing
	 * the standard normal distribution
	 */
	public Normal ()
	{
		setMeanAndStdDeviation (0, 1);
		rng = new Random();
	}
	/**
	 * Constructor of the Normal class
	 *@param mean 
	 *@param stdDev
	 */
	public Normal (double mean, double _sigma)
	{
		setMeanAndStdDeviation (mean, _sigma);
		rng = new Random();
	}
	
	/**
	 * Returns the name of the distribution
	 */
	public String type()
	{
		return "normal distribution";
	}
	
	/**
	 * Function generates a double value according to the set attributes
	 *@return the sample
	 */
	public double getRV ()
	{
		
		return sigma * rng.nextGaussian() + mu;
	}

	/**
	 * Method return the mean of the distribution as double value
	 *@return the mean
	 */
	public double getMean() 
	{ 
		return mu;
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 *@return the variance
	 */
	public double getVariance() 
	{
		return sigma*sigma;
	}
	
	/**
	 * Method sets the standard deviation and the mean according to the given parameters
	 *@param m the new mean
	 *@param s the new standard deviation value
	 */
	public void setMeanAndStdDeviation (double m, double s)
	{
        mu = m;
		sigma = s;
	}
	
	/**
	 * Method sets the mean according to the given parameter
	 *@param s the new standard deviation value
	 */
	public void setMean (double m)
	{
		mu = m;
    }

	/**
	 * Method sets the standard deviation according to the given parameter
	 *@param s the new standard deviation value
	 */
	public void setStdDeviation (double s)
	{
		sigma = s;
    }
}
