package com.barchart.missive.api;

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
