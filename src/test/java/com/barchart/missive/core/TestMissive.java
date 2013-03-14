package com.barchart.missive.core;

import static com.barchart.missive.core.TestSpec.BOOLEAN1;
import static com.barchart.missive.core.TestSpec.BOOLEAN1_V;
import static com.barchart.missive.core.TestSpec.ENUM;
import static com.barchart.missive.core.TestSpec.ENUM_V;
import static com.barchart.missive.core.TestSpec.INTEGER;
import static com.barchart.missive.core.TestSpec.INTEGER_V;
import static com.barchart.missive.core.TestSpec.LIST;
import static com.barchart.missive.core.TestSpec.MISSIVE;
import static com.barchart.missive.core.TestSpec.STRING;
import static com.barchart.missive.core.TestSpec.STRING_V;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.barchart.missive.api.Tag;

public class TestMissive {
	
	public static TestSimpleMissive simple1;
	public static TestSimpleMissive simple2;
	public static TestCaseMissive missive1;
	public static TestCaseMissive missive2;
	
	public static final TestList list1 = new TestList();
	public static final TestList list2 = new TestList();
	
	@Before
	public void before() {
		
		final Manifest<ObjectMap> manifest = new Manifest<ObjectMap>();
		manifest.put(TestSimpleMissive.class, 
				new Tag<?>[] {TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1});
		manifest.put(TestCaseMissive.class, new Tag<?>[]{
				TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1,
				TestSpec.STRING, TestSpec.LIST, TestSpec.MISSIVE
		});
		
		ObjectMapFactory.install(manifest);
		
		simple1 = ObjectMapFactory.build(TestSimpleMissive.class);
		simple2 = ObjectMapFactory.build(TestSimpleMissive.class);
		missive1 = ObjectMapFactory.build(TestCaseMissive.class);
		missive2 = ObjectMapFactory.build(TestCaseMissive.class);
		
		list1.add("test1");
		list1.add("test2");
		
		list2.add("test1");
		list2.add("test2");
		
		simple1.set(ENUM, ENUM_V);
		simple1.set(INTEGER, INTEGER_V);
		simple1.set(BOOLEAN1, BOOLEAN1_V);
		
		simple2.set(ENUM, ENUM_V);
		simple2.set(INTEGER, INTEGER_V);
		simple2.set(BOOLEAN1, BOOLEAN1_V);
		
		missive1.set(ENUM, ENUM_V);
		missive1.set(INTEGER, INTEGER_V);
		missive1.set(BOOLEAN1, BOOLEAN1_V);
		missive1.set(STRING, STRING_V);
		missive1.set(LIST, list1);
		missive1.set(MISSIVE, simple1);
		
		missive2.set(ENUM, ENUM_V);
		missive2.set(INTEGER, INTEGER_V);
		missive2.set(BOOLEAN1, BOOLEAN1_V);
		missive2.set(STRING, STRING_V);
		missive2.set(LIST, list2);
		missive2.set(MISSIVE, simple2);
		
	}
	
	@Test
	public void testEquals() {
		
		assertTrue(simple1.equals(simple2));
		assertTrue(simple2.equals(simple1));
		
		assertTrue(missive1.equals(missive2));
		assertTrue(missive2.equals(missive1));
		
		list1.add("test3");
		
		assertFalse(missive1.equals(missive2));
		assertFalse(missive2.equals(missive1));
		
		list1.remove("test3");
		
		assertTrue(simple1.equals(simple2));
		assertTrue(simple2.equals(simple1));
		
		assertTrue(missive1.equals(missive2));
		assertTrue(missive2.equals(missive1));
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testBuild() {
		
		final Map<Tag, Object> map = new HashMap<Tag, Object>();
		
		map.put(ENUM, ENUM_V);
		map.put(INTEGER, INTEGER_V);
		map.put(BOOLEAN1, BOOLEAN1_V);
		
		TestSimpleMissive simple = ObjectMapFactory.build(TestSimpleMissive.class, map);
		
		assertTrue(simple.get(ENUM) == ENUM_V);
		assertTrue(simple.get(INTEGER) == INTEGER_V);
		assertTrue(simple.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(simple.size() == 3);
		
		simple = ObjectMapFactory.build(TestSimpleMissive.class, TestCaseMissive.class, map);
		
		assertTrue(simple.get(ENUM) == ENUM_V);
		assertTrue(simple.get(INTEGER) == INTEGER_V);
		assertTrue(simple.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(simple.size() == 3);
		
		simple = ObjectMapFactory.build(TestSimpleMissive.class, simple1);
		
		assertTrue(simple.get(ENUM) == ENUM_V);
		assertTrue(simple.get(INTEGER) == INTEGER_V);
		assertTrue(simple.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(simple.size() == 3);
		
		simple = ObjectMapFactory.build(TestSimpleMissive.class, TestCaseMissive.class, simple1);
		
		assertTrue(simple.get(ENUM) == ENUM_V);
		assertTrue(simple.get(INTEGER) == INTEGER_V);
		assertTrue(simple.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(simple.size() == 3);
		
		simple = ObjectMapFactory.build(TestSimpleMissive.class, TestCaseMissive.class, missive1);
		
		assertTrue(simple.get(ENUM) == ENUM_V);
		assertTrue(simple.get(INTEGER) == INTEGER_V);
		assertTrue(simple.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(simple.size() == 3);
		
		TestCaseMissive subMissive = simple.cast(TestCaseMissive.class);
		
		assertTrue(subMissive.get(ENUM) == ENUM_V);
		assertTrue(subMissive.get(INTEGER) == INTEGER_V);
		assertTrue(subMissive.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(subMissive.get(STRING).equals(STRING_V));
		assertTrue(subMissive.get(LIST).equals(list1));
		assertTrue(subMissive.get(MISSIVE).equals(simple1));
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
