package com.barchart.missive.value;

import com.barchart.missive.core.MissiveException;

public class NullMissive extends ValueMissive implements NullValue<ValueMissive> {

	private static final ValueTag<?>[] NULL_TAGS = new ValueTag[0];
	
	static {
		install(NULL_TAGS);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V, T extends ValueType<V>> V get(final ValueTag<T> tag) throws MissiveException {
		return (V) tag.type().getNull();
	}
	
	@Override
	public<V, T extends ValueType<V>> void set(final ValueTag<T> tag, final V value)
			throws MissiveException {
		
	}

	@Override
	public boolean contains(final ValueTag<?> tag) {
		return false;
	}

	@Override
	public ValueTag<?>[] tags() {
		return NULL_TAGS;
	}

	@Override
	public int size() {
		return 0;
	}
	
}
