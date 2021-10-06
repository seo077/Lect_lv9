package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	public static FileManager instance = new FileManager();

	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	private String ItemFileName = "item.txt";
	private String MemberFileName = "member.txt";
	
	private File itemFile = new File(ItemFileName);
	private File memberFile = new File(MemberFileName);
	
	private ItemManager im = ItemManager.instance;
	private CharacterManager gm = CharacterManager.instance;
	
	public void save() {
		String itemString = im.toString();
		String memberString = gm.toString();
		
		try {
			fw = new FileWriter(itemFile);
			fw.write(itemString);
			fw.close();
			
			fw = new FileWriter(memberFile);
			fw.write(memberString);
			fw.close();
		} catch (Exception e) {
			System.out.println("저장 실패");
		}
	}

	public void load() {
		im.clear();
		gm.clear();
		if(itemFile.exists()) {
			try {
				fr = new FileReader(itemFile);
				br = new BufferedReader(fr);
				
				String data = br.readLine();
				while(data != null) {
					String temp[] = data.split("/");
					im.setData(temp);
					data = br.readLine();
				}
				fr.close();
				br.close();
				System.out.println(">> 아이템 로드 성공");
			} catch (Exception e) {
				System.out.println(">> 아이템 로드 실패");
			}
		}
		if(memberFile.exists()) {
			try {
				fr = new FileReader(memberFile);
				br = new BufferedReader(fr);
				
				String data = br.readLine();
				while(data != null) {
					String temp[] = data.split("/");
					int size = temp.length;
					gm.setData(temp);
					data = br.readLine();
				}
				
				fr.close();
				br.close();
				System.out.println(">> 캐릭터 로드 성공");
			} catch (Exception e) {
				System.out.println(">> 캐릭터 로드 실패");
				// TODO: handle exception
			}
		}
	}
}
