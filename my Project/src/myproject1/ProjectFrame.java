package myproject1;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProjectFrame extends JFrame{
	
	Container c = getContentPane();
	
	JButton btnIncome  = new JButton("����");
	JTextField txtIncome = new JTextField(10);
	String[] strIncome = { "�޿�", "��ü", "�������", "��Ÿ����" };
	JComboBox<String> cmbIncome = new JComboBox<>(strIncome);
	
	JButton btnExpense  = new JButton("����");
	JTextField txtExpense = new JTextField(10);
	String[] strExpense = { "�ĺ�", "����", "�����", "���ں�", "�Ƿ��", "������", "����", "��α�", "����", "����" };
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
