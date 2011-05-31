package tum.des.homework.simulator;

import java.util.Properties;

public class RandVarUni extends RandVar {
	long min;
	long max;
	

	public RandVarUni(String prefix, Properties p)
	{
		super(prefix, p);
		this.min = getScale()*Long.parseLong(p.getProperty(prefix+".min", "0"));
		this.max = getScale()*Long.parseLong(p.getProperty(prefix+".max", "1"));
	}
	
	public RandVarUni(long min, long max) {
		this(min, max, null);
	}
	
	public RandVarUni(long min, long max, Long seed) {
		super(seed);
		this.min = min;
		this.max = max;
	}

	public long getLong() {
		return min + (long)(getGenerator().nextDouble() * (1 + max - min));
	}
}
