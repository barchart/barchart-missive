package com.barchart.missive.core;

import java.util.HashMap;
import java.util.Map;

/*
 * Results 100,000 instances = 35MB
 * Results 1,000,000 instances = 204MB
 * Results 10,000,000 instances = 1,832 MB
 */
public class MissiveMemoryTest {
	
	public static final int TEST_SIZE = 10 * 1000 * 1000;
	
	public static final Tag<Long> TAG1 = Tag.create(Long.class);
	public static final Tag<Long> TAG2 = Tag.create(Long.class);
	public static final Tag<Long> TAG3 = Tag.create(Long.class);
	public static final Tag<Long> TAG4 = Tag.create(Long.class);
	public static final Tag<Long> TAG5 = Tag.create(Long.class);
	public static final Tag<Long> TAG6 = Tag.create(Long.class);
	public static final Tag<Long> TAG7 = Tag.create(Long.class);
	public static final Tag<Long> TAG8 = Tag.create(Long.class);
	public static final Tag<Long> TAG9 = Tag.create(Long.class);
	public static final Tag<Long> TAG10 = Tag.create(Long.class);
	public static final Tag<Long> TAG11 = Tag.create(Long.class);
	public static final Tag<Long> TAG12 = Tag.create(Long.class);
	public static final Tag<Long> TAG13 = Tag.create(Long.class);
	public static final Tag<Long> TAG14 = Tag.create(Long.class);
	public static final Tag<Long> TAG15 = Tag.create(Long.class);
	public static final Tag<Long> TAG16 = Tag.create(Long.class);
	public static final Tag<Long> TAG17 = Tag.create(Long.class);
	public static final Tag<Long> TAG18 = Tag.create(Long.class);
	public static final Tag<Long> TAG19 = Tag.create(Long.class);
	public static final Tag<Long> TAG20 = Tag.create(Long.class);

	public static final Tag<?>[] TAGS = Tag.collectTop(MissiveMemoryTest.class);
	
	public static class TestMissive extends Missive {
		
		static {
			install(TAGS);
		}
		
	}
	
	public static final Map<Integer, Missive> map = 
			new HashMap<Integer, Missive>();
	
	
	public static void main(final String[] args) {
		
		for(int i = 0; i < TEST_SIZE; i++) {
			map.put(i, Missive.build(TestMissive.class));
		}
		
		System.out.println("Map built");
		
		while(true) {
			// Check visualvm
		}
		
	}
	
}
