package tum.des.homework.simulator;

import java.util.Properties;

import tum.des.homework.util.Log;
import tum.des.homework.util.Log.LogLevel;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiscreteEventSimulator sim = new DiscreteEventSimulator();

		Properties props = Utils.loadProperties("properties.properties");

		if (props.containsKey("log.level"))
			;
		Log.setLevel(LogLevel.valueOf(props.getProperty("log.level")));

		//		LogLevel.valueOf(arg0)

		sim.init(props);

		// start simulation
		sim.start();
	}

}
