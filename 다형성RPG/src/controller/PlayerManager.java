package controller;

import java.util.ArrayList;

import models.P_Healer;
import models.P_Warrior;
import models.P_Wizard;
import models.Player;

public class PlayerManager {

	private static PlayerManager instance = new PlayerManager();
	private PlayerManager() {};
	public static PlayerManager getInstance() {
		return instance;
	}
	
	public void playerSet(ArrayList<Player> player) {
		player.add(new P_Warrior("전사", 1000, 45, 3));
		player.add(new P_Wizard("마법사", 800, 60, 3));
		player.add(new P_Healer("힐러", 500, 70, 5));
	}
}
