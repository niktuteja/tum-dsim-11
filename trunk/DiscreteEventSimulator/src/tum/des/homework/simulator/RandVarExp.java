package tum.des.homework.simulator;

import java.util.Properties;

public class RandVarExp extends RandVar {
	double mean;

	RandVarExp(String prefix, Properties p)
	{
		super(prefix, p);
		this.mean = getScale()*Double.parseDouble(p.getProperty(prefix+".mean"));
		
		String lambda = p.getProperty(prefix+".lambda");
		if (lambda != null)
			this.mean = 1/(getScale()*Double.parseDouble(lambda));
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
