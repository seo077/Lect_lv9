package controller;

import java.util.ArrayList;

import models.Monster;
import models.Player;

public class StageBattle extends Stage{
	private static StageBattle instance = new StageBattle();
	private StageBattle() {}
	public static StageBattle getInstance() {
		return instance;
	}


	
	@Override
	public boolean run() {
		GameManager gm = GameManager.getInstance();
		while(true) {
			System.out.println("=====[BATTLE]=====");
			gm.printCharacterInfo();
			if(!gm.battle()) {
				return false;
			}
		}
	}



}
