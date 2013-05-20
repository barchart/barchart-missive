package bench.proto_2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Descriptor;
import org.openfeed.proto.data.MarketEntry.Type;
import org.openfeed.proto.data.MarketEntrySpec;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.TagFactory;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;

public class EntryMap implements TagMap {
	
	// Cannot cast com.google.protobuf.Descriptors$EnumValueDescriptor to org.openfeed.proto.data.MarketEntry$Action

	public static final Tag<Action> ACTION = TagFactory.create("action", Action.class);
	public static final Tag<Type> TYPE = TagFactory.create("type", Type.class);
	public static final Tag<Descriptor> DESCRIPTOR = TagFactory.create("descriptor", Descriptor.class);
	public static final Tag<Long> MARKET_ID = TagFactory.create("marketId", Long.class);
	public static final Tag<Long> SEQUENCE = TagFactory.create("sequence", Long.class);
	public static final Tag<Long> TIME_STAMP = TagFactory.create("timeStamp", Long.class);
	public static final Tag<Long> TRADE_DATE = TagFactory.create("tradeDate", Long.class);

	public static final Tag<Long> PRICE_MANTISSA = TagFactory.create("priceMantissa", Long.class);
	public static final Tag<Long> PRICE_EXPONENT = TagFactory.create("priceExponent", Long.class);
	public static final Tag<Long> SIZE_MANTISSA = TagFactory.create("sizeMantissa", Long.class);
	public static final Tag<Long> SIZE_EXPONENT = TagFactory.create("sizeExponent", Long.class);

	public static final Tag<Long> INDEX = TagFactory.create("index", Long.class);
	public static final Tag<Long> ORDER_ID = TagFactory.create("orderId", Long.class);
	public static final Tag<Long> ORDER_COUNT = TagFactory.create("orderCount", Long.class);
	
	public static final Tag<?>[] ALL = TagFactory.collectTop(EntryMap.class);
	
	public final static FieldDescriptor[] fields;
	public final static FieldAccessorTable fieldTable;
	public static Method getter;
	
	static {
		
		Field field = null;
		try {
			field = MarketEntrySpec.class.getDeclaredField(
					"internal_static_org_openfeed_proto_data_MarketEntry_fieldAccessorTable");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		field.setAccessible(true);
		try {
			fieldTable = (FieldAccessorTable) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 

		Method[] methods = FieldAccessorTable.class.getDeclaredMethods();
		
		for(Method method : methods) {
			method.setAccessible(true);
			if(method.getName().equals("getField")) {
				getter = method;
			}
		}
		
		Class<?>[] clazzes = FieldAccessorTable.class.getDeclaredClasses();
		for(Class<?> clazz : clazzes) {
			System.out.println(clazz.getCanonicalName());
		}
		
		final com.google.protobuf.Descriptors.Descriptor d = 
				MarketEntry.getDescriptor();
		
		fields = new FieldDescriptor[TagFactory.maxIndex()];
		
		for(final Tag tag: ALL) {
			
			FieldDescriptor fd = d.findFieldByName(tag.name());
			if(fd != null) {
				fields[tag.index()] = fd;
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
			return tag.cast(getter.invoke(entry, fields[tag.index()]));
		} catch (Exception e) {
			throw new MissiveException(e);
		} 
		//return tag.cast(entry.getField(fields[tag.index()]));
	}

	@Override
	public boolean contains(Tag<?> tag) {
		return (fields[tag.index()] != null) && 
				entry.hasField(fields[tag.index()]);
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
