package tum.des.homework.simulator;

import java.lang.reflect.Constructor;
import java.util.Properties;

public class DistributionFactory {

	public static RandVar getDistribution(String prefix, Properties props)
	{
		RandVar rv = null;
		String dist = props.getProperty(prefix+".dist");
		if (dist == null)
			throw new IllegalArgumentException("Prop: " + prefix+".dist" + " not set");
		
		try {
			Class<?> clazz = Class.forName("tum.des.homework.simulator.RandVar"+dist);
			Constructor<?> constructor = clazz.getConstructor(String.class, Properties.class);
			rv = (RandVar) constructor.newInstance(prefix, props);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		return rv;
	}
}
