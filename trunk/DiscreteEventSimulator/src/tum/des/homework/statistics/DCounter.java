package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public class DCounter extends Counter {

	public DCounter(SimulationState simState) {
		super(simState);
	}

	@Override
	public void count(double value) {
		sumPowerOne += value;

		numSamples++;

		min = sumPowerOne < min ? sumPowerOne : min;
		max = sumPowerOne > max ? sumPowerOne : max;
	}

	@Override
	public double getMean() {
		return sumPowerOne / numSamples;
	}
}
