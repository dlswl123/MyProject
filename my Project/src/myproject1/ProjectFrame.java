package myproject1;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProjectFrame extends JFrame{
	
	Container c = getContentPane();
	
	JButton btnIncome  = new JButton("수입");
	JTextField txtIncome = new JTextField(10);
	String[] strIncome = { "급여", "이체", "사업수입", "기타수입" };
	JComboBox<String> cmbIncome = new JComboBox<>(strIncome);
	
	JButton btnExpense  = new JButton("지출");
	JTextField txtExpense = new JTextField(10);
	String[] strExpense = { "식비", "쇼핑", "교통비", "숙박비", "의료비", "교육비", "보험", "기부금", "월세", "이자" };
	JComboBox<String> cmbExpense = new JComboBox<>(strExpense);
	
	
	public ProjectFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My Project");
		setUI();
//		setListenet();
		setSize(500, 150);
		setVisible(true);
	} // ProjectFrame

	private void setUI() {
		
	} // setUI()
	
} // class
