package °æ¸¶;

public class Pos {
	private int x;
	private int y;
	private boolean goal;
	
	public Pos(int x, int y) {
		this.setX(x);
		this.y = y;
		this.goal = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	
	
}
