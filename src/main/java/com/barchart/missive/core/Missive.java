package com.barchart.missive.core;


/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface Missive extends SafeTagMap {

	public boolean isSubsetOf(final Missive m);
	
	public <M extends Missive> M cast(M m);
	
}
