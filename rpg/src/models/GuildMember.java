package models;

public class GuildMember {
	private String name;
	private int level;
	private int hp;
	private int maxhp;
	private int att;
	private int def;
	private boolean party;
	
	private Item weapon;
	private Item armor;
	private Item ring;
	
	public GuildMember(String name,int level,int hp,int maxhp,int att,int def,boolean party) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.maxhp = maxhp;
		this.att = att;
		this.def = def;
		this.party = party;
	}
}
