package it.univr.is.database;

public class Tripla <T extends Object> {
	private T left;
	private T center;
	private T right;
	
	public T getLeft() {
		return left;
	}
	
	public void setLeft(T left) {
		this.left = left;
	}
	
	public T getCenter() {
		return center;
	}
	
	public void setCenter(T center) {
		this.center = center;
	}
	
	public T getRight() {
		return right;
	}
	
	public void setRight(T right) {
		this.right = right;
	}
	
	@SuppressWarnings("unchecked") // T estende Object per definizione
	public T[] toArray(){
		return (T[]) new Object[] {left, center, right};
	}
	
}
