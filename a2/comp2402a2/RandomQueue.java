package comp2402a2;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * An implementation of a RandomQueue.  This is like a standard queue,
 * except that when we remove an element, we get a random element.
 * 
 * TODO: This implementation requires Theta(size) time on average for
 * poll() operation.  Improve this so that it takes constant time.
 * @author morin
 *
 * @param <T> the type of elements stored in the queue
 */
public class RandomQueue<T> extends AbstractQueue<T> {
	/**
	 * The list that stores our queue elements
	 */
	List<T> l;
	
	/**
	 * A source of random numbers
	 */
	Random r;
	
	/**
	 * The index of the next element we will return
	 */
	int next;
	
	public RandomQueue() {
		l = new ArrayList<T>();
		r = new Random();
	}
	
	/**
	 * Return an iterator for the elements in the queue
	 * Note: this iterator does not necessarily enumerate the elements
	 * in random order
	 */
	@Override
	public Iterator<T> iterator() {
		return l.iterator();
	}
	
	@Override
	public int size() {
		return l.size();
	}
	
	@Override
	public boolean offer(T x) {
		l.add(x);
		next = r.nextInt(l.size());
		return true;
	}
	
	/**
	 * Return at the next element that will be returned by poll()/remove()
	 * without actually removing it
	 */
	@Override
	public T peek() {
		if (l.size() == 0) {
			return null;
		}
		assert(next >= 0 && next <= l.size()-1);
		return l.get(next);
	}
	
	/**
	 * Remove and return a random element from the queue
	 */
	@Override
	public T poll() {
		if (l.size() == 0) {
			return null;
		}
		assert(next >= 0 && next <= l.size()-1);
		
		// switch element at next with last element
		T temp = l.get(next);
		l.set(next, l.get(l.size() -1 ));
		l.set(l.size() - 1, temp);
		
		T x = l.remove(l.size() - 1);
		next = (l.size() > 0) ? r.nextInt(l.size()) : -1;
		return x;
	}
	
	/**
	 * A stupid method to help with tests
	 * @param b
	 * @throws AssertionError
	 */
	protected static void myassert(boolean b) throws AssertionError {
		if (!b) {
			throw new AssertionError();
		}
	}
	
	/**
	 * Some test code
	 * @param args ignored
	 */
	public static void main(String[] args) {
		RandomQueue<Integer> q = new RandomQueue<Integer>();
		
		// some simple tests and output to check for randomness
		int n = 16;
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < n; i++) {
				q.add(i);
			}
			boolean[] checked = new boolean[n];
			for (int i = 0; i < n; i++) {
				myassert(q.size() == n-i);
				Integer k = q.remove();
				myassert(checked[k] == false);
				checked[k] = true;
				System.out.print(k);
				if (q.isEmpty()) {
					System.out.println();
				} else {
					System.out.print(",");
				}
			}
			myassert(q.isEmpty());
		}
		// heavier-duty tests to check for randomness
		n = 10000;
		for (int i = 0; i < n; i++) {
			q.add((i % 2 == 0) ? i : n+i);
		}
		boolean[] checked = new boolean[n];
		Integer max = -1;
		int records = 0;
		for (int i = 0; i < n; i++) {
			myassert(q.size() == n-i);
			Integer k = q.remove();
			k = (k > n) ? k - n : k;
			if (k > max) {
				max = k;
				records++;
			}
			myassert(checked[k] == false);
			checked[k] = true;
		}
		myassert(q.isEmpty());
		System.out.println("# records = " + records
				+ " (expected " + Math.log(n) + ")");
		
		// some performance tests
		n = 100000;
		System.out.print("Adding " + n + " elements...");
		long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			q.add(i);
		}
		long stop = System.nanoTime();
		double elapsed = 1e-9*(stop-start);
		System.out.println("done (" + elapsed + "s) [ "
				+ (int)(((double)n)/elapsed) + " ops/sec ]");
		Random r = new Random();
		System.out.print("Performing " + 5*n + " queue operations...");
		start = System.nanoTime();
		for (int i = n; i < 5*n; i++) {
			if (r.nextBoolean() == false) {
				q.remove();
			} else {
				q.add(i);
			}
		}
		stop = System.nanoTime();
		elapsed = 1e-9*(stop-start);
		System.out.println("done (" + elapsed + "s) [ "
				+ (int)(((double)n)/elapsed) + " ops/sec ]");
	}
}
