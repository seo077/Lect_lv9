package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Character;
import models.GuildMember;
import models.Inventory;
import models.Monster;

public class CharacterManager {
	public static CharacterManager instance = new CharacterManager();

	private ArrayList<Character> members = new ArrayList<>();
	private ArrayList<GuildMember> myMembers = new ArrayList<>();

	// private MonsterManager mm = MonsterManager.instance;

	public void ownGuildManage() {
		while (true) {
			String menu = "[1.길드원 목록] [2.길드원 추가] [3.길드원 삭제] [4.파티원 교체] [0.뒤로가기]";
			System.out.println(menu);
			int sel = Rpg.intSel();

			if (sel == 1) {
				printMyMembers();
			} else if (sel == 2) {
				addMyMember();
			} else if (sel == 3) {
				delMyMember();
			} else if (sel == 4) {
				replaceParty();
			} else if (sel == 0) {
				break;
			}
		}
	}

	private void replaceParty() {
		int idx[] = new int[4];
		int num = 0;
		int size = myMembers.size();
		if (size > 0) {
			System.out.println("====== 파티원 =======");
			for (int i = 0; i < size; i++) {
				if (this.myMembers.get(i).getParty()) {
					idx[num] = i;
					GuildMember tmp = myMembers.get(i);
					System.out.printf("(%d) <name : %s> <level : %d> <hp : %d/%d> <att : %d> <def : %d>\n", num + 1,
							tmp.getName(), tmp.getLevel(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(), tmp.getDef());
					int itemSize = tmp.getItemSize();
					if (itemSize > 0) {
						for (int j = 0; j < itemSize; j++) {
							System.out.printf("--> <종류  : %s> <이름  : %s> <능력 : %s>\n ", tmp.getItemKind(j),
									tmp.getItemName(j), tmp.getItemPower(j));
						}
					}
					num++;
				}
			}
			System.out.println("=============================");
			System.out.print("삭제할 파티원 선택 : ");
			String s = Rpg.scan.next();
			int sel = ItemManager.intCheck(s) - 1;
			if (sel >= 0 && sel < 4) {
				printMyMembers();
				System.out.print("추가할 파티원 선택 :");
				s = Rpg.scan.next();
				int replaceIdx = ItemManager.intCheck(s) - 1;
				if (replaceIdx >= 0 && replaceIdx < size) {
					if (!this.myMembers.get(replaceIdx).getParty()) {
						this.myMembers.get(idx[sel]).setParty(false);
						this.myMembers.get(replaceIdx).setParty(true);
						System.out.printf("<이름 : %s>를 <이름 : %s>로 변경\n", this.myMembers.get(idx[sel]).getName(),
								this.myMembers.get(replaceIdx).getName());
					} else {
						System.out.println("이미 파티에 참여중인 멤버입니다.");
					}
				}

			}

		} else {
			System.out.println("길드원이 없습니다.");
		}
	}

	public int MyMemberSize() {
		return this.myMembers.size();
	}

	public void printMyMembers() {
		int size = myMembers.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				GuildMember tmp = myMembers.get(i);
				System.out.printf("(%d) <name : %s> <level : %d> <hp : %d/%d> <att : %d> <def : %d> <party : %b>\n",
						i + 1, tmp.getName(), tmp.getLevel(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(), tmp.getDef(),
						tmp.getParty());
				int itemSize = tmp.getItemSize();
				if (itemSize > 0) {
					for (int j = 0; j < itemSize; j++) {
						System.out.printf("--> <종류  : %s> <이름  : %s> <능력 : %s>\n ", tmp.getItemKind(j),
								tmp.getItemName(j), tmp.getItemPower(j));
					}
				}
			}
		} else {
			System.out.println("길드원이 없습니다.");
		}
	}

