package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Cheatable;
import models.M_Bat;
import models.M_Orc;
import models.M_Wolf;
import models.Monster;
import models.P_Healer;
import models.P_Warrior;
import models.P_Wizard;
import models.Player;

public class GameManager {
	public static Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);
	public static final int enemyNum = 4;
	public static final int playerNum = 3;

	private static int diePlayer = 0;
	private static int dieMonster = 0;

	private static GameManager instance = new GameManager();

	private GameManager() {
	};

	public static GameManager getInstance() {
		return instance;
	}

	private ArrayList<Monster> enemy = new ArrayList<>();
	private ArrayList<Player> player = new ArrayList<>();

	private void setEnemy() {
		MonsterManager.getInstance().enemySet(enemy);
	}

	private void setPlayer() {
		PlayerManager.getInstance().playerSet(player);
	}

	public void printCharacterInfo() {
		System.out.println("=====[PLAYER]=====");
		// �÷��̾� ���� ����Ʈ
		int size = player.size();
		for (int i = 0; i < size; i++) {
			player.get(i).printInfo();
		}
		System.out.println("=====[MONSTER]=====");
		// ���� ���� ����Ʈ
		size = enemy.size();
		for (int i = 0; i < size; i++) {
			enemy.get(i).printInfo();
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~");
	}

	public void run() {
		if (StageTitle.getInstance().run()) {
			this.setPlayer();
			while (true) {
				if (StageLobby.getInstance().run()) {
					this.dieMonster = 0;
					this.setEnemy();
					if (!StageBattle.getInstance().run()) {
						if (this.dieMonster == this.enemyNum) {
							continue;
						}
						if (this.diePlayer == this.playerNum) {
							System.out.println("���� ���");
							break;
						}
					}
				} else {
					break;
				}
			}

		}

	}

	public static int checkInt(String temp) {
		int sel = -1;
		while (true) {
			try {
				sel = Integer.parseInt(temp);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (sel == -1) {
				continue;
			}
			break;
		}
		return sel;
	}

	public boolean battle() {
		if (playerattack()) {
			return false;
		}
		try {
			Thread.sleep(1000);
			System.out.println("\n");
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.printCharacterInfo();
		if (monsterattack()) {
			return false;
		}
		return true;
	}

	private boolean monsterattack() {
		int ranPlayerIdx[] = new int[this.player.size()];

		System.out.println("=====[������ ����]=====");
		for (int i = 0; i < this.enemy.size(); i++) {
			int skill = this.ran.nextInt(3);
			int defenderIdx = this.ran.nextInt(this.player.size());
			if (skill == 0) {
				int ranPlayer = -1;
				int cnt = 0;
				while (true) {
					ranPlayer = this.ran.nextInt(this.player.size());

					if (ranPlayerIdx[ranPlayer] == 0) {
						System.out.printf("[%s]�� ��ų�� ����մϴ�.\n", this.enemy.get(i).getName());
						ranPlayerIdx[ranPlayer] = -1;
						MonsterUseSkill(i, ranPlayer);
						break;
					}else {
						cnt++;
					}

					if(cnt == this.player.size()) {
						this.enemy.get(i).attack(this.player.get(defenderIdx).getName());
						this.player.get(defenderIdx).damage(this.enemy.get(i).getPower());
						break;
					}
				}
			} else {
				this.enemy.get(i).attack(this.player.get(defenderIdx).getName());
				this.player.get(defenderIdx).damage(this.enemy.get(i).getPower());
			}
			checkDiePlayer();
			if (this.diePlayer == this.playerNum) {
				System.out.println("�й��ߴ�...");
				return true;
			}
		}
		return false;
	}

	private boolean playerattack() {
		for (int i = 0; i < this.player.size(); i++) {
			if (i != 0) {
				this.printCharacterInfo();
			}

			if (!this.player.get(i).getFaint() && !this.player.get(i).getSilent()) {
				int ranEnemy = ran.nextInt(this.enemy.size());

				System.out.println("=====[�޴�����]=====");
				System.out.printf("[%s] [1.����] [2.��ų]\n", this.player.get(i).getName());

				String temp = this.scan.next();
				int sel = checkInt(temp);

				if (sel == 1) {
					this.player.get(i).attack(this.enemy.get(ranEnemy).getName());
					this.enemy.get(ranEnemy).damage(this.player.get(i).getPower());
				} else if (sel == 2) {
					if (this.player.get(i).getCurSkillCnt() != 0) {
						playerUseSkill(i, ranEnemy);
						this.player.get(i).setCurSkillCnt(-1);
					} else {
						System.out.println("��ų�� ����� �� �����ϴ�.");
					}
				} else {
					i--;
				}
			} else {
				if (this.player.get(i).getFaint()) {
					System.out.printf("[���ݺҰ�][%s�� ������ �����Դϴ�.(��ũ�� ��ų ����)]\n", this.player.get(i).getName());
					this.player.get(i).setFaint(false);
				}
				if (this.player.get(i).getSilent()) {
					System.out.printf("[���ݺҰ�][%s�� ħ�� �����Դϴ�.(������ ��ų ����)]\n", this.player.get(i).getName());
					this.player.get(i).setSilent(false);
				}
			}

			checkDieEnemy();
			if (this.dieMonster == this.enemyNum) {
				System.out.println("�¸��ߴ�!!");
				return true;
			}

		}
		return false;
	}

	private void checkDiePlayer() {
		ArrayList<Player> temp = new ArrayList<>();
		for (int i = 0; i < this.player.size(); i++) {
			if (this.player.get(i).getCurHp() > 0) {
				temp.add(this.player.get(i));
			} else {
				System.out.printf("[%s]�� ����߽��ϴ�.\n", this.player.get(i).getName());
				this.diePlayer++;
			}
		}
		this.player = temp;
		temp = null;
	}

	private void checkDieEnemy() {
		ArrayList<Monster> temp = new ArrayList<>();
		for (int i = 0; i < this.enemy.size(); i++) {
			if (this.enemy.get(i).getCurHp() > 0) {
				temp.add(this.enemy.get(i));
			} else {
				System.out.printf("[%s]�� óġ�߽��ϴ�.\n", this.enemy.get(i).getName());
				this.dieMonster++;
			}
		}
		this.enemy = temp;
		temp = null;
	}

	private void MonsterUseSkill(int idx, int ranPlayer) {
		String name = this.enemy.get(idx).getName();

		if (name.equals("����")) {
			M_Bat bat = (M_Bat) this.enemy.get(idx);

			bat.skill(this.player.get(ranPlayer));

		} else if (name.equals("��ũ")) {
			M_Orc orc = (M_Orc) this.enemy.get(idx);

			orc.skill(this.player.get(ranPlayer));
		} else {
			M_Wolf wolf = (M_Wolf) this.enemy.get(idx);

			int size = this.player.size();
			for (int i = 0; i < size; i++) {
				wolf.skill(this.player.get(i));
			}
		}

	}

	private void playerUseSkill(int idx, int ranEnemy) {
		String name = this.player.get(idx).getName();
		System.out.printf("[%s�� ��ų�� ����մϴ�.]\n", name);

		if (name.equals("����")) {
			while (true) {
				P_Warrior warrior = (P_Warrior) this.player.get(idx);
				System.out.println("[�� �� �Ѹ��� 100�� �������� �����մϴ�.]");
				int size = enemy.size();
				for (int i = 0; i < size; i++) {
					System.out.print("(" + (i + 1) + ")");
					enemy.get(i).printInfo();
				}
				String temp = this.scan.next();
				int sel = checkInt(temp) - 1;

				if (sel >= 0 && sel <= size) {
					warrior.skill(this.enemy.get(sel));
					break;
				} else {
					break;
				}
			}
		} else if (name.equals("������")) {
			P_Wizard wizard = (P_Wizard) this.player.get(idx);
			System.out.println("[������ �ڱ� �ڽ��� �����մϴ�.]");
			for (int i = 0; i < this.enemy.size(); i++) {
				try {
					Cheatable cheat = (Cheatable) this.enemy.get(i);
					wizard.skill((Cheatable) this.enemy.get(i));
				} catch (Exception e) {
					System.out.printf("[%s]�� �����罺ų�� ������ ���� �ʽ��ϴ�.\n", this.enemy.get(i).getName());
				}
			}
		} else {
			P_Healer healer = (P_Healer) this.player.get(idx);
			System.out.println("[������ ��ų�� ����߽��ϴ�.]");
			System.out.println("[������ �������� ġ�Ḧ �����մϴ�.]");

			P_Warrior warrior = (P_Warrior) this.player.get(0);
			P_Wizard wizard = (P_Wizard) this.player.get(1);
			healer.skill(warrior);
			healer.skill(wizard);
		}

	}

}
