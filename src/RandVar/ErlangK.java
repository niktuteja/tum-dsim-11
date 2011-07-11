package RandVar;
/**
 * ErlangK class
 * Generates ErlangK distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-17
 */
public abstract class ErlangK extends RandVar
{
	/**
	 * Attribute: mean = k / lambda
	 */
	public double lambda;
	
	/**
	 * Attribute: k
	 */
	public int k;
	
	/**
	 * Constructor of the Exponential class
	 * lambda and k are initialized with the value 1
	 * => Exponential Distribution  with mean = 1
	 */
	public ErlangK ()
	{
		lambda = 1;
		k = 1;
	}

	/**
	 * Constructor of the Exponential class
	 *@param _lambda the lambda value
	 *@param k
	 */
	public ErlangK (int _k, double _lambda)
	{
		lambda = _lambda;
		k = _k;
	}
	
	/**
	 * Returns the name of the distribution
	 */
	public String type ()
	{
		return "ErlangK distribution";
	}
	
	/**
	 * Function generates a double value according to the set attributes
	 *@return the sample
	 */
	public abstract double getRV ();

	/**
	 * Method return the mean of the distribution as double value
	 *@return the mean
	 */
	public double getMean() 
	{ 
		return (k / lambda); 
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 *@return the variance
	 */
	public double getVariance() 
	{
		return k / (lambda * lambda);
	}
	
	/**
	 * Method sets the standard deviation and the mean according to the given parameter
	 *@param m the new mean of the ErlangK distribution
	 *@param s the new standard deviation value
	 */
	public void setMeanAndStdDeviation(double m, double s)
	{
        lambda = 1 / m;
        double diffFloor;
		double diffCeil;
		int kFloor;
		int kCeil;
    
        kFloor = (int) (Math.floor ( 1 / (s * s * lambda * lambda)) );
        k = kFloor;
        diffFloor = Math.abs( s - getStdDeviation() );
        kCeil = (int) (Math.ceil ( 1 / (s * s * lambda * lambda)));
        k = kCeil;
        diffCeil = Math.abs(s - getStdDeviation());
    
        if( diffFloor < diffCeil)
		{
            k = kFloor;
        }
    }
	
	/**
	 * Method sets the mean according to the given parameter
	 *@param the new mean value
	 */
	public void setMean (double m)
	{
        setMeanAndStdDeviation(m,getStdDeviation());
    }

	/**
	 * Method sets the standard deviation according to the given parameter
	 *@param the new standard deviation value
	 */
	public void setStdDeviation (double s)
	{
        setMeanAndStdDeviation(getMean(),s);
    }

	/**
	 * Function prints the statistical characteristics of the random variable to the console
	 */
	public void report ()
	{
		System.out.println(type());
		System.out.println("parameters:");
		System.out.println("lambda = " + lambda);				
		System.out.println("k = " + k);		
		super.report();
	}

}
