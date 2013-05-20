package bench.proto_2;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Descriptor;
import org.openfeed.proto.data.MarketEntry.Type;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;

public class TestEntryMap {
	
	public static void main(final String[] args) {
		
		MarketEntry me = MarketEntry.newBuilder()
				.setAction(Action.ADD)
				.setType(Type.ASK)
				.addDescriptor(Descriptor.ADJUSTED_TRADE)
				.setMarketId(1)
				.setSequence(2)
				.setTimeStamp(3)
				.setTradeDate(4)
				.setPriceMantissa(5)
				.setPriceExponent(6)
				.setSizeMantissa(7)
				.setSizeExponent(8)
				.setIndex(9)
				.setOrderId(10)
				.setOrderCount(11)
				.build();
		
		TagMap testMap = new EntryMap(me);
		
		for(Tag t : testMap.tagsList()) {
			System.out.println(t.name() + " " + testMap.get(t));
		}
		
	}
	

}
