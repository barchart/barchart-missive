package com.barchart.missive.core;


/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface Missive extends SafeTagMap {

	boolean isSubsetOf(final Missive m);
	
	<M extends Missive> M cast(M m);
	
}
