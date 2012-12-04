package com.barchart.missive.spec;

import static com.barchart.missive.spec.TestSpec.BOOLEAN1;
import static com.barchart.missive.spec.TestSpec.BOOLEAN2;
import static com.barchart.missive.spec.TestSpec.BOOLEAN3;
import static com.barchart.missive.spec.TestSpec.BYTE;
import static com.barchart.missive.spec.TestSpec.CHARACTER;
import static com.barchart.missive.spec.TestSpec.DOUBLE;
import static com.barchart.missive.spec.TestSpec.FLOAT;
import static com.barchart.missive.spec.TestSpec.INTEGER;
import static com.barchart.missive.spec.TestSpec.LONG;
import static com.barchart.missive.spec.TestSpec.SHORT;

import com.barchart.missive.Tag;
import com.barchart.missive.fast.FastMissive;

public class PrimitiveMissive extends FastMissive {
	
	public PrimitiveMissive() {
		includeTags(new Tag<?>[]{
				BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, 
				BOOLEAN1, BOOLEAN2, BOOLEAN3, CHARACTER});
	}

}
