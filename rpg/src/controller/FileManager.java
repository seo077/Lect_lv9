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
			System.out.println("���� ����");
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
					int size = temp.length;
					im.setData(temp);
					data = br.readLine();
				}
				fr.close();
				br.close();
			} catch (Exception e) {

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
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
