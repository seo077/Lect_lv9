package controller;

import java.util.ArrayList;

import models.GuildMember;

public class GuildManager {
	public static GuildManager instance = new GuildManager();

	private ArrayList<GuildMember>members = new ArrayList<>();
	
	public void ownGuildManage() {
		// TODO Auto-generated method stub
		
	}

	public void addMember() {
		System.out.print("name : ");
		String name = Rpg.scan.next();
		while(true) {
			System.out.print("level : ");
			String temp = Rpg.scan.next();
			int level = ItemManager.intCheck(temp);
			if(level == -1) {
				continue;
			}
			System.out.print("hp : ");
			temp = Rpg.scan.next();
			int hp = ItemManager.intCheck(temp);
			if(hp == -1) {
				continue;
			}
			System.out.print("maxhp : ");
			temp = Rpg.scan.next();
			int maxhp = ItemManager.intCheck(temp);
			if(maxhp == -1) {
				continue;
			}
			System.out.print("att : ");
			temp = Rpg.scan.next();
			int att = ItemManager.intCheck(temp);
			if(att == -1) {
				continue;
			}
			System.out.print("def : ");
			temp = Rpg.scan.next();
			int def = ItemManager.intCheck(temp);
			if(def == -1) {
				continue;
			}
			this.members.add(new GuildMember(name, level, hp, maxhp, att, def, false));
			
			break;
		}
	}

	public void delMember() {
		// TODO Auto-generated method stub
		
	}

	public void sort() {
		// TODO Auto-generated method stub
		
	}

	public void printAllMember() {
		int size = this.getMemberSize();
		for(int i=0;i<size;i++) {
			GuildMember tmp = this.members.get(i);
			System.out.printf("(%d) <name : %s> <level : %d> <hp : %d/%d> <att : %d> <def : %d> <party : %b>\n",i+1,tmp.getName(),tmp.getLevel(),tmp.getHp(),tmp.getMaxhp(),tmp.getAtt(),tmp.getDef(),tmp.getParty());
		}
	}
	public int getMemberSize() {
		return this.members.size();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void clear() {
		this.members = new ArrayList<>();
	}
}
