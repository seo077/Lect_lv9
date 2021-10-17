package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Cheatable;
import models.M_Bat;
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
	
	
	private static int diePlayer = 0;
	private static int dieMonster = 0;

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
					this.dieMonster = 0;
					this.setEnemy();
					if(!StageBattle.getInstance().run()) {
						if(this.dieMonster == this.enemyNum) {
							continue;
						}
						if(this.diePlayer == this.playerNum) {
							System.out.println("게임 모버");
							break;
						}
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

	public boolean battle() {
		if(playerattack()) {
			return false;
		}
	
		return true;
	}
	
	
	private boolean playerattack() {
		for(int i=0;i<this.player.size();i++) {
			if(i != 0) {
				this.printCharacterInfo();
			}
			int ranEnemy = ran.nextInt(this.enemy.size());
			
			System.out.println("=====[메뉴선택]=====");
			System.out.printf("[%s] [1.어택] [2.스킬]\n",this.player.get(i).getName());
		
			String temp = this.scan.next();
			int sel = checkInt(temp);
			
			if(sel == 1) {
				this.player.get(i).attack(this.enemy.get(ranEnemy).getName());
				this.enemy.get(ranEnemy).damage(this.player.get(i).getPower());
			}else if(sel == 2) {
				if(this.player.get(i).getCurSkillCnt() != 0) {
					playerUseSkill(i,ranEnemy);
					this.player.get(i).setCurSkillCnt(-1);
				}else {
					System.out.println("스킬을 사용할 수 없습니다.");
				}
			}else {
				i--;
			}
			checkDieEnemy();
			if(this.dieMonster == this.enemyNum) {
				System.out.println("승리했다!!");
				return true;
			}
			if(this.diePlayer == this.playerNum) {
				System.out.println("패배했다...");
				return true;
			}
		}
		return false;
	}

	private void checkDieEnemy() {
		ArrayList<Monster>temp = new ArrayList<>();
		for(int i=0;i<this.enemy.size();i++) {
			if(this.enemy.get(i).getCurHp()>0) {
				temp.add(this.enemy.get(i));
			}else {
				System.out.printf("[%s]를 처치했습니다.\n",this.enemy.get(i).getName());
				this.dieMonster++;
			}
		}
		this.enemy = temp;
		temp = null;
	}

	private void playerUseSkill(int idx,int ranEnemy) {
		String name = this.player.get(idx).getName();
		System.out.printf("[%s가 스킬을 사용합니다.]\n",name);
		
		if(name.equals("전사")) {
			while(true) {
				P_Warrior warrior = (P_Warrior)this.player.get(idx);
				System.out.println("[적 중 한명을 100의 데미지로 공격합니다.]");
				int size = enemy.size();
				for(int i=0;i<size;i++) {
					System.out.print("("+(i+1)+")");
					enemy.get(i).printInfo();
				}
				String temp = this.scan.next();
				int sel = checkInt(temp)-1;
				
				if(sel >=0 && sel <=size) {
					warrior.skill(this.enemy.get(sel));
					break;
				}else {
					break;
				}
			}
		}else if(name.equals("마법사")) {
			P_Wizard wizard = (P_Wizard) this.player.get(idx);
			System.out.println("[적들이 자기 자신을 공격합니다.]");
			for(int i=0;i<this.enemy.size();i++) {
				try {
					Cheatable cheat = (Cheatable)this.enemy.get(i);
					wizard.skill((Cheatable) this.enemy.get(i));
				} catch (Exception e) {
					System.out.printf("[%s]는 마법사스킬의 영향을 받지 않습니다.\n",this.enemy.get(i).getName());
				}
			}
		}else {
			P_Healer healer = (P_Healer) this.player.get(idx);
			System.out.println("[힐러가 스킬을 사용했습니다.]");
			System.out.println("[힐러가 팀원들의 치료를 시작합니다.]");
			
			P_Warrior warrior = (P_Warrior) this.player.get(0);
			P_Wizard wizard = (P_Wizard) this.player.get(1);
			healer.skill(warrior);
			healer.skill(wizard);
		}
		
		
	}

}
