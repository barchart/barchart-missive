package com.barchart.missive.core;

import com.barchart.missive.api.Tag;

public class TestSimpleMissive extends MissiveSafe {
	
	static {
		install(new Tag<?>[] {TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1});
	}

}
