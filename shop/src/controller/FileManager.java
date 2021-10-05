package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	public static FileManager instance = new FileManager();

	private String UserFileName = "users.txt";
	private String ItemFileName = "items.txt";
	private String CartFileName = "carts.txt";

	private File userFile = new File(UserFileName);
	private File itemFile = new File(ItemFileName);
	private File cartFile = new File(CartFileName);

	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;

	private ItemManager im = ItemManager.instance;
	private CartManger cm = CartManger.instance;
	private UserManager um = UserManager.instance;

	public void load() {
		im.clear();
		cm.clear();
		um.clear();

		if (userFile.exists()) {
			try {
				fr = new FileReader(userFile);
				br = new BufferedReader(fr);

				String data = br.readLine();
				while (data != null) {
					String temp[] = data.split("/");
					um.addUser(temp);
					data = br.readLine();
				}

				fr.close();
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (itemFile.exists()) {
			try {
				fr = new FileReader(itemFile);
				br = new BufferedReader(fr);

				String data = br.readLine();
				while (data != null) {
					String temp[] = data.split("/");
					im.addItem(temp);
					data = br.readLine();
				}

				fr.close();
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (cartFile.exists()) {
			try {
				fr = new FileReader(cartFile);
				br = new BufferedReader(fr);

				String data = br.readLine();
				while (data != null) {
					String temp[] = data.split("/");
					cm.addCart(temp);
					data = br.readLine();
				}

				fr.close();
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void save() {
		String itemData = im.toString();
		String userData = um.toString();
		String cartData = cm.toString();

		try {
			fw = new FileWriter(itemFile);
			fw.write(itemData);
			fw.close();

			fw = new FileWriter(userFile);
			fw.write(userData);
			fw.close();

			fw = new FileWriter(cartFile);
			fw.write(cartData);
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
