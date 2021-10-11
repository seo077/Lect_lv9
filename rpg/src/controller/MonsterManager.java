package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Monster;

public class MonsterManager {
	public static MonsterManager instance = new MonsterManager();
	private CharacterManager cm = CharacterManager.instance;
	
	
	private ArrayList<Monster>monster = new ArrayList<>();
	private ArrayList<Monster>battleMonster = new ArrayList<>();

	public void addMonster() {
		System.out.print("몬스터 이름 : ");
		String name = Rpg.scan.next();
		int level = 0;
		int hp = 0;
		int maxhp = 0;
		int att = 0;
		int def = 0;
		while(true) {
			System.out.print("몬스터 레벨 : ");
			String temp = Rpg.scan.next();
			level = intCheck(temp);
			if(level == -1) {
				continue;
			}
			break;
		}
		while(true) {
			System.out.print("몬스터 체력 : ");
			String temp = Rpg.scan.next();
			hp = intCheck(temp);
			if(level == -1) {
				continue;
			}
			break;
		}
		while(true) {
			System.out.print("몬스터 max체력 : ");
			String temp = Rpg.scan.next();
			maxhp = intCheck(temp);
			if(level == -1) {
				continue;
			}
			break;
		}
		while(true) {
			System.out.print("몬스터 공격력 : ");
			String temp = Rpg.scan.next();
			att = intCheck(temp);
			if(att == -1) {
				continue;
			}
			break;
		}
		while(true) {
			System.out.print("몬스터 방어력 : ");
			String temp = Rpg.scan.next();
			def = intCheck(temp);
			if(def == -1) {
				continue;
			}
			break;
		}
			
		this.monster.add(new Monster(name, level, hp, maxhp, att, def));
	}

	private int intCheck(String temp) {
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sel;
	}

	public void delMonster() {
		int size = this.monster.size();
		printAllMonster();
		int sel = Rpg.intSel()-1;
		if(sel>=0 && sel<size) {
			System.out.printf("<몬스터 이름 : %s> 삭제 완료\n",this.monster.get(sel).getName());
			this.monster.remove(sel);
		}
	}

	public void printAllMonster() {
		int size = this.monster.size();
		System.out.println(size);
		for(int i=0;i<size;i++) {
			Monster temp = this.monster.get(i);
			System.out.printf("(%d) <몬스터 이름 : %s> <레벨 : %d> <공격 : %d> <방어 : %d>\n",i+1,temp.getName(),temp.getLevel(),temp.getAtt(),temp.getDef());
		}
	}
	
	public ArrayList<Monster> pickBattleMonster() {
		int size = this.monster.size();
		Random ran = new Random();
		int monsterCnt = 3;
		ArrayList<Monster> monster = new ArrayList<>();
		int mon[] = new int[monsterCnt];
		for(int i=0;i<monsterCnt;) {
			int r = ran.nextInt(size);
			int check = -1;
			for(int j=0;j<monsterCnt;j++) {
				if(r == mon[j]) {
					check = j;
				}
			}
			
			if(check == -1) {
				mon[i] = r;
				String name = this.monster.get(r).getName();
				int level = this.monster.get(r).getLevel();
				int hp = this.monster.get(r).getHp();
				int maxhp = this.monster.get(r).getMaxHp();
				int att = this.monster.get(r).getAtt();
				int def = this.monster.get(r).getDef();
				
				Monster temp = new Monster(name, level,hp,maxhp, att, def);
				this.battleMonster.add(temp);
				monster.add(temp);
				i++;
			}
		}
		return monster;
	}
	


	@Override
	public String toString() {
		String data = "";
		int size = this.monster.size();
		for(int i=0;i<size;i++) {
			data += this.monster.get(i).getName()+"/";
			data += this.monster.get(i).getLevel()+"/";
			data += this.monster.get(i).getHp()+"/";
			data += this.monster.get(i).getMaxHp()+"/";
			data += this.monster.get(i).getAtt()+"/";
			data += this.monster.get(i).getDef();
			if(i != size-1) {
				data += "\n";
			}
		}
		return data;
	}

	public void setData(String[] temp) {
		String name = temp[0];
		int level = Integer.parseInt(temp[1]);
		int hp = Integer.parseInt(temp[2]);
		int maxhp = Integer.parseInt(temp[3]);
		int att = Integer.parseInt(temp[4]);
		int def = Integer.parseInt(temp[5]);
		this.monster.add(new Monster(name, level,hp,maxhp, att, def));
	}

	
	


}
