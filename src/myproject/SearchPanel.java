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
	
	
//	JButton btnInsert = new JButton("�Է�");
	JButton btnSearchAll = new JButton("��ü��ȸ");
	JTextField txtSearch = new JTextField(10);
	String[] searchList = {"�ݾ�", "ī�װ�", "�޸�"};
	JComboBox<String> cmbSearch = new JComboBox<>(searchList);
	JButton btnSearch = new JButton("��ȸ");
	JTextArea txaMessage = new JTextArea();
	String[] strArray = {"���ڼ�", "�ݾ׼�", "��¥��", "���������" };
//	String[] strColumnNames = {"ledname", "ledmoney",  "ledday","ledtype"};
	JComboBox<String> cmbArrayType = new JComboBox<>(strArray);
//	JButton btnDelete = new JButton("����");
	
	ProjectDao dao = ProjectDao.getInstance();
	SearchDto dto = new SearchDto();
	
	public SearchPanel() {
		this.setLayout(new BorderLayout());
		JPanel pnlNorth = new JPanel(new GridLayout(2, 1));
		pnlNorth.setOpaque(false);
		JPanel pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
//		pnlButtons.add(btnInsert);
//		pnlButtons.add(btnDelete);
		pnlNorth.add(pnlButtons);
		JPanel pnlSearch = new JPanel();
		pnlSearch.setOpaque(false);
		pnlSearch.add(cmbSearch);
		pnlSearch.add(txtSearch);
		pnlSearch.add(btnSearch);
		pnlSearch.add(btnSearchAll);
		pnlSearch.add(cmbArrayType);
		btnSearchAll.addActionListener(this);
		btnSearch.addActionListener(this);
//		cmbArrayType.addActionListener(this);
		pnlNorth.add(pnlSearch);
		this.add(pnlNorth, BorderLayout.NORTH);
		
		this.add(new JScrollPane(txaMessage), BorderLayout.CENTER);
		
		
	} // ������
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String searchKey = null;
		String searchTxt = "";
		
		if(!(txtSearch.getText().equals(""))) {
			searchTxt = txtSearch.getText();
		}
		
		if (obj == btnSearch) {
			String search = (String)cmbSearch.getSelectedItem();
			System.out.println("btnSearch : " + search);
			if (search.equals("�ݾ�")) {
				searchKey = "ledmoney";
			} else if(search.equals("ī�װ�")) {
				searchKey = "ledname";
			} else if (search.equals("�޸�")) {
				searchKey = "ledmemo";
			}
			
			Vector<LegerVo> vec = dao.search(new SearchDto(searchKey, searchTxt));
			printMessage(vec);
			
		} else if (obj == btnSearchAll) {
			String searchArray = null;
			String item = (String)cmbArrayType.getSelectedItem();
//				System.out.println(item);
			SearchDto dto = new SearchDto();
			if (item.equals("���ڼ�")) {
				searchArray = "ledname";
			} else if (item.equals("�ݾ׼�")) {
				searchArray = "ledmoney";
			} else if (item.equals("��¥��")) {
				searchArray = "ledday";
			} else if (item.equals("���������")) {
				searchArray = "ledtype";
			}
			dto.setSearchArray(searchArray);
			
			Vector<LegerVo> vec = dao.searchAll(dto);
			printMessage(vec);
			
//		} else if (obj == cmbArrayType) {
////			if () {
////				
////			}
//			JComboBox<String> cmb = (JComboBox<String>)obj;
//			int index = cmb.getSelectedIndex();
//			String orderColName = strColumnNames[index];
////			System.out.println(orderColName);
//			
//			Vector<LegerVo> vec = dao.searchAll(orderColName);
//			printMessage(vec);
		}
	}
	
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
		txaMessage.append("======�˻��Ϸ�====== \n");
	}
	public void arrayType() {
		
	}
} // class
