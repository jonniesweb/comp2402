package comp2402a3;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class implements the IntervalSet interface for storing a set of
 * intervals, which may or may not be disjoint.
 * 
 * @author morin
 * 
 * @param <K>
 */
public class OverlappingIntervalSet<K extends Comparable<K>> implements
IntervalSet<K> {
	SortedSet<Interval<K>> intervals;
	
	public OverlappingIntervalSet() {
		intervals = new TreeSet<Interval<K>>();
	}
	
	@Override
	public boolean add(Interval<K> i) {
		
		// voodoo
		SortedSet<Interval<K>> s = intervals.tailSet(i);
		if (s.isEmpty() || s.first().compareTo(i) != 0) {
			intervals.add(i);
			return true;
		}
		// end voodoo
		
		SortedSet<Interval<K>> hs = intervals.headSet(new Interval<K>(i.a, i.a));
		SortedSet<Interval<K>> ts = intervals.tailSet(new Interval<K>(i.b, i.b));
		SortedSet<Interval<K>> its = intervals.tailSet(new Interval<K>(i.a, i.a));
		
		if (!hs.isEmpty() && hs.last().contains(i.a)) {
			i.a = hs.last().b;
		} else if (!its.isEmpty() && its.first().a.compareTo(i.a) < 0) {
			i.a = its.first().a;
		}
		
		if (!ts.isEmpty() && ts.first().contains(i.b)) {
			i.b = ts.first().a;
		}
		
		SortedSet<Interval<K>> newSet = new TreeSet<Interval<K>>();
		newSet.addAll(hs);
		newSet.addAll(ts);
		newSet.add(i);
		
		//		System.out.println();
		//		System.out.println("ADDING ELEMENT");
		//		System.out.println("set: " + intervals);
		//		System.out.println("i: " + i);
		//		System.out.println("hs: " + hs);
		//		System.out.println("ts: " + ts);
		//		System.out.println("its: " + its);
		//		System.out.println("newset: " + newSet);
		//		System.out.println();
		
		intervals = newSet;
		
		return true;
		
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
			
			SortedSet<Interval<K>> tsn = ts.tailSet(new Interval<K>(ts.first().b, ts.first().b));
			
			
			Interval<K> u = ts.first();
			if (u.contains(x)) {
				return true;
			}
			
			if (!tsn.isEmpty() && tsn.first().a.equals(x)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// run some tests for disjoint intervals
		DumbIntervalSet.runTests(new OverlappingIntervalSet<Integer>());
		
		OverlappingIntervalSet<Integer> s = new OverlappingIntervalSet<Integer>();
		
		System.out.println("adding elements...");
		add(s, 1, 3);
		add(s, 5, 6);
		add(s, 6, 6);
		add(s, 7, 9);
		add(s, 12, 15);
		System.out.println("Done. Adding test element...");
		
		System.out.println(s.intervals);
		
		add(s, 13, 14);
		
		System.out.println(s.intervals);
		System.out.println("Finished");
		
		for (int i = 0; i < 16; i++) {
			System.out.println(i + " " + s.contains(i));
		}
		
	}
	
	private static void add(OverlappingIntervalSet<Integer> s, int a, int b) {
		Assert(s.add(new Interval<Integer>(a, b)));
	}
	
	static void Assert(boolean a) {
		if (!a) {
			throw new AssertionError();
		}
	}
	
}