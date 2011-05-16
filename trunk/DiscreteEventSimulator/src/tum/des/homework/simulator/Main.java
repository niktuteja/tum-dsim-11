package tum.des.homework.simulator;

import tum.des.homework.simulator.Utils;
import java.util.Properties;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiscreteEventSimulator sim = new DiscreteEventSimulator();
		
		Properties props = Utils.loadProperties("properties.properties");
		
		sim.init(props);
		
		// start simulation
		sim.start();
	}
	
}
