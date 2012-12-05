package com.barchart.missive.hash;

import static com.barchart.missive.TestSpec.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.core.Missive;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class TestHashMissive {

	@Test
	public void test() {
		
		final Missive m = new HashMissive(TAGS);
		final Missive mext = new HashMissive(TAGSEXT);
		
		/* Test subset */
		assertTrue(mext.isSupersetOf(m));
		
		/* Test cast */
		
		
	}
	
}
