/**
 * CounterCollection class
 * Consists of Counters and Histograms
 * 
 * @author Alexander Klein
 * @version 1.0.0 
 * @since 2005-11-06
 */
public class CounterCollection
{
	/**
	 * Refer the CounterCollection constructer for attribute abbreviations
	 */
	
	public DiscreteCounter		dc_cwt;
	public DiscreteCounter		dc_cst;
	public DiscreteCounter		dc_ciat;
	public DiscreteCounter		dc_bp;

	public TDCounter	cc_su;
	public TDCounter	cc_qo;

	/**
	 * Example of how to use the trace object
	 */
	//public Trace traceExample = new Trace ("example");

	/**
	 * Attribute: static CounterCollection c 
	 * Events in the EventChain use this public static CounterCollection 
	 */
	public static CounterCollection c;
	/**
	 * Constructor initializes all counters and histograms
	 */
	public CounterCollection ()
	{
		//
		dc_cwt = new DiscreteCounter  ("waiting time per customer");
		dc_cst = new DiscreteCounter  ("service time per customer");
		dc_bp  = new DiscreteCounter  ("blocking probability");
		dc_ciat = new DiscreteCounter ("customer interarrival time");
		//Time dependant values
		cc_su = new TDCounter           ("server utilization over time");
		cc_qo = new TDCounter           ("queue occupancy over time");
		
	}
	/**
	 * Function calls the reset method of all counters and histograms
	 */
	public void reset()
	{
		dc_cwt.reset();
		dc_cst.reset();
		dc_bp.reset();
		dc_ciat.reset();
		cc_qo.reset();
		cc_su.reset();
	}
	/**
	 * Function calls the reset method of all counters and histograms
	 */
	public void report ()
	{
		dc_cwt.report();
		dc_cst.report();
		dc_bp.report();
		dc_ciat.report();
		cc_su.report();
		cc_qo.report();
	}
}
