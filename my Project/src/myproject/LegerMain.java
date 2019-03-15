package myproject;


import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javafx.scene.image.Image;

@SuppressWarnings("serial")
public class LegerMain extends JFrame {
	
	Container c = getContentPane();
	JTabbedPane tab = new JTabbedPane();
	
	InputPanel pnlInput = new InputPanel();
	SearchPanel pnlSearch = new SearchPanel();
	StatsPanel pnlStats = new StatsPanel();

	
	public LegerMain() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My Project");
		setUI();
		setSize(600, 500);
		setVisible(true);
		
	} // ProjectMain()

	
	private void setUI() {

		//가계부 입력부분
		pnlInput.setBackground(new Color(212, 244, 250));
		tab.add("입력", pnlInput);
		
//		 TODO 입력된내용 일간 출력부분
		pnlSearch.setBackground(new Color(232, 217, 255));
		tab.add("조회", pnlSearch);
		
//		 TODO 입력된내용 주간 통계출력부분
		tab.add("통계", pnlStats);
		
//		 TODO 연간 연말정산 출력부분
		tab.add("연말", new JPanel());
		c.add(tab);
		
	} // setUI()
	
	
	public static void main(String[] args) {
		new LegerMain();
		
	} // main

} // class
