package com.barchart.missive.value;


public class MissiveType<T extends ValueMissive> implements ValueType<T> {

	public static final NullMissive NULL_MISSIVE = ValueMissive.build(NullMissive.class);
	
	final Class<T> clazz;
	final int size;
	
	public MissiveType(final Class<T> type) {
		//TODO figure out how to get rid of this
		T builder = ValueMissive.build(type);
		this.clazz = type;
		this.size = ValueMissive.bytesIn(type);
	}
	
	@Override
	public T get(byte[] bytes, int index) {
		return ValueMissive.build(clazz, bytes, index);
	}

	@Override
	public byte[] put(byte[] bytes, int index, T value) {
		
		System.arraycopy(value.bytes, 0, bytes, index, value.bytes.length);
		
		return bytes;
	}

	@Override
	public boolean isNull(byte[] bytes, int index) {
		return get(bytes, index) == NULL_MISSIVE;
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
