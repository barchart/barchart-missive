package bench.proto_2;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Descriptor;
import org.openfeed.proto.data.MarketEntry.Type;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.TagFactory;
import com.google.protobuf.Descriptors.FieldDescriptor;

public class EntryMap implements TagMap {

	private final static FieldDescriptor[] fields;
	
	static {
		// TODO Init fields array
		fields = null;
	}
	
	private final MarketEntry entry;
	
	public EntryMap(final MarketEntry entry) {
		this.entry = entry;
	}
	
	@Override
	public <V> V get(Tag<V> tag) throws MissiveException {
		return tag.cast(entry.getField(fields[tag.index()]));
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
	
	public static final Tag<Action> ACTION = TagFactory.create(Action.class);
	
	public static final Tag<Type> TYPE = TagFactory.create(Type.class);
	
	public static final Tag<Descriptor> DESCRIPTOR = TagFactory.create(Descriptor.class);
	
	
	public static final Tag<Long> MARKET_ID = TagFactory.create(Long.class);
	
	public static final Tag<Long> INDEX = TagFactory.create(Long.class);
	
	//Timestamp, trade date

	public static final Tag<Long> PRICE_MANTISSA = TagFactory.create(Long.class);
	public static final Tag<Long> PRICE_EXPONENT = TagFactory.create(Long.class);

	public static final Tag<Long> SIZE_MANTISSA = TagFactory.create(Long.class);
	public static final Tag<Long> SIZE_EXPONENT = TagFactory.create(Long.class);

	public static final Tag<?>[] ALL = TagFactory.collectTop(EntryMap.class);


}
