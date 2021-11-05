package nemo;

import java.util.ArrayList;

public class RectBrush {
	private ArrayList<Integer>x;
	private ArrayList<Integer>y;
	private int size;
	private int color;
	
	public RectBrush() {
		this.x = new ArrayList<>();
		this.y = new ArrayList<>();
	}

	public void addX(int x) {
		this.x.add(x);
		this.size++;
	}
	public void addY(int y) {
		this.y.add(y);
	}
	public int getX(int idx) {
		return this.x.get(idx);
	}
	public int getY(int idx) {
		return this.y.get(idx);
	}

	public int getSize() {
		return size;
	}
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
