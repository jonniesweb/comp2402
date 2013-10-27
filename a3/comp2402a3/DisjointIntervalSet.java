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
	
	public boolean add(Interval<K> i) {
		// TODO: add checking to see if i overlaps anything in the set
		//       -- if so, don't add it and return false. Otherwise, add 
		// it and return true
		return false;
	}

	public void clear() {
		intervals.clear();
	}

	public boolean contains(K x) {
		// TODO Add code for searching here.  See Interval.main() for an example
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DumbIntervalSet.runTests(new DisjointIntervalSet<Integer>());
	}

}
