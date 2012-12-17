/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
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
		
		/* Test cast */
		
		
	}
	
}
