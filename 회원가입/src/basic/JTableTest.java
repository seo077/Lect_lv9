package basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame implements MouseListener,KeyListener{

	private final String[]labels = {"NAME","AGE","SEX","KOREAN","ENGLISH","MATH"};
	private JTextField[] fields = new JTextField[6];
	
	private JScrollPane scrolledTable;
	private JTable table;
	
	private JButton addBtn;
	private JButton delBtn;
	
	public JTableTest(String title) {
		//BorderLayout : PanelÀÇ À§Ä¡¸¦ Àß ¹èÄ¡ÇØ ÁÜ.
		// ex>panel.add(textArea,BorderLayout.CENTER);
		this.setLayout(new BorderLayout(10,10));
		
		//GridLayout(int rows,int cols, int hgap,int vgap)
		//ÁöÁ¤µÈ ¼öÀÇ Çà°ú ¿­À» °¡Áö´Â ·¹ÀÌ¾Æ¿ôÀ» ÀÛ¼ºÇÏ°í ·¹ÀÌ¾Æ¿ô °£ ¿©¹éÀ» ÁØ´Ù.
		JPanel topPanel = new JPanel(new GridLayout(6,4,10,5));
		for(int i=0;i<6;i++) {
			topPanel.add(new JLabel(labels[i]));
			fields[i] = new JTextField(30);
			fields[i].addKeyListener(this);
			topPanel.add(fields[i]);
		}
		
		
		//BorderFactory.createEmptyBorder(10,10,10,10) --> ¿©¹é ÁÖ±â
		//»ó,ÇÏ,ÁÂ,¿ì 10¾¿ ¶ç¿ì±â
		topPanel.setBackground(Color.pink);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add("North",topPanel); //°¡Àå À§¶È¿¡ Panel ¼³Á¤
		
		//JTble Àº modelÇÏ°í Ç×»ó ¯˜ÀÌ »ç¿ë
		//DefaultTableModel model = new DefaultTableModel(row,col);
		//JTable table = new JTable(model);
		
		//Å×ÀÌºí ÄÃ·³ÀÇ ÀÌµ¿À» ¹æÁö
		//¤¤table.getTableHeader().setReorderingAllowed(false);
		//Å×ÀÌºí ÄÃ·³ÀÇ »çÀÌÁî¸¦ °íÁ¤
		//¤¤table.getTableHeader().setResizingAllowed(false);
		//Å×ÀÌºí ·Î¿ì¸¦ ÇÑ°³¸¸ ¼±ÅÃ°¡´ÉÇÏ°Ô ÇÑ´Ù.
		//¤¤table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Å×ÀÌºí¿¡ µ¥ÀÌÅÍ¸¦ Ãß°¡
		//¤¤ model.addRow(·Î¿ì ÇÑ ÁÙÀÇ µ¥ÀÌÅÍ);
		
		//µ¥ÀÌÅÍ »èÁ¦
		// 1> ¼±ÅÃÇÑ ·Î¿ì¸¸ »èÁ¦
		// ¤¤ int select = table.getSelectedRow();
		// ¤¤ table.removeRow(select);
		// 2> ÀüÃ¼ ·Î¿ì »èÁ¦
		// ¤¤ table.removeAll();
		
		//headerÃß°¡, ÇàÀº 0°³
		DefaultTableModel model = new DefaultTableModel(labels,0);
		
		table = new JTable(model);
		table.addMouseListener(this);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//½ºÅ©·Ñ µÉ ¼ö ÀÖµµ·Ï JScrollPane Àû¿ë
		scrolledTable = new JScrollPane(table);
		//°¡Àå ÀÚ¸® ¶ç¿ò(padding)
		scrolledTable.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//°¡¿îµ¥¿¡ JTable Ãß°¡
		this.add("Center",scrolledTable);
		
		////////////////////////////////////////////////
		JPanel rightPanel = new JPanel(new GridLayout(5,1,10,10));
		
		addBtn = new JButton("·¹ÄÚµå Ãß°¡");
		addBtn.addMouseListener(this);
		delBtn = new JButton("·¹ÄÚµå »èÁ¦");
		delBtn.addMouseListener(this);
		
		rightPanel.add(addBtn);
		rightPanel.add(delBtn);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//¿À¸¥ÂÊ¿¡ ¹öÆ° Ãß°¡
		this.add("East",rightPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(620,400);
		this.setLocationRelativeTo(null); // Ã¢ °¡¿îµ¥¿¡ À§Ä¡
		this.setVisible(true);
	}
	
	//Ãß°¡
	public void addRecord() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		String record[] = new String[6];
		for(int i=0;i<6;i++) {
			if(isInvalidInput(fields[i].getText())) {
				System.out.println("Invalid input");
				return;
			}
			record[i] = fields[i].getText();
		}
		model.addRow(record);
		
		//¸ðµç TextField ºñ¿ì±â
		for(int i=0;i<6;i++) {
			fields[i].setText("");
		}
		fields[0].requestFocus();
	}
	
	private boolean isInvalidInput(String input) {
		return input == null || input.length() == 0;
	}

	
	//»èÁ¦
	public void removeRecord(int index) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		if(index < 0) {
			//·¹ÅäµåÀÇ ¼ö È®ÀÎ
			if(table.getRowCount() == 0) { //ºñ¾îÀÖ´Â Å×ÀÌºíÀÌ¸é
				return;
			}
			index = 0;
		}
		model.removeRow(index);
	}
	
	//ÀÐ±â
	public void printCell(int row,int col) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		System.out.println(model.getValueAt(row, col));
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			addRecord();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addBtn) {
			addRecord();
		}
		if(e.getSource() == delBtn) {
			int selected = table.getSelectedRow();
			removeRecord(selected);
		}
		
		if(e.getSource() == table) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			
			printCell(row, col);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
