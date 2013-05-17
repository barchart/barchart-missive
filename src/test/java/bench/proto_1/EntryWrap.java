package bench.proto_1;

import org.openfeed.proto.data.MarketEntry;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.core.Manifest;
import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.ObjectMap;
import com.barchart.missive.core.ObjectMapFactory;
import com.barchart.util.values.provider.ValueBuilder;

/**
 * Wrapper for {@link MarketEntry}.
 */
public class EntryWrap implements TagMap {

	static {

		final Manifest<ObjectMap> manifest = new Manifest<ObjectMap>();

		ObjectMapFactory.install(manifest);

	}

	final MarketEntry entry;

	public EntryWrap(final MarketEntry entry) {
		this.entry = entry;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) throws MissiveException {

		if (tag == EntryTag.INDEX) {
			return (V) new Long(entry.getIndex());
		}

		if (tag == EntryTag.PRICE) {
			return (V) ValueBuilder.newPrice(entry.getPriceMantissa(),
					entry.getPriceExponent());
		}

		if (tag == EntryTag.SIZE) {
			return (V) ValueBuilder.newSize(entry.getSizeMantissa());
		}

		return null;
	}

	@Override
	public boolean contains(final Tag<?> tag) {

		if (tag == EntryTag.INDEX) {
			return entry.hasIndex();
		}

		if (tag == EntryTag.PRICE) {
			return entry.hasPriceMantissa();
		}

		if (tag == EntryTag.SIZE) {
			return entry.hasSizeMantissa();
		}

		return false;
	}

	@Override
	public Tag<?>[] tagsList() {
		return EntryTag.ALL;
	}

	@Override
	public int mapSize() {
		return tagsList().length;
	}

}
