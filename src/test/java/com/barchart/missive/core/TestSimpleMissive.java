package com.barchart.missive.core;

public class TestSimpleMissive extends MissiveSafe {
	
	static {
		install(new Tag<?>[] {TestSpec.ENUM, TestSpec.INTEGER, TestSpec.BOOLEAN1});
	}

}
