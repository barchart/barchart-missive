package com.barchart.missive.api;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestLexicon {
	
	public static final Tag<Byte> BYTE = new Tag<Byte>("BYTE", Byte.class);
	public static final Tag<Short> SHORT = new Tag<Short>("SHORT", Short.class);
	public static final Tag<Integer> INTEGER = new Tag<Integer>("INTEGER", Integer.class);
	public static final Tag<Long> LONG = new Tag<Long>("LONG", Long.class);
	public static final Tag<Float> FLOAT = new Tag<Float>("FLOAT", Float.class);
	public static final Tag<Double> DOUBLE = new Tag<Double>("DOUBLD", Double.class);
	public static final Tag<Boolean> BOOLEAN1 = new Tag<Boolean>("BOOLEAN1", Boolean.class);
	public static final Tag<Boolean> BOOLEAN2 = new Tag<Boolean>("BOOLEAN2", Boolean.class);
	public static final Tag<Boolean> BOOLEAN3 = new Tag<Boolean>("BOOLEAN3", Boolean.class);
	public static final Tag<Character> CHARACTER = new Tag<Character>("CHARACTER", Character.class);
	
	public static final Manifest TestPrims = new Manifest("TestPrims", new Tag<?>[]{
		BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN1, BOOLEAN2, BOOLEAN3, CHARACTER	
	});
	
	@Test
	public void testPrimitiveParsing() {
		
		final Map<Integer, Object> data = new HashMap<Integer, Object>();
		data.put(BYTE.getName().hashCode(), "0");
		data.put(SHORT.getName().hashCode(), "0");
		data.put(INTEGER.getName().hashCode(), "0");
		data.put(LONG.getName().hashCode(), "0");
		data.put(FLOAT.getName().hashCode(), "0.0");
		data.put(DOUBLE.getName().hashCode(), "0.0");
		data.put(BOOLEAN1.getName().hashCode(), "true");
		data.put(BOOLEAN2.getName().hashCode(), "Y");
		data.put(BOOLEAN3.getName().hashCode(), "false");
		data.put(CHARACTER.getName().hashCode(), "X");
		
		final Raw testRaw = new Raw();
		
		final Map<Integer, Tag<?>> tagMap = new HashMap<Integer, Tag<?>>();
		for(final Tag<?> tag : TestPrims.getTags()) {
			tagMap.put(tag.getName().hashCode(), tag);
		}
		
		final Map<String, Manifest> manifestMap = new HashMap<String, Manifest>();
		manifestMap.put(TestPrims.name(), TestPrims);
		
//		final Lexicon testLexi = new Lexicon(tagMap);
//		
//		final Missive testMsv = testLexi.toMissive(testRaw);
//		
//		assertTrue(testMsv.get(BYTE) == 0);
//		assertTrue(testMsv.get(SHORT) == 0);
//		assertTrue(testMsv.get(INTEGER) == 0);
//		assertTrue(testMsv.get(LONG) == 0);
//		assertTrue(testMsv.get(FLOAT) == 0.0);
//		assertTrue(testMsv.get(DOUBLE) == 0.0);
//		assertTrue(testMsv.get(BOOLEAN1) == true);
//		assertTrue(testMsv.get(BOOLEAN2) == true);
//		assertTrue(testMsv.get(BOOLEAN3) == false);
//		assertTrue(testMsv.get(CHARACTER) == 'X');
//		
	}

}
