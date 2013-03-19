package com.barchart.missive.core2;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.Manifest;
import com.barchart.missive.core.ObjectMap;
import com.barchart.missive.core.ObjectMapFactory;
import com.barchart.missive.core.ObjectMapSafe;
import com.barchart.missive.core.TagFactory;

public class TestMissive {

	public static final Tag<String> BACON = TagFactory.create(String.class);
	public static final Tag<String> HAM = TagFactory.create(String.class);
	public static final Tag<String> EGGS = TagFactory.create(String.class);
	public static final Tag<String> HASHBROWNS = TagFactory.create(String.class);
	
	public static final Tag<?>[] SMALL = new Tag<?>[]{BACON, EGGS};
	public static final Tag<?>[] NORMAL = new Tag<?>[]{HASHBROWNS};
	public static final Tag<?>[] SUPER = new Tag<?>[]{HAM};
	
	static class SmallBreakfast extends ObjectMap {
		
	}
	
	static class SmallBreakfastSafe extends ObjectMapSafe {
		
	}
	
	public static class NormalBreakfast extends SmallBreakfast {
		
	}
	
	public static class NormalBreakfastSafe extends SmallBreakfastSafe {
		
	}
	
	public static class SuperBreakfast extends NormalBreakfast {
		
	}
	
	public static class SuperBreakfastSafe extends NormalBreakfastSafe {
		
	}
	
	@Before
	public void before() {
		
		final Manifest<ObjectMap> manifest = new Manifest<ObjectMap>();
		manifest.put(TestProtected.class, TestMissive.SMALL);
		manifest.put(SmallBreakfast.class, SMALL);
		manifest.put(SmallBreakfastSafe.class, SMALL);
		manifest.put(NormalBreakfast.class, NORMAL);
		manifest.put(NormalBreakfastSafe.class, NORMAL);
		manifest.put(SuperBreakfast.class, SUPER);
		manifest.put(SuperBreakfastSafe.class, SUPER);
		
		ObjectMapFactory.install(manifest);
		
	}
	
	@Test
	public void testMissive() {
		
		/* Ensures we can get an instance of a non-public constructor or class */
		TestProtected testProtected = ObjectMapFactory.build(TestProtected.class);
		
		assertTrue(testProtected.get(BACON).equals("Bacon"));
		
		SuperBreakfast superBfast = ObjectMapFactory.build(SuperBreakfast.class);
		SuperBreakfastSafe superSafe = ObjectMapFactory.build(SuperBreakfastSafe.class);
		
		NormalBreakfast normBfast = ObjectMapFactory.build(NormalBreakfast.class);
		NormalBreakfastSafe normSafe = ObjectMapFactory.build(NormalBreakfastSafe.class);
		
		SmallBreakfast smallBfast = ObjectMapFactory.build(SmallBreakfast.class);
		SmallBreakfastSafe smallSafe = ObjectMapFactory.build(SmallBreakfastSafe.class);
		
		assertTrue(smallBfast.contains(BACON));
		assertTrue(smallBfast.contains(EGGS));
		assertTrue(!smallBfast.contains(HASHBROWNS));
		assertTrue(!smallBfast.contains(HAM));
		
		assertTrue(normBfast.contains(BACON));
		assertTrue(normBfast.contains(EGGS));
		assertTrue(normBfast.contains(HASHBROWNS));
		assertTrue(!normBfast.contains(HAM));
		
		assertTrue(superBfast.contains(BACON));
		assertTrue(superBfast.contains(EGGS));
		assertTrue(superBfast.contains(HASHBROWNS));
		assertTrue(superBfast.contains(HAM));
		
		assertTrue(smallSafe.contains(BACON));
		assertTrue(smallSafe.contains(EGGS));
		assertTrue(!smallSafe.contains(HASHBROWNS));
		assertTrue(!smallSafe.contains(HAM));
		
		assertTrue(normSafe.contains(BACON));
		assertTrue(normSafe.contains(EGGS));
		assertTrue(normSafe.contains(HASHBROWNS));
		assertTrue(!normSafe.contains(HAM));
		
		assertTrue(superSafe.contains(BACON));
		assertTrue(superSafe.contains(EGGS));
		assertTrue(superSafe.contains(HASHBROWNS));
		assertTrue(superSafe.contains(HAM));
		
		smallSafe.set(BACON, "bacon");
		smallSafe.set(EGGS, "eggs");
		
		assertTrue(smallSafe.get(BACON).equals("bacon"));
		assertTrue(smallSafe.get(EGGS).equals("eggs"));
		
	}
	
}
