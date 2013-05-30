package bench.invoke;

import java.lang.reflect.Method;

public class TestInfInvoke {

	public static void main(final String[] args) throws Exception {
		
		Class<Dodad> doClass = Dodad.class;
		
		Method meth = doClass.getDeclaredMethod("hash", new Class<?>[0]);
		
		Dodad dodad = new Dodad(1111111111);
		final Object[] param = new Object[0];
		
		int local = 0;
		
		while(true) {
//			meth.invoke(dodad, param);
//			local ^= dodad.dodad;
		}
		
	}
	
}
