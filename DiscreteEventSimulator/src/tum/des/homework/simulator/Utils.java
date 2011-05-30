package tum.des.homework.simulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {

	public static Properties loadProperties(String path) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props;
	}

	public static long getRandomNumberBetween(long l, long m) {
		return l + (long) (Math.random() * (m - l));
	}

	public static long secondsToTicks(long seconds, SimulationState state) {
		return seconds * state.getResolution();
	}

	public static long ticksToSeconds(long ticks, long resolution) {
		return ticks * resolution;
	}

	public static long secondsToTicks(double seconds, SimulationState state) {
		return (long)(seconds * state.getResolution());
	}
}
