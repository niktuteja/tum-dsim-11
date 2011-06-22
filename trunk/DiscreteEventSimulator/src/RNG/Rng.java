package RNG;

import java.util.ArrayList;

import Analysis.DiscreteCounter;

public class Rng {
	long a; // multiplier
	long z_0; // seed
	long c; // increment
	long m; // modulus

	long z_new; // z_i
	long z_old; // z_i-1
	
	//s. http://en.wikipedia.org/wiki/Autocorrelation
	DiscreteCounter counter;
	DiscreteCounter counterS;
	
	double[] randomNumbers = new double[0];
	int samplesCount = 0;
	int autoCorrelationMaxLag = 10;

	public Rng(long a, long c, long z_0, long m) {
		this.a = a;
		this.c = c;
		this.z_0 = z_0;
		this.m = m;

		this.z_old = z_0;
		
		this.counter = new DiscreteCounter("LCG random numbers");
	}

	/** @deprecated (VD) */
	@Deprecated
	public Rng() {
		this(16807, 0, 1, 2147483647); // m = 2^31 - 1
	}

	/** @deprecated (VD) */
	@Deprecated
	public Rng(long seed) {
		this();
		setSeed(seed);
	}

	public double nextDouble() {
		z_new = (a * z_old + c) % m;
		z_old = z_new;

		// Generates values in [0;1]
		double next = (double) (z_new + 1) / ((double) (m + 1));

		// Code for generating values in ]0;1[: 
		//		double next = (double) (z_new) / ((double) (m - 1));
		
		counter.count(next);
		
		//cache
		if (samplesCount < randomNumbers.length)
			randomNumbers[samplesCount++] = next;
		
		return next;
	}
	
	public void resetSamplesCache(int size) {
		 randomNumbers = new double[size];
		 samplesCount = 0;
	}
	
	@Override
	public String toString() {
		return String.format("Rng<a=%d, c=%d, z_0=%d, m=%d>", a, c, z_0, m);
	}

	//basis: wikipedia
	public double autoCorrelation(int lag) {
		if (lag > autoCorrelationMaxLag) {
			return 0;
		}
			
		double mu_t = counter.getMean();
		double stddev_t = Math.sqrt(counter.getVariance());
		
		for (int i = 0; i < lag; i++) {
			double next = nextDouble();
			counter.count(next);
			
			if (samplesCount < randomNumbers.length)
				randomNumbers[samplesCount++] = next;
		}
		
		double mu_s = counter.getMean(); //s = t + lag
		double stddev_s = counter.getStdDev();
		
		DiscreteCounter covCounter = new DiscreteCounter("covariation");
		
		for (int i = 0; i < randomNumbers.length-lag; i++) {
			//for calculating E[(X_t * X_s]
			covCounter.count(randomNumbers[i] * randomNumbers[i+lag]);
		}
		
		//return cov(X_t, X_s) / (stddev_t * stddev_s)
		return (covCounter.getMean() - mu_s * mu_t) / (stddev_t * stddev_s);
	}

	public void setSeed(long seed) {
		this.z_0 = seed;
		this.z_old = this.z_0;
	}

}
