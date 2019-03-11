package myproject1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProjectMain extends JFrame implements ActionListener{
	
	Container c = getContentPane();
	final private int basicDeduction = 1500000;
	
	JLabel lblsal = new JLabel("급여");
	JLabel lblcon = new JLabel("지출");
	JLabel lbltax = new JLabel("세금");
	JTextField txtsal = new JTextField();
	JTextField txtcon = new JTextField();
	JTextField txttax = new JTextField();
	
	JButton btnCal = new JButton("계산");
	JButton btnResult = new JButton("조회");
	
	int sal;
	int con;
	int result;
	
	public ProjectMain() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My Project");
		setUI();
		setListenet();
		setSize(500, 150);
		setVisible(true);
		
	} // ProjectMain()
	
	private void setListenet() {
		btnCal.addActionListener(this);
		btnResult.addActionListener(this);
		
	}

	private void setUI() {
		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new BorderLayout());
		JPanel pnlTxt = new JPanel();
		pnlTxt.setLayout(new GridLayout(2, 1));
		pnlTxt.add(lblsal);
		pnlTxt.add(txtsal);
		pnlTxt.add(lblcon);
		pnlTxt.add(txtcon);
		JPanel pnlButton = new JPanel();
		
		pnlButton.add(btnCal);
		pnlButton.add(btnResult);
		pnlCenter.add(pnlTxt, BorderLayout.CENTER);		
		pnlCenter.add(pnlButton, BorderLayout.SOUTH);
		c.add(pnlCenter, BorderLayout.CENTER);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new GridLayout(1, 2));
		pnlSouth.add(lbltax);
		pnlSouth.add(txttax);
		c.add(pnlSouth, BorderLayout.SOUTH);
		
		
	} // setUI()

	public static void main(String[] args) {
		new ProjectMain();
		
	} // main

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
//		System.out.println(obj);
		sal = Integer.parseInt(txtsal.getText());
		con = Integer.parseInt(txtcon.getText());
			if (obj == btnCal) {
				if (sal < 5000000) {
					result = (int)(sal * 0.7);
					result = sal - result - basicDeduction - con;
					if( result <= 12000000) {
						result = (int)((float)(result * 0.06));
					} else if (result > 12000000 && result <= 46000000) {
						result = (int)((float)(result * 0.15) - 1080000);
					} else if (result > 46000000 && result <= 88000000) {
						result = (int)((float)(result * 0.24) - 5220000);
					} else if (result > 88000000 && result <= 150000000) {
						result = (int)((float)(result * 0.35) - 14900000);
					} else if (result > 150000000 && result <= 300000000) {
						result = (int)((float)(result * 0.38) - 19400000);
					}
					if (result < 0) {
						result = 0;
					}
					txttax.setText(String.valueOf(result));
					
				} else if( sal <= 15000000 && sal > 5000000) {
					 result = (int)((float)(sal - 5000000) * 0.4) + 3500000;
//					 System.out.println(result);
					 result = sal - result - basicDeduction - con;
					 txttax.setText(String.valueOf(result));
//					 System.out.println(result);
				} else if(sal <= 45000000 && sal > 15000000) {
					
				} else if(sal <= 100000000 && sal > 45000000) {
					
				} else if(sal > 100000000) {
					
				}
					
//				System.out.println("계산");
			} else if (obj == btnResult) {
				
				System.out.println("조회");
			}
		
	}

} // class
