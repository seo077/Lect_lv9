package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import models.Character;
import models.Monster;

public class Rpg {
	public static Rpg instance = new Rpg();
	public static Scanner scan = new Scanner(System.in);
	public static int myMoney = 50000;
	public static int party = 0;

	private FileManager fm = FileManager.instance;
	private ItemManager im = ItemManager.instance;
	private CharacterManager cm = CharacterManager.instance;
	private RpgManager rm = RpgManager.instance;
	private MonsterManager mm = MonsterManager.instance;

	public void run() {
		fm.load(); // 상점아이템 캐릭터 몬스터 불러오기
		while (true) {
			printMainMenu();
			if (selMainMenu()) {
				break;
			}
		}
	}

	private void printMainMenu() {
		System.out.println("내 돈 : " + Rpg.myMoney + "원");
		String menu = "[1.길드 관리] [2.상점] [3.인벤토리] [4.저장] [5.로드] [6.전투]\n[7.관리자] [0.종료]";
		System.out.println(menu);
	}

	private boolean selMainMenu() {
		int sel = intSel();

		if (sel == 1) {
			cm.ownGuildManage();
		} else if (sel == 2) {
			im.shop();
		} else if (sel == 3) {
			im.inventory();
		} else if (sel == 4) {
			fm.ownSave();
		} else if (sel == 5) {
			fm.ownLoad();
		} else if (sel == 6) {
			if (cm.MyMemberSize() < 4) {
				System.out.println("[전투 불가] 파티원이 부족합니다.");
			} else {
				battle();
			}
		} else if (sel == 7) {
			if (checkManager()) {
				rm.manage();
			}
			fm.save();
		} else if (sel == 0) {
			return true;
		}

		return false;
	}

