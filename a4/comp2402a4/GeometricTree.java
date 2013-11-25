package comp2402a4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {
	
	public GeometricTree() {
		super(new GeometricTreeNode());
	}
	
	public void inorderDraw() {
		assignLevels();
		// TODO: use your code here instead
		
		GeometricTreeNode w = firstNode();
		for (int i = 0; w != nil; i++) {
			w.position.x = i;
			w = nextNode(w);
		}
	}
	
	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) {
			return;
		}
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}
	
	/**
	 * Draw each node so that it's x-coordinate is as small as possible without
	 * intersecting any other node at the same level the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();
		
		GeometricTreeNode d = r;
		d.position.x = 0;
		
		Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
		q.add(r);
		while (!q.isEmpty()) {
			GeometricTreeNode u = q.remove();
			
			if (d.position.y == u.position.y) {
				u.position.x = d.position.x + 1;
				d = u;
			} else {
				u.position.x = 0;
				d = u;
			}
			
			if (u.left != nil) {
				q.add(u.left);
			}
			if (u.right != nil) {
				q.add(u.right);
			}
		}
		
		r.position.x = 0;
		
	}
	
	public void balancedDraw() {
		
		// assign sizes
		calculateSizes(r);
		int x = 0;
		int y = 0;
		GeometricTreeNode current = firstNode();
		while (current != nil) {
			current.size = size3(current);
			current = nextNodePost(current);
		}
		current = r;
		do {
			while (current.left != nil || current.right != nil) {
				if (current.right != nil && current.left != nil) {
					current = smallerChild(current);
					y++;
					
				} else {
					if (current.left != nil) {
						current = current.left;
					} else {
						current = current.right;
					}
					x++;
					
				}
				setPosition(current, x, y);
				
			}
			do {
				current = current.parent;
			} while (current != nil
					&& ((current.left == nil || current.right == nil) || largerChild(current).position.x > 0));
			if (current == nil) {
				break;
			}
			y = current.position.y;
			current = largerChild(current);
			current.position.y = y;
			x++;
			current.position.x = x;
		} while (true);
		
	}
	
	private void calculateSizes(GeometricTreeNode u) {
		if (u == null) {
			return;
		}
		u.position.x = 0;
		calculateSizes(u.left);
		calculateSizes(u.right);
	}
	
	private void setPosition(GeometricTreeNode current, int x, int y) {
		current.position.x = x;
		current.position.y = y;
	}
	
	protected void assignLevels() {
		assignLevels(r, 0);
	}
	
	private int size3(GeometricTreeNode u) {
		if (u.left != nil && u.right != nil) {
			return 1 + u.left.size + u.right.size;
		}
		
		if (u.left != nil) {
			return 1 + u.left.size;
			
		} else if (u.right != nil) {
			return 1 + u.right.size;
		}
		
		return 1;
	}
	
	private GeometricTreeNode nextNodePost(GeometricTreeNode u) {
		if (u.parent != nil && u.parent.left == u) {
			u = u.parent;
			if (u.right != nil) {
				u = u.right;
				while (u.left != nil || u.right != nil) {
					while (u.left != nil) {
						u = u.left;
					}
					if (u.right != nil) {
						u = u.right;
					}
				}
			}
		} else {
			u = u.parent;
		}
		return u;
	}
	
	private GeometricTreeNode smallerChild(GeometricTreeNode u) {
		
		if (u.left.size < u.right.size) {
			return u.left;
		} else {
			return u.right;
		}
	}
	
	private GeometricTreeNode largerChild(GeometricTreeNode u) {
		
		if (u.right.size > u.left.size) {
			return u.right;
		} else {
			return u.left;
		}
	}
	
	protected void assignLevels(GeometricTreeNode u, int currentLevel) {
		if (u == null) {
			return;
		}
		u.position.y = currentLevel;
		assignLevels(u.left, currentLevel + 1);
		assignLevels(u.right, currentLevel + 1);
	}
	
	// public static void main(String[] args) {
	// GeometricTree t = new GeometricTree();
	// galtonWatsonTree(t, 100);
	// System.out.println(t);
	// t.inorderDraw();
	// System.out.println(t);
	// }
	
}
