package com.barchart.missive.api.spec;

import static com.barchart.missive.api.spec.TestSpec.BOOLEAN1;
import static com.barchart.missive.api.spec.TestSpec.BOOLEAN2;
import static com.barchart.missive.api.spec.TestSpec.BOOLEAN3;
import static com.barchart.missive.api.spec.TestSpec.BYTE;
import static com.barchart.missive.api.spec.TestSpec.CHARACTER;
import static com.barchart.missive.api.spec.TestSpec.DOUBLE;
import static com.barchart.missive.api.spec.TestSpec.FLOAT;
import static com.barchart.missive.api.spec.TestSpec.INTEGER;
import static com.barchart.missive.api.spec.TestSpec.LONG;
import static com.barchart.missive.api.spec.TestSpec.SHORT;

import com.barchart.missive.Tag;
import com.barchart.missive.fast.FastMissive;

public class PrimitiveMissive extends FastMissive {
	
	public PrimitiveMissive() {
		includeTags(new Tag<?>[]{
				BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, 
				BOOLEAN1, BOOLEAN2, BOOLEAN3, CHARACTER});
	}

}
