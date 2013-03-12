package com.barchart.missive.value.types;

import java.nio.charset.Charset;

import com.barchart.missive.value.NullValue;
import com.barchart.missive.value.ValueType;

public class StringType implements ValueType<String> {

	public static Charset CHARSET = Charset.forName("UTF-8");
	
	@Override
	public String getValue(byte[] bytes, int index) {
		return new String(bytes, CHARSET);
	}

	@Override
	public String getValue(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] putValue(String value, byte[] bytes, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNull(byte[] bytes, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public NullValue<String> getNull() {
		// TODO Auto-generated method stub
		return null;
	}

}
