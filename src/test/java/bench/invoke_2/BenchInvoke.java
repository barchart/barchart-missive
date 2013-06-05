package bench.invoke_2;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

/**
 * Compare direct-vs-reflective method calls.
 */
public class BenchInvoke {

	/**
	 * Parameterize caliper runs.
	 */
	public static enum Type {

		RETURN_INTEGER() {
			@Override
			public Method method() throws Exception {
				return Bean.class.getDeclaredMethod("returnInteger");
			}

			@Override
			public int timeDirect(final long reps) throws Exception {
				int k = 0;
				for (int i = 0; i < reps; i++) {
					k ^= BEAN.returnInteger();
				}
				return k;
			}

		}, //

		RETURN_VOID() {
			@Override
			public Method method() throws Exception {
				return Bean.class.getDeclaredMethod("returnVoid");
			}

			@Override
			public int timeDirect(final long reps) throws Exception {
				int temp = 0;
				for (int i = 0; i < reps; i++) {
					BEAN.returnVoid();
					temp ^= BEAN.dodad;
				}
				return temp;
			}

		}, //

		;

		/**
		 * Method represented by {@link Type}
		 */
		public abstract Method method() throws Exception;

		/**
		 * Measure reflection method call.
		 */
		public int timeInvoke(final long reps) throws Exception {
			final Method method = method();
			Object result = null;
			for (int i = 0; i < reps; i++) {
				result = method.invoke(BEAN);
			}
			return result == null ? 0 : result.hashCode();
		}

		/**
		 * Measure direct method call.
		 */
		public abstract int timeDirect(long reps) throws Exception;

		Type() {

		}

	}

	/**
	 * Static instance used in benchmarks.
	 */
	static final Bean BEAN = new Bean();

	/**
	 * Compare direct-vs-reflective method calls.
	 */
	public static class Benchmark1 extends Benchmark {

		/**
		 * Automatically set by caliper from {@link Type#values()}.
		 */
		@Param
		Type type;

		/** Direct java method call. */
		public int timeDirect(final long reps) throws Exception {
			return type.timeDirect(reps);
		}

		/** Reflective java method call . */
		public int timeInvoke(final long reps) throws Exception {
			return type.timeInvoke(reps);
		}

	}

	static final Logger log = LoggerFactory.getLogger(BenchInvoke.class);

	public static void main(final String... args) {

		log.info("init");

		CaliperMain
				.main(Benchmark1.class,
						new String[] {
								"--print-config",
								"-Cvm.args= -Dsun.reflect.noInflation=true -XX:PermSize=100m -XX:MaxPermSize=100m -Xms1200m -Xmx1200m -XX:NewSize=200m -XX:MaxNewSize=200m -XX:SurvivorRatio=2 -XX:TargetSurvivorRatio=90",
								"--instrument=micro", "--time-limit=0", "--verbose"});

		log.info("done");

	}

}
