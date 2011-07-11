package RandVar;

/**
 * Discrete Event Simulation SS 2011
 *
 * Discrete random variable
 * Generates geom distributed samples
 *
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2010-11-08
 */
public class Geom extends RandVar
{
	public double p;
	
	/**
	 * Constructor of the Geom class
	 */
	public Geom ()
	{
		p = 0.5;
	}

	/**
	 * Constructor of the Geom class
	 *@param p success probability
	 */
	public Geom (double p)
	{
		this.p = p;
	}

	/**
	 * Constructor of the Geom class
	 *@param p success probability
	 *@param rng
	 */
	public Geom (double p, Rng rng)
	{
		this.p = p;
		this.rng = rng;
	}

	/**
	 * Returns the name of the distribution
	 */
	public String type()
	{
		return "geom distribution";
	}
	
	/**
	 * Function calculates a double  
	 * according to the geom distribution
	 *@return the sample
	 */
	public double getRV()
	{
		return Math.floor(Math.log(rng.nextDouble())/Math.log(1-p));
	}

	/**
	 * Method return the mean of the distribution as double value
	 *@return the mean
	 */
	public double getMean() 
	{ 
		return (1-p)/p; 
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 *@return the variance
	 */
	public double getVariance() 
	{
		return (1-p)/(p*p);
	}

	/**
	 * Function modifies attribute p to get the desired mean
	 */
	public void setMean (double m)
	{
		if (m != -1)
			p = 1/(m+1);
		else
			System.out.println("This mean value makes no sense for the geom distribution!");
	}

	/**
	 * Function modifies attribute p to get the desired standard deviation.
	 * sigma^2*p^2+p-1=0;
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
		System.out.println("p = " + p);		
		super.report();
	}

	@Override
	public void setMeanAndStdDeviation(double m, double s) {
		// TODO Auto-generated method stub
		
	}
}
