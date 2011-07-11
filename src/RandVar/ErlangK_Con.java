package RandVar;
/**
 * ErlangK class
 * Generates ErlangK distributed samples via convolution
 */
public class ErlangK_Con extends ErlangK {
	public Exponential exp;
	
	public ErlangK_Con(int k, double lambda)
	{
		super(k, lambda);
		exp = new Exponential(lambda);
	}
	
	public double getRV ()	{
		double sum = 0;
		
		for (int i = 0; i < k; i++)
			sum = sum + exp.getRV();
		
		return sum;
		
    }
}
