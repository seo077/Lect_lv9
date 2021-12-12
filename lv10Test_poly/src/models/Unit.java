package models;

public class Unit {
	private String name;
	private int maxHp;
	private int hp;
	private int att;
	private boolean die;
	private boolean mute;
	
	public Unit(String name, int maxHp, int att) {
		this.name = name;
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.att = att;
	}

	public void printInfo() {
		int hphp = this.hp;
		if(this.hp<=0) {
			hphp = 0;
			System.out.printf("[%s] [%d/%d] [%d] (»ç¸Á)\n",this.name,hphp,this.maxHp,this.att);
		}else {
			System.out.printf("[%s] [%d/%d] [%d]\n",this.name,hphp,this.maxHp,this.att);
		}
	}
	
	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public boolean isDie() {
		return die;
	}

	public void setDie(boolean die) {
		this.die = die;
	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}
	
}
