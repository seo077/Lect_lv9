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
	
	private String ItemFileName = "item.txt"; //전제 아이템
	private String MemberFileName = "member.txt"; //전체 캐릭터
	private String MyGuildFileName = "guild.txt"; //내 길드원
	private String MyInventoryFileName = "inventory.txt"; //내 아이템
	
	private File itemFile = new File(ItemFileName);
	private File memberFile = new File(MemberFileName);
	private File myGuildFile = new File(MyGuildFileName);
	private File myInventoryFile = new File(MyInventoryFileName);
	
	private ItemManager im = ItemManager.instance;
	private CharacterManager cm = CharacterManager.instance;
	
	public void save() {
		String itemString = im.toString();
		String memberString = cm.toString();
		
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
		cm.clear();
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
					cm.setData(temp);
					data = br.readLine();
				}
				
				fr.close();
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void ownSave() {
		System.out.println("save");
		String guildString = cm.guildToString();
		System.out.println(guildString);
		//String inventoryString = im.inventoryToString();
		
		try {
			fw = new FileWriter(myGuildFile);
			fw.write(guildString);
			fw.close();
			
//			fw = new FileWriter(myInventoryFile);
//			fw.write(inventoryString);
//			fw.close();
		} catch (Exception e) {
			System.out.println("저장 실패");
		}
	}

	public void ownLoad() {
		// TODO Auto-generated method stub
		
	}
}
