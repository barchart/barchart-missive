package example.market;

import com.barchart.missive.core.Tag;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

public interface MarketField {

	Tag<TimeValue> MARKET_TIME = new Tag<TimeValue>() {
	};

	Tag<SizeValue> BOOK_SIZE = Tag.create(SizeValue.class);

}