	private void battle() {
		Random ran = new Random();
		ArrayList<Monster> monster = mm.pickBattleMonster();
		ArrayList<Character> party = cm.partyMember();

		while (true) {
			int def = ran.nextInt(2);
			
			printMonster(monster);
			printParty(party);
			

			if(BattleMenu(monster,party,def)) {	
				break;
			}
		}

		
		// 파티원 정보 업데이트 + 사망한 파티원 교체
		if(party.size() == 0) {//몬스터 처치 실패
			cm.updateDieParty();
		}else if(monster.size() == 0) { //몬스터 처치 성공
			System.out.println("**보상 : 10000원");
			Rpg.myMoney += 10000;
			int size = party.size();
			cm.updateDieParty(party);
			for(int i=0;i<size;i++) {
				System.out.printf("파티원 <name : %s>의 레벨이 1올라갑니다.\n",party.get(i).getName());
			}
		}else {
			System.out.println("*전투를 중단합니다.");
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private boolean BattleMenu(ArrayList<Monster> monster, ArrayList<Character> party,int def) {
		System.out.println("[1.공격] [2.방어] [0.전투 중단]");
		int sel = Rpg.intSel();
		
		try {
			// 몬스터가 공격
			if(sel == 2) {
				attParty(monster,party,true);
			}else {
				if(!attParty(monster,party,false)) { //파티원 모두 사망 시 break;
					return true;
				}
			}
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		if(sel == 1) {
			if(!monsterAtt(monster,party,def)) { //monster모두 사망시 break;
				return true;
			}
		}else if(sel == 0) { //전투 중단 시 break;
			return true;
		}
		return false;
	}



	

	private void printParty(ArrayList<Character> party) {
		System.out.println("========== 파티원 ==========");
		int size = party.size();
		for (int i = 0; i < size; i++) {
			Character temp = party.get(i);
			System.out.printf("(%d) <name : %s> <level : %d> <hp %d/%d> <att : %d> <def : %d>\n", i + 1, temp.getName(),
					temp.getLevel(), temp.getHp(), temp.getMaxhp(), temp.getAtt(), temp.getDef());
		}
		System.out.println("=========================");
	}

	private void printMonster(ArrayList<Monster> monster) {
		System.out.println("------------------------------------------");
		System.out.println("몬스터를 처치하세요!!");
		int size = monster.size();
		for (int i = 0; i < size; i++) {
			Monster temp = monster.get(i);
			System.out.printf("(%d) <name : %s> <level : %d> <att : %d> <def : %d>\n", i + 1, temp.getName(),
					temp.getLevel(), temp.getAtt(), temp.getDef());
			System.out.printf(" -- >  hp : %s <hp : %d/%d> \n", printHp(temp.getHp(), temp.getMaxHp()), temp.getHp(),
					temp.getMaxHp());
		}
		System.out.println("------------------------------------------");
	}

	private String printHp(int hp, int maxHp) {
		String str = "";
		int st = maxHp / 10;

		int cnt = hp / st;
		int x = hp % st;

		for (int i = 0; i < cnt; i++) {
			str += "■" + " ";
		}
		for (int i = cnt; i < 10; i++) {
			if (x > 0 && i == cnt) {
				str += "■" + " ";
			} else {
				str += "□" + " ";
			}
		}
		return str;
	}

	private boolean attParty(ArrayList<Monster> monster, ArrayList<Character> party,boolean def) {
		Random ran = new Random();
		int ranMonster = ran.nextInt(monster.size());
		int ranParty = ran.nextInt(party.size());


		Monster tempMonster = monster.get(ranMonster);
		Character tempCha = party.get(ranParty);

		System.out.printf("※몬스터 <이름 : %s>가 파티원<이름 : %s>를 공격함  -%d hp\n", tempMonster.getName(), tempCha.getName(),
				tempMonster.getAtt());
		
		if(def) {
			System.out.printf("※파티원 <이름 : %s>가 몬스터<이름 : %s>의 공격을 방어함\n",  tempCha.getName(),tempMonster.getName());
			System.out.printf("※몬스터 <이름 : %s>의 공격력 %d 감소\n",  tempMonster.getName(),tempCha.getDef());
			int att = tempMonster.getAtt() - tempCha.getDef();
			if(att < 0) {
				att = 0;
			}
			tempCha.setHp(-att);
		}else {
			tempCha.setHp(-tempMonster.getAtt());
		}
		
		if(tempCha.getHp() <= 0){
			System.out.printf("파티원 <이름 : %s> 사망\n", tempCha.getName());
			party.remove(ranParty);
		}
		
		if (party.size() == 0) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("+++++파티원 전원 사망! 몬스터 처치 실패!!");
			return false;
		}
		
		return true;
	}

	
	private boolean monsterAtt(ArrayList<Monster> monster, ArrayList<Character> party,int def) {
		Random ran = new Random();
		int ranMonster = ran.nextInt(monster.size());
		Monster tempMonster = monster.get(ranMonster);
		
		for(int i=0;i<party.size();i++) {
			Character temp = party.get(i);
			System.out.printf("(%d) <name : %s> <level : %d> <hp %d/%d> <att : %d> <def : %d>\n", i + 1, temp.getName(),
					temp.getLevel(), temp.getHp(), temp.getMaxhp(), temp.getAtt(), temp.getDef());
		}
		int sel = 0;
		while(true) {
			System.out.print("공격할 파티원 ");
			sel = Rpg.intSel()-1;
			if(sel >=0 && sel < party.size()) {
				break;
			}
		}
		Character tempCha = party.get(sel);
		System.out.printf("※파티원 <이름 : %s>가 몬스터<이름 : %s>를 공격함  -%d hp\n",  tempCha.getName(),tempMonster.getName(),
				tempCha.getAtt());
		
		if(def == 0) {
			tempMonster.minusHp(tempCha.getAtt());
		}else {
			System.out.printf("※몬스터 <이름 : %s>가 파티원<이름 : %s>의 공격을 방어함\n",  tempMonster.getName(),tempCha.getName());
			System.out.printf("※파티원 <이름 : %s>의 공격력 %d 감소\n",  tempCha.getName(),tempMonster.getDef());
			int att = tempCha.getAtt()-tempMonster.getDef();
			if(att < 0) {
				att = 0;
			}
			tempMonster.minusHp(att);
		}
	
		if(tempMonster.getHp() <= 0){
			System.out.printf("몬스터 <이름 : %s> 사망\n", tempMonster.getName());
			monster.remove(ranMonster);
		}
		
		if (monster.size() == 0) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("+++++몬스터 처치 성공!! 살아남은 파티원 모두 Level Up!");
			return false;
		}
		
		return true;
	}
	
	private boolean checkManager() {
		System.out.print("관리자 비밀번호를 입력하세요(0000): ");
		String pw = this.scan.next();

		if (pw.equals("0000")) {
			return true;
		}
		System.out.println("관리자만 입장 가능");
		return false;
	}

	public static int intSel() {
		while (true) {
			System.out.print("선택 : ");
			String temp = Rpg.scan.next();

			int sel = -1;
			try {
				sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
		}
	}
}
