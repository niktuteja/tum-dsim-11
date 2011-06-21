package Analysis;

import Simulator.SimState;

public class TerminationTDCounter extends TDCounter {
	
	private double lastMean = Double.NaN;

	public TerminationTDCounter(String string) {
	    super(string);
		 
	}
	
	@Override
	public void count(double x) {
		double tmp = SimState.s.now - lastSampleTime;
		super.count(x);
		
		if (SimState.s.deltaSteadyState < 0)
			return;
		
		if (tmp == 0) // false results otherwise
			return;
		
		double curMean = getMean();
		
		if (lastMean != Double.NaN)
		{
			if (SimState.s.now >= SimState.s.endTransientPhase 					
					&& Math.abs(curMean - lastMean) < SimState.s.deltaSteadyState)
			{
				SimState.s.stop = true;
			}
		}
		
		lastMean = curMean;
	}

}
