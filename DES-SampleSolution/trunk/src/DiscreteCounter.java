/**
 * DiscreteCounter class
 * Class just counts the given values without regarding the time the system
 * remained in this state.
 * 
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-06
 */
public class DiscreteCounter extends Counter
{
	/**
	 * Attribute: Number of samples taken
	 */
	public long numSamples;
	/**
	 * Constructor that uses super constructor 
	 * to initialize the DiscreteCounter
	 */
	public DiscreteCounter ()
	{
		super ();
		numSamples = 0;
	}
	/**
	 * Constructor that uses the given argument and the super constructor 
	 * to initialize the DiscreteCounter
	 *@param oVariable name of the observed variable
	 */
	public DiscreteCounter (String oVariable)
	{
		super (oVariable);
		numSamples = 0;
	}
	/**
	 * Method resets all attributes
	 */
	public void reset ()
	{
		super.reset();
		numSamples = 0;
	}
	/**
	 * Method calculates and returns the mean of the observed variable
	 * without regarding the counting time
	 *@return the calculated time independent mean
	 */
	public double getMean() 
	{
		return (numSamples > 0 ? (sumPowerOne / numSamples) : 0 );
	}
	/**
	 * Method calculates and returns the variance of the observed variable
	 * without regarding the counting time
	 *@return the calculated time independent variance
	 */
	public double getVariance()
	{
		if (numSamples > 1)
		{
			double tmp1 = getMean();
			double tmp2 = sumPowerTwo / numSamples;
			return numSamples / (double)(numSamples-1) * (tmp2 - tmp1*tmp1); //Equation 2.30
		}
		else
		{
			return 0;
		}
	}
	/**
	 * Method counts the given argument 
	 * by extending the "original" counting method.
	 *@param x the double value to count
	 */
	public void count(double x)
	{
		super.count(x);
		sumPowerOne+=x;
		sumPowerTwo+=x*x;
		numSamples++;
	}
	/**
	 * Method visualizes the calculated statistics by using the report method
	 * of the super class.
	 */
	public void report() 
	{
		System.out.println("\ndiscrete counter");
		super.report();
		System.out.println("number of samples: " + numSamples);
	}
}
