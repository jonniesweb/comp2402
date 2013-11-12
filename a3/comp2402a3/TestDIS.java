package comp2402a3;



public class TestDIS {
	public static void main(String[] args) {
		DisjointIntervalSet<Integer> s = new DisjointIntervalSet<Integer>();
		
		Assert(s.add(new Interval<Integer>(1, 2)));
		Assert(s.add(new Interval<Integer>(4, 5)));
		Assert(s.add(new Interval<Integer>(2, 4)));
		Assert(s.add(new Interval<Integer>(9, 9)));
		
		//		Assert(s.add(new Interval<Integer>(4, 8)));
		Assert(!s.add(new Interval<Integer>(3, 3)));
		Assert(s.add(new Interval<Integer>(30, 32)));
		Assert(s.add(new Interval<Integer>(-100, -99)));
		Assert(s.add(new Interval<Integer>(32, 32)));
		Assert(!s.add(new Interval<Integer>(31, 31)));
		
		
		
		System.out.println(s.intervals);
		
		//		for (int i = 0; i < 10; i++) {
		//			System.out.println(i + " " + s.contains(i));
		//		}
		//
	}
	
	static void Assert(boolean a) {
		if (!a) {
			throw new AssertionError();
		}
	}
}
