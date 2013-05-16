package com.barchart.missive.core.dispatch;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;

public interface DispatchSpec {

	Tag<String> ALIVE = TagFactory.create("ALIVE", String.class);
	Tag<String> ANIMAL = TagFactory.create("ANIMAL", String.class);
	Tag<String> DOG = TagFactory.create("DOG", String.class);
	Tag<String> REX = TagFactory.create("REX", String.class);
	Tag<String> SPARKY = TagFactory.create("SPARKY", String.class);

}
