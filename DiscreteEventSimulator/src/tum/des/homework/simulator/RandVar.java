package tum.des.homework.simulator;

import java.util.Properties;
import java.util.Random;

public abstract class RandVar {
	private Random randomNumberGenerator;
	
	protected RandVar(String prefix, Properties p)
	{
		String seed = p.getProperty(prefix+".seed", null);
		if (seed == null)
			this.randomNumberGenerator = new Random();
		else
			this.randomNumberGenerator = new Random(Long.parseLong(seed));
	}
	
	protected RandVar(Long seed)
	{
		if (seed != null)
			this.randomNumberGenerator = new Random(seed);
		else
			this.randomNumberGenerator = new Random();
	}
	
	
	Random getGenerator()
	{
		return randomNumberGenerator;
	}
	
	abstract public long getLong();
}
