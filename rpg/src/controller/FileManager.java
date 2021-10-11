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
	
	private String MonsterFileName = "monster.txt"; //전제 몬스터
	private String ItemFileName = "item.txt"; //전제 아이템
	private String MemberFileName = "Character.txt"; //전체 캐릭터
	private String MyGuildFileName = "guild.txt"; //내 길드원
	private String MyInventoryFileName = "inventory.txt"; //내 아이템
	
	private File monsterFile = new File(MonsterFileName);
	private File itemFile = new File(ItemFileName);
	private File memberFile = new File(MemberFileName);
	private File myGuildFile = new File(MyGuildFileName);
	private File myInventoryFile = new File(MyInventoryFileName);
	
	private ItemManager im = ItemManager.instance;
	private CharacterManager cm = CharacterManager.instance;
	private MonsterManager mm = MonsterManager.instance;
	
	public void save() {
		String monsterString = mm.toString();
		String itemString = im.toString();
		String memberString = cm.toString();
		
		try {
			fw = new FileWriter(monsterFile);
			fw.write(monsterString);
			fw.close();
			
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
		if(monsterFile.exists()) {
			try {
				fr = new FileReader(monsterFile);
				br = new BufferedReader(fr);
				
				String data = br.readLine();
				while(data != null) {
					String temp[] = data.split("/");
					mm.setData(temp);
					data = br.readLine();
				}
				fr.close();
				br.close();
			
			} catch (Exception e) {
				
			}
		}
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
		String guildString = cm.guildToString();
		String inventoryString = im.inventoryToString();
		
		try {
			fw = new FileWriter(myGuildFile);
			fw.write(guildString);
			fw.close();
			
			fw = new FileWriter(myInventoryFile);
			fw.write(inventoryString);
			fw.close();
		} catch (Exception e) {
			System.out.println("저장 실패");
		}
	}

	public void ownLoad() {
		im.inventoryClear();
		cm.guildClear();
		
		if(myGuildFile.exists()) {
			try {
				fr = new FileReader(myGuildFile);
				br = new BufferedReader(fr);
				
				String data = br.readLine();
				while(data != null) {
					String temp[] = data.split("/");
					cm.setMyGuild(temp);
					data = br.readLine();
				}
				
				fr.close();
				br.close();
			} catch (Exception e) {
			}
		}
		if(myInventoryFile.exists()) {
			try {
				fr = new FileReader(myInventoryFile);
				br = new BufferedReader(fr);
				
				String data = br.readLine();
				while(data != null) {
					String temp[] = data.split("/");
					im.setInventory(temp);
					data = br.readLine();
				}
				
				fr.close();
				br.close();
				System.out.println("로드 성공");
			} catch (Exception e) {
				System.out.println("로드 실패");
				// TODO: handle exception
			}
		}
	}
}
