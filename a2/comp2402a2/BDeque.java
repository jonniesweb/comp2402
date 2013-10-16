package comp2402a2;

public class BDeque<T> extends ArrayDeque<T> {
	public BDeque(int b, Class<T> clz) {
		super(clz);
		a = f.newArray(b);
	}
	
	protected void resize() {
		// do nothing - BDeques have a fixed capacity
	}
	
	public void pushFront(T x) {
		add(0, x);
	}
	
	public T popFront() {
		return remove(0);
	}
	
	public void pushBack(T x) {
		add(n, x);
	}
	
	public T popBack() {
		return remove(n-1);
	}
}
