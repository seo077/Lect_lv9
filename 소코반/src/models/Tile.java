package models;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {
	public static final int ROAD = 1;
	public static final int WALL = 2;
	public static final int BALL = 4;
	public static final int GOAL = 5;
	public static final int BALL_ENTERED = 6;
	
	private int state;
	private boolean player;
	private int x,y,w,h;
	
	private String fileName;
	private ImageIcon image;
	
	public Tile(int state, int x, int y, int w, int h) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.fileName = String.format("images/tile%d.png", this.state);
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(temp);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		
		this.fileName = String.format("images/tile%d.png", this.state);
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(temp);
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

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String getFileName() {
		return fileName;
	}


	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public boolean isPlayer() {
		return player;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}
}
