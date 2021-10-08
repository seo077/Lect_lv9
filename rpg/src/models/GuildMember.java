package models;

import java.util.ArrayList;

public class GuildMember {

	private Character cha;
	private boolean party;
	private ArrayList<Inventory>items = new ArrayList<>();
	
	public GuildMember(Character cha,boolean party) {
		this.cha = cha;
		this.party = party;
	}
	
	public String getName() {
		return this.cha.getName();
	}
	public int getLevel() {
		return this.cha.getLevel();
	}
	public int getPrice() {
		return this.cha.getPrice();
	}
	public int getHp() {
		return this.cha.getHp();
	}
	public int getMaxhp() {
		return this.cha.getMaxhp();
	}
	public int getAtt() {
		return this.cha.getAtt();
	}
	public int getDef() {
		return this.cha.getDef();
	}
	public boolean getParty() {
		return this.party;
	}
	public int getItemSize() {
		return this.items.size();
	}
	public Object getItemKind(int j) {
		return this.items.get(j).getKind();
	}
	public Object getItemPower(int j) {
		return this.items.get(j).getPower();
	}
	public Object getItemName(int j) {
		return this.items.get(j).getName();
	}

	public void addItem(Inventory inventory) {
		this.items.add(inventory);
		int eff = inventory.getEffect();
		int po = inventory.getPower();
		if(eff == 0) {
			this.cha.setLevel(po);
		}else if(eff == 1) {
			this.cha.setHp(po);
		}else if(eff == 2) {
			this.cha.setAtt(po);
		}else if(eff == 3) {
			this.cha.setDef(po);
		}
	}

	public void setParty(boolean b) {
		this.party = b;
	}

	public int getItemEffect(int j) {
		return this.items.get(j).getEffect();
	}

	public int getItemPrice(int j) {
		return this.items.get(j).getPrice();
	}

	public int getItemCnt(int j) {
		return this.items.get(j).getCnt();
	}

	public boolean getItemAvailable(int j) {
		return this.items.get(j).getAvailable();
	}

	public void setHp(int att) {
		this.cha.setHp(-att);;
	}
}
