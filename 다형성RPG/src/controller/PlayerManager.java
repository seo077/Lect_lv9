package controller;

import java.util.ArrayList;

import models.P_Healer;
import models.P_Warrior;
import models.P_Wizard;
import models.Player;

public class PlayerManager {

	private ArrayList<Player> player = new ArrayList<>();
	private void playerSet() {
		player.add(new P_Warrior("����", 1000, 45, 3));
		player.add(new P_Wizard("������", 800, 60, 3));
		player.add(new P_Healer("����", 500, 70, 5));
	}
}
