package tum.des.homework.simulator;

import javax.tools.JavaCompiler;
import java.util.Random;
import java.lang.Math;

public class RandVar {
    long mean;
    Random randomNumberGenerator;

    public RandVar(long mean, long seed) {
        this.mean = mean;
        this.randomNumberGenerator = new Random(seed);
    }

    public long getLong() {
        double uniformRandomNumber = randomNumberGenerator.nextDouble();
        return (long) ((-Math.log(uniformRandomNumber)) / (1.0/mean));
    }
}
