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

	/* Slides: "Time between the last change of the system state 
	 * and the current simulation time has to be considered" 
	 */

	@Override
	public void count(double value) {
		sumPowerOne += value;

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
