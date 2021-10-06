package controller;

import java.util.ArrayList;

import models.GuildMember;

public class GuildManager {
	public static GuildManager instance = new GuildManager();

	private ArrayList<GuildMember> members = new ArrayList<>();

	public void ownGuildManage() {
		// TODO Auto-generated method stub

	}

	public void addMember() {
		System.out.print("name : ");
		String name = Rpg.scan.next();
		while (true) {
			System.out.print("level : ");
			String temp = Rpg.scan.next();
			int level = ItemManager.intCheck(temp);
			if (level == -1) {
				continue;
			}
			System.out.print("hp : ");
			temp = Rpg.scan.next();
			int hp = ItemManager.intCheck(temp);
			if (hp == -1) {
				continue;
			}
			System.out.print("maxhp : ");
			temp = Rpg.scan.next();
			int maxhp = ItemManager.intCheck(temp);
			if (maxhp == -1) {
				continue;
			}
			System.out.print("att : ");
			temp = Rpg.scan.next();
			int att = ItemManager.intCheck(temp);
			if (att == -1) {
				continue;
			}
			System.out.print("def : ");
			temp = Rpg.scan.next();
			int def = ItemManager.intCheck(temp);
			if (def == -1) {
				continue;
			}
			this.members.add(new GuildMember(name, level, hp, maxhp, att, def, false));

			break;
		}
	}

	public void delMember() {
		int size = this.getMemberSize();
		printAllMember();
		System.out.print("삭제할 멤버 선택 : ");
		String temp = Rpg.scan.next();
		
		int idx = ItemManager.intCheck(temp)-1;
		if(idx >= 0 &&  idx < size) {
			this.members.remove(idx);
		}
	}

	public void sort() {
		int size = this.getMemberSize();
		for(int i=0;i<size;i++) {
			int min = this.members.get(i).getLevel();
			int minIdx = i;
			for(int j=i;j<size;j++) {
				if(min > this.members.get(j).getLevel()) {
					min = this.members.get(j).getLevel();
					minIdx = j;
				}
			}
			
			GuildMember temp = this.members.get(i);
			this.members.set(i,this.members.get(minIdx));
			this.members.set(minIdx, temp);
		}
	}

	public void printAllMember() {
		int size = this.getMemberSize();
		for (int i = 0; i < size; i++) {
			GuildMember tmp = this.members.get(i);
			System.out.printf("(%d) <name : %s> <level : %d> <hp : %d/%d> <att : %d> <def : %d> <party : %b>\n", i + 1,
					tmp.getName(), tmp.getLevel(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(), tmp.getDef(),
					tmp.getParty());
		}
	}

	public int getMemberSize() {
		return this.members.size();
	}

	@Override
	public String toString() {
		int size = this.getMemberSize();
		String data = "";
		for(int i=0;i<size;i++) {
			data += this.members.get(i).getName()+"/";
			data += this.members.get(i).getLevel()+"/";
			data += this.members.get(i).getHp()+"/";
			data += this.members.get(i).getMaxhp()+"/";
			data += this.members.get(i).getAtt()+"/";
			data += this.members.get(i).getDef()+"/";
			data += this.members.get(i).getParty();
			if(i != size-1) {
				data += "\n";
			}
		}
		return data;
	}

	public void clear() {
		this.members = new ArrayList<>();
	}

	public void setData(String[] temp) {
		this.members.add(new GuildMember(temp[0], Integer.parseInt(temp[1]),  Integer.parseInt(temp[2]),  Integer.parseInt(temp[3]),  Integer.parseInt(temp[4]),  Integer.parseInt(temp[5]),Boolean.parseBoolean(temp[6])));
	}
}
