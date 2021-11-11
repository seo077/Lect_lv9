package models;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Map {
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		
		this.fileName = makeFileName(this.state);
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(70, 70,Image.SCALE_SMOOTH);
		this.im = new ImageIcon(temp);
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

	public boolean isPlayer() {
		return player;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}

	public ImageIcon getIm() {
		return im;
	}


	public static final int BOMB = 1;
	public static final int BRICK = 2;
	public static final int GRASS = 3;
	public static final int ITEM = 4;
	
	private int state;
	private int x,y,w,h;
	private boolean player;
	private String fileName;
	private ImageIcon im;
	
	public Map(int state, int x, int y, int w, int h) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.fileName = makeFileName(this.state);
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(70, 70,Image.SCALE_SMOOTH);
		this.im = new ImageIcon(temp);
		
	}

	private String makeFileName(int state) {
		String name = "";
		if(this.state == this.BOMB) {
			name = "images/bomb.jpg";
		}else if(this.state == this.BRICK) {
			name = "images/brick.jpeg";
		}else if(this.state == this.GRASS) {
			name = "images/grass.jpg";
		}else if(this.state == this.ITEM) {
			name = "images/item.jpg";
		}
		return name;
	}
}
