package tum.des.homework.simulator;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.Properties;

import tum.des.homework.simulator.events.EventBase;

public class EventComparatorFactory {

	@SuppressWarnings("unchecked")
	public static Comparator<? extends EventBase> getComparator(String prefix, Properties props)
	{
		Comparator<? extends EventBase> rv = null;
		String comp = props.getProperty(prefix+".comparator");
		if (comp == null)
			return null;
		
		try {
			Class<?> clazz = Class.forName("tum.des.homework.simulator.events."+comp+"Comparator");
			Constructor<?> constructor = clazz.getConstructor();
			rv = (Comparator<? extends EventBase>) constructor.newInstance();
		} catch (Exception e) {
			return null;
		}
		
		return rv;
	}
}
