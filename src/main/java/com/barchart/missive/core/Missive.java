package com.barchart.missive.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.barchart.missive.util.ClassUtil;

public abstract class Missive implements TagMap {
	
	private static final AtomicInteger classCount = new AtomicInteger(0);
	
	/* ClassCode, TagCode */
	protected static int[][] indexRegistry;
	protected static Tag<?>[][] tagRegistry;
	
	private final static ConcurrentMap<Class<? extends Missive>, Integer> classMap = 
			new ConcurrentHashMap<Class<? extends Missive>, Integer>();
	
	protected final int classCode;
	protected final Object[] values;
	
	@SuppressWarnings("unchecked")
	protected Missive(Tag<?>[] tags) {
		
		if(tags == null) {
			tags = new Tag<?>[0];
		}
		
		/* Get current class object */
		final Class<?>[] trace = new ClassUtil.ClassTrace().getClassContext();
		
		Class<? extends Missive> current = (Class<? extends Missive>) trace[0];
		
		if(!classMap.containsKey(current)) {
			
			synchronized(this) {
			
				final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();
				
				for(final Class<?> clazz : trace) {
					if(clazz.isAssignableFrom(Missive.class)) {
						
						/* Store all tags of superclasses */
						Tag<?>[] classTags = new Tag<?>[0];
						try {
							classTags = (Tag<?>[]) clazz.getDeclaredField("tags").get(new Tag<?>[0]);
						} catch (final Exception e) {
							e.printStackTrace();
						} 
						
						for(Tag<?> t : classTags) {
							tagSet.add(t);
						}
						
						current = (Class<? extends Missive>) clazz;
					} else {
						break;
					}
				}
				
				/* Build new tag array and update tag registry */
				final Tag<?>[] newTags = new Tag<?>[tagSet.size()];
				int counter = 0;
				for(Tag<?> t : tagSet) {
					newTags[counter] = t;
					counter++;
				}
				
				final Tag<?>[][] newTagRegistry = new Tag<?>[classCount.get() + 1][];
				System.arraycopy(tagRegistry, 0, newTagRegistry, 0, classCount.get());
				newTagRegistry[classCount.get() - 1] = newTags;
				tagRegistry = newTagRegistry;
				
				/* Build new index array and update index registry */
				final int[] newIndexes = new int[Tag.maxIndex()];
				for(int i  = 0; i < Tag.maxIndex(); i++) {
					newIndexes[i] = -1;
				}
				counter = 0;
				for(Tag<?> t : tagSet) {
					newIndexes[t.index()] = counter;
					counter++;
				}
				
				final int[][] newIndexRegistry = new int[classCount.get() + 1][];
				System.arraycopy(indexRegistry, 0, newIndexRegistry, 0, classCount.get());
				newIndexRegistry[classCount.get() + 1] = newIndexes;
				indexRegistry = newIndexRegistry;
				
				/* Assign new class code and update counter */
				classMap.put(current, classCount.getAndIncrement());
			
			}
			
		}
		
		classCode = classMap.get(current);
		values = new Object[tagRegistry[classCode].length];
	}
	
	static synchronized void incrementIndexRegistry() {
		
		final int oldSize = indexRegistry[0].length;
		
		for(int i = 0; i < indexRegistry.length; i++) {
			final int[] newIndexes = new int[oldSize + 1];
			System.arraycopy(indexRegistry[i], 0, newIndexes, 0, oldSize);
			indexRegistry[i] = newIndexes;
			indexRegistry[i][oldSize] = -1;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) throws MissiveException {
		return (V) values[indexRegistry[classCode][tag.index()]];
	}

	@Override
	public boolean contains(final Tag<?> tag) {
		return indexRegistry[classCode][tag.index()] != -1;
	}

	@Override
	public Tag<?>[] tags() {
		return tagRegistry[classCode];
	}

	@Override
	public int size() {
		return tagRegistry[classCode].length;
	}

}
