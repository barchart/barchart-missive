package com.barchart.missive.core;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

/*
 * Results 100,000 instances = 35MB
 * Results 1,000,000 instances = 204MB
 * Results 10,000,000 instances = 1,832 MB
 */
public class MissiveMemoryTest {

	public static final int TEST_SIZE = 1 * 1000 * 1000;

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
			map.put(i, Missive.build(TestMissive.class));
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

}
