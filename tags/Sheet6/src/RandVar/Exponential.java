package RandVar;

/**
 * Discrete Event Simulation WS2010/2011
 *
 * Exponential class
 * Generates exponential distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2010-11-08
 */

public class  Exponential extends RandVar
{
	/**
	 * Attribute: mean = 1 / lambda
	 */
	public double lambda;

	/**
	 * Constructor of the Exponential class
	 * lambda is initialized with the value 1
	 * => mean = 1
	 */
	public Exponential ()
	{
		lambda = 1;
	}

	/**
	 * Constructor of the Exponential class
	 *@param la the lambda value
	 */
	public Exponential (double la)
	{
		lambda = la;
	}

	/**
	 * Returns the name of the distribution
	 */
	public String type()
	{
		return "exponential distribution";
	}

	/**
	 * Function generates a double value according to the set attributes
	 *@return the sample
	 */
	public double getRV () 
	{
        return (- (Math.log (rng.nextDouble()+Double.MIN_VALUE) * ( 1 / lambda) ));
    }

	/**
	 * Method return the mean of the distribution as double value
	 *@return the mean
	 */
	public double getMean() 
	{ 
		return (1 / lambda); 
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 *@return the variance
	 */
	public double getVariance() 
	{
		return (1 / (lambda * lambda));
	}
	
	/**
	 * Method sets the mean according to the given parameter
	 *@param the new mean value
	 */
	public void setMean(double m)
	{
        if (m > 0)
		{
            lambda = (1 / m);
        }
		else
		{
            System.out.println("mean > 0 required");
		}
    }

	/**
	 * Interface
	 */
	public void setMeanAndStdDeviation(double m, double s)
	{
        System.out.println("not applicable");
    }

    /**
	 * Interface
	 */
    public void setStdDeviation(double s){
        System.out.println("not applicable");
    }
	
	/**
	 * Function prints the statistical characteristics of the random variable to the console
	 */
	public void report ()
	{
		System.out.println(type());
		System.out.println("parameters:");
		System.out.println("lambda = " + lambda);		
		super.report ();
	}
}
