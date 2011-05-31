package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public class DCounter extends Counter {

	public DCounter(SimulationState simState) {
		super(simState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void count(double value) {
		min = value < min ? value : min;
		max = value > max ? value : max;

		sumPowerOne += value;
		numSamples++;
	}

	@Override
	public double getMean() {
		return sumPowerOne / numSamples;
	}
}
