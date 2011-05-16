package tum.des.homework.simulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {

	public static Properties loadProperties(String path){
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
	
	
	public static int getRandomNumberBetween(int start, int end){
		return start + (int)Math.random() * end;
	}
	
	public static long secondsToTicks(long seconds, long resolution){
		return seconds * resolution;
	}
	
	public static long ticksToSeconds(long ticks, long resolution){
		return ticks * resolution;
	}
}
