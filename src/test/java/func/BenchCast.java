/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package func;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchCast {

	protected static final Logger log = LoggerFactory.getLogger(BenchNext.class);

	public static void main(final String[] args) {

		final ParentMissive p = ParentMissive.next();

		visit(p.castAsSubclass(ChildMissive.next()));

		final long testSize = 10 * 1000 * 1000;
		final int numTests = 5;

		for (int j = 0; j < numTests; j++) {

			for (int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(new ChildMissive()));
			}

			long start = System.currentTimeMillis();
			for (int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(new ChildMissive()));
			}
			log.info("{}", System.currentTimeMillis() - start);

			for (int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(ChildMissive.next()));
			}

			start = System.currentTimeMillis();
			for (int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(ChildMissive.next()));
			}
			log.info("{}", System.currentTimeMillis() - start);

		}

	}

	public static void visit(final ParentMissive m) {
		log.info("Parent Visit");
	}

	public static void visit(final ChildMissive m) {
		// log.info("Child Visit");
	}

}
