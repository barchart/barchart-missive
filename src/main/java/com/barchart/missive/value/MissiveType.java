package com.barchart.missive.value;

import com.barchart.missive.core.MissiveException;

public class MissiveType<T extends ValueMissive> implements ValueType<T> {

	public static final NullMissive NULL_MISSIVE = ValueMissive.build(NullMissive.class);
	
	final Class<T> clazz;
	final int size;
	
	@SuppressWarnings("unused")
	public MissiveType(final Class<T> type) {
		//TODO figure out how to get rid of this
		T builder = ValueMissive.build(type);
		this.clazz = type;
		this.size = ValueMissive.bytesIn(type);
	}
	
	@Override
	public T getValue(final byte[] bytes, final int index) {
		return ValueMissive.build(clazz, bytes, index);
	}

	@Override
	public T getValue(final byte[] bytes) {
		return ValueMissive.build(clazz, bytes, 0);
	}

	@Override
	public byte[] getBytes(final T value) {
		return value.bytes;
	}
	
	@Override
	public byte[] putValue(final T value,final byte[] bytes,final int index) {
		System.arraycopy(value.bytes, 0, bytes, index, value.bytes.length);
		return bytes;
	}

	@Override
	public boolean isNull(byte[] bytes, int index) {
		return getValue(bytes, index) == NULL_MISSIVE;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public NullValue<T> getNull() {
		return (NullValue<T>) NULL_MISSIVE;
	}
	
	private static class NullMissive extends ValueMissive implements NullValue<ValueMissive> {
		
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

}
