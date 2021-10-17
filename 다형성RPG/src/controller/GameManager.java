package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Monster;
import models.P_Healer;
import models.P_Warrior;
import models.P_Wizard;
import models.Player;

public class GameManager {
	public static Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);
	public static final int enemyNum = 4;
	public static final int playerNum = 3;

	private static GameManager instance = new GameManager();

	private GameManager() {
	};

	public static GameManager getInstance() {
		return instance;
	}
	
	private ArrayList<Monster>enemy = new ArrayList<>();
	private ArrayList<Player>player = new ArrayList<>();
	
	private void setEnemy() {
		MonsterManager.getInstance().enemySet(enemy);
	}
	

	private void setPlayer() {
		PlayerManager.getInstance().playerSet(player);
	}
	
	public void printCharacterInfo() {
		System.out.println("=====[PLAYER]=====");
		//플레이어 정보 프린트
		int size = player.size();
		for(int i=0;i<size;i++) {
			player.get(i).printInfo();
		}
		System.out.println("=====[MONSTER]=====");
		//몬스터 정보 프린트
		size = enemy.size();
		for(int i=0;i<size;i++) {
			enemy.get(i).printInfo();
		}
		
	}

	public void run() {
		if (StageTitle.getInstance().run()) {
			this.setPlayer();
			while (true) {
				if (StageLobby.getInstance().run()) {
					this.setEnemy();
					if(!StageBattle.getInstance().run()) {
						break;
					}
				}else {
					break;
				}
			}

		}

	}

	public static int checkInt(String temp) {
		int sel = -1;
		while (true) {
			try {
				sel = Integer.parseInt(temp);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (sel == -1) {
				continue;
			}
			break;
		}
		return sel;
	}

	public void battle() {
		playerattack();
	}
	
	
	private void playerattack() {
		for(int i=0;i<this.playerNum;i++) {
			if(i != 0) {
				this.printCharacterInfo();
			}
			int ranEnemy = ran.nextInt(this.enemyNum);
			
			System.out.println("=====[메뉴선택]=====");
			System.out.printf("[%s] [1.어택] [2.스킬]\n",this.player.get(i).getName());
		
			String temp = this.scan.next();
			int sel = checkInt(temp);
			
			if(sel == 1) {
				this.player.get(i).attack(this.enemy.get(ranEnemy).getName());
				this.enemy.get(ranEnemy).damage(this.player.get(i).getPower());
			}else if(sel == 2) {
				playerUseSkill(i,ranEnemy);
				this.player.get(i).setCurSkillCnt(-1);
			}else {
				i--;
			}
		}
	}

	private void playerUseSkill(int idx,int ranEnemy) {
		if(idx == 0) {
			P_Warrior warrior = (P_Warrior) this.player.get(idx);
			warrior.skill();
		}else if(idx == 1) {
			P_Wizard wizard = (P_Wizard) this.player.get(idx);
			wizard.skill();
		}else if(idx == 2) {
			P_Healer healer = (P_Healer) this.player.get(idx);
			
			P_Warrior warrior = (P_Warrior) this.player.get(0);
			P_Wizard wizard = (P_Wizard) this.player.get(1);
			healer.skill(warrior);
			healer.skill(wizard);
		}
	}

}
