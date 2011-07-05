package RandVar;

/**
 * Discrete Event Simulation WS2010/2011
 * 
 * Normal class Generates normal distributed samples
 * 
 * @author D�lle
 * @version 1.0.0
 * @since 2010-11-08
 */

public class Normal extends RandVar {
	/**
	 * Attribute: mean = 1 / lambda
	 */
	public double mean;
	
	public double variance;


	/**
	 * Constructor of the Exponential class
	 * 
	 * @param mu
	 *            the lambda value
	 */
	public Normal(double mu, double var) {
		mean = mu;
		variance = var;
	}

	/**
	 * Returns the name of the distribution
	 */
	public String type() {
		return "normal distribution";
	}

	/**
	 * Function generates a double value according to the set attributes
	 * 
	 * @return the sample
	 */
	public double getRV() {
		// Accept-reject method
		// http://en.wikipedia.org/wiki/Box-Muller_transform
		double u1, u2, v1 = 0, v2 = 0, w, y = 0, x1, x2;
		boolean accept = false;
		
		while(!accept) {
		
			u1 = rng.nextDouble();
			u2 = rng.nextDouble();
			
			v1 = 2 * u1 - 1;
			v2 = 2 * u2 - 1;
			w = (v1 * v1) + (v2 * v2);
			
			if (w <= 1) {
				y = Math.sqrt(-2 * Math.log(w) / w);
				accept = true;
			}
		}
		x1 = y * v1;
		x2 = y * v2;
		
		//scale
		return getMean() + getStdDeviation() * x1; //TODO: @Alex: what about x2?
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
		return variance;
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
		variance = s * s;
	}

	/**
	 * Function prints the statistical characteristics of the random variable to
	 * the console
	 */
	public void report() {
		System.out.println(type());
		System.out.println("parameters:");
		System.out.println("mean = " + mean);
		super.report();
	}
}
