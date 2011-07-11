package Analysis;
/**
 * Abstract Histogram class
 * Basic class for all histograms
 * Defining interface and functions of histograms
 * 
 * @author Alexander Klein
 * @version 1.0.1
 * @since 2011-06-26
 */
import java.util.Locale;
import java.util.Vector;
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public abstract class  Histogram
{
	/**
	 * Attribute: Values below this value increment the "first" interval
	 */
	double lowerBound;
	/**
	 * Attribute: Values greater than this value increment the "last" interval
	 */
	double upperBound;
	/**
	 * Attribute: Interval size
	 */
	double delta;
	/**
	 * Attribute: Number of intervals
	 */
	int numIntervals;
	/**
	 * Attribute: Vector that stores the histogram data
	 */
	Vector<Number> bins;
	/**
	 * Attribute: name of the observed variable
	 */
	String observedVariable;
	/**
	 * Attribute: String representing the type of the histogram
	 */
	String type;
	/**
	 * Attribute: print format of doubles
	 */
	DecimalFormat df = new DecimalFormat( "0.000", new DecimalFormatSymbols(Locale.ENGLISH) );
	/**
	 * file output object
	 */
	protected PrintWriter file;
	/**
	 * Constructor used by extending classes to initialize the histogram 
	 *@param oVariable name of the observed variable
	 *@param lb lower bound of the histogram
	 *@param uB upper bound of the histogram
	 */
	public Histogram (String oVariable, double lB, double uB)
	{
		observedVariable = oVariable;
		lowerBound = lB;
		upperBound = uB;
		bins = new Vector<Number>();
		type = "This is the Base class";
	}
	/**
	 * Method initializes the histogram by preparing the vector for counting
	 */
	public void reset()
	{
		for(int i = 0; i < numIntervals; i++)
		{
			bins.set(i, new Double (0));
		}
		file = null;
	}
	/**
	 * Function returns the number of the interval that has to be incremented
	 *@param x the value to count
	 *@return the index of the interval
	 */
	public int getBinNumber (double x) 
	{
		if ( x < lowerBound )
		{
			return 0;
		}
		else if ( x >= upperBound)
		{
			return numIntervals-1;
		}
		else
		{
			return ((int) (Math.floor ( (x - lowerBound) / delta)) );
		}
	}
	/**
	 * Function sets the number of intervals to the value of the given argument i and
	 * resets the vector.
	 *@param i the new number of intervals of the histogram
	 */
	public void setupNumIntervals (int i)
	{
		numIntervals = i;
		bins.setSize(numIntervals);
		delta = (upperBound - lowerBound) / numIntervals;
		reset();
	}
	/**
	 * Abstract function count(double x) that has to be implemented by extending classes
	 */
	// public abstract void count (double x);
	/**
	 * Abstract function divisor() that has to be implemented by extending classes
	 */
	public abstract double divisor ();
	/**
	 * Function tries to write the counted values to three files. Filename is the name of the
	 * observed variable + extension.
	 *
	 * The first file with extension ".hist" represents the usual histogram.
	 * The second file with extension ".epdf" represents the probability density function.
	 * The third file with extension ".dist" represents the distribution.
	 *@throws Exception if the files can not be created or overwritten.
	 */
	public void fileReport() 
	throws Exception
	{
		String tmp = observedVariable;
		tmp+="_";
		tmp+=type;
		String sHist=tmp;
		String sPDF=tmp;
		String sDist=tmp;

		sHist+=(".hist.csv");
		sPDF +=(".epdf.csv");
		sDist+=(".dist.csv");

		File fhist = new File(sHist);
		File fpdf = new File(sPDF);
		File fdist = new File(sDist);

		fhist.delete();
		fpdf.delete();
		fdist.delete();

		fhist.createNewFile();
		fpdf.createNewFile();
		fdist.createNewFile();

		FileOutputStream oHist = new FileOutputStream (fhist); 
		FileOutputStream oPDF = new FileOutputStream (fpdf); 
		FileOutputStream oDist = new FileOutputStream (fdist); 

		oHist.write(("[lower bound; , upper bound) , relative frequency\n").getBytes());
		oPDF.write(("[lower bound; , upper bound) , probability density\n").getBytes());
		oDist.write(("(lower bound+upper bound)/2 , probability\n").getBytes());

		for(int i = 0; i < numIntervals; i++)
		{
			oHist.write((""+ String.valueOf(lowerBound+i*delta) + " , " + String.valueOf(lowerBound+ (i+1)*delta) + " , " + bins.get(i) + "\n").getBytes());
			oPDF.write((""+ String.valueOf(lowerBound+i*delta) + " , " + String.valueOf(lowerBound+ (i+1)*delta) +" , " + String.valueOf( ((((Double) bins.get(i)).doubleValue()) /divisor())/delta) + "\n").getBytes());
			oDist.write(("" + String.valueOf(lowerBound+(i+0.5)*delta) + " , " + String.valueOf(Double.valueOf(((bins.get(i)).toString()))/divisor()) + "\n").getBytes());
		}

		oHist.close();
		oPDF.close();
		oDist.close();
	}
	
	/**
	 * Function writes the histogram, the probability density function and the distribution to the console instead of writting
	 * the values to files.
	 */
	public void report() 
	{
		String tmp = "\n"+observedVariable;
		tmp+="_";
		tmp+=type;
		
		// print relative frequency histogram to console
		System.out.println(tmp);
		System.out.println("[lower bound ; upper bound[ , relative frequency");
		for(int i = 0; i < numIntervals; i++)
		{
			System.out.println("[" + df.format(lowerBound+i*delta) + " ; " + 
					df.format(lowerBound+ (i+1)*delta) + "[" + " , " + bins.get(i));
		}
		
		// print probability density histogram to console
		System.out.println(tmp);
		System.out.println("[lower bound ; upper bound[ ,  probability density");
		for(int i = 0; i < numIntervals; i++)
		{
			double value = bins.get(i).doubleValue() / divisor() / delta;
			System.out.println("[" +(df.format(lowerBound+i*delta) + " ; " + df.format(lowerBound+ (i+1)*delta) + "["+" , " 
					+ df.format(value)));
		}

		// print probability histogram to console
		System.out.println(tmp);
		System.out.println("value , probability");
		for(int i = 0; i < numIntervals; i++)
		{
			double value = bins.get(i).doubleValue() /divisor();
			System.out.println(df.format(lowerBound+(i+0.5)*delta) + " , " +  df.format(value));
		}
		
		// print probability histogram to console
		System.out.println(tmp);
		System.out.println("value , cumulative distribution function");
		double temp = 0;
		for(int i = 0; i < numIntervals; i++)
		{
			temp = temp + (((Double) bins.get(i)).doubleValue()) /divisor();
			System.out.println(df.format(lowerBound+(i+0.5)*delta) + " , " + df.format(temp) );
		}
	}
	/**
	 * Writes the value to a text file
	 *@param x the value to write
	 */
	public void write (double x)
	{
		file.print(x + " ");
	}
	/**
	 * Sets up the PrintWriter for file output or removes it
	 */
	public boolean enableWrite(boolean enabled)
	{
		if (enabled) {
			String filename = this.getClass().getSimpleName() + "_" + observedVariable + ".txt";
			try {
				file = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
			} catch (IOException ex) {
				file = null;
				return false;
			}
			
			return true;
		} else {
			if (file != null)
				file.close();
			file = null;
			return true;
		}
	}
}