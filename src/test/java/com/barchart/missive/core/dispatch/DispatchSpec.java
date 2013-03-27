package com.barchart.missive.core.dispatch;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;

public final class DispatchSpec {

	public static final Tag<String> ALIVE = TagFactory.create("ALIVE", String.class);
	public static final Tag<String> ANIMAL = TagFactory.create("ANIMAL", String.class);
	public static final Tag<String> DOG = TagFactory.create("DOG", String.class);
	public static final Tag<String> REX = TagFactory.create("REX", String.class);
	public static final Tag<String> SPARKY = TagFactory.create("SPARKY", String.class);
	
}
