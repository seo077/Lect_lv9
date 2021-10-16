package controller;

import java.util.Random;
import java.util.Scanner;

public class GameManager {
	public static Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);

	private static GameManager instance = new GameManager();

	private GameManager() {
	};

	public static GameManager getInstance() {
		return instance;
	}


	public void run() {
		
	}

	

	



}
