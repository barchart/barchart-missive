package com.barchart.missive.core;

import com.barchart.missive.api.Tag;

public class TestCaseMissive extends MissiveSafe {

	static {
		install(new Tag<?>[]{
				TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1,
				TestSpec.STRING, TestSpec.LIST, TestSpec.MISSIVE
		});
	}
	
}
