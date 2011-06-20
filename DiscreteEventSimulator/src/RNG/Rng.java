package RNG;

public class Rng {
	long a; // multiplier
	long z_0; // seed
	long c; // increment
	long m; // modulus

	long z_new; // z_i
	long z_old; // z_i-1

	public Rng(long a, long c, long z_0, long m) {
		this.a = a;
		this.c = c;
		this.z_0 = z_0;
		this.m = m;

		this.z_old = z_0;
	}

	public Rng() {
		this(16807, 0, 1, 2147483647); // m = 2^31 - 1
	}

	public Rng(long seed) {
		this();
		this.z_0 = seed;
	}

	public double nextDouble() {
		z_new = (a * z_old + c) % m;
		z_old = z_new;
		return (double) z_new / (double) (m - 1);
	}

	@Override
	public String toString() {
		return String.format("Rng<a=%d, c=%d, z_0=%d, m=%d>", a, c, z_0, m);
	}

	public double autoCorrelation(int lag) {
		if (lag > 10) {
			return 0;
		}

		return 1;
	}

}
