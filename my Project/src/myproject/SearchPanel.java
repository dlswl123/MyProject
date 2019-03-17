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
	
	
//	JButton btnInsert = new JButton("�Է�");
	JButton btnSearchAll = new JButton("��ü��ȸ");
	JTextField txtSearch = new JTextField(10);
	String[] searchList = {"�ݾ�", "ī�װ�", "�޸�"};
	JComboBox<String> cmbSearch = new JComboBox<>(searchList);
	JButton btnSearch = new JButton("��ȸ");
	JTextArea txaMessage = new JTextArea();
	String[] strArray = {"ī�װ���", "�ݾ׼�", "��¥��", "���������" };
	JComboBox<String> cmbArrayType = new JComboBox<>(strArray);
//	JButton btnDelete = new JButton("����");
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
		
		
	} // ������
	
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
		if (item.equals("���ڼ�")) {
			searchArray = "ledname";
		} else if (item.equals("�ݾ׼�")) {
			searchArray = "ledmoney";
		} else if (item.equals("��¥��")) {
			searchArray = "ledday";
		} else if (item.equals("���������")) {
			searchArray = "ledtype";
		}
//		System.out.println(item);
		dto.setSearchArray(searchArray);
		
		if (obj == btnSearch || obj == txtSearch) {
			String search = (String)cmbSearch.getSelectedItem();
//			System.out.println("btnSearch : " + search);
			if (search.equals("�ݾ�")) {
				searchKey = "ledmoney";
			} else if(search.equals("ī�װ�")) {
				searchKey = "ledname";
			} else if (search.equals("�޸�")) {
				searchKey = "ledmemo";
			} // �˻���ư Ŭ���� �˻��κи� ����
			Vector<LegerVo> vec = dao.search(new SearchDto(searchKey, searchTxt, searchArray));
			printMessage(vec);
			
//			System.out.println("search : " + searchArray);
			
			//��ü�˻�
		} else if (obj == btnSearchAll) {
			Vector<LegerVo> vec = dao.searchAll(dto);
			printMessage(vec);

		} // ��ü�˻���ư Ŭ���� ������ü �˻�
	} // actionPerformed() 
	
	// �޼��� ��ºκ�
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
	} // printMessage()
	

} // class
