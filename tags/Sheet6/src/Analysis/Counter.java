package Analysis;
import java.io.*;
import java.util.Scanner;

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
	 * Attribute: Object for data output to a text file 
	 */
	PrintWriter file;
	/**
	 * Constructor uses the reset() method to initialize
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
	 * Abstract method getMean () used to define the interface of all counters
	 */
	public abstract double getMean ();
	/**
	 * Abstract method getVariance () used to define the interface of all counters
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
		file = null;
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
		if (file != null)
			write(x);
	}
	/**
	 * Writes the value to a text file
	 *@param x the value to write
	 */
	public void write (double x)
	{
		file.print(x + " ");
	}
	/**
	 * Sets up the PrintWriter for file output or removes it
	 */
	public boolean enableWrite(boolean enabled)
	{
		if (enabled) {
			String filename = this.getClass().getSimpleName() + "_" + observedVariable + ".txt";
			try {
				file = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
			} catch (IOException ex) {
				file = null;
				return false;
			}
			
			return true;
		} else {
			if (file != null)
				file.close();
			file = null;
			return true;
		}
	}
	/**
	 * reads a file and counts the values
	 */
	public boolean readFile(String filename)
	{
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader(filename)));
			
			while (s.hasNext())
				count(Double.parseDouble(s.next()));
			
		} catch (FileNotFoundException ex) {
			return false;
		}
		return true;
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
