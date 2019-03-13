package myproject;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class InputPanel extends JPanel implements ActionListener {
	
	final int INCOME = 10;
	final int EXPENSE = 20;
	
	ProjectDao dao = ProjectDao.getInstance();
	
	JTabbedPane tab = new JTabbedPane();
	JPanel pnlInput = new JPanel();
	InOutVo iovo = new InOutVo();
	
	JButton btnIncome  = new JButton("����");
	JButton btnExpense  = new JButton("����");
	JButton btnSave = new JButton("�Է�");
	
	String[] strIncome = { "�޿�", "�������", "��Ÿ����" };
	JComboBox<String> cmbIncome = new JComboBox<>(strIncome);
	String[] strExpense = { "�ĺ�", "����", "�����", "���ں�", "�Ƿ��", "������", "�����", "��α�", "����", "����" };
	JComboBox<String> cmbExpense = new JComboBox<>(strExpense);
	
	JLabel lblList = new JLabel("ī�װ�");
	JLabel lblMoney = new JLabel("�ݾ�");
	JTextField txtList = new JTextField(20);
	JTextField txtMoney = new JTextField(20);
	
	ButtonGroup group = new ButtonGroup();
	JRadioButton rdoCreditCard  = new JRadioButton("ī��");
	JRadioButton rdoCheckCard = new JRadioButton("üũī��");
	JRadioButton rdoCash = new JRadioButton("����");
	JRadioButton rdoTrans = new JRadioButton("��ü");
	
	JLabel lblDate = new JLabel("��¥");
	JTextField txtDay = new JTextField(20);
	JLabel lblMemo = new JLabel("�޸�");
	JTextField txtMemo = new JTextField(20);
	
	
	public InputPanel() {
		setUI();		
	}
	  
	private void setUI() {
		// �Է��� �����κ�
		this.setLayout(new GridLayout(7, 1));
//		this.setBackground(Color.WHITE);
		// ��, �� �κ� ��ư
		JPanel pnlButton = new JPanel(new FlowLayout());
		pnlButton.setOpaque(false);
		pnlButton.add(btnIncome);
		pnlButton.add(btnExpense);
		btnExpense.addActionListener(this);
		btnIncome.addActionListener(this);
		this.add(pnlButton);
		
		// ī�װ� ���úκ�
		JPanel pnlList = new JPanel();
		pnlList.setOpaque(false);
		pnlList.add(lblList);
		pnlList.add(cmbIncome);
		pnlList.add(cmbExpense);
		cmbIncome.setVisible(false);
		cmbExpense.setVisible(false);
		this.add(pnlList);
//		pnlCenter.add(lblMoney);
		
		// �ݾ� �Էºκ�
		JPanel pnlMoney = new JPanel();
		pnlMoney.setOpaque(false);
		pnlMoney.add(lblMoney);
		pnlMoney.add(txtMoney);
		this.add(pnlMoney);
		
		// �������� ���úκ�
		JPanel pnlRadio = new JPanel(new FlowLayout());
		pnlRadio.setOpaque(false);
		group.add(rdoCreditCard);
		group.add(rdoCheckCard);
		group.add(rdoCash);
		group.add(rdoTrans);
		pnlRadio.add(rdoCreditCard);
		pnlRadio.add(rdoCheckCard);
		pnlRadio.add(rdoCash);
		pnlRadio.add(rdoTrans);
		this.add(pnlRadio);
		
		// ��¥�Է� �κ�
		JPanel pnlDay = new JPanel();
		pnlDay.setOpaque(false);
		pnlDay.add(lblDate);
		pnlDay.add(txtDay);
		this.add(pnlDay);
		
		// �޸��Է� �κ�
		JPanel pnlMemo = new JPanel();
		pnlMemo.setOpaque(false);
		pnlMemo.add(lblMemo);
		pnlMemo.add(txtMemo);
		this.add(pnlMemo);
		
		// �Է�(����) �κ�
		JPanel pnlSave = new JPanel(new FlowLayout());
		pnlSave.setOpaque(false);
		pnlSave.add(btnSave);
		this.add(pnlSave);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Object obj = e.getSource();
				dao.insert(getData());
				System.out.println("�ԷµǾ����ϴ�");
			}
		});
		
//		pnlInput.add(pnlSave);
//		this.add(pnlInput);

	} // setUI()
	
	private LegerVo getData() {
		int ledNo = 0;
		String ledName = "";
		if (btnIncome.isEnabled() == false) {
			ledNo = INCOME;
			ledName = (String)cmbIncome.getSelectedItem();
		} else if (btnExpense.isEnabled() == false) {
			ledNo = EXPENSE;
			ledName = (String)cmbExpense.getSelectedItem();
		} // ��ư�Է°������� �Է¹޴�  cmb����
		int ledMoney = Integer.parseInt(txtMoney.getText());
		String ledType = "";
		if (rdoCreditCard.isSelected() == true) {
			ledType = rdoCreditCard.getText();
		} else if(rdoCheckCard.isSelected() == true) {
			ledType = rdoCheckCard.getText();
		} else if(rdoCash.isSelected() == true) {
			ledType = rdoCash.getText();
		} else if(rdoTrans.isSelected() == true) {
			ledType = rdoTrans.getText();
		} // rdo��ư�� ���� �Է¹��� �ؽ�Ʈ ����
		
		String ledDay = txtDay.getText();
		String ledMemo = txtMemo.getText();
		System.out.println("rdoCheckCard" + rdoCheckCard);
		LegerVo vo = new LegerVo(ledNo, ledName, ledMoney, ledType, ledDay, ledMemo);
		System.out.println(vo.toString());
		return vo;
	} //  ��ư �Է�
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JButton button = (JButton)obj;
		button.setEnabled(false);
//		System.out.println(button);
		
		// ���Լ��ý� �����缱�úҰ��� and ���� �޺��ڽ� ���
		if (obj == btnIncome) {
			btnExpense.setEnabled(true);
			cmbIncome.setVisible(true);
			cmbExpense.setVisible(false);
			
//			System.out.println("����");
			
		// ���⼱�ý� �����缱�úҰ��� and ���� �޺��ڽ� ���
		} else if (obj == btnExpense) {
			btnIncome.setEnabled(true);
			cmbExpense.setVisible(true);
			cmbIncome.setVisible(false);
//			System.out.println("����");
			
		} // if~else if��
		
		
	} // actionPerformed
	
} // class
