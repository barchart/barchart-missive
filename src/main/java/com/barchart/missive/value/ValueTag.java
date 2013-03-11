package com.barchart.missive.value;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.util.ClassUtil;

public class ValueTag<T extends ValueType<?>> {
	
	private static AtomicInteger counter = new AtomicInteger(0);

	public static final Logger log = LoggerFactory.getLogger(ValueTag.class);

	/**
	 * Create val tag for given name and type.
	 * 
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <V extends ValueType<?>> ValueTag<V> 
			create(final String name, final V valType) {
		return new ValueTag<V>(name, valType);
	}
	
	/**
	 * Collect all val tags from provided type.
	 * 
	 * @throws MissiveException
	 */
	public static ValueTag<?>[] collectAll(final Class<?> clazz)
			throws MissiveException {
		try {
			return ClassUtil.constantFieldsAll(clazz, ValueTag.class).toArray(
					new ValueTag[0]);
		} catch (final Exception e) {
			throw new MissiveException(e);
		}
	}

	/**
	 * Collect top level val tags from provided type.
	 * 
	 * @throws MissiveException
	 */
	public static ValueTag<?>[] collectTop(final Class<?> clazz)
			throws MissiveException {
		try {
			return ClassUtil.constantFieldsTop(clazz, ValueTag.class).toArray(
					new ValueTag[0]);
		} catch (final Exception e) {
			throw new MissiveException(e);
		}
	}
	
	/**
	 * Returns the number of value tags created.
	 * @return
	 */
	public static int maxIndex() {
		return counter.get();
	}
	
	protected static int nameHash(final Class<?> clazz) {
		return clazz.getName().hashCode();
	}

	protected static int nextIndex() {
		ValueMissive.incrementIndexRegistry();
		return counter.getAndIncrement();
	}
	
	private final T type; 

	private final int hashCode = nameHash(getClass());

	private final int index = nextIndex();
	
	private volatile String name;
	
	private final Class<?> spot;

	private ValueTag(final String name, final T type) throws MissiveException {
		
		try {
			
			this.name = name;
			this.spot = ClassUtil.instanceSpot(5);
			this.type = type;
			
		} catch (final Throwable e) {
			log.error("construct failure", e);
			throw new MissiveException(e);
		}
	}
	
	protected String defaultTagName() {
		return "TAG=" + index;
	}
	
	/**
	 * Returns the ValueType this tag identifies.
	 * 
	 * @return
	 */
	public T type() {
		return type;
	}
	
	/** Tag name. */
	public final String name() {
		/** lazy init */
		String name = this.name;
		if (name == null) {
			synchronized (this) {
				try {
					name = ClassUtil.constantFieldName(spot, this);
				} catch (final Throwable e) {
					name = defaultTagName();
				}
				this.name = name;
			}
		}
		return name;
	}
	
	/** Tag instantiation index. */
	public int index() {
		return index;
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}
	
	@Override
	public String toString() {
		return name();
	}

}
