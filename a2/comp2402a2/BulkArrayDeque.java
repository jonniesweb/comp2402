package comp2402a2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BulkArrayDeque<T> extends ArrayDeque<T> {
	public BulkArrayDeque(Class<T> clazz) {
		super(clazz);
	}
	
	/**
	 * Add all the elements of c to this array deque, starting
	 * at position i
	 * @param i
	 * @param c
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// this implementation is too slow
		//  make it run in O(c.size()+n-i) time.
		
		Object[] d = c.toArray();
		int numNew = d.length;
		
		// resize a to proper size. Committing a sin by not keeping size of
		// array a power of 2
		T[] tempArray = f.newArray(c.size() + a.length);
		System.arraycopy(a, 0, tempArray, 0, a.length);
		a = tempArray;
		
		
		int numMoved = n - index;
		if (numMoved > 0) {
			System.arraycopy(a, index, a, index + numNew, numMoved);
		}
		
		System.arraycopy(d, 0, a, index, numNew);
		n += numNew;
		
		
		
		//		for (T x : c) {
		//			add(i++, x);
		//	}
		return true;
	}
	
	/**
	 * Simple test driver. Warning --- does no correctness testing
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 1000000;
		Stopwatch timer = new Stopwatch();
		Random rand = new Random();
		
		List<Integer> a = new BulkArrayDeque<Integer>(Integer.class);
		
		System.out.print("Adding " + n + " elements...");
		System.out.flush();
		timer.start();
		for (int i = 0; i < n; i++) {
			a.add(rand.nextInt());
		}
		timer.stop();
		System.out.println("done (" + timer.elapsedSeconds() + "s)");
		
		List<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			al.add(i);
		}
		
		System.out.print("Adding " + n + " bulk elements...");
		System.out.flush();
		timer.start();
		a.addAll(n/2, al);
		timer.stop();
		System.out.print("done (" + timer.elapsedSeconds() + "s)");
		
	}
}
