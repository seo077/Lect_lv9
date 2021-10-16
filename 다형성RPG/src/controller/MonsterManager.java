package controller;

import java.util.ArrayList;

import models.M_Bat;
import models.M_Orc;
import models.M_Wolf;
import models.Monster;

public class MonsterManager {
	
	private String[]monster = {"π⁄¡„","ø¿≈©","¥¡¥Î"};
	private ArrayList<Monster> enemy = new ArrayList<>();
	
	private void enemySet() {
		for(int i=0;i<4;i++) {
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
