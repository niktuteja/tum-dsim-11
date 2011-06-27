package RandVar;

public class Rng {
	public static final long serialVersionUID = 0;
	
	/** standard LCG parameters; used in the formula  z_n = (a * z_(n-1) + c) % m; */
	protected long a, c, z, m;
	
	public Rng() {
		setParameters(1, 16807, 0, 1 << 31 - 1);
	}
	
	public Rng(long seed, long _a, long _c, long _m) {
		// seed == z0
		setParameters(seed, _a, _c, _m);
	}

	public double nextDouble() {
		// the correct formula follows
		return getRV() / (double)(m-1);
	}

	public double getRV() {
		z = (a * z + c) % m;
		return z;
	}

	public void setParameters(long seed, long _a, long _c, long size) {
		z = seed;
		a = _a;
		c = _c;
		m = size;
		
		/* ensure that z is in range */
		z = z % m;
	}
	
	public String toString() {
		return "LCG a=" + a + ", c=" + c + ", m=" + m;
	}
}
