package myproject;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javafx.scene.image.Image;

@SuppressWarnings("serial")
public class LegerMain extends JFrame implements ActionListener{
	
	Container c = getContentPane();
	JTabbedPane tab = new JTabbedPane();
	
	InputPanel pnlInput = new InputPanel();
	SearchPanel pnlSearch = new SearchPanel();


	JButton btnInput = new JButton("입력");
	JButton btnStats = new JButton("통계");
	
	LegerDialog inputDialog = new LegerDialog(this, "입력", true, pnlInput);
	
	public LegerMain() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My Project");
		setUI();
		setSize(600, 500);
		setVisible(true);
		
	} // ProjectMain()

	
	private void setUI() {

		JPanel pnlNorth = new JPanel();
		pnlSearch.setBackground(new Color(232, 217, 255));
		pnlNorth.setBackground(new Color(232, 217, 255));
		pnlNorth.add(btnInput);
		pnlNorth.add(btnStats);
		btnInput.addActionListener(this);
		btnStats.addActionListener(this);
		c.add(pnlNorth, BorderLayout.NORTH);
		c.add(pnlSearch, BorderLayout.CENTER);
		
	} // setUI()
	
	
	public static void main(String[] args) {
		new LegerMain();
		
	} // main


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnInput) {
			inputDialog.setSize(450, 500);
			inputDialog.setVisible(true);
		} else if(obj == btnStats) {
			StatsPanel pnlStats = new StatsPanel();
			LegerDialog statsDialog = new LegerDialog(this, "통계", true, pnlStats);
			statsDialog.setSize(900, 500);
			statsDialog.setVisible(true);
		}
		
	}

} // class
