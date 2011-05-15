package tum.des.homework.simulator;

public class DiscreteEventSimulator {

	// flag for determining if the machine is (or should be) stopped.
	private boolean stopped = false;

	/**
	 * Starts the simulation.
	 */
	public void start() {
		while (true) {
			if (stopped == true)
				break;
			
			// TODO get next event
			
			// TODO check for termination event, if so break
			
			// TODO execute event
		}
	}
	
	/**
	 * Stops the simulation
	 */
	public void stop() {
		this.stopped = true;
	}

}
