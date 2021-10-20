package controller;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import models.Black;
import models.Customer;
import models.Gold;

public class AtmManager {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	
	private static AtmManager instance = new AtmManager();
	private AtmManager() {};
	public static AtmManager getInstance() {
		return instance;
	}
	
	public static Map<Integer, String>gradeQualification = new HashMap<>(); //고객의 totalMoney가 key값 이상이면 value등급
	
	private String bankName = "";
	private static int log = -1;
	private ArrayList<Customer>customers = new ArrayList<>();
	private Map<String, Service>service = new HashMap<String, Service>();
	
	public static int getLog() { //로그인 getter
		return log;
	}
	
	public void run() {
		setBankName("GREEN");
		setBankService();
		setBankGrade();
		printMenu();
	}
	
	private void setBankGrade() {
		this.gradeQualification.put(0, "BLACK");
		this.gradeQualification.put(100000, "GOLD");
		this.gradeQualification.put(500000, "PLATINUM");
		this.gradeQualification.put(1000000, "DIAMOND");
	}
	
	private void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	private void setBankService() {
		this.service.put("입금", PayinService.getInstance());
		this.service.put("출금", WithdrawService.getInstance());
		this.service.put("이체", TransferService.getInstance());
		this.service.put("계좌개설", OpenAccService.getInstance());
		this.service.put("예금", DepositService.getInstance());
	}
	
	private void printMenu() {
		while(true) {
			printAllCustomer();
			System.out.printf("===== [%s BANK] =====\n",bankName);
			System.out.println("[1.로그인] [2.회원가입] [3.종료하기]");
			int sel = selInteger();
			
			if(sel == 1) {
				if(login()) {
					
				}
			}else if(sel == 2) {
				join();
			}else if(sel == 3) {
				break;
			}
		}
	}
	
	private void printAllCustomer() {
		int size = this.customers.size();
		for(int i=0;i<size;i++) {
			this.customers.get(i).printInfo();
		}
	}
	private void join() {
		System.out.print("이름 입력 : ");
		String name = scan.next();
		System.out.print("아이디 입력 : ");
		String id = scan.next();
		System.out.print("비밀번호 입력 : ");
		String pw = scan.next();
		String acc = accMaker();
		
		String grade = checkGrade(1000);
		this.customers.add(new Black(grade, name, id, pw, acc));
	}
	
	private String checkGrade(int myTotalMoney) {
		Object money[] = this.gradeQualification.keySet().toArray();
		Arrays.sort(money);
		String grade = "";
		if(myTotalMoney >= (Integer)money[0]) {
			grade = this.gradeQualification.get(money[0]);
		}else if(myTotalMoney >= (Integer)money[1]) {
			grade = this.gradeQualification.get(money[1]);
		}else if(myTotalMoney >= (Integer)money[2]) {
			grade = this.gradeQualification.get(money[2]);
		}else if(myTotalMoney >= (Integer)money[3]) {
			grade = this.gradeQualification.get(money[3]);
		}
		return grade;
	}
	private String accMaker() {
		int size = customers.size();
		while(true) {
			int temp = ran.nextInt(89999)+10000;
			String acc = String.valueOf(temp);
			int check = -1;
			for(int i=0;i<size;i++) {
				int accSize = this.customers.get(i).accsCnt();
				for(int j=0;j<accSize;j++) {
					if(acc.equals(this.customers.get(i).getAcc(j))) {
						check = j;
					}
				}
			}
			if(check == -1) {
				return acc;
			}
		}
	}
	private boolean login() {
		System.out.print("아이디 입력 : ");
		String id = scan.next();
		int idx = idxOfId(id);
		System.out.print("비밀번호 입력 : ");
		String pw = scan.next();
		
		if(idx != -1 && pw.equals(this.customers.get(idx).getPw())) {
			System.out.printf("%s님 횐영합니다.\n",this.customers.get(idx).getName());
			this.log = idx;
			return true;
		}else {
			System.out.println("아이디와 비밀번호를 확인하세요");
			return false;
		}
	}
	private int idxOfId(String id) {
		int idx = -1;
		int size = this.customers.size();
		for(int i=0;i<size;i++) {
			if(id.equals(this.customers.get(i).getId())) {
				idx = i;
			}
		}
		return idx;
	}
	private int selInteger() {
		while(true) {
			System.out.print("메뉴 선택 : ");
			String temp = scan.next();
			
			try {
				int sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				System.out.println("#숫자를 입력하세요");
			}
		}
	}
}
