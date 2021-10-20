package controller;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import models.Account;
import models.Black;
import models.Customer;
import models.Diamond;
import models.Gold;
import models.Platinum;

public class AtmManager {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();

	private static AtmManager instance = new AtmManager();

	private AtmManager() {
	};

	public static AtmManager getInstance() {
		return instance;
	}

	public static Map<Integer, String> gradeQualification = new HashMap<>(); // ���� totalMoney�� key�� �̻��̸� value���

	private String bankName = "";
	private static int log = -1;
	private ArrayList<Customer> customers = new ArrayList<>();

	private String curService = "";
	private String nextService = "";
	private Map<String, Service> service = new HashMap<String, Service>();

	private void setBankService() {
		this.service.put("�Ա�", PayinService.getInstance());
		this.service.put("���", WithdrawService.getInstance());
		this.service.put("��ü", TransferService.getInstance());
		this.service.put("���°���", OpenAccService.getInstance());
		this.service.put("�ְ��º���", ChangeRep.getInstance());
	}

	public static int getLog() { // �α��� getter
		return log;
	}

	public void run() {
		setBankName("GREEN");
		setBankGrade();
		setBankService();
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

	private void printMenu() {
		while (true) {
			printAllCustomer();
			System.out.printf("===== [%s BANK] =====\n", bankName);
			System.out.println("[1.�α���] [2.ȸ������] [3.�����ϱ�]");
			int sel = selInteger();

			if (sel == 1) {
				if (login()) {
					while(true) {
						this.nextService = loginMenu(this.curService);
						if(!this.nextService.equals("")) {
							Service service = this.service.get(nextService);
							this.customers = service.moveService(customers);
							this.customers = this.changeGrade();
							this.curService = "";
						}else {
							break;
						}
						
					}
				}
			} else if (sel == 2) {
				join();
			} else if (sel == 3) {
				break;
			}
		}
	}

	private String loginMenu(String curService) {
		if(curService.equals("")) {
			while(true) {
				System.out.println("[1.�Ա�] [2.���] [3.��ü] [4.���°���] [5.�� ���� ����] [0.�α׾ƿ�]");
				int sel = AtmManager.selInteger();
				
				if(sel == 1) {
					return "�Ա�";
				}else if(sel == 2) {
					return "���";
				}else if(sel == 3) {
					return "��ü";
				}else if(sel == 4) {
					return "���°���";
				}else if(sel == 5) {
					return "�ְ��º���";
				}else if(sel == 0) {
					return "";
				}
			}
		}else {
			return "";
		}
	}

	private void printAllCustomer() {
		System.out.println("------------------------");
		int size = this.customers.size();
		for (int i = 0; i < size; i++) {
			this.customers.get(i).printInfo();
		}
		System.out.println("------------------------");
	}

	private void join() {
		System.out.print("�̸� �Է� : ");
		String name = scan.next();
		String id = "";
		while(true) {
			System.out.print("���̵� �Է� : ");
			id = scan.next();
			if(!checkIdDup(id)) {
				break;
			}else {
				System.out.println("[���̵� �ߺ�]��� �Ұ�");
			}
		}
		System.out.print("��й�ȣ �Է� : ");
		String pw = scan.next();
		String acc = accMaker();
		System.out.printf("%s���� ù��° ���� ���� : [���¹�ȣ : %s]\n", name, acc);

		String grade = checkGrade(1000);
		this.customers.add(new Black(grade, name, id, pw, acc, true)); // ù��° ���°� �� ����
	}

	private boolean checkIdDup(String id) {
		int size = this.customers.size();
		int check = -1;
		for (int i = 0; i < size; i++) {
			if(id.equals(this.customers.get(i).getId())) {
				check = i;
			}
		}
		
		if(check == -1) {
			return false;
		}else {
			return true;
		}
	}

	private String checkGrade(int myTotalMoney) {
		Object money[] = this.gradeQualification.keySet().toArray();
		Arrays.sort(money);
		String grade = "";
		if (myTotalMoney <= (Integer) money[1]) {
			grade = this.gradeQualification.get(money[0]);
		} else if (myTotalMoney <= (Integer) money[2]) {
			grade = this.gradeQualification.get(money[1]);
		} else if (myTotalMoney <= (Integer) money[3]) {
			grade = this.gradeQualification.get(money[2]);
		} else {
			grade = this.gradeQualification.get(money[3]);
		}
		return grade;
	}

	private String accMaker() {
		int size = customers.size();
		while (true) {
			int temp = ran.nextInt(89999) + 10000;
			String acc = String.valueOf(temp);
			int check = -1;
			for (int i = 0; i < size; i++) {
				int accSize = this.customers.get(i).accsCnt();
				for (int j = 0; j < accSize; j++) {
					if (acc.equals(this.customers.get(i).getAcc(j))) {
						check = j;
					}
				}
			}
			if (check == -1) {
				return acc;
			}
		}
	}

	private boolean login() {
		System.out.print("���̵� �Է� : ");
		String id = scan.next();
		int idx = idxOfId(id);
		System.out.print("��й�ȣ �Է� : ");
		String pw = scan.next();

		if (idx != -1 && pw.equals(this.customers.get(idx).getPw())) {
			System.out.printf("%s�� Ⱥ���մϴ�.\n", this.customers.get(idx).getName());
			this.log = idx;
			return true;
		} else {
			System.out.println("���̵�� ��й�ȣ�� Ȯ���ϼ���");
			return false;
		}
	}

	private int idxOfId(String id) {
		int idx = -1;
		int size = this.customers.size();
		for (int i = 0; i < size; i++) {
			if (id.equals(this.customers.get(i).getId())) {
				idx = i;
			}
		}
		return idx;
	}

	public static int selInteger() {
		while (true) {
			System.out.print("�޴� ���� : ");
			String temp = scan.next();

			try {
				int sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				System.out.println("#���ڸ� �Է��ϼ���");
			}
		}
	}

	public ArrayList<Customer> changeGrade() {
		int total = this.customers.get(log).getTotalMoney();
		String curGrade = this.customers.get(log).getGrade();
		String nextGrade = checkGrade(total);

		
		if (!curGrade.equals(nextGrade)) {
			System.out.println(curGrade + "->" + nextGrade);//
			Customer cu = this.customers.get(log);
			String name = cu.getName();
			String id = cu.getId();
			String pw = cu.getPw();
			int totalMoney = cu.getTotalMoney();
			int repAccIdx = cu.getRepAccIdx();
			ArrayList<Account> accs = cu.getAccs();

			System.out.printf("[�̸� : %s]�� [��� : %s]�� ����˴ϴ�.\n", name, nextGrade);

			if (nextGrade.equals("BLACK")) {
				Black newcus = new Black(nextGrade, name, id, pw, totalMoney, repAccIdx, accs);
				this.customers.add(newcus);
			} else if (nextGrade.equals("GOLD")) {
				Gold newcus = new Gold(nextGrade, name, id, pw, totalMoney, repAccIdx, accs);
				this.customers.add(newcus);
			} else if (nextGrade.equals("PLATINUM")) {
				Platinum newcus = new Platinum(nextGrade, name, id, pw, totalMoney, repAccIdx, accs);
				this.customers.add(newcus);
			} else if (nextGrade.equals("DIAMOND")) {
				Diamond newcus = new Diamond(nextGrade, name, id, pw, totalMoney, repAccIdx, accs);
				this.customers.add(newcus);
			}

			//this.customers.remove(log);
			this.log = this.customers.size()-1;
		
		}
		return this.customers;
	}
}
