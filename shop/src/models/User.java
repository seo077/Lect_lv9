package models;

public class User {
	private String name;
	private String id;
	private String pw;

	public User(String name, String id, String pw) {
		this.name = name;
		this.id = id;
		this.pw = pw;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public String getPw() {
		return this.pw;
	}

	@Override
	public String toString() {
		String userData = "";
		userData += this.name + "/";
		userData += this.id + "/";
		userData += this.pw;
		return userData;
	}
}
