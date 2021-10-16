package controller;

import java.util.ArrayList;

import models.M_Bat;
import models.M_Orc;
import models.M_Wolf;
import models.Monster;

public class MonsterManager {
	private static MonsterManager instance = new MonsterManager();
	private MonsterManager() {}
	public static MonsterManager getInstance() {
		return instance;
	}
	
	private String[]monster = {"¹ÚÁã","¿ÀÅ©","´Á´ë"};
	
	public void enemySet(ArrayList<Monster> enemy) {
		for(int i=0;i<GameManager.enemyNum;i++) {
			int ran = GameManager.ran.nextInt(monster.length);
			if(ran == 0) {
				int power[] = ranpower();
				enemy.add(new M_Bat(monster[ran], power[0], power[1]));
			}else if(ran == 1) {
				int power[] = ranpower();
				enemy.add(new M_Orc(monster[ran], power[0], power[1]));
			}else if(ran == 2) {
				int power[] = ranpower();
				enemy.add(new M_Wolf(monster[ran], power[0], power[1]));
			}
		}
	}
	
	private int[] ranpower() {
		int maxHp = GameManager.ran.nextInt(200) + 100;
		int power = GameManager.ran.nextInt(10) + 10;
		int ranNum[] = {maxHp,power};
		return ranNum;
	}
}
