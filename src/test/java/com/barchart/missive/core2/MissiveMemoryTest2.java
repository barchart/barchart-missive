package com.barchart.missive.core2;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.Missive;
import com.barchart.missive.core.MissiveSafe;
import com.barchart.missive.core.TagFactory;
import com.barchart.missive.core2.MissiveMemoryTest.TestMissive;

public class MissiveMemoryTest2 {
	
	public static final int TEST_SIZE = 1 * 1000 * 1000;

	public static final Tag<Long> TAG1 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG2 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG3 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG4 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG5 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG6 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG7 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG8 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG9 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG10 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG11 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG12 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG13 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG14 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG15 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG16 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG17 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG18 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG19 = TagFactory.create(Long.class);
	public static final Tag<Long> TAG20 = TagFactory.create(Long.class);

	public static final Tag<?>[] TAGS = TagFactory.collectTop(MissiveMemoryTest.class);

	public static class TestMissive extends MissiveSafe {

		static {
			install(TAGS);
		}

	}
	
	public static final Map<Integer, Missive> map = new HashMap<Integer, Missive>();

	static final void doGC(final int count) throws Exception {
		for (int k = 0; k < count; k++) {
			Runtime.getRuntime().gc();
			System.out.println("Run GC : " + k);
			Thread.sleep(1 * 1000);
		}
	}
	
	public static void main(final String[] args) throws Exception {

		final MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();

		doGC(3);

		final MemoryUsage heap1 = memBean.getHeapMemoryUsage();
		final MemoryUsage nonheap1 = memBean.getNonHeapMemoryUsage();

		final int batch = 100 * 1000;

		for (int i = 0; i < TEST_SIZE; i++) {
			map.put(i, makeNewTestMissive());
			if (i % batch == 0) {
				System.out.println(String.format("Map : %,d", i));
			}
		}

		System.out.println("Map built.");

		doGC(3);

		final MemoryUsage heap2 = memBean.getHeapMemoryUsage();
		final MemoryUsage nonheap2 = memBean.getNonHeapMemoryUsage();

		final long diffHeap = heap2.getUsed() - heap1.getUsed();
		final long diffNonHeap = nonheap2.getUsed() - nonheap1.getUsed();
		final long diffTotal = diffHeap + diffNonHeap;

		final long instanceSize = diffTotal / TEST_SIZE;
		final long perFiledSize = instanceSize / TAGS.length;

		System.out.println(String.format(
		//
				"Memory difference: \t" + //
						"Heap: %,d \t" + //
						"Non-Heap: %,d \t" + //
						"Instance: %,d \t" + //
						"PerField: %,d \t", //
				diffHeap, diffNonHeap, instanceSize, perFiledSize));

		System.out.println("Check in VisualVM.");
		while (true) {
			
		}

	}
	
	public static Missive makeNewTestMissive() {
		
		MissiveSafe m = Missive.build(TestMissive.class);
		
		for(Tag t : TAGS) {
			m.set(t, new Long((long) Math.random() * 100));
		}
		
		return m;
		
	}

}
