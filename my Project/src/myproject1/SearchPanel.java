package myproject1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener {
	
	
//	JButton btnInsert = new JButton("입력");
	JTextField txtSearch = new JTextField(15);
	String[] searchList = {"입금", "출금"};
	JComboBox<String> cmbSearch = new JComboBox<>(searchList);
	JButton btnSearch = new JButton("조회");
	JTextArea txaResult = new JTextArea();
	String[] strArray = {"글자순", "금액순", "날짜순", "결제방법순" };
	JComboBox<String> cmbArrayType = new JComboBox<>(strArray);
//	JButton btnDelete = new JButton("삭제");
	
	public SearchPanel() {
		this.setLayout(new BorderLayout());
		JPanel pnlNorth = new JPanel(new GridLayout(2, 1));
		JPanel pnlButtons = new JPanel();
//		pnlButtons.add(btnInsert);
//		pnlButtons.add(btnDelete);
		pnlNorth.add(pnlButtons);
		JPanel pnlSearch = new JPanel();
		pnlSearch.add(cmbSearch);
		pnlSearch.add(txtSearch);
		pnlSearch.add(btnSearch);
		pnlSearch.add(cmbArrayType);
		pnlNorth.add(pnlSearch);
		this.add(pnlNorth, BorderLayout.NORTH);
		
		this.add(new JScrollPane(txaResult), BorderLayout.CENTER);
	} // 생성자
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnSearch) {
			
//		} else if (obj == btnInsert) {
//			InputPanel pnl = new InputPanel();
		}
		
	}
	
} // class
