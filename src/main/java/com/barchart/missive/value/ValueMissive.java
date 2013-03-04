package com.barchart.missive.value;

import com.barchart.missive.core.Missive;

@SuppressWarnings("rawtypes")
public abstract class ValueMissive extends Missive {
	
	abstract <P extends ValueTag, R> R visit(Functor<P, R> func);
	
	abstract <P extends ValueTag> void visit(ValueTask<P> task);
	
	abstract void visit(ValueTaskSet taskSet);

}
