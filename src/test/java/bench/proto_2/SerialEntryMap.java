package bench.proto_2;

import java.util.List;

import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Type;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.TagFactory;

public class SerialEntryMap implements TagMap {

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
	
	
	
	@Override
	public <V> V get(Tag<V> tag) throws MissiveException {
		
		return null;
	}

	@Override
	public boolean contains(Tag<?> tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tag<?>[] tagsList() {
		return ALL;
	}

	@Override
	public int mapSize() {
		return 0;
	}

}
