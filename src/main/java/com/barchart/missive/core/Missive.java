/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;


/**
 * The Missive interface extends to a tag map the notion of a specific type.
 * A missive is defined by the tags for which containsTag returns true.  However,
 * a missive may also store tag value pairs which are not accessible.  If the 
 * missive has a subclass which does make visible these fields they can be accessed
 * by calling castAsSubclass.     
 * 
 * @author Gavin M Litchfield
 *
 */
public interface Missive extends SafeTagMap {

	public <M extends Missive> boolean isCastableTo(M m);
	
	/**
	 * If M is a subclass of the current missive, and the missive has the required extra 
	 * fields, then this method will return m with the appropriate value fields visible.  
	 * Otherwise a MissiveException will be thrown.
	 * 
	 * @param m
	 * @return
	 */
	public <M extends Missive> M castAsSubclass(M m);
	
}
