package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import models.School;
import models.Student;
import models.Subject;
import models.�����а�;
import models.�ǿ���;
import models.��ǻ�Ͱ��а�;
import models.ȭ�л������а�;

public class StudentManager {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	
	private static StudentManager instance = new StudentManager();
	private StudentManager() {}
	public static StudentManager getInstance() {
		return instance;
	}
	
	private HashMap<Integer, Manage> menu = new HashMap<>();
	private static String schoolName = "";
	
	
	public static String getSchoolName() {
		return schoolName;
	}
	
	public void run() {
		setData();
		DepartmentMange.getInstance().setData();
		StudentManage.getInstance().setData();
		while(true) {
			if(!printMenu()) {
				break;
			}
		}
	}
	
	private void setData() {
		this.schoolName = "�׸�";
		this.menu.put(1, DepartmentMange.getInstance());
		this.menu.put(2, StudentManage.getInstance());
		this.menu.put(3, LoginManage.getInstance());
	}
	
	private boolean printMenu() {
		while(true) {
			System.out.printf("=== [%s SCHOOL] ===\n",this.schoolName);
			String menu = "  {1.�а� ����}\n  {2.�л� ����}\n  {3.�α���}\n  {4.�����ϱ�}";
			System.out.println(menu);
			System.out.println("===================");
			
			int sel = selInt();
			if(sel > 0 && sel<4) {
				this.menu.get(sel).run();
			}else if(sel == 4) {
				return false;
			}else {
				continue;
			}
			return true;
		}
	}
	
	public static int selInt() {
		while(true) {
			System.out.print("���� : ");
			String temp = scan.next();
			try {
				int sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	

}
