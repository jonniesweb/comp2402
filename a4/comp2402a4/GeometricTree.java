package comp2402a4;

import java.util.Random;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	public GeometricTree() {
		super (new GeometricTreeNode());
	}
	
	public void inorderDraw() {
		assignLevels();
		// TODO: use your code here instead
		Random rand = new Random();
		randomX(r, rand);
	}
	
	
	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) return;
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}
	
	
	/**
	 * Draw each node so that it's x-coordinate is as small
	 * as possible without intersecting any other node at the same level 
	 * the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();
		Random rand = new Random();
		randomX(r, rand);
	}
	
	
	public void balancedDraw() {
		assignLevels();
		Random rand = new Random();
		randomX(r, rand);
	}
		
	protected void assignLevels() {
		assignLevels(r, 0);
	}
	
	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}
	
	public static void main(String[] args) {
		GeometricTree t = new GeometricTree();
		galtonWatsonTree(t, 100);
		System.out.println(t);
		t.inorderDraw();
		System.out.println(t);
	}
	
}
