package bench.caliper_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bench.caliper_1.Tutorial.Benchmark2;

/**
 * Verify caliper sanity.
 * 
 * @see CaliperTest
 */
public class CaliperBase {

	static final Logger log = LoggerFactory.getLogger(CaliperBase.class);

	static final int COUNT = 10 * 1000 * 1000;

	/** Run caliper reference benchmark manually. */
	static void test(final Benchmark2 bench) {

		{
			final long timeStart = System.nanoTime();
			bench.timeCurrentTimeMillis(COUNT);
			final long timeFinish = System.nanoTime();
			final long timeDiff = timeFinish - timeStart;
			log.info("CurrentTimeMillis: {}", timeDiff / COUNT);
		}

		{
			final long timeStart = System.nanoTime();
			bench.timeNanoTime(COUNT);
			final long timeFinish = System.nanoTime();
			final long timeDiff = timeFinish - timeStart;
			log.info("NanoTime: {}", timeDiff / COUNT);
		}

	}

	public static void main(final String... args) {

		log.info("init");

		final Benchmark2 bench = new Tutorial.Benchmark2();

		test(bench);
		test(bench);
		test(bench);

		log.info("done");

	}

}
