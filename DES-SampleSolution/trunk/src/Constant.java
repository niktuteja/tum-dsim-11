/**
 * Discrete Event Simulation SS2011
 *
 * Constant class
 * Generates exponential distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2010-11-08
 */

public class Constant extends RandVar
{
	/**
	 * Attribute: mean = 1 / lambda
	 */
	public double mean;

	/**
	 * Constructor of the Exponential class
	 * lambda is initialized with the value 1
	 * => mean = 1
	 */
	public Constant ()
	{
		mean = 1;
	}

	/**
	 * Constructor of the Constant class
	 *@param la the lambda value
	 */
	public Constant (double mean)
	{
		this.mean = mean;
	}

	/**
	 * Returns the name of the distribution
	 */
	public String type()
	{
		return "constant distribution";
	}

	/**
	 * Function generates a double value according to the set attributes
	 *@return the sample
	 */
	public double getRV () 
	{
        return mean;
    }

	/**
	 * Method return the mean of the distribution as double value
	 *@return the mean
	 */
	public double getMean() 
	{ 
		return mean; 
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 *@return the variance
	 */
	public double getVariance() 
	{
		return 0;
	}
	
	/**
	 * Method sets the mean according to the given parameter
	 *@param the new mean value
	 */
	public void setMean(double mean)
	{
        this.mean = mean;
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
		System.out.println("mean = " + mean);		
		super.report ();
	}
}
