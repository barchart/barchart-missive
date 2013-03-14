package com.barchart.missive.core;

import com.barchart.missive.api.Tag;

public class TestSimpleMissive extends ObjectMapSafe {
	
	static {
		ObjectMapFactory.install(TestSimpleMissive.class, 
				new Tag<?>[] {TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1});
	}

}
