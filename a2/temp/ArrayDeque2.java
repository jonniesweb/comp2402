package comp2402a2;

import java.util.AbstractList;

/**
 * An implementation of the List interface that allows for fast modifications
 * at both the head and tail.
 *
 * @param <T> the type of objects stored in this list
 */
public class ArrayDeque2<T> extends AbstractList<T> {
	/**
	 * The class of elements stored in this queue
	 */
	protected Factory<T> f;
	
	// The following are made public on purpose - for testing purposes
	
	/**
	 * Array used to store elements
	 */
	public T[] a;
	
	/**
	 * Index of next element to de-queue
	 */
	public int j;
	
	/**
	 * Number of elements in the queue
	 */
	public int n;
	
	/**
	 * Grow the internal array
	 */
	protected void resize() {
		// TODO implement this
	}
	
	/**
	 * Constructor
	 */
	public ArrayDeque2(Class<T> t) {
		f = new Factory<T>(t);
		a = f.newArray(1);
		j = 0;
		n = 0;
	}
	
	public int size() {
		return n;
	}
	
	public T get(int i) {
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		return a[j+i];  // look, no mod
	}
	
	public T set(int i, T x) {
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		T y = a[j+i];   // look, no mod
		a[j+i] = x;
		return y;
	}
	
	public void add(int i, T x) {
		// TODO: modify this to avoid running off either end
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		if (n+1 > a.length) resize();
		if (i < n/2) {	// shift a[0],..,a[i-1] left one position
			j = (j == 0) ? a.length - 1 : j - 1; // (j-1) mod a.length
			for (int k = 0; k <= i-1; k++)
				a[j+k] = a[j+k+1];
		} else {	    // shift a[i],..,a[n-1] right one position
			for (int k = n; k > i; k--)
				a[j+k] = a[j+k-1];
		}
		a[j+i] = x;
		n++;
	}
	
	public T remove(int i) {
		if (i < 0 || i > n - 1)	throw new IndexOutOfBoundsException();
		T x = a[j+i];
		if (i < n/2) {  // shift a[0],..,[i-1] right one position
			for (int k = i; k > 0; k--)
				a[j+k] = a[j+k-1];
			j = (j + 1) % a.length;
		} else {        // shift a[i+1],..,a[n-1] left one position
			for (int k = i; k < n-1; k++)
				a[j+k] = a[j+k+1];
		}
		n--;
		if (3*n < a.length) resize();
		return x;
	}
	
	public void clear() {
		n = 0;
		resize();
	}
}
