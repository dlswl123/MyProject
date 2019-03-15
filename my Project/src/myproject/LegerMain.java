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

		//����� �Էºκ�
		pnlInput.setBackground(new Color(212, 244, 250));
		tab.add("�Է�", pnlInput);
		
//		 TODO �Էµȳ��� �ϰ� ��ºκ�
		pnlSearch.setBackground(new Color(232, 217, 255));
		tab.add("��ȸ", pnlSearch);
		
//		 TODO �Էµȳ��� �ְ� �����ºκ�
		tab.add("���", pnlStats);
		
//		 TODO ���� �������� ��ºκ�
		tab.add("����", new JPanel());
		c.add(tab);
		
	} // setUI()
	
	
	public static void main(String[] args) {
		new LegerMain();
		
	} // main

} // class
