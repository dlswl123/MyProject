package myproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
	String[] searchList = {"금액", "카테고리", "메모"};
	JComboBox<String> cmbSearch = new JComboBox<>(searchList);
	JButton btnSearch = new JButton("조회");
	JTextArea txaMessage = new JTextArea();
	String[] strArray = {"글자순", "금액순", "날짜순", "결제방법순" };
	JComboBox<String> cmbArrayType = new JComboBox<>(strArray);
//	JButton btnDelete = new JButton("삭제");
	
	ProjectDao dao = ProjectDao.getInstance();
	SearchDto dto = new SearchDto();
	
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
		cmbArrayType.addActionListener(this);
		btnSearch.addActionListener(this);
		pnlNorth.add(pnlSearch);
		this.add(pnlNorth, BorderLayout.NORTH);
		
		this.add(new JScrollPane(txaMessage), BorderLayout.CENTER);
	} // 생성자
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String searchKey = null;
		String searchArray = (String)cmbArrayType.getSelectedItem();
		System.out.println(searchArray);
		if (obj == btnSearch) {
			String search = (String)cmbSearch.getSelectedItem();
			System.out.println(search);
			if (search.equals("금액")) {
				searchKey = "ledmoney";
			} else if(search.equals("카테고리")) {
				searchKey = "ledname";
			} else if (search.equals("메모")) {
				searchKey = "ledmemo";
			}
			
			String searchTxt = txtSearch.getText();
			Vector<LegerVo> vec = dao.search(new SearchDto(searchKey, searchTxt));
			
			txaMessage.setText("");
			for (LegerVo vo : vec) {
				int ledno = vo.getLedNo();
				String ledname = vo.getLedName();
				int ledmoney = vo.getLedMoney();
				String ledtype = vo.getLedType();
				String ledday = vo.getLedDay();
				String ledmemo = vo.getLedMemo();
				
				String message = ledno    + "\t|"
							   + ledname  + "\t|"
							   + ledmoney + "\t|"
							   + ledtype  + "\t|"
							   + ledday   + "\t|"
							   + ledmemo  + "\n";
				txaMessage.append(message);
			}
			txaMessage.append("======검색완료====== \n");
			
			
//		} else if (obj == btnInsert) {
//			InputPanel pnl = new InputPanel();
//		} else if (searchArray) {
//			
//		} else if () {
//			
//		}
		}
	}
	
} // class
