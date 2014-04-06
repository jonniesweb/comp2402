package comp2402a5;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * An unfinished implementation of an AVL tree (for exercises)
 * @author morin
 *
 * @param <T>
 */
public class AVLTree<T> extends
BinarySearchTree<AVLTree.Node<T>, T> implements SSet<T> {
	/**
	 * A random number source
	 */
	Random rand;
	
	public static class Node<T> extends BSTNode<Node<T>,T> {
		int h;  // the height of the node
	}
	
	public AVLTree() {
		sampleNode = new Node<T>();
		rand = new Random();
		c = new DefaultComparator<T>();
	}
	
	@Override
	public int height(Node<T> u) {
		return (u == null) ? 0 : u.h;
	}
	
	@Override
	public boolean add(T x) {
		Node<T> u = new Node<T>();
		u.x = x;
		if (super.add(u)) {
			for (Node<T> w = u; w != nil; w = w.parent) {
				// walk back up to the root adjusting heights
				w.h = Math.max(height(w.left), height(w.right)) + 1;
			}
			fixup(u);
			return true;
		}
		return false;
	}
	
	@Override
	public void splice(Node<T> u) {
		Node<T> w = u.parent;
		super.splice(u);
		for (Node<T> z = u; z != nil; z = z.parent) {
			z.h = Math.max(height(z.left), height(z.right)) + 1;
		}
		fixup(w);
	}
	
	public void checkHeights(Node<T> u) {
		if (u == nil) {
			return;
		}
		checkHeights(u.left);
		checkHeights(u.right);
		if (height(u) != 1 + Math.max(height(u.left), height(u.right))) {
			throw new RuntimeException("Check heights shows incorrect heights");
		}
		int dif = height(u.left) - height(u.right);
		if (dif < -1 || dif > 1) {
			throw new RuntimeException("Check heights found height difference of " + dif);
		}
	}
	
	/**
	 * TODO: finish writing this method
	 * @param u
	 */
	public void fixup(Node<T> u) {
		while (u != nil) {
			int dif = height(u.left) - height(u.right);
			if (dif > 1) {
				if(height(u.left.left) - height(u.left.right) < 0) {
					rotateLeft(u.left);
				}
				rotateRight(u);
				// TODO: add code here to fix AVL condition on the path from u to the root, if necessary
			} else if (dif < -1) {
				if(height(u.right.right) - height(u.right.left) < 0) {
					rotateRight(u.right);
				}
				rotateLeft(u);
				// TODO: add code here to fix AVL condition on the path from u to the root, if necessary
			}
			u = u.parent;
		}
	}
	
	@Override
	public void rotateLeft(Node<T> u) {
		super.rotateLeft(u);
		// TODO: Recompute height values at u and u.parent
		for (Node<T> w = u; w != nil; w = w.parent) {
			// walk back up to the root adjusting heights
			w.h = Math.max(height(w.left), height(w.right)) + 1;
		}
	}
	
	@Override
	public void rotateRight(Node<T> u) {
		super.rotateRight(u);
		// TODO: Recompute height values at u and u.parent
		for (Node<T> w = u; w != nil; w = w.parent) {
			// walk back up to the root adjusting heights
			w.h = Math.max(height(w.left), height(w.right)) + 1;
		}
	}
	
	public static <T> T find(SortedSet<T> ss, T x) {
		SortedSet<T> ts = ss.tailSet(x);
		if (!ts.isEmpty()) {
			return ts.first();
		}
		return null;
	}
	/**
	 * This just does some very basic correctness testing
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree<Integer> t = new AVLTree<Integer>();
		Random r = new Random(0);
		System.out.print("Running AVL tests...");
		int n = 1000;
		for (int i = 0; i < n; i++) {
			t.add(r.nextInt(2*n));
			t.checkHeights(t.r);
		}
		for (int i = 0; i < n; i++) {
			t.remove(r.nextInt(2*n));
			t.checkHeights(t.r);
		}
		System.out.println("done");
		
		t.clear();
		System.out.print("Running correctness tests...");
		n = 100000;
		SortedSet<Integer> ss = new TreeSet<Integer>();
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			Integer x = rand.nextInt(2*n);
			boolean b1 = t.add(x);
			boolean b2 = ss.add(x);
			if (b1 != b2) {
				throw new RuntimeException("Adding " + x + " gives " + b2
						+ " in SortedSet and " + b1 + " in AVL Tree");
			}
		}
		for (int i = 0; i < n; i++) {
			Integer x = rand.nextInt(2*n);
			Integer x1 = t.find(x);
			Integer x2 = find(ss, x);
			if (x1 != x2) {
				throw new RuntimeException("Searching " + x + " gives " + x2
						+ " in SortedSet and " + x1 + " in AVL Tree");
			}
			ss.headSet(x);
		}
		for (int i = 0; i < n; i++) {
			Integer x = rand.nextInt(2*n);
			boolean b1 = t.remove(x);
			boolean b2 = ss.remove(x);
			if (b1 != b2) {
				throw new RuntimeException("Error (2): Removing " + x + " gives " + b2
						+ " in SortedSet and " + b1 + " in AVL Tree");
			}
		}
		for (int i = 0; i < n; i++) {
			Integer x = rand.nextInt(2*n);
			Integer x1 = t.find(x);
			Integer x2 = find(ss, x);
			if (x1 != x2) {
				throw new RuntimeException("Error (3): Searching " + x + " gives " + x2
						+ " in SortedSet and " + x1 + " in AVL Tree");
			}
			ss.headSet(x);
		}
		System.out.println("done");
		
	}
}
