/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package func;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	protected static final Logger log = LoggerFactory.getLogger(Test.class);

	public static void main(final String[] args) {

		final long testSize = 100 * 1000 * 1000;

		/* Warmup */
		// for(int i = 0; i < testSize; i++) {
		// ParentMissive x = new ParentMissive();
		// }
		//
		// long start = System.currentTimeMillis();
		// for(int i = 0; i < testSize; i++) {
		// ParentMissive x = new ParentMissive();
		// }
		// log.info(System.currentTimeMillis() - start);

		final ThreadMXBean mxbean = ManagementFactory.getThreadMXBean();

		/* Warmup */
		for (int i = 0; i < testSize; i++) {
			final ParentMissive x = ParentMissive.next();
		}

		long start = mxbean.getCurrentThreadCpuTime();
		for (int i = 0; i < testSize; i++) {
			final ParentMissive x = ParentMissive.next();
		}
		log.info("{}", mxbean.getCurrentThreadCpuTime() - start);

		/* Warmup */
		for (int i = 0; i < testSize; i++) {
			final ChildMissive x = ChildMissive.next();
		}

		start = mxbean.getCurrentThreadCpuTime();
		for (int i = 0; i < testSize; i++) {
			final ChildMissive x = ChildMissive.next();
		}
		log.info("{}", mxbean.getCurrentThreadCpuTime() - start);
	}

}
