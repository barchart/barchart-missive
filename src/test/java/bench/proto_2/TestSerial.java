package bench.proto_2;

import java.util.Arrays;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.Action;
import org.openfeed.proto.data.MarketEntry.Type;

public class TestSerial {
	
	public static void main(final String[] args) {
		
		MarketEntry entry = MarketEntry.newBuilder()
					.setAction(Action.EDIT)
					.setType(Type.ASK)
					.setTimeStamp(1000)
					.build();
		
		System.out.println(Arrays.toString(entry.toByteArray()));
		
		
	}

}
