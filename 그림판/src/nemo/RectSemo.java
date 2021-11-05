package nemo;

public class RectSemo {
	private int x[] ,y[];
	private int cnt = 3;
	private boolean end;
	private int color;
	
	public RectSemo() {
		this.setX(new int[this.cnt]);
		this.setY(new int[this.cnt]);
	}

	public int[] getX() {
		return x;
	}

	public void setX(int x[]) {
		this.x = x;
	}

	public int[] getY() {
		return y;
	}

	public void setY(int y[]) {
		this.y = y;
	}
	
	public int getCnt() {
		return this.cnt;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
