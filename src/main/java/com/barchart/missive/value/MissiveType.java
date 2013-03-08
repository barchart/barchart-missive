package com.barchart.missive.value;


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

}
