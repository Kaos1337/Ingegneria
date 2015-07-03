package it.univr.is.database;

public class TriplaString {
	private String left;
	private String center;
	private String right;
	
	public String getLeft() {
		return left;
	}
	
	public void setLeft(String left) {
		this.left = left;
	}
	
	public String getCenter() {
		return center;
	}
	
	public void setCenter(String center) {
		this.center = center;
	}
	
	public String getRight() {
		return right;
	}
	
	public void setRight(String right) {
		this.right = right;
	}
	
	public String[] toArray(){
		return new String[]{left, center, right};
	}
	
}
