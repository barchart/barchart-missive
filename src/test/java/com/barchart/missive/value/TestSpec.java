package com.barchart.missive.value;

import com.barchart.missive.value.types.IntegerType;
import com.barchart.missive.value.types.LongType;

public class TestSpec {

	public static final ValueTag<IntegerType> INT_1 =
			ValueTag.create("IntTag1", new IntegerType());
	
	public static final ValueTag<IntegerType> INT_2 =
			ValueTag.create("IntTag2", new IntegerType());
	
	public static final ValueTag<IntegerType> INT_3 =
			ValueTag.create("IntTag3", new IntegerType());
	
	public static final ValueTag<LongType> LONG_1 =
			ValueTag.create("LongTag1", new LongType());
	
	public static final ValueTag<LongType> LONG_2 =
			ValueTag.create("LongTag2", new LongType());
	
	public static final ValueTag<LongType> LONG_3 =
			ValueTag.create("LongTag3", new LongType());
	
	public static final ValueTag<?>[] INTS = new ValueTag<?>[]{INT_1, INT_2, INT_3};
	
	public static final ValueTag<?>[] LONGS = new ValueTag<?>[]{LONG_1, LONG_2, LONG_3};
	
	public static final ValueTag<IntMissiveType> INT_MISSIVE =
			ValueTag.create("IntMissive", new IntMissiveType());
	
	public static final ValueTag<?>[] TAGS = ValueTag.collectTop(TestSpec.class);
	
	
	
}
