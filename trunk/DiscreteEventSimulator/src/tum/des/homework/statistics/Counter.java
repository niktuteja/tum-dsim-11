package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public abstract class Counter {
	SimulationState simState;
	double sumPowerOne; // the sum of all counted values
	double sumPowerTwo; // sum squared?
	double min;
	double max;
	long numSamples; // the number of samples taken

	abstract public void count(double value);

	abstract public double getMean();

	@Override
	public String toString() {
		return String.format("Counter<numSamples=%d, sum=%f, min=%f, max=%f, mean=%f>", numSamples, sumPowerOne, min, max, getMean());
	}
}
