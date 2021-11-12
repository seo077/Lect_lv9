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
		//BorderLayout : Panel�� ��ġ�� �� ��ġ�� ��.
		// ex>panel.add(textArea,BorderLayout.CENTER);
		this.setLayout(new BorderLayout(10,10));
		
		//GridLayout(int rows,int cols, int hgap,int vgap)
		//������ ���� ��� ���� ������ ���̾ƿ��� �ۼ��ϰ� ���̾ƿ� �� ������ �ش�.
		JPanel topPanel = new JPanel(new GridLayout(6,4,10,5));
		for(int i=0;i<6;i++) {
			topPanel.add(new JLabel(labels[i]));
			fields[i] = new JTextField(30);
			fields[i].addKeyListener(this);
			topPanel.add(fields[i]);
		}
		
		
		//BorderFactory.createEmptyBorder(10,10,10,10) --> ���� �ֱ�
		//��,��,��,�� 10�� ����
		topPanel.setBackground(Color.pink);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add("North",topPanel); //���� ���ȿ� Panel ����
		
		//JTble �� model�ϰ� �׻� ���� ���
		//DefaultTableModel model = new DefaultTableModel(row,col);
		//JTable table = new JTable(model);
		
		//���̺� �÷��� �̵��� ����
		//��table.getTableHeader().setReorderingAllowed(false);
		//���̺� �÷��� ����� ����
		//��table.getTableHeader().setResizingAllowed(false);
		//���̺� �ο츦 �Ѱ��� ���ð����ϰ� �Ѵ�.
		//��table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//���̺� �����͸� �߰�
		//�� model.addRow(�ο� �� ���� ������);
		
		//������ ����
		// 1> ������ �ο츸 ����
		// �� int select = table.getSelectedRow();
		// �� table.removeRow(select);
		// 2> ��ü �ο� ����
		// �� table.removeAll();
		
		//header�߰�, ���� 0��
		DefaultTableModel model = new DefaultTableModel(labels,0);
		
		table = new JTable(model);
		table.addMouseListener(this);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//��ũ�� �� �� �ֵ��� JScrollPane ����
		scrolledTable = new JScrollPane(table);
		//���� �ڸ� ���(padding)
		scrolledTable.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//����� JTable �߰�
		this.add("Center",scrolledTable);
		
		////////////////////////////////////////////////
		JPanel rightPanel = new JPanel(new GridLayout(5,1,10,10));
		
		addBtn = new JButton("���ڵ� �߰�");
		addBtn.addMouseListener(this);
		delBtn = new JButton("���ڵ� ����");
		delBtn.addMouseListener(this);
		
		rightPanel.add(addBtn);
		rightPanel.add(delBtn);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//�����ʿ� ��ư �߰�
		this.add("East",rightPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(620,400);
		this.setLocationRelativeTo(null); // â ����� ��ġ
		this.setVisible(true);
	}
	
	//�߰�
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
		
		//��� TextField ����
		for(int i=0;i<6;i++) {
			fields[i].setText("");
		}
		fields[0].requestFocus();
	}
	
	private boolean isInvalidInput(String input) {
		return input == null || input.length() == 0;
	}

	
	//����
	public void removeRecord(int index) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		if(index < 0) {
			//������� �� Ȯ��
			if(table.getRowCount() == 0) { //����ִ� ���̺��̸�
				return;
			}
			index = 0;
		}
		model.removeRow(index);
	}
	
	//�б�
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
