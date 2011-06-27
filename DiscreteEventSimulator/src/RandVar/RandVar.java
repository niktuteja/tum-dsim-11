package RandVar;

public abstract class RandVar {
	/**
	 * Attribute: java random number generator. Creates random numbers between 0
	 * and 1 uniformly distributed
	 */
	public Rng rng = new Rng();

	/**
	 * Abstract method type() shall return the name of the distribution
	 */
	public abstract String type();

	/**
	 * Abstract method getRV() returns uniform distributed samples
	 */
	public abstract double getRV();

	/**
	 * Method sets the seed of the rng
	 */
	public void setSeed(int seed) {
		rng.z = seed;
	}

	/**
	 * Abstract method returns the greatest long that is smaller than the
	 * sample+0.5
	 */
	public long getLongRV() {
		return (long) (Math.floor(getRV() + 0.5));
	}

	/**
	 * Returns the mean of the RandVar
	 */
	public abstract double getMean();

	/**
	 * Returns the variance of the RandVar
	 */
	public abstract double getVariance();

	/**
	 * Function calculates and returns the standard deviation by using the
	 * getVariance() function.
	 * 
	 * @return the standard deviation
	 */
	public double getStdDeviation() {
		return Math.sqrt(getVariance());
	}

	/**
	 * Function calculates and returns the variation coefficient by using the
	 * getStdDeviation() and getMean() functions.
	 * 
	 * @return the variation coefficient
	 */
	public double getCvar() {
		if (getMean() == 0) {
			return (getStdDeviation() == 0 ? 0 : Double.MAX_VALUE);
		} else {
			return getStdDeviation() / getMean();
		}
	}

	/**
	 * Function sets the variance according to given parameter if the parameter
	 * is greater than zero
	 * 
	 * @param v
	 *            new value of the variance
	 */
	public void setVariance(double v) {
		if (v >= 0) {
			setStdDeviation(Math.sqrt(v));
		} else {
			System.out.println("variance == " + v + " must be positive.");
		}
	}

	/**
	 * Function sets the variation coefficient according to the given parameter
	 * c. If the mean is equal to zero nothing is done.
	 * 
	 * @param c
	 *            the value to set
	 */
	public void setCvar(double c) {
		if (getMean() != 0) {
			double stddev = getMean() * c;
			setStdDeviation(stddev);
		} else {
			System.out.println("mean == 0: cvar can not be set.");
		}
	}

	/**
	 * Abstract function setMean(double) has to be implemented by extending
	 * classes
	 * 
	 * @param m
	 *            the value to set
	 */
	public abstract void setMean(double m);

	/**
	 * Abstract function setStdDeviation(double) has to be implemented by
	 * extending classes
	 * 
	 * @param s
	 *            the value to set
	 */
	public abstract void setStdDeviation(double s);

	/**
	 * Abstract function setMeandAndStdDeviation(double,double) has to be
	 * implemented by extending classes
	 * 
	 * @param m
	 *            the value to set
	 * @param s
	 *            the value to set
	 */
	public abstract void setMeanAndStdDeviation(double m, double s);

	/**
	 * Function sets the mean and variation coefficient according to the given
	 * parameters.
	 * 
	 * @param m
	 *            the value to set
	 * @param s
	 *            the value to set
	 */
	public void setMeanAndCvar(double m, double c) {
		double s = m * c;
		setMeanAndStdDeviation(m, s);
	}

	/**
	 * Function prints the statistical characteristics of the random variable to
	 * the console
	 */
	public void report() {
		System.out.println("propterties:");
		System.out.println("mean: " + getMean());
		System.out.println("cvar: " + getCvar());
		System.out.println("stdDev: " + getStdDeviation());
		System.out.println("variance: " + getVariance());
	}
}
