package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public abstract class Counter {
	SimulationState simState;
	double sumPowerOne; // the sum of all counted values
	double sumPowerTwo; // sum squared?
	double min;
	double max;
	long numSamples; // the number of samples taken

	void count(double value) {
		min = value < min ? value : min;
		max = value > max ? value : max;

		sumPowerOne += value;
	}

	double getMean() {
		return sumPowerOne / numSamples;
	}
}
