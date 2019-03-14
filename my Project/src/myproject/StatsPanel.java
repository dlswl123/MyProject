package myproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel {
	
	JPanel pnlMonthly = new JPanel();
	JPanel pnlStats = new JPanel();
	JPanel pnlIncome = new JPanel();
	JPanel pnlExpense = new JPanel();
	JLabel lblIncome = new JLabel("수입");
	JTextField txtIncome = new JTextField(10);
	JLabel lblExpense = new JLabel("지출");
	JTextField txtExpense = new JTextField(10);
	
	JTextArea txaIncome = new JTextArea();
	JTextArea txaExpense = new JTextArea();
	String message = null;
	
	ProjectDao dao = ProjectDao.getInstance();
	
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
		pnlStats.add(txaIncome);
		Vector<LegerVo> income = dao.statsPie(10);
		printMessage(income);
		txaIncome.append(message);
		Vector<LegerVo> expense = dao.statsPie(20);
		printMessage(expense);
		txaExpense.append(message);
		pnlStats.add(txaExpense);
		this.add(pnlStats, BorderLayout.CENTER);
		
	} // 생성자
	
	public void printMessage(Vector<LegerVo> vec) {
		System.out.println(vec);
		for (LegerVo vo : vec) {
			int ledno = vo.getLedNo();
			String ledname = vo.getLedName();
			int ledmoney = vo.getLedMoney();
			String ledtype = vo.getLedType();
			String ledday = vo.getLedDay();
			String strLedday = "";
			if (ledday != null) {
				strLedday = ledday.substring(0, 10);
			}
			String ledmemo = vo.getLedMemo();
			String strLedmemo = "";
			if (ledmemo != null) {
				strLedmemo = ledmemo;
			}
			message = ledno    + "\t|"
				    + ledname  + "\t|"
				    + ledmoney + "\t|"
				    + ledtype  + "\t|"
				    + strLedday   + "\t|"
				    + strLedmemo  + "\n";
		}
		
	} // printMessage()
	
//	class chartPaint extends JPanel {
//		
//		final int START_ARC = 90;
//		final int X = 50;
//		final int Y = 50;
//		final int WIDTH = 300;
//		final int HEIGHT = 300;
//		final int circle = 360;
//		
//		
//		
//		int sum;
//		
//		
//		@Override
//		public void printComponents(Graphics g) {
//			super.printComponents(g);
//			
//			
//		}
//	}
	
	
} // class
