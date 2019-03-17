package myproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener {
	
	
//	JButton btnInsert = new JButton("입력");
	JButton btnSearchAll = new JButton("전체조회");
	JTextField txtSearch = new JTextField(10);
	String[] searchList = {"금액", "카테고리", "메모"};
	JComboBox<String> cmbSearch = new JComboBox<>(searchList);
	JButton btnSearch = new JButton("조회");
	JTextArea txaMessage = new JTextArea();
	String[] strArray = {"카테고리순", "금액순", "날짜순", "결제방법순" };
	JComboBox<String> cmbArrayType = new JComboBox<>(strArray);
//	JButton btnDelete = new JButton("삭제");
	LegerDao dao = LegerDao.getInstance();
	SearchDto dto = new SearchDto();
	
	public SearchPanel() {
		this.setLayout(new BorderLayout());
		JPanel pnlNorth = new JPanel(new GridLayout(2, 1));
		pnlNorth.setOpaque(false);
		JPanel pnlSearch = new JPanel();
		pnlSearch.setOpaque(false);
		pnlSearch.add(cmbSearch);
		pnlSearch.add(cmbArrayType);
		pnlSearch.add(txtSearch);
		pnlSearch.add(btnSearch);
		pnlSearch.add(btnSearchAll);
		txaMessage.setEditable(false);
		btnSearchAll.addActionListener(this);
		btnSearch.addActionListener(this);
		pnlNorth.add(pnlSearch);
		this.add(pnlNorth, BorderLayout.NORTH);
		
		this.add(new JScrollPane(txaMessage), BorderLayout.CENTER);
		
		
	} // 생성자
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
//		System.out.println(obj);
		String searchKey = null;
		String searchTxt = "";
		String searchArray = null;
		
		String item = (String)cmbArrayType.getSelectedItem();
		
		if(!(txtSearch.getText().equals(""))) {
			searchTxt = txtSearch.getText();
		}
		
		SearchDto dto = new SearchDto();
		if (item.equals("글자순")) {
			searchArray = "ledname";
		} else if (item.equals("금액순")) {
			searchArray = "ledmoney";
		} else if (item.equals("날짜순")) {
			searchArray = "ledday";
		} else if (item.equals("결제방법순")) {
			searchArray = "ledtype";
		}
//		System.out.println(item);
		dto.setSearchArray(searchArray);
		
		if (obj == btnSearch || obj == txtSearch) {
			String search = (String)cmbSearch.getSelectedItem();
//			System.out.println("btnSearch : " + search);
			if (search.equals("금액")) {
				searchKey = "ledmoney";
			} else if(search.equals("카테고리")) {
				searchKey = "ledname";
			} else if (search.equals("메모")) {
				searchKey = "ledmemo";
			} // 검색버튼 클릭시 검색부분만 선택
			Vector<LegerVo> vec = dao.search(new SearchDto(searchKey, searchTxt, searchArray));
			printMessage(vec);
			
//			System.out.println("search : " + searchArray);
			
			//전체검색
		} else if (obj == btnSearchAll) {
			Vector<LegerVo> vec = dao.searchAll(dto);
			printMessage(vec);

		} // 전체검색버튼 클릭시 내용전체 검색
	} // actionPerformed() 
	
	// 메세지 출력부분
	public void printMessage(Vector<LegerVo> vec) {
		txaMessage.setText("");
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
			String message = ledno    + "\t|"
						   + ledname  + "\t|"
						   + ledmoney + "\t|"
						   + ledtype  + "\t|"
						   + strLedday   + "\t|"
						   + strLedmemo  + "\n";
			txaMessage.append(message);
		}
		txaMessage.append("======검색완료====== \n");
	} // printMessage()
	

} // class
