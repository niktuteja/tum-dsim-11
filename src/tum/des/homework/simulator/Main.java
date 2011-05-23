package tum.des.homework.simulator;

import java.util.Properties;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiscreteEventSimulator sim = DiscreteEventSimulator.getInstance();

		Properties props = Utils.loadProperties("properties.properties");

		sim.init(props);

		// start simulation
		sim.start();
	}

}
