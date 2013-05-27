package bench.proto_2;

import java.util.Collections;
import java.util.List;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Descriptor;
import org.openfeed.proto.data.MarketEntry.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

/**
 * Compare market entry access: native vs missive.
 */
public class BenchEntryMap {

	public static class Benchmark1 extends Benchmark {

		/**
		 * Generate reference market entry.
		 */
		static final MarketEntry construct() {
			final MarketEntry.Builder builder = MarketEntry.newBuilder();

			builder.setAction(Action.ADD);
			builder.setType(Type.ASK);
			builder.addDescriptor(Descriptor.ADJUSTED_TRADE);
			builder.setMarketId(1);
			builder.setSequence(2);
			builder.setTimeStamp(3);
			builder.setTradeDate(4);
			builder.setPriceMantissa(5);
			builder.setPriceExponent(6);
			builder.setSizeMantissa(7);
			builder.setSizeExponent(8);
			builder.setIndex(9);
			builder.setOrderId(10);
			builder.setOrderCount(11);

			return builder.build();
		}

		static final MarketEntry NATIVE = construct();

		static final EntryMap MISSIVE = new EntryMap(construct());

		/**
		 * Note: no object creation.
		 */
		public int timeNative(final long reps) {

			/**
			 * Expose local variables out-of-loop to prevent hot-spot
			 * optimization.
			 */
			Action action = Action.ADD;
			Type type = Type.ASK;
			long marketID = 0L;
			List<Descriptor> list = Collections.emptyList();
			long sequence = 0L;
			int tradeDate = 0;
			long priceMantissa = 0L;
			int priceExponent = 0;
			long sizeMantissa = 0L;
			int sizeExponent = 0;
			long index = 0;
			long orderID = 0L;
			int orderCount = 0;

			for (int i = 0; i < reps; i++) {
				action = NATIVE.getAction();
				type = NATIVE.getType();
				list = NATIVE.getDescriptorList();
				marketID = NATIVE.getMarketId();
				sequence = NATIVE.getSequence();
				tradeDate = NATIVE.getTradeDate();
				priceMantissa = NATIVE.getPriceMantissa();
				priceExponent = NATIVE.getPriceExponent();
				sizeMantissa = NATIVE.getSizeMantissa();
				sizeExponent = NATIVE.getSizeExponent();
				index = NATIVE.getIndex();
				orderID = NATIVE.getOrderId();
				orderCount = NATIVE.getOrderCount();
			}

			/**
			 * Use local variables out-of-loop to prevent hot-spot optimization.
			 */
			return (int) (action.hashCode() + type.hashCode() + marketID
					+ list.hashCode() + sequence + tradeDate + priceMantissa
					+ priceExponent + sizeMantissa + sizeExponent + index
					+ orderID + orderCount);
		}

		/**
		 * Note: lots of object creation.
		 * <p>
		 * Unless using escape analysis, but EA makes it 3x times slower.
		 * <p>
		 * https://wikis.oracle.com/display/HotSpotInternals/EscapeAnalysis
		 * <p>
		 */
		public int timeMissive(final long reps) {
			/**
			 * Expose local variables out-of-loop to prevent hot-spot
			 * optimization.
			 */
			Action action = Action.ADD;
			Type type = Type.ASK;
			long marketID = 0L;
			List<Descriptor> list = Collections.emptyList();
			long sequence = 0L;
			int tradeDate = 0;
			long priceMantissa = 0L;
			int priceExponent = 0;
			long sizeMantissa = 0L;
			int sizeExponent = 0;
			long index = 0;
			long orderID = 0L;
			int orderCount = 0;

			for (int i = 0; i < reps; i++) {
				action = MISSIVE.get(EntryMap.ACTION);
				type = MISSIVE.get(EntryMap.TYPE);
				list = MISSIVE.get(EntryMap.DESCRIPTOR);
				marketID = MISSIVE.get(EntryMap.MARKET_ID);
				sequence = MISSIVE.get(EntryMap.SEQUENCE);
				tradeDate = MISSIVE.get(EntryMap.TRADE_DATE);
				priceMantissa = MISSIVE.get(EntryMap.PRICE_MANTISSA);
				priceExponent = MISSIVE.get(EntryMap.PRICE_EXPONENT);
				sizeMantissa = MISSIVE.get(EntryMap.SIZE_MANTISSA);
				sizeExponent = MISSIVE.get(EntryMap.SIZE_EXPONENT);
				index = MISSIVE.get(EntryMap.INDEX);
				orderID = MISSIVE.get(EntryMap.ORDER_ID);
				orderCount = MISSIVE.get(EntryMap.ORDER_COUNT);
			}

			/**
			 * Use local variables out-of-loop to prevent hot-spot optimization.
			 */
			return (int) (action.hashCode() + type.hashCode() + marketID
					+ list.hashCode() + sequence + tradeDate + priceMantissa
					+ priceExponent + sizeMantissa + sizeExponent + index
					+ orderID + orderCount);
		}

	}

	static final Logger log = LoggerFactory.getLogger(BenchEntryMap.class);

	public static void main(final String... args) {

		log.info("init");

		/** No EA, Trash GC. */
		CaliperMain.main(Benchmark1.class, new String[] { "--print-config",
				"-Cvm.args=-Xmx2g -Xms2g -server" });

		/** Force EA, no GC. */
		// CaliperMain.main(Benchmark1.class, new String[] { "--print-config",
		// "-Cvm.args=-Xmx2g -Xms2g -server -XX:CompileThreshold=1 " });

		log.info("done");

	}

}
