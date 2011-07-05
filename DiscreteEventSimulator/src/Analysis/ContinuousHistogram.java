package Analysis;

import Simulator.SimState;

public class ContinuousHistogram extends Histogram {

	/**
	 * Attribute: Simulation time of the last sample
	 */
	public long lastSampleTime = -1;
	/**
	 * Attribute: Simulation time of the first sample
	 */
	public long firstSampleTime = 0;
	/**
	 * Attribute: Number of samples
	 */
	public double lastSampleSize = 0;

	public ContinuousHistogram(String oVariable, double lB, double uB,
			int numIntervals) {
		super(oVariable, lB, uB);

		setupNumIntervals(numIntervals);
	}

	@Override
	public void count(double x) {
		double deltaT = SimState.s.now - lastSampleTime;
		// If this statement is true there must be an error in the simulation
		// => abort
		if (deltaT < 0) {
			System.out.println("last = " + lastSampleTime + " now = "
					+ SimState.s.now);
			System.exit(-1);
		}

		// ignore if first sample
		if (lastSampleTime >= 0)
		{
			int binNumber = getBinNumber(lastSampleSize * deltaT);
			bins.set(binNumber, bins.get(binNumber).doubleValue() + 1);
		}
		
		lastSampleTime = SimState.s.now;
		lastSampleSize = x;
	}

	@Override
	public double divisor() {
		long tmp = lastSampleTime - firstSampleTime;
		return (tmp > 0 ? (double) tmp : 1);
	}

}
