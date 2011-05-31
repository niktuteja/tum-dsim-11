package tum.des.homework.simulator;

import java.util.Properties;

public class RandVarExp extends RandVar {
	long mean;

	RandVarExp(String prefix, Properties p)
	{
		super(prefix, p);
		this.mean = Long.parseLong(p.getProperty(prefix+".mean"));
	}
	
	public RandVarExp(long mean, Long seed) {
		super(seed);
		this.mean = mean;
	}

	public long getLong() {
		double uniformRandomNumber = getGenerator().nextDouble();
		return (long) ((-Math.log(uniformRandomNumber)) / (1.0 / mean));
	}
}
