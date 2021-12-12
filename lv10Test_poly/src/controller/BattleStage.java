package controller;

import java.util.ArrayList;

import models.Healer;
import models.Monster;
import models.Orc;
import models.Player;
import models.Warrior;
import models.Wizard;

public class BattleStage extends Stage{

	private static BattleStage instance = new BattleStage();
	private BattleStage() {};
	public static BattleStage getInstance() {
		return instance;
	}
	
	//전투 인원
	private final int NUM = 3;
	private MonsterManager mm = MonsterManager.getInstance();
	private PlayerManager pm = PlayerManager.getInstance();
	
	private ArrayList<Monster>mon_list;
	private ArrayList<Player>player_list;
	
	@Override
	public void init() {
		this.mon_list = new ArrayList<>();
		this.player_list = new ArrayList<>();
		
		this.mon_list = mm.pickMonster(NUM);
		this.player_list = pm.pickPlayer(NUM);
	}
	
	@Override
	public boolean nextStage() {
		for(int i=0;i<NUM;i++) {
			selMenu(i);
			if(win()) {
				System.out.println("게임에서 승리하였습니다.!!!");
				GameManager.nextStage="LOBBY";
				return true;
			}
		}
		monsterAttack();
		if(lose()) {
			System.out.println("게임에서 패배하였습니다...");
			GameManager.nextStage="LOBBY";
			return true;
		}
		return false;
	}
	
	private void monsterAttack() {
		battleInfo();
		System.out.println("===== [몬스터들의 공격] =====");
		for(int i=0;i<NUM;i++) {
			int r = GameManager.ran.nextInt(3);
			Monster mon = this.mon_list.get(i);
			if(r== 0) {//스킬 사용
				if(mon.getName().contains("오크")) {
					Orc orc = (Orc)mon;
					orc.skill(player_list);
				}else {
					int sel = selPlayer();
					mon.skill(this.player_list.get(sel));
				}
			}else {//공격
				int sel = selPlayer();
				this.player_list.get(sel).attacked(mon.getName(), mon.getAtt());
			}
		}
	}
	
	private int selPlayer() {
		while(true) {
			int sel = GameManager.ran.nextInt(NUM);
			
			if(this.player_list.get(sel).getHp() > 0) {
				return sel;
			}
		}
		
	}
	
	private boolean lose() {
		int cnt = 0;
		for(int i=0;i<NUM;i++) {
			if(this.player_list.get(i).isDie()) {
				cnt++;
			}
		}
		
		if(cnt == this.NUM) {
			return true;
		}
		return false;
	}
	
	private boolean win() {
		int cnt = 0;
		for(int i=0;i<NUM;i++) {
			if(this.mon_list.get(i).isDie()) {
				cnt++;
			}
		}
		
		if(cnt == this.NUM) {
			return true;
		}
		return false;
	}
	
	private void selMenu(int turn) {
		battleInfo();
		
		Player p = this.player_list.get(turn);
		System.out.println("===== [메뉴 선택] =====");
		System.out.printf("[%s] [1.어택] [2.스킬]\n",this.player_list.get(turn).getName());
		int sel = checkInt();
		if(sel == 1) {
			if(!p.isMute()) {
				int r = selMonster();
				this.mon_list.get(r).attacked(p.getName(),p.getAtt());
			}else {
				System.out.print("침묵중 >>>\n");
				p.setMute(false);
			}
		}else {
			if(p.getName().contains("전사")) {
				int r = selMonster();
				Warrior wa = (Warrior)p;
				wa.skill(mon_list.get(r));
			}else if(p.getName().contains("힐러")) {
				Healer h = (Healer)p;
				h.skill(player_list);
			}else if(p.getName().contains("마법사")) {
				Wizard w = (Wizard)p;
				int r = selMonster();
				w.skill(mon_list.get(r), mon_list);
			}
		}
	}
	
	private int selMonster() {
		while(true) {
			int sel = GameManager.ran.nextInt(NUM);
			
			if(this.mon_list.get(sel).getHp() > 0) {
				return sel;
			}
		}
		
	}
	
	private int checkInt() {
		while(true){
			String temp = GameManager.scan.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(temp);
				if(sel == 1 || sel == 2) {
					return sel;
				}else {
					System.out.println("1 또는 2를 입력하세요");
				}
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
		}
	}

	private void battleInfo() {
		System.out.println("===== [BATTLE] =====");
		System.out.println("===== [PLAYER] =====");
		printPlayerInfo();
		System.out.println("===== [MONSTER] =====");
		printMonsterInfo();
	}
	
	private void printPlayerInfo() {
		for(int i=0;i<NUM;i++) {
			player_list.get(i).printInfo();
		}
	}
	private void printMonsterInfo() {
		for(int i=0;i<NUM;i++) {
			mon_list.get(i).printInfo();
		}
	}
	

}
