package com.barchart.missive.core.dispatch;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.barchart.missive.core.ObjectMap;
import com.barchart.missive.core.ObjectMapFactory;

public class TestDispatchService {

	@Before
	public void before() {
		ObjectMapFactory.install(new DispatchManifest());
	}
	
	@Test
	public void testClass() {
		
		final Sparky sparky = ObjectMapFactory.build(Sparky.class);
		
		assertTrue(!sparky.hasSubclass());
		
		ObjectMap cast = sparky.superclass();
		assertTrue(cast.getClass().equals(Dog.class));
		
		cast = cast.superclass();
		assertTrue(cast.getClass().equals(Animal.class));
		
		cast = cast.superclass();
		assertTrue(cast.getClass().equals(Alive.class));
		
		assertTrue(!cast.hasSuperclass());
		
		cast = cast.subclass();
		assertTrue(cast.getClass().equals(Animal.class));
		
		cast = cast.subclass();
		assertTrue(cast.getClass().equals(Dog.class));
		
		cast = cast.subclass();
		assertTrue(cast.getClass().equals(Sparky.class));
		
	}
	
	@Test
	public void testDispatch() {
		
		DispatchExample dispatch = new DispatchExample();
		
		ObjectMap sparky = ObjectMapFactory.build(Sparky.class);
		sparky = sparky.cast(Alive.class);
		
		while(sparky.hasSubclass()) {
			dispatch.handle(sparky);
			sparky = sparky.subclass();
		}
		dispatch.handle(sparky);
		
		assertTrue(sparky.get(DispatchSpec.ALIVE).equals("ALIVE"));
		assertTrue(sparky.get(DispatchSpec.ANIMAL).equals("ANIMAL"));
		assertTrue(sparky.get(DispatchSpec.DOG).equals("DOG"));
		assertTrue(sparky.get(DispatchSpec.SPARKY).equals("SPARKY"));
		
	}
	
}
