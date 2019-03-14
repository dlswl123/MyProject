package myproject;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
class InputPanel extends JPanel implements ActionListener {
	
	final int INCOME = 10;
	final int EXPENSE = 20;
	
	ProjectDao dao = ProjectDao.getInstance();
	
	JTabbedPane tab = new JTabbedPane();
	JPanel pnlInput = new JPanel();
	InOutVo iovo = new InOutVo();
	
	JButton btnIncome  = new JButton("수입");
	JButton btnExpense  = new JButton("지출");
	JButton btnSave = new JButton("입력");
	
	String[] strIncome = { "급여", "사업수입", "기타수입" };
	JComboBox<String> cmbIncome = new JComboBox<>(strIncome);
	String[] strExpense = { "식비", "쇼핑", "교통비", "숙박비", "의료비", "교육비", "보험료", "기부금", "월세", "이자" };
	JComboBox<String> cmbExpense = new JComboBox<>(strExpense);
	
	JLabel lblList = new JLabel("카테고리");
	JLabel lblMoney = new JLabel("금액");
	JTextField txtList = new JTextField(20);
	JTextField txtMoney = new JTextField(20);
	
	ButtonGroup group = new ButtonGroup();
	JRadioButton rdoCreditCard  = new JRadioButton("카드");
	JRadioButton rdoCheckCard = new JRadioButton("체크카드");
	JRadioButton rdoCash = new JRadioButton("현금");
	JRadioButton rdoTrans = new JRadioButton("이체");
	
	JLabel lblDate = new JLabel("날짜");
	JTextField txtDay = new JTextField(20);
	JLabel lblMemo = new JLabel("메모");
	JTextField txtMemo = new JTextField(20);
	
	Date now = new Date(System.currentTimeMillis());
	SpinnerDateModel model = new SpinnerDateModel();
	JSpinner spinDate = new JSpinner(model);
	DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
	JSpinner.DateEditor editor = new JSpinner.DateEditor(spinDate, "yyyy.MM.dd");
	String date = "";
	
	public InputPanel() {
		JFormattedTextField ftxtf = editor.getTextField();
		ftxtf.setEditable(false);
		ftxtf.setHorizontalAlignment(JTextField.CENTER);
		date = dateFormat.format((Date)model.getValue());
		spinDate.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Date value = (Date)model.getValue();
				Date next = (Date)model.getNextValue();
				if (value != null && next != null) {
					date = dateFormat.format(value);
					System.out.println(date);
				}
				
			}
		});
		setUI();
	}
	  
	private void setUI() {
		// 입력탭 설정부분
		this.setLayout(new GridLayout(7, 1));
//		this.setBackground(Color.WHITE);
		// 입, 출 부분 버튼
		JPanel pnlButton = new JPanel(new FlowLayout());
		pnlButton.setOpaque(false);
		pnlButton.add(btnIncome);
		pnlButton.add(btnExpense);
		btnExpense.addActionListener(this);
		btnIncome.addActionListener(this);
		this.add(pnlButton);
		
		// 카테고리 선택부분
		JPanel pnlList = new JPanel();
		pnlList.setOpaque(false);
		pnlList.add(lblList);
		pnlList.add(cmbIncome);
		pnlList.add(cmbExpense);
		cmbIncome.setVisible(false);
		cmbExpense.setVisible(false);
		this.add(pnlList);
//		pnlCenter.add(lblMoney);
		
		// 금액 입력부분
		JPanel pnlMoney = new JPanel();
		pnlMoney.setOpaque(false);
		pnlMoney.add(lblMoney);
		pnlMoney.add(txtMoney);
		this.add(pnlMoney);
		
		// 결제수단 선택부분
		JPanel pnlRadio = new JPanel(new FlowLayout());
		pnlRadio.setOpaque(false);
		group.add(rdoCreditCard);
		group.add(rdoCheckCard);
		group.add(rdoCash);
		group.add(rdoTrans);
		rdoCreditCard.setOpaque(false);
		rdoCash.setOpaque(false);
		rdoCheckCard.setOpaque(false);
		rdoTrans.setOpaque(false);
		pnlRadio.add(rdoCreditCard);
		pnlRadio.add(rdoCheckCard);
		pnlRadio.add(rdoCash);
		pnlRadio.add(rdoTrans);
		this.add(pnlRadio);
		
		// 날짜입력 부분
		JPanel pnlDay = new JPanel();
		pnlDay.setOpaque(false);
		pnlDay.add(lblDate);
//		pnlDay.add(txtDay);
		pnlDay.add(spinDate);
		this.add(pnlDay);
		
		// 메모입력 부분
		JPanel pnlMemo = new JPanel();
		pnlMemo.setOpaque(false);
		pnlMemo.add(lblMemo);
		pnlMemo.add(txtMemo);
		this.add(pnlMemo);
		
		// 입력(저장) 부분
		JPanel pnlSave = new JPanel(new FlowLayout());
		pnlSave.setOpaque(false);
		pnlSave.add(btnSave);
		this.add(pnlSave);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Object obj = e.getSource();
				dao.insert(getData());
				System.out.println("입력되었습니다");
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
		} // 버튼입력값에따라 입력받는  cmb설정
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
		} // rdo버튼에 따라 입력받을 텍스트 설정
		
		String ledDay = date;
		String ledMemo = txtDay.getText();
		System.out.println("rdoCheckCard" + rdoCheckCard);
		LegerVo vo = new LegerVo(ledNo, ledName, ledMoney, ledType, ledDay, ledMemo);
		System.out.println(vo.toString());
		return vo;
	} //  버튼 입력
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JButton button = (JButton)obj;
		button.setEnabled(false);
//		System.out.println(button);
		
		// 수입선택시 수입재선택불가능 and 수입 콤보박스 출력
		if (obj == btnIncome) {
			btnExpense.setEnabled(true);
			cmbIncome.setVisible(true);
			cmbExpense.setVisible(false);
			
//			System.out.println("수입");
			
		// 지출선택시 지출재선택불가능 and 지출 콤보박스 출력
		} else if (obj == btnExpense) {
			btnIncome.setEnabled(true);
			cmbExpense.setVisible(true);
			cmbIncome.setVisible(false);
//			System.out.println("지출");
			
		} // if~else if문
		
		
	} // actionPerformed
	
} // class
