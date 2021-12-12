package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	public static Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);
	
	private static GameManager instance = new GameManager();
	private GameManager() {};
	public static GameManager getInstance() {
		return instance;
	}
	
	public static String nextStage = "TITLE";
	private Map<String, Stage>stages = new HashMap<String, Stage>();
	private void setting() {
		stages.put("TITLE", TitleStage.getInstance());
		stages.put("LOBBY", LobbyStage.getInstance());
		stages.put("BATTLE", BattleStage.getInstance());
	}
	
	public void run() {
		setting();
		
		boolean go = false;
		while(!go) {
			go = this.update();
		}
	}
	
	public boolean update() {
		Stage stage = stages.get(this.nextStage);
		stage.init();
		
		boolean go = false;
		while(!go) {
			go = stage.nextStage();
		}
		
		if(this.nextStage.equals("")) {
			System.out.println("게임을 종료합니다...");
			return true;
		}else {
			return false;
		}
	}
}
