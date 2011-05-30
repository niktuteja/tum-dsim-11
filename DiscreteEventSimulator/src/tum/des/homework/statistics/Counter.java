package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public abstract class Counter {
	SimulationState simState;
	double sumPowerOne; // the sum of all counted values
	double sumPowerTwo; // sum squared?
	double min;
	double max;
	long numSamples; // the number of samples taken

	abstract void count(double value);

	abstract double getMean();
}
