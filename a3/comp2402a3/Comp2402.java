package comp2402a3;

public class Comp2402 {

	protected static void myassert(boolean b) throws AssertionError {
		if (!b) {
			throw new AssertionError();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(-1 >>> 1);
	}

}
