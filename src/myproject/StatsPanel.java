package myproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel {
	
	JPanel pnlMonthly = new JPanel();
	JPanel pnlStats = new JPanel();
	JPanel pnlIncome = new JPanel();
	JPanel pnlExpense = new JPanel();
	JLabel lblIncome = new JLabel("월 수입");
	JTextField txtIncome = new JTextField(10);
	JLabel lblExpense = new JLabel("월 지출");
	JTextField txtExpense = new JTextField(10);
	
	public StatsPanel() {
		this.setLayout(new BorderLayout());
		// North 총금액 출력부분
		pnlMonthly.add(lblIncome);
		pnlMonthly.add(txtIncome);
		pnlMonthly.add(lblExpense);
		pnlMonthly.add(txtExpense);
		this.add(pnlMonthly, BorderLayout.NORTH);
		
		// Center 통계차트 panel
		pnlStats.setLayout(new GridLayout(1, 2));
		pnlIncome.setBackground(new Color(255, 255, 162));
		pnlExpense.setBackground(new Color(255, 217, 236));
		pnlStats.add(pnlIncome);
		pnlStats.add(pnlExpense);
		this.add(pnlStats, BorderLayout.CENTER);
		
		
	} // 생성자
	
	
} // class
