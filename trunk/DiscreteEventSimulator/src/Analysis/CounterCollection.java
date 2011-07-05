package Analysis;

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
	 * Refer the CounterCollection constructor for attribute abbreviations
	 */
	
	public DiscreteCounter		dc_cwt;
	public DiscreteCounter		dc_cst;
	public DiscreteCounter		dc_ciat;
	public DiscreteCounter		dc_bp;
	public DiscreteCounter		dc_uc;
	public DiscreteCounter		dc_sc;

	public TDCounter	cc_su;
	public TDCounter	cc_qo;
	
	public DiscreteHistogram	dh_cwt;
	public ContinuousHistogram	ch_qo;

	/**
	 * Attribute: static CounterCollection c 
	 * Events in the EventChain use this public static CounterCollection 
	 */
	public static CounterCollection cc;
	/**
	 * Constructor initializes all counters and histograms
	 */
	public CounterCollection ()
	{
		//Time independent values
		dc_cwt = new DiscreteCounter  ("waiting time per customer");
		dc_cst = new DiscreteCounter  ("service time per customer");
		dc_ciat = new DiscreteCounter ("customer interarrival time");
		dc_bp  = new DiscreteCounter  ("blocking probability");
		dc_uc  = new DiscreteCounter  ("unsatisfied customer");
		dc_sc  = new DiscreteCounter  ("satisfied customer");
		
		//Time dependent values
		cc_su = new TDCounter           ("server utilization over time");
		cc_qo = new TDCounter           ("queue occupancy over time");
		
		//Histograms
		dh_cwt = new DiscreteHistogram ("waiting time per customer",0,10);
		dh_cwt.setupNumIntervals(100);
		ch_qo = new ContinuousHistogram("queue occupancy over time",0,100);
		ch_qo.setupNumIntervals(100);

		
	}
	/**
	 * Function calls the reset method of all counters and histograms
	 */
	public void reset()
	{
		dc_cwt.reset();
		dc_cst.reset();
		dc_bp.reset();
		dc_uc.reset();
		dc_sc.reset();
		dc_ciat.reset();
		cc_qo.reset();
		cc_su.reset();
		
		dh_cwt.reset();
		ch_qo.reset();
	}
	/**
	 * Function calls the reset method of all counters and histograms
	 */
	public void report ()
	{
		dc_cwt.report();
		dc_cst.report();
		dc_ciat.report();
		dc_bp.report();
		dc_uc.report();
		dc_sc.report();
		cc_su.report();
		cc_qo.report();
		
		dh_cwt.report();
		ch_qo.report();
	}
}
