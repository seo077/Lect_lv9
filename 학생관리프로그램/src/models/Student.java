package models;

import java.util.ArrayList;

public class Student {
	private String name;
	private int hakbun;
	private School major;
	private boolean takeMinor;
	private School minor;
	private ArrayList<Subject>sub;
	
	public Student(String name,int hakbun,School major) {
		this.name = name;
		this.hakbun = hakbun;
		this.major = major;
		this.sub = new ArrayList<>();
	}
	
	public Student(String name,int hakbun, School major ,boolean takeMinor, School minor,Subject sub) {
		this.name = name;
		this.hakbun = hakbun;
		this.major = major;
		this.takeMinor = true;
		this.minor = minor;
		this.sub = new ArrayList<>();
		this.sub.add(sub);
	}
	public Student(String name,int hakbun, School major,Subject sub) {
		this.name = name;
		this.hakbun = hakbun;
		this.major = major;
		this.sub = new ArrayList<>();
		this.sub.add(sub);
	}
	
	
	public void printInfo() {
		if(this.takeMinor) {
			System.out.printf("[이름 : %s] [학번 : %d] [전공 : %s] [부전공 : %s]\n",this.name,this.hakbun,this.major.getName(),this.minor.getName());
		}else {
			System.out.printf("[이름 : %s] [학번 : %d] [전공 : %s] [부전공 : %s]\n",this.name,this.hakbun,this.major.getName(),"없음");
		}
		int size = this.subSize();
		for(int i=0;i<size;i++) {
			System.out.printf("-> (%d)",i+1);
			this.sub.get(i).printInfo();
		}
	}
	
	public void selMinor(Minor minor) {
		School sc = (School)minor;
		this.takeMinor = true;
		this.minor = sc;
		System.out.printf("[학번 :%d]-> [부전공 : %s]선택\n",this.hakbun,this.minor);
	}
	public int subSize() {
		return this.sub.size();
	}


	public int getHakbun() {
		return this.hakbun;
	}


	public String getName() {
		return this.name;
	}


	public boolean getTakeMinor() {
		return this.takeMinor;
	}


	public School getMajor() {
		return this.major;
	}


	public School getMinor() {
		return this.minor;
	}


	public void addSub(Subject subject) {
		this.sub.add(subject);
	}

	public void printSub() {
		int size = this.subSize();
		for(int i=0;i<size;i++) {
			System.out.printf("(%d)",i+1);
			this.sub.get(i).printInfo();
		}
	}

	public void addScore(int sel, int score) {
		this.sub.get(sel).addScore(score);
	}
}
