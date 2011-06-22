package RandVar;

import RNG.Rng;

/**
 * Discrete Event Simulation SS2011
 * 
 * Constant class Generates Erlang-k distributed samples
 * 
 * @author Vincenz Dšlle
 * @version 1.0.0
 * @since 2011-06-20
 */

public class Erlang extends RandVar {
	/**
	 * Attribute: mean = k / lambda
	 */
	public double lambda;

	public int k;

	/**
	 * Constructor of the Erlang-k class lambda is initialized with the value 1
	 * => mean = 1
	 */
	public Erlang(int k) {
		this.k = k;
		this.lambda = 1;
	}

	/**
	 * Constructor of the Erlang class
	 * 
	 * @param la
	 *            the lambda value
	 */
	public Erlang(int k, double la) {
		this.k = k;
		this.lambda = la;
	}

	/**
	 * Constructor of the Exponential class
	 * 
	 * @param la
	 *            the lambda value
	 */
	public Erlang(int k, double la, Rng rng) {
		this.k = k;
		this.lambda = la;
		this.rng = rng;
	}

	/**
	 * Returns the name of the distribution
	 */
	@Override
	public String type() {
		return "Erlang-k distribution";
	}

	/**
	 * Function generates a double value according to the set attributes by
	 * convolution using Exp.
	 * 
	 * @return the sample
	 */
	@Override
	public double getRV() {
		Exponential exp = new Exponential(lambda * k, rng);
		double rv = 0;

		for (int i = 0; i < k; i++) {
			rv += exp.getRV();
		}

		return rv;
	}

	/**
	 * Method return the mean of the distribution as double value
	 * 
	 * @return the mean
	 */
	@Override
	public double getMean() {
		return (k / lambda);
	}

	/**
	 * Method calculates the variance of the distribution as double value
	 * 
	 * @return the variance
	 */
	@Override
	public double getVariance() {
		return (k / (lambda * lambda));
	}

	/**
	 * Method sets the mean according to the given parameter
	 * 
	 * @param the
	 *            new mean value
	 */
	@Override
	public void setMean(double m) {
		if (m > 0) {
			lambda = (1 / m);
		} else {
			System.out.println("mean > 0 required");
		}
	}

	/**
	 * Interface
	 */
	@Override
	public void setMeanAndStdDeviation(double m, double s) {
		System.out.println("not applicable");
	}

	/**
	 * Interface
	 */
	@Override
	public void setStdDeviation(double s) {
		System.out.println("not applicable");
	}

	/**
	 * Function prints the statistical characteristics of the random variable to
	 * the console
	 */
	@Override
	public void report() {
		System.out.println(type());
		System.out.println("parameters:");
		System.out.println("lambda = " + lambda);
		super.report();
	}
}
