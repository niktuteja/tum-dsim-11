package tum.des.homework.simulator;

import java.util.Random;

public class RandVarExp {
	long mean;
	Random randomNumberGenerator;

	public RandVarExp(long mean, long seed) {
		this.mean = mean;
		this.randomNumberGenerator = new Random(seed);
	}

	public long getLong() {
		double uniformRandomNumber = randomNumberGenerator.nextDouble();
		return (long) ((-Math.log(uniformRandomNumber)) / (1.0 / mean));
	}
}
