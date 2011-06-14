package tum.des.homework.statistics;

import tum.des.homework.simulator.SimulationState;

public class TDCounter extends Counter {
	public TDCounter(SimulationState simState) {
		super(simState);
		// TODO Auto-generated constructor stub
	}

	long firstSampleTime;
	long lastSampleTime;
	double lastSampleSize;

	@Override
	public void count(double value) {
		// Changed according to Alexanders feedback from sheet 2 (daniel)
		sumPowerOne += lastSampleSize * (simState.getTicks() - lastSampleTime);

		numSamples++;
		lastSampleSize = value;
		lastSampleTime = simState.getTicks();

		if (numSamples == 0) {
			firstSampleTime = simState.getTicks();
		}
	}

	// Returns the normalized utilization 
	@Override
	public double getMean() {
		long totalDuration = lastSampleTime - firstSampleTime;
		return sumPowerOne / totalDuration;
	}

	@Override
	public String toString() {
		return String.format(
				"TDCounter<numSamples=%d, sum=%f, min=%f, max=%f, firstSampleTime=%d, lastSampleTime=%d, totalDuration=%d, mean=%f>",
				numSamples, sumPowerOne, min, max, firstSampleTime, lastSampleTime, lastSampleTime - firstSampleTime, getMean());
	}

}
