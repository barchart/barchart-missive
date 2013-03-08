package com.barchart.missive.value;
import static com.barchart.missive.value.TestSpec.*;

public class ExampleMissive extends ValueMissive {
	
	static {
		install(TAGS);
	}
	
	public Functor<ExampleMissive, ExampleMissive> addInts() {
		
		final ExampleMissive miss = this;
		
		return new Functor<ExampleMissive, ExampleMissive>() {

			@Override
			public ExampleMissive run(final ExampleMissive that) {
				
				for(final ValueTag tag : INTS) {
				
					set(tag, (Integer)get(tag) + (Integer)that.get(tag));
					
				}
				
				return miss;
			}
			
		};
		
	}

}
