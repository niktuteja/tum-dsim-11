package tum.des.homework.simulator;

import java.util.Properties;

public class RandVarExp extends RandVar {
	double lambda = 1.0;

	public RandVarExp(String prefix, Properties p) {
		super(prefix, p);

		String mean = p.getProperty(prefix + ".mean");
		if (mean != null)
			this.lambda = 1 / Double.parseDouble(mean);

		String lambda = p.getProperty(prefix + ".lambda");
		if (lambda != null)
			this.lambda = Double.parseDouble(lambda);

		this.lambda /= getScale();
	}

	public RandVarExp(long mean, Long seed) {
		super(seed);
		this.lambda = 1 / mean;
	}

	@Override
	public long getLong() {
		double uniformRandomNumber = getGenerator().nextDouble();
		long v = (long) (-Math.log(uniformRandomNumber) / lambda);
		//		Log.d("randexp", "value = %d", v);
		return v;
	}
}
