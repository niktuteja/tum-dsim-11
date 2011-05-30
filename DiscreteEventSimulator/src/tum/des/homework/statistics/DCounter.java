package tum.des.homework.statistics;

public class DCounter extends Counter {
	long numSamples; // the number of samples taken

	@Override
	void count(double value) {
		min = value < min ? value : min;
		max = value > max ? value : max;

		sumPowerOne += value;
	}

	@Override
	double getMean() {
		return sumPowerOne / numSamples;
	}
}
