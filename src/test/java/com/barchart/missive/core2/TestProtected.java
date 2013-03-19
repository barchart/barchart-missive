package com.barchart.missive.core2;

import com.barchart.missive.core.ObjectMap;

class TestProtected extends ObjectMap {
	
	@Override
	public void init() {
		set(TestMissive.BACON, "Bacon");
	}
	
}
