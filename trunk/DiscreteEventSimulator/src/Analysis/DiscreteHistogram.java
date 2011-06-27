package Analysis;

public class DiscreteHistogram extends Histogram {

	public DiscreteHistogram(String oVariable, double lB, double uB, int numIntervals) {
		super(oVariable, lB, uB);
		
		setupNumIntervals(numIntervals);
	}

	@Override
	public void count(double x) {
		int binNumber = getBinNumber(x);
		bins.set(binNumber, bins.get(binNumber).doubleValue() + 1);
		numObservedVariables++;
	}

	@Override
	public double divisor() {
		return numObservedVariables;
	}

}
