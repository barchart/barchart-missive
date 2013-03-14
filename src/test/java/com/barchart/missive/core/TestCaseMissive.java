package com.barchart.missive.core;

import com.barchart.missive.api.Tag;

public class TestCaseMissive extends TestSimpleMissive {

	static {
		ObjectMapFactory.install(TestCaseMissive.class, new Tag<?>[]{
				TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1,
				TestSpec.STRING, TestSpec.LIST, TestSpec.MISSIVE
		});
	}
	
}
