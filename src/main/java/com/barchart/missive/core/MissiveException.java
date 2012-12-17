/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
@SuppressWarnings("serial")
public class MissiveException extends RuntimeException {

	public MissiveException(final String reason) {
		super(reason);
	}
	
	public MissiveException(final Throwable e) {
		super(e);
	}
	
}
