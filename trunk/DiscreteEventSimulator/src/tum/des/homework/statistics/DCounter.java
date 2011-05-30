package tum.des.homework.statistics;

public class DCounter extends Counter {

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
