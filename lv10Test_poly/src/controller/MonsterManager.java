package controller;

import java.util.ArrayList;

import models.Bat;
import models.Monster;
import models.Orc;
import models.Wolf;

public class MonsterManager {
	private static MonsterManager instance = new MonsterManager();
	private MonsterManager() {};
	public static MonsterManager getInstance() {
		return instance;
	}
	
	private ArrayList<Monster>mon_list = null;
	
	public ArrayList<Monster> pickMonster(int num){
		mon_list = new ArrayList<Monster>();
		for(int i=0;i<num;i++) {
			int r = GameManager.ran.nextInt(3);
			int maxHp = GameManager.ran.nextInt(100)+100;
			int att = GameManager.ran.nextInt(30)+30;
			
			if(r == 0) {
				String name = String.format("¿ÀÅ©%d", i+1);
				Orc orc = new Orc(name, maxHp, att);
				mon_list.add(orc);
			}else if(r==1) {
				String name = String.format("´Á´ë%d", i+1);
				Wolf wolf = new Wolf(name, maxHp, att);
				mon_list.add(wolf);
			}else if(r==2) {
				String name = String.format("¹ÚÁã%d", i+1);
				Bat bat = new Bat(name, maxHp, att);
				mon_list.add(bat);
			}
		}
		return mon_list;
	}
}
