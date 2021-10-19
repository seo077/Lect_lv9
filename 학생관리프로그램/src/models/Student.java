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
	
	
	public void printInfo() {
		if(this.takeMinor) {
			System.out.printf("[�̸� : %s] [�й� : %d] [���� : %s] [������ : %s]\n",this.name,this.hakbun,this.major.getName(),this.minor.getName());
		}else {
			System.out.printf("[�̸� : %s] [�й� : %d] [���� : %s] [������ : %s]\n",this.name,this.hakbun,this.major.getName(),"����");
		}
		int size = this.subSize();
		for(int i=0;i<size;i++) {
			System.out.printf("-> (%d)",i+1);
			this.sub.get(i).printInfo();
		}
		
	}
	
	private int subSize() {
		return this.sub.size();
	}


	public int getHakbun() {
		return this.hakbun;
	}


	public String getName() {
		return this.name;
	}
}
