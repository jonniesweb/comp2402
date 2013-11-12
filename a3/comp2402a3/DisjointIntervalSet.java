package comp2402a3;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class implements the IntervalSet interface for storing a set of
 * disjoint intervals
 * @author morin
 *
 * @param <K>
 */
public class DisjointIntervalSet<K extends Comparable<K>> implements IntervalSet<K> {
	SortedSet<Interval<K>> intervals;
	
	public DisjointIntervalSet() {
		intervals = new TreeSet<Interval<K>>();
	}
	
	@Override
	public boolean add(Interval<K> i) {
		
		SortedSet<Interval<K>> s = intervals.tailSet(i);
		if (s.isEmpty() || s.first().compareTo(i) != 0) {
			intervals.add(i);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void clear() {
		intervals.clear();
	}
	
	@Override
	public boolean contains(K x) {
		SortedSet<Interval<K>> ts =
				intervals.tailSet(new Interval<K>(x,x)); // Find stuff >= [i,i)
		if (!ts.isEmpty()) {
			Interval<K> u = ts.first(); // if it's there, it's in
			// the first interval
			if (u.contains(x)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DumbIntervalSet.runTests(new DisjointIntervalSet<Integer>());
	}
	
}
