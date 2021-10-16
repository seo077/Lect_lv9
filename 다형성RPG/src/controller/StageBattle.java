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

	
	private int diePlayer = 0;
	private int dieMonster = 0;
	
	@Override
	public boolean run() {
		GameManager gm = GameManager.getInstance();
		while(true) {
			System.out.println("=====[BATTLE]=====");
			gm.printCharacterInfo();
			gm.battle();
			if(playerWin()) {
				System.out.println("승리했다!!");
				return true;
			}
			if(playerLose()) {
				System.out.println("패배했다...");
				return false;
			}
			
		}
	}
	
	private boolean playerWin() {
		if(this.dieMonster == GameManager.enemyNum) {
			return true;
		}
		return false;
	}
	private boolean playerLose() {
		if(this.diePlayer == GameManager.playerNum) {
			return true;
		}
		return false;
	}



}