	private void addMyMember() {
		int size = this.getCharacterSize();
		int allPage = size / 5;
		int page = 0;
		while (true) {
			System.out.println("--------------------------------------");
			int temp = page;
			for (int i = page * 5; i < page * 5 + 5; i++) {
				if (i < size) {
					Character tmp = this.members.get(i);
					System.out.printf(
							"(%d) <name : %s> <level : %d> <price : %d> <hp : %d/%d> <att : %d> <def : %d> \n", i + 1,
							tmp.getName(), tmp.getLevel(), tmp.getPrice(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(),
							tmp.getDef());
				}
			}
			System.out.printf("----------- < 이전(b) (%d/%d page) 이후(a) > -----------\n", page, allPage);

			System.out.print("추가할 멤버 선택('-1'입력 시 종료) : ");
			String sel = Rpg.scan.next();

			if (sel.equals("b")) {
				temp--;
			} else if (sel.equals("a")) {
				temp++;
			} else {
				int idx = ItemManager.intCheck(sel) - 1;
				if (idx == -2) {
					break;
				}
				if (idx >= 0 && idx < size) {
					System.out.printf("캐릭터 <이름 :%s>를 추가합니다.\n", this.members.get(idx).getName());
					System.out.printf("%d원 차감\n", this.members.get(idx).getPrice());
					Rpg.myMoney -= this.members.get(idx).getPrice();
					Character c = this.members.get(idx);

					String name = c.getName();
					int level = c.getLevel();
					int hp = c.getHp();
					int maxhp = c.getMaxhp();
					int att = c.getAtt();
					int def = c.getDef();

					Character cha = new Character(name, level, hp, maxhp, att, def);
					if (Rpg.party < 4) {
						this.myMembers.add(new GuildMember(cha, true));
						Rpg.party++;
					} else {
						this.myMembers.add(new GuildMember(cha, false));
					}
				}
			}

			if (temp * 5 < 0 || temp * 5 >= size) {
				System.out.println("없는 페이지입니다.");
			} else {
				page = temp;
			}
		}
	}

	private void delMyMember() {
		printMyMembers();
		int size = this.myMembers.size();
		System.out.println("선택 : ");
		String sel = Rpg.scan.next();
		int idx = ItemManager.intCheck(sel) - 1;
		if (idx >= 0 && idx < size) {
			if (this.myMembers.get(idx).getParty()) {
				int newParty = newParty(idx);
				this.myMembers.get(newParty).setParty(true);
				System.out.printf("멤버 <이름 :%s>를 삭제합니다.\n", this.myMembers.get(idx).getName());
				System.out.printf("<이름 : %s>를 파티원으로 변경합니다.\n", this.myMembers.get(newParty).getName());
				this.myMembers.remove(idx);
			} else {
				System.out.printf("멤버 <이름 :%s>를 삭제합니다.\n", this.myMembers.get(idx).getName());
				this.myMembers.remove(idx);
			}
		}
	}

	private int newParty(int idx) {
		int index = -1;
		int size = this.MyMemberSize();
		for (int i = idx + 1; i < size; i++) {
			if (!this.myMembers.get(i).getParty()) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			for (int i = 0; i < idx; i++) {
				if (!this.myMembers.get(i).getParty()) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	public void addCharacter() {
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
			this.members.add(new Character(name, level, hp, maxhp, att, def));

			break;
		}
	}

	public void delCharacter() {
		int size = this.getCharacterSize();
		int allPage = size / 5;
		int page = 0;
		while (true) {
			System.out.println("--------------------------------------");
			int temp = page;
			for (int i = page * 5; i < page * 5 + 5; i++) {
				if (i < size) {
					Character tmp = this.members.get(i);
					System.out.printf(
							"(%d) <name : %s> <level : %d> <price : %d> <hp : %d/%d> <att : %d> <def : %d> \n", i + 1,
							tmp.getName(), tmp.getLevel(), tmp.getPrice(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(),
							tmp.getDef());
				}
			}
			System.out.printf("----------- < 이전(b) (%d/%d page) 이후(a) > -----------\n", page, allPage);

			System.out.print("삭제할 캐릭터 선택 : ");
			String sel = Rpg.scan.next();

			if (sel.equals("b")) {
				temp--;
			} else if (sel.equals("a")) {
				temp++;
			} else {
				int idx = ItemManager.intCheck(sel) - 1;
				if (idx >= 0 && idx < size) {
					System.out.printf("캐릭터 <이름 :%s>를 삭제합니다.\n", this.members.get(idx).getName());
					this.members.remove(idx);
				}
				break;
			}

			if (temp * 5 < 0 || temp * 5 >= size) {
				System.out.println("없는 페이지입니다.");
			} else {
				page = temp;
			}
		}

	}

	public void sort() {
		int size = this.getCharacterSize();
		for (int i = 0; i < size; i++) {
			int min = this.members.get(i).getLevel();
			int minIdx = i;
			for (int j = i; j < size; j++) {
				if (min > this.members.get(j).getLevel()) {
					min = this.members.get(j).getLevel();
					minIdx = j;
				}
			}

			Character temp = this.members.get(i);
			this.members.set(i, this.members.get(minIdx));
			this.members.set(minIdx, temp);
		}
	}

	public void printAllCharacter() {
		int size = this.getCharacterSize();
		int allPage = size / 5;
		int page = 0;
		while (true) {
			System.out.println("--------------------------------------");
			int temp = page;
			for (int i = page * 5; i < page * 5 + 5; i++) {
				if (i < size) {
					Character tmp = this.members.get(i);
					System.out.printf(
							"(%d) <name : %s> <level : %d> <price : %d> <hp : %d/%d> <att : %d> <def : %d> \n", i + 1,
							tmp.getName(), tmp.getLevel(), tmp.getPrice(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(),
							tmp.getDef());
				}
			}
			System.out.printf("----------- < 이전(b) (%d/%d page) 이후(a) > -----------\n", page, allPage);
			System.out.println("뒤로 가기 : e");
			String sel = Rpg.scan.next();

			if (sel.equals("b")) {
				temp--;
			} else if (sel.equals("a")) {
				temp++;
			} else if (sel.equals("e")) {
				break;
			}

			if (temp * 5 < 0 || temp * 5 >= size) {
				System.out.println("없는 페이지입니다.");
			} else {
				page = temp;
			}
		}
	}

	public int getCharacterSize() {
		return this.members.size();
	}

	@Override
	public String toString() {
		int size = this.getCharacterSize();
		String data = "";
		for (int i = 0; i < size; i++) {
			data += this.members.get(i).getName() + "/";
			data += this.members.get(i).getLevel() + "/";
			data += this.members.get(i).getHp() + "/";
			data += this.members.get(i).getMaxhp() + "/";
			data += this.members.get(i).getAtt() + "/";
			data += this.members.get(i).getDef();

			if (i != size - 1) {
				data += "\n";
			}
		}
		return data;
	}

	public void clear() {
		this.members = new ArrayList<>();
	}

	public void setData(String[] temp) {
		this.members.add(new Character(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
				Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Integer.parseInt(temp[5])));
	}

	public void addItem(int idx, Inventory inventory) {
		this.myMembers.get(idx).addItem(inventory);
	}

	public String getName(int chasel) {
		return this.myMembers.get(chasel).getName();
	}

	public String guildToString() {
		String data = "";
		int size = this.MyMemberSize();
		for (int i = 0; i < size; i++) {
			data += this.myMembers.get(i).getName() + "/";
			data += this.myMembers.get(i).getLevel() + "/";
			data += this.myMembers.get(i).getPrice() + "/";
			data += this.myMembers.get(i).getHp() + "/";
			data += this.myMembers.get(i).getMaxhp() + "/";
			data += this.myMembers.get(i).getAtt() + "/";
			data += this.myMembers.get(i).getDef() + "/";
			data += this.myMembers.get(i).getParty() + "\n";
			int itemSize = this.myMembers.get(i).getItemSize();
			if (itemSize > 0) {
				for (int j = 0; j < itemSize; j++) {
					data += this.myMembers.get(i).getItemKind(j) + "/";
					data += this.myMembers.get(i).getItemEffect(j) + "/";
					data += this.myMembers.get(i).getItemName(j) + "/";
					data += this.myMembers.get(i).getItemPower(j) + "/";
					data += this.myMembers.get(i).getItemPrice(j) + "/";
					data += this.myMembers.get(i).getItemCnt(j) + "/";
					data += this.myMembers.get(i).getItemAvailable(j) + "\n";
				}
			}
		}
		return data.substring(0, data.length() - 1);
	}

	public void setMyGuild(String[] temp) {
		int size = temp.length;
		if (size > 7) {
			String name = temp[0];
			int level = Integer.parseInt(temp[1]);
			int price = Integer.parseInt(temp[2]);
			Rpg.myMoney -= price;
			int hp = Integer.parseInt(temp[3]);
			int maxhp = Integer.parseInt(temp[4]);
			int att = Integer.parseInt(temp[5]);
			int def = Integer.parseInt(temp[6]);
			Character cha = new Character(name, level, price, hp, maxhp, att, def);
			boolean party = Boolean.parseBoolean(temp[7]);
			if (party) {
				Rpg.party++;
			}
			this.myMembers.add(new GuildMember(cha, party));
		} else {
			int idx = this.myMembers.size() - 1;
			String kind = temp[0];
			int effect = Integer.parseInt(temp[1]);
			String name = temp[2];
			int power = Integer.parseInt(temp[3]);
			int price = Integer.parseInt(temp[4]);
			int cnt = Integer.parseInt(temp[5]);
			boolean b = Boolean.parseBoolean(temp[6]);
			Inventory inventory = new Inventory(kind, effect, name, power, price, cnt, b);
			this.myMembers.get(idx).addItem(inventory);
		}
	}

	public void guildClear() {
		Rpg.party = 0;
		this.myMembers = new ArrayList<>();
	}

	private void printParty() {
		int size = this.MyMemberSize();
		int idx[] = new int[4];
		int num = 0;
		System.out.println("====== 파티원 =======");
		for (int i = 0; i < size; i++) {
			if (this.myMembers.get(i).getParty()) {
				idx[num] = i;
				GuildMember tmp = myMembers.get(i);
				System.out.printf("(%d) <name : %s> <level : %d> <hp : %d/%d> <att : %d> <def : %d>\n", num + 1,
						tmp.getName(), tmp.getLevel(), tmp.getHp(), tmp.getMaxhp(), tmp.getAtt(), tmp.getDef());
				int itemSize = tmp.getItemSize();
				if (itemSize > 0) {
					for (int j = 0; j < itemSize; j++) {
						System.out.printf("--> <종류  : %s> <이름  : %s> <능력 : %s>\n ", tmp.getItemKind(j),
								tmp.getItemName(j), tmp.getItemPower(j));
					}
				}
				num++;
			}
		}
		System.out.println("=============================");
	}

	public String getMemberName(int ranMember) {
		return this.myMembers.get(ranMember).getName();
	}

	public void minusPartyHp(int ranMember, int att) {
		this.myMembers.get(ranMember).setHp(att);
	}

	public void dieParty(int ranMember) {
		if (this.myMembers.get(ranMember).getHp() == 0) {
			System.out.printf("파티원 <이름 : %s>이 사망하였습니다.\n", this.myMembers.get(ranMember).getName());
			this.myMembers.remove(ranMember);
		}

	}

	public ArrayList<Character> partyMember() {
		ArrayList<Character> party = new ArrayList<>();
		int size = this.MyMemberSize();
		for (int i = 0; i < size; i++) {
			if (this.myMembers.get(i).getParty()) {
				String name = this.myMembers.get(i).getName();
				int level = this.myMembers.get(i).getLevel();
				int hp = this.myMembers.get(i).getHp();
				int maxhp = this.myMembers.get(i).getMaxhp();
				int att = this.myMembers.get(i).getAtt();
				int def = this.myMembers.get(i).getDef();
				party.add(new Character(name, level, hp, maxhp, att, def));
			}
		}
		return party;
	}

	public void updateDieParty() {
		Rpg.party = 0;
		for (int i = this.MyMemberSize() - 1; i >= 0; i--) {
			if (this.myMembers.get(i).getParty()) {
				int newParty = newParty(i);
				if (newParty == -1) {
					System.out.printf("사망한 파티원 <이름 :%s>를 삭제합니다. 파티원으로  추가할 인원이 부족합니다.\n",
							this.myMembers.get(i).getName());
				} else {
					this.myMembers.get(newParty).setParty(true);
					System.out.printf("사망한 파티원 <이름 :%s>를 <이름 : %s>로 파티원으로 변경합니다.\n", this.myMembers.get(i).getName(),
							this.myMembers.get(newParty).getName());
					Rpg.party++;
				}

				this.myMembers.remove(i);
			}
		}
	}

	public void updateDieParty(ArrayList<Character> party) {
		int die = party.size();
		Rpg.party = die;
		for (int i = this.MyMemberSize() - 1; i >= 0; i--) {
			for (int j = 0; j < die; j++) {
				if (this.myMembers.get(i).getParty()
						&& !this.myMembers.get(i).getName().equals(party.get(j).getName())) {					
					this.myMembers.remove(i);
				} else if (this.myMembers.get(i).getParty()
						&& this.myMembers.get(i).getName().equals(party.get(j).getName())) {
					this.myMembers.get(i).plusLevel(1);
				}

			}
		}
	}
}
