package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public class DCounter extends Counter {

	public DCounter(SimulationState simState) {
		super(simState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void count(double value) {
		sumPowerOne += value;
// FIXME apply min max accordingly		
//		min = value < min ? value : min;
//		max = value > max ? value : max;
		
		// TODO also inc if negative count?!
		numSamples++;
	}

	@Override
	public double getMean() {
		return sumPowerOne / numSamples;
	}
}
