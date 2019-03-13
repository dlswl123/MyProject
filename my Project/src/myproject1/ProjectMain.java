package myproject1;


import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ProjectMain extends JFrame {
	
	Container c = getContentPane();
	JTabbedPane tab = new JTabbedPane();
	
	InputPanel pnlInput = new InputPanel();
	SearchPanel pnlSearch = new SearchPanel();
	
//	MyPanel pnl1 = new MyPanel();
	
	public ProjectMain() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My Project");
		setUI();
		setSize(500, 500);
		setVisible(true);
		
	} // ProjectMain()

	private void setUI() {

		//가계부 입력부분
		pnlInput.setBackground(Color.WHITE);
		tab.add("입력", pnlInput);
		
		// TODO 입력된내용 일간 출력부분
		tab.add("일간", pnlSearch);
		
		// TODO 입력된내용 주간 통계출력부분
		tab.add("월간", new JPanel());
		
		// TODO 연간 연말정산 출력부분
		tab.add("연간", new JPanel());
		c.add(tab);
		
	} // setUI()
	
	
	
	public static void main(String[] args) {
		new ProjectMain();
		
	} // main

} // class
