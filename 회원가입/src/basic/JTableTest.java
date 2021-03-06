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
		//BorderLayout : Panel의 위치를 잘 배치해 줌.
		// ex>panel.add(textArea,BorderLayout.CENTER);
		this.setLayout(new BorderLayout(10,10));
		
		//GridLayout(int rows,int cols, int hgap,int vgap)
		//지정된 수의 행과 열을 가지는 레이아웃을 작성하고 레이아웃 간 여백을 준다.
		JPanel topPanel = new JPanel(new GridLayout(6,4,10,5));
		for(int i=0;i<6;i++) {
			topPanel.add(new JLabel(labels[i]));
			fields[i] = new JTextField(30);
			fields[i].addKeyListener(this);
			topPanel.add(fields[i]);
		}
		
		
		//BorderFactory.createEmptyBorder(10,10,10,10) --> 여백 주기
		//상,하,좌,우 10씩 띄우기
		topPanel.setBackground(Color.pink);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add("North",topPanel); //가장 위똑에 Panel 설정
		
		//JTble 은 model하고 항상 캍이 사용
		//DefaultTableModel model = new DefaultTableModel(row,col);
		//JTable table = new JTable(model);
		
		//테이블 컬럼의 이동을 방지
		//ㄴtable.getTableHeader().setReorderingAllowed(false);
		//테이블 컬럼의 사이즈를 고정
		//ㄴtable.getTableHeader().setResizingAllowed(false);
		//테이블 로우를 한개만 선택가능하게 한다.
		//ㄴtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//테이블에 데이터를 추가
		//ㄴ model.addRow(로우 한 줄의 데이터);
		
		//데이터 삭제
		// 1> 선택한 로우만 삭제
		// ㄴ int select = table.getSelectedRow();
		// ㄴ table.removeRow(select);
		// 2> 전체 로우 삭제
		// ㄴ table.removeAll();
		
		//header추가, 행은 0개
		DefaultTableModel model = new DefaultTableModel(labels,0);
		
		table = new JTable(model);
		table.addMouseListener(this);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//스크롤 될 수 있도록 JScrollPane 적용
		scrolledTable = new JScrollPane(table);
		//가장 자리 띄움(padding)
		scrolledTable.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//가운데에 JTable 추가
		this.add("Center",scrolledTable);
		
		////////////////////////////////////////////////
		JPanel rightPanel = new JPanel(new GridLayout(5,1,10,10));
		
		addBtn = new JButton("레코드 추가");
		addBtn.addMouseListener(this);
		delBtn = new JButton("레코드 삭제");
		delBtn.addMouseListener(this);
		
		rightPanel.add(addBtn);
		rightPanel.add(delBtn);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//오른쪽에 버튼 추가
		this.add("East",rightPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(620,400);
		this.setLocationRelativeTo(null); // 창 가운데에 위치
		this.setVisible(true);
	}
	
	//추가
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
		
		//모든 TextField 비우기
		for(int i=0;i<6;i++) {
			fields[i].setText("");
		}
		fields[0].requestFocus();
	}
	
	private boolean isInvalidInput(String input) {
		return input == null || input.length() == 0;
	}

	
	//삭제
	public void removeRecord(int index) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		if(index < 0) {
			//레토드의 수 확인
			if(table.getRowCount() == 0) { //비어있는 테이블이면
				return;
			}
			index = 0;
		}
		model.removeRow(index);
	}
	
	//읽기
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
