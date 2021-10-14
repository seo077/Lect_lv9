package controller;

import java.util.ArrayList;
import java.util.Collection;

import models.Hero;
import models.Unit;
import models.Zombie;
import models.ZombieKing;

public class Game {
	private static Game instance = new Game();
	private Game() {};
	public static Game getInstance() {
		return instance;
	}
	
	private Hero player;
	private ArrayList<Unit>enemy = new ArrayList<>();
	
	private void setGame() {
		this.player = new Hero("용사", 100, 5, 1, 1);
		
		this.enemy.add(new Zombie("그냥 좀비", 25, 5, 1, 3));
		this.enemy.add(new Zombie("힘센 좀비", 45, 10, 2, 6));
		this.enemy.add(new Zombie("정예 좀비", 65, 15, 3, 9));
		this.enemy.add(new ZombieKing("좀비왕", 100, 20, 4, 12, 50));
	}
	public void run(){
		setGame();
		printMenu();
	}
	
	private void printMenu() {
		// TODO Auto-generated method stub
		
	}
}
