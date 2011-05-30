package tum.des.homework.statistics;

public class DCounter extends Counter {

	@Override
	void count(double value) {
		min = value < min ? value : min;
		max = value > max ? value : max;

		sumPowerOne += value;
		numSamples++;
	}

	@Override
	double getMean() {
		return sumPowerOne / numSamples;
	}
}
