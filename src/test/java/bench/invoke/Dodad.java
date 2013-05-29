package bench.invoke;

public class Dodad {

	final static long a = 1223106847L;
    final static long m = (1L << 32)-5;
	
	public int dodad = 0;
	
	public Dodad(final int input) {
		dodad = input;
	}
	
	// Just some random junk
	public void hash() {
		
		int n = dodad;
		long h = a*(n&0xffffffffL);
		long j = h >>> 32;
        long k = h & 0xffffffffL;
        long h1 = j*5+k;
        j = h1 >>> 32;
        k = h1 & 0xffffffffL;
	    long h2 = j*5+k;            
	    dodad = (int) (h2 >= m ? (h2-m) : h2);
	   
	}
	
}
