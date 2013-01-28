package com.barchart.missive.hash;

import static com.barchart.missive.TestSpec.*;
import static com.barchart.missive.TestSpec.BYTE;
import static com.barchart.missive.TestSpec.CHARACTER;
import static com.barchart.missive.TestSpec.DOUBLE;
import static com.barchart.missive.TestSpec.ENUM;
import static com.barchart.missive.TestSpec.FLOAT;
import static com.barchart.missive.TestSpec.INTEGER;
import static com.barchart.missive.TestSpec.LONG;
import static com.barchart.missive.TestSpec.SHORT;
import static com.barchart.missive.TestSpec.STRING;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.barchart.missive.ImplementationTests;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMap;

public class TestHashTagMap {
	
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
		
		TagMap tmap = new HashTagMap(map);
		
		ImplementationTests.testTagMap(tmap);
		
	}

}
