package controller;

import java.util.ArrayList;

import models.Healer;
import models.Monster;
import models.Player;
import models.Warrior;
import models.Wizard;

public class PlayerManager {

	private static PlayerManager instance = new PlayerManager();
	private PlayerManager() {};
	public static PlayerManager getInstance() {
		return instance;
	}
	
	private ArrayList<Player>player_list = null;
	
	public ArrayList<Player> pickPlayer(int num){
		player_list = new ArrayList<>();
		for(int i=0;i<num;i++) {
			int r = GameManager.ran.nextInt(3);
			int maxHp = GameManager.ran.nextInt(100)+200;
			int att = GameManager.ran.nextInt(30)+50;
			
			if(r == 0) {
				String name = String.format("전사%d", i+1);
				Warrior warrior = new Warrior(name, maxHp, att);
				player_list.add(warrior);
			}else if(r==1) {
				String name = String.format("힐러%d", i+1);
				Healer healer = new Healer(name, maxHp, att);
				player_list.add(healer);
			}else if(r==2) {
				String name = String.format("마법사%d", i+1);
				Wizard wizard = new Wizard(name, maxHp, att);
				player_list.add(wizard);
			}
		}
		return player_list;
	}
	
}
