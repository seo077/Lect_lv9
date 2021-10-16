package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.M_Bat;
import models.M_Orc;
import models.M_Wolf;
import models.Monster;
import models.P_Healer;
import models.P_Warrior;
import models.P_Wizard;
import models.Player;

public class GameManager {
	public static Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);

	private static GameManager instance = new GameManager();

	private GameManager() {
	};

	public static GameManager getInstance() {
		return instance;
	}

	private ArrayList<Player> player = new ArrayList<>();
	private ArrayList<Monster> enemy = new ArrayList<>();

	public void run() {
		gameSet();
	}

	private void gameSet() {
		playerSet();
		enemySet();
	}

	private void playerSet() {
		player.add(new P_Warrior("Àü»ç", 1000, 45, 3));
		player.add(new P_Wizard("¸¶¹ý»ç", 800, 60, 3));
		player.add(new P_Healer("Èú·¯", 500, 70, 5));
	}

	private void enemySet() {
		int maxHp = this.ran.nextInt(200)+100;
		int power = this.ran.nextInt(10)+10;
		enemy.add(new M_Bat("¹ÚÁã", maxHp, power));
		maxHp = this.ran.nextInt(200)+100;
		power = this.ran.nextInt(10)+10;
		enemy.add(new M_Orc("¿ÀÅ©", maxHp, power));
		maxHp = this.ran.nextInt(200)+100;
		power = this.ran.nextInt(10)+10;
		enemy.add(new M_Wolf("´Á´ë", maxHp, power));
	}

}
