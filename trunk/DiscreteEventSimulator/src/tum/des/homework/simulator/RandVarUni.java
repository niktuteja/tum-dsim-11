package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.util.Log;

public class RandVarUni extends RandVar {
	long min;
	long max;
	

	public RandVarUni(String prefix, Properties p)
	{
		super(prefix, p);
		this.min = (long)(getScale()*Long.parseLong(p.getProperty(prefix+".min", "0")));
		this.max = (long)(getScale()*Long.parseLong(p.getProperty(prefix+".max", "1")));
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
		long v = min + (long)(getGenerator().nextDouble() * (1 + max - min));
		Log.v("RandVarUni", "%d", v);
		return v;
	}
}
