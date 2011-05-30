package tum.des.homework.simulator;

import java.util.Random;

public class RandVarUni {
	long min;
	long max;
	Random randomNumberGenerator;

	public RandVarUni(long min, long max, long seed) {
		this.min = min;
		this.max = max;
		this.randomNumberGenerator = new Random(seed);
	}

	public long getLong() {
		return Utils.getRandomNumberBetween(min, max);
	}
}
