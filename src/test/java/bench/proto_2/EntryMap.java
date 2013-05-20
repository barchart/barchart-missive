package bench.proto_2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Descriptor;
import org.openfeed.proto.data.MarketEntry.Type;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.TagFactory;

public class EntryMap implements TagMap {
	
	private static final String GET = "get";
	private static final String HAS = "has";
	
	private static final MarketEntry DUMMY = MarketEntry.getDefaultInstance();
	private static final Object[] NULL = new Object[0];
	
	@SuppressWarnings("serial")
	public static class DescriptorList extends ArrayList<Descriptor> {}
	
	public static final Tag<Action> ACTION = TagFactory.create("Action", Action.class);
	public static final Tag<Type> TYPE = TagFactory.create("Type", Type.class);
	public static final Tag<List> DESCRIPTOR = TagFactory.create("DescriptorList", List.class);
	public static final Tag<Long> MARKET_ID = TagFactory.create("MarketId", Long.class);
	public static final Tag<Long> SEQUENCE = TagFactory.create("Sequence", Long.class);
	public static final Tag<Long> TIME_STAMP = TagFactory.create("TimeStamp", Long.class);
	public static final Tag<Integer> TRADE_DATE = TagFactory.create("TradeDate", Integer.class);

	public static final Tag<Long> PRICE_MANTISSA = TagFactory.create("PriceMantissa", Long.class);
	public static final Tag<Integer> PRICE_EXPONENT = TagFactory.create("PriceExponent", Integer.class);
	public static final Tag<Long> SIZE_MANTISSA = TagFactory.create("SizeMantissa", Long.class);
	public static final Tag<Integer> SIZE_EXPONENT = TagFactory.create("SizeExponent", Integer.class);

	public static final Tag<Long> INDEX = TagFactory.create("Index", Long.class);
	public static final Tag<Long> ORDER_ID = TagFactory.create("OrderId", Long.class);
	public static final Tag<Integer> ORDER_COUNT = TagFactory.create("OrderCount", Integer.class);
	
	public static final Tag<?>[] ALL = TagFactory.collectTop(EntryMap.class);
	
	public static final Method[] getters;
	public static final Method[] hasers;
	
	static {
		
		getters = new Method[TagFactory.maxIndex()];
		hasers = new Method[TagFactory.maxIndex()];
		
		final Method[] methods = MarketEntry.class.getDeclaredMethods();
		
		for(final Tag<?> t : ALL) {
			
			for(final Method m : methods) {
				if(m.getName().equals(GET + t.name())) {
					getters[t.index()] = m;
				} else if(m.getName().equals(HAS + t.name())) {
					hasers[t.index()] = m;
				}
			}
			
		}
		
		/*
		 * Init methods
		 */
		for(final Method m : getters) {
			if(m != null) {
				initMethod(m);
			}
		}
		for(final Method m : hasers) {
			if(m != null) {
				initMethod(m);
			}
		}
		
	}
	
	/*
	 * Call method 15 times to trigger compiler inflation
	 */
	private static void initMethod(final Method m) {
		
		for(int i = 0; i < 15; i++) {
			try {
				m.invoke(DUMMY, NULL);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	private final MarketEntry entry;
	
	// Used by object pool
	private final AtomicBoolean inUse = new AtomicBoolean(true);
	
	public static EntryMap make(final MarketEntry entry) {
		// ObjectPool
		return new EntryMap(entry);
	}
	
	public EntryMap(final MarketEntry entry) {
		this.entry = entry;
		inUse.set(true);
	}
	
	public MarketEntry getProto() {
		// Release this instance back to object pool 
		inUse.set(false);
		return entry;
	}
	
	@Override
	public <V> V get(Tag<V> tag) throws MissiveException {
		try {
			return tag.cast(getters[tag.index()].invoke(entry, NULL));
		} catch (final Exception e) {
			throw new MissiveException(e);
		} 
		//return tag.cast(entry.getField(fields[tag.index()]));
	}

	@Override
	public boolean contains(Tag<?> tag) {
		try {
			return (hasers[tag.index()] != null) && 
					(Boolean)hasers[tag.index()].invoke(entry, NULL);
		} catch (final Exception e) {
			throw new MissiveException(e);
		} 
	}

	@Override
	public Tag<?>[] tagsList() {
		return ALL;
	}

	@Override
	public int mapSize() {
		return ALL.length;
	}
	

}
