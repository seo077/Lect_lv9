package snake_gui;

public class Rect {
	private int x,y,w,h;
	private boolean snake;
	private boolean item;

	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public boolean isSnake() {
		return snake;
	}

	public void setSnake(boolean snake) {
		this.snake = snake;
	}

	public boolean isItem() {
		return item;
	}

	public void setItem(boolean item) {
		this.item = item;
	}

	
}
