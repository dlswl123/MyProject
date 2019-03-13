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

		//����� �Էºκ�
		pnlInput.setBackground(Color.WHITE);
		tab.add("�Է�", pnlInput);
		
		// TODO �Էµȳ��� �ϰ� ��ºκ�
		tab.add("�ϰ�", pnlSearch);
		
		// TODO �Էµȳ��� �ְ� �����ºκ�
		tab.add("����", new JPanel());
		
		// TODO ���� �������� ��ºκ�
		tab.add("����", new JPanel());
		c.add(tab);
		
	} // setUI()
	
	
	
	public static void main(String[] args) {
		new ProjectMain();
		
	} // main

} // class
