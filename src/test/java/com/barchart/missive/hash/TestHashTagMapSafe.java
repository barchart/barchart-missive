/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.hash;

import static com.barchart.missive.TestSpec.BOOLEAN1;
import static com.barchart.missive.TestSpec.BOOLEAN1_V;
import static com.barchart.missive.TestSpec.BYTE;
import static com.barchart.missive.TestSpec.BYTE_V;
import static com.barchart.missive.TestSpec.CHARACTER;
import static com.barchart.missive.TestSpec.CHARACTER_V;
import static com.barchart.missive.TestSpec.DOUBLE;
import static com.barchart.missive.TestSpec.DOUBLE_V;
import static com.barchart.missive.TestSpec.ENUM;
import static com.barchart.missive.TestSpec.ENUM_V;
import static com.barchart.missive.TestSpec.FLOAT;
import static com.barchart.missive.TestSpec.FLOAT_V;
import static com.barchart.missive.TestSpec.INTEGER;
import static com.barchart.missive.TestSpec.INTEGER_V;
import static com.barchart.missive.TestSpec.LONG;
import static com.barchart.missive.TestSpec.LONG_V;
import static com.barchart.missive.TestSpec.SHORT;
import static com.barchart.missive.TestSpec.SHORT_V;
import static com.barchart.missive.TestSpec.STRING;
import static com.barchart.missive.TestSpec.STRING_V;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.barchart.missive.ImplementationTests;
import com.barchart.missive.TestSpec;
import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagMapSafe;

public class TestHashTagMapSafe {
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test() {
		
		Map<Tag, Object> map = new HashMap<Tag, Object>();
		
		map.put(ENUM, ENUM_V);
		map.put(BYTE, BYTE_V);
		map.put(SHORT, SHORT_V);
		map.put(INTEGER, INTEGER_V);
		map.put(LONG, LONG_V);
		map.put(FLOAT, FLOAT_V);
		map.put(DOUBLE, DOUBLE_V);
		map.put(BOOLEAN1, BOOLEAN1_V);
		map.put(CHARACTER, CHARACTER_V);
		map.put(STRING, STRING_V);
		
		TagMapSafe tmap1 = new HashTagMapSafe(map);
		
		ImplementationTests.testTagMap(tmap1);
		
		TagMapSafe tmap2 = new HashTagMapSafe(TestSpec.TAGS);
		tmap2.set(ENUM, ENUM_V);
		tmap2.set(BYTE, BYTE_V);
		tmap2.set(SHORT, SHORT_V);
		tmap2.set(INTEGER, INTEGER_V);
		tmap2.set(LONG, LONG_V);
		tmap2.set(FLOAT, FLOAT_V);
		tmap2.set(DOUBLE, DOUBLE_V);
		tmap2.set(BOOLEAN1, BOOLEAN1_V);
		tmap2.set(CHARACTER, CHARACTER_V);
		tmap2.set(STRING, STRING_V);
		
		ImplementationTests.testTagMap(tmap2);
		
	}

}
