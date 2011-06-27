import Analysis.*;
import Simulator.*;
import RandVar.*;

/**
 * Exercise 5 Discrete Event Simulation SS 2011
 * 
 * DES05 class Main class performing the simulation.
 * 
 * @author Bader, Dšlle, Pšhlmann
 * @version 1.0.0
 * @since 2011-06-21
 */
public class DES05 {
	/**
	 * main. Application entry point for DES exercise 5
	 * 
	 * @param args
	 *            if no arguments are given, all subsections will run. Otherwise
	 *            give a number from 1 to 3 to indicate which subsection to run.
	 */
	public static void main(String[] args) {
		//Ex 1
		testExercise1a();
		
		exercise1d();
		exercise1e();
	}
	
	private static void exercise1d() {
		RandVar randVar = new Normal(10, 2);
		Histogram hist = new DiscreteHistogram("normal distributed randVar", 0, 20, 100);
		
		for (int i = 0; i < 10e7; i++) {
			hist.count(randVar.getRV());
		}
		
		//Ex 1c !?
		hist.report();
	}

	private static void exercise1e() {
		RandVar randVar = new Geometric(0.1);
		Histogram hist = new DiscreteHistogram("normal distributed randVar", 0, 20, 20);
		
		for (int i = 0; i < 10e7; i++) {
			hist.count(randVar.getRV());
		}
		
		hist.report();
	}
	
	private static void testExercise1a() {
		Histogram hist = new DiscreteHistogram("testVar", 0, 10, 10);
		hist.count(1.5);
		hist.count(1.9);
		hist.count(2.1);
		hist.count(15);
		
		hist.report();
	}
}
