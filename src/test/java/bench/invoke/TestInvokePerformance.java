package bench.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestInvokePerformance {
	
public static int ITERS = 100 * 1000 * 1000;
	
	public static void main(final String[] args) throws SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, 
			InvocationTargetException {
		
		Class<Dodad> doClass = Dodad.class;
		
		Method meth = doClass.getDeclaredMethod("hash", new Class<?>[0]);
		
		Dodad dodad = new Dodad(1111111111);
		final Object[] param = new Object[0];
		long start = 0;
		long stop = 0;
		
		int local = 0;
		
		long natRes = 0;
		
		/* Warm up */
		for(int i = 0; i < ITERS; i++) {
			dodad.hash();
			local ^= dodad.dodad;
		}
		// Ensure local is used
		System.out.println(local);
		
		/* Test normal */
		start = System.nanoTime();
		for(int i = 0; i < ITERS; i++) {
			dodad.hash();
			local ^= dodad.dodad;
		}
		stop = System.nanoTime();
		
		// Ensure local is used
		System.out.println(local);
		
		natRes = stop - start;
		
		dodad = new Dodad(1111111112);
		
		/* Warm up */
		for(int i = 0; i < ITERS; i++) {
			meth.invoke(dodad, param);
			local ^= dodad.dodad;
		}
		// Ensure local is used
		System.out.println(local);
		
		/* Test invoke */
		start = System.nanoTime();
		for(int i = 0; i < ITERS; i++) {
			meth.invoke(dodad, param);
			local ^= dodad.dodad;
		}
		stop = System.nanoTime();
		
		// Ensure local is used
		System.out.println(local);
		
		System.out.println("Native took " + natRes);
		System.out.println("Invoke took " + (stop - start));
		
	}

}
