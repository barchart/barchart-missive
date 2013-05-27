package bench.caliper_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.caliper.runner.CaliperMain;

/**
 * Verify caliper sanity.
 * 
 * @see CaliperBase
 */
public class CaliperTest {

	static final Logger log = LoggerFactory.getLogger(CaliperTest.class);

	public static void main(final String... args) {

		log.info("init");

		CaliperMain.main(Tutorial.Benchmark2.class,
				new String[] { "--print-config" });

		log.info("done");

	}

}
