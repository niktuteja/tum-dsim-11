package tum.des.homework.statistics;

public class TDCounter extends Counter {
	long firstSampleTime;
	long lastSampleTime;
	double lastSampleSize;

	/* Slides: "Time between the last change of the system state 
	 * and the current simulation time has to be considered" 
	 */

	@Override
	void count(double value) {
		//
		// TODO
		//

		numSamples++;
		lastSampleSize = value;
		lastSampleTime = simState.getTicks();

		if (numSamples == 0) {
			firstSampleTime = simState.getTicks();
		}
	}

	@Override
	double getMean() {
		return sumPowerOne / numSamples;
	}

}
