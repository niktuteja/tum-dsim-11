package RandVar;

/**
 * Discrete Event Simulation SS2011
 *
 * NormalAcc class
 * Generates normal distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2011-06-21
 */

public class NormalAcc extends RandVar
{
	public double mu;
	public double sigma;
	
	public Rng rng;
		
	/**
	 * Constructor of the Normal class without parameters
	 * initializes with mu = 0 and sigma = 1 representing
	 * the standard normal distribution
	 */
	public NormalAcc ()
	{
		setMeanAndStdDeviation (0, 1);
		rng = new Rng();
	}
	/**
	 * Constructor of the Normal class
	 *@param mean 
	 *@param stdDev
	 */
	public NormalAcc (double mean, double _sigma)
	{
		setMeanAndStdDeviation (mean, _sigma);
		rng = new Rng();
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
		double v1, v2,w;
		while(true)
		{
			v1 = 2*rng.nextDouble()-1;
			v2 = 2*rng.nextDouble()-1;
			w = v1*v1 + v2*v2;
			if (w <= 1)
			{
				double y = Math.pow(-2*Math.log(w)/w,0.5);
				
        		return sigma * v1*y + mu;
				//This method generates two samples according to the normal distribution.
				//Here, we discard the second number. However, the number could be stored
				//and used when the function is called for the next time.
				//return sigma * v2*y + mu;
			}
		}
	
 	}

	
	 /* returns the probability of a given x under the Normal distribution */
	private double norm_func(double x) 
	{
		return 1/Math.sqrt(2*Math.PI*Math.pow(sigma,2))*Math.pow(Math.E, -1*Math.pow(x-mu,2) /(2*Math.pow(sigma,2)));
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
