package tum.des.homework.simulator;

import java.util.Properties;
import java.util.Random;

public abstract class RandVar {
	private Random randomNumberGenerator;
	private double scale = 1;
	
	protected RandVar(String prefix, Properties p)
	{
		String seed = p.getProperty(prefix+".seed", null);
		if (seed == null)
			this.randomNumberGenerator = new Random();
		else
			this.randomNumberGenerator = new Random(Long.parseLong(seed));
		
		String scale = p.getProperty(prefix+".scale", null);
		if (scale != null)
		{
			if (scale.charAt(0) == '@')
				scale = p.getProperty(scale.substring(1), null);
			
			if (scale != null)
				this.scale = Double.parseDouble(scale);
		}
			
	}
	
	protected RandVar(Long seed)
	{
		if (seed != null)
			this.randomNumberGenerator = new Random(seed);
		else
			this.randomNumberGenerator = new Random();
	}
	

	double getScale() {
		return scale;
	}
	
	
	Random getGenerator()
	{
		return randomNumberGenerator;
	}
	
	abstract public long getLong();
}
