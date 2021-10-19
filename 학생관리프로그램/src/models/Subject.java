package models;

public abstract class Subject {
	private String subName;
	private int score;
	
	public Subject(String subName) {
		this.subName = subName;
	}
	
	public abstract void printInfo();
}
