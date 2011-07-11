package RandVar;

/**
 * Discrete Event Simulation WS2010/2011
 * 
 * Geometric class Generates geometric distributed samples
 * 
 * @author Dšlle
 * @version 1.0.0
 * @since 2010-11-08
 */

public class Geometric extends RandVar {
	/**
	 * Attribute: mean = 1 / lambda
	 */
	public double mean;
	
	public double p;


	/**
	 * Constructor of the Exponential class
	 * 
	 * @param mu
	 *            the lambda value
	 */
	public Geometric(double p) {
		this.p = p;
		this.mean =  (1-p) / p;
	}

	/**
	 * Returns the name of the distribution
	 */
	public String type() {
		return "geometric distribution";
	}

	/**
	 * Function generates a double value according to the set attributes
	 * 
	 * @return the sample
	 */
	public double getRV() {
		//by inversion
		return Math.floor(Math.log(rng.nextDouble()) / Math.log(1 - p));
	}

	/**
	 * Method return the mean of the distribution as double value
	 * 
	 * @return the mean
	 */
	public double getMean() {
		return mean;
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 * 
	 * @return the variance
	 */
	public double getVariance() {
		return (1 - p) / (p * p);
	}

	/**
	 * Method sets the mean according to the given parameter
	 * 
	 * @param the
	 *            new mean value
	 */
	public void setMean(double m) {
		if (m > 0) {
			mean = m;
			p = 1 / (m + 1); //m = (1-p) / p
		} else {
			System.out.println("mean > 0 required");
		}
	}

	/**
	 * Interface
	 */
	public void setMeanAndStdDeviation(double m, double s) {
		System.out.println("not applicable");
	}

	/**
	 * Interface
	 */
	public void setStdDeviation(double s) {
		System.out.println("not applicable");
	}

	/**
	 * Function prints the statistical characteristics of the random variable to
	 * the console
	 */
	public void report() {
		System.out.println(type());
		System.out.println("parameters:");
		System.out.println("p = " + p);
		System.out.println("mean = " + mean);
		super.report();
	}
}
