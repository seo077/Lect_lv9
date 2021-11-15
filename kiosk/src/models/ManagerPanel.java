package models;

import javax.swing.JButton;

public class ManagerPanel extends MyUtill{
	private String nextPage = "";
	private JButton back;
	
	@Override
	public String updateNextPage() {
		return this.nextPage;
	}

	@Override
	public void resetNextPage() {
		this.nextPage = "";
	}

}
