package Analysis;

import java.util.ArrayList;

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
	/** autocorrelation history data */
	ArrayList<Double> initialValues;
	ArrayList<Double> recentValues;
	double EXY[];

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
		init();
	}
	/**
	 * Constructor that uses the given argument and the super constructor 
	 * to initialize the DiscreteCounter
	 *@param oVariable name of the observed variable
	 */
	public DiscreteCounter (String oVariable)
	{
		super (oVariable);
		init();
	}
	
	private void init()
	{
		numSamples = 0;
		recentValues = new ArrayList<Double>();
		initialValues = new ArrayList<Double>();
		EXY = new double[11];
	}
	
	/**
	 * Method resets all attributes
	 */
	public void reset ()
	{
		super.reset();
		init();
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
	
	private double calculateVariance(double sum, double sumPow2, long count)
	{
		return (sumPow2 - sum * sum / count) / (count-1);
	}
	
	/**
	 * Method calculates and returns the variance of the observed variable
	 * without regarding the counting time
	 *@return the calculated time independent variance
	 */
	public double getVariance()
	{
		if (numSamples > 1) {
			return calculateVariance(sumPowerOne, sumPowerTwo, numSamples);
		} else {
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
		
		/* keep data for autocorrelation calculation */
		if (numSamples <= 10) {
			initialValues.add(x);
		} else {
			recentValues.remove(0);
		}
		recentValues.add(x);
		
		for (int j = 0; j <= 10 && j < numSamples; j++) {
			EXY[j] += x * recentValues.get(recentValues.size() - 1 - j);
		}
		
		numSamples++;
	}
	/**
	 * Method visualizes the calculated statistics by using the report method
	 * of the super class.
	 */
	public void report() 
	{
		System.out.println("discrete counter\n");
		super.report();
		System.out.println("number of samples: " + numSamples);
	}
	
	
	/**
	 * Calculate the autoCorrelation of the Value Series for a given lag
	 * A one pass calculation method is used so that storing all values is not
	 * necessary, as that would lead to OutOfMemory Exceptions in long simulations
	 * 
	 * Note: The two pass method, where the means of the data series are calculated
	 * first are numerically better, i.e. more stable.
	 * 
	 * The instability of the one pass method will lead to an error of roughly 1/numSamples
	 * 
	 * @param lag amount to shift the series for the autoCorrelation calculation. 0 <= lag <= 10
	 */
	public double autoCorrelation(int lag) {
		if (lag > 10 || lag > numSamples)
			return 0;
		
		double first = 0, last = 0, firstPow2 = 0, lastPow2 = 0;
		// the first n and the last n values must be dropped from the two averages and the Pow2 sums
		for (int i = 0; i < lag; i++) {
			first += initialValues.get(i);
			firstPow2 += initialValues.get(i) * initialValues.get(i); 
			last += recentValues.get(recentValues.size() - i - 1);
			lastPow2 += recentValues.get(recentValues.size() - i - 1) * recentValues.get(recentValues.size() - i - 1);
		}
		
		/* sum of the values in each of the two data series */
		double sum1 = sumPowerOne - first;
		double sum2 = sumPowerOne - last;
		
		double cov = (EXY[lag] - sum1 * sum2 / numSamples ) / numSamples;
		
		double var1 = calculateVariance(sum1, sumPowerTwo - firstPow2, numSamples - lag);
		double var2 = calculateVariance(sum2, sumPowerTwo - lastPow2, numSamples - lag);
		
		return cov / Math.sqrt(var1 * var2);
	}
}
