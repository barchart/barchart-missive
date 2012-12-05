package com.barchart.missive.core;


/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface Missive extends SafeTagMap {

	/*
	 * Strict or not?
	 * 
	 * Only consider visible fields?
	 */
	public boolean isSupersetOf(final Missive m); 
	
	//public boolean isSubsetOf(final Missive m); ???
	
	public <M extends Missive> M cast(M m);
	
}
