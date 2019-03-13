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
	JTextField txtSearch = new JTextField(15);
	String[] searchList = {"�ݾ�", "ī�װ�"};
	JComboBox<String> cmbSearch = new JComboBox<>(searchList);
	JButton btnSearch = new JButton("��ȸ");
	JTextArea txaMessage = new JTextArea();
	String[] strArray = {"���ڼ�", "�ݾ׼�", "��¥��", "���������" };
	JComboBox<String> cmbArrayType = new JComboBox<>(strArray);
//	JButton btnDelete = new JButton("����");
	
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
		btnSearch.addActionListener(this);
		pnlNorth.add(pnlSearch);
		this.add(pnlNorth, BorderLayout.NORTH);
		
		this.add(new JScrollPane(txaMessage), BorderLayout.CENTER);
	} // ������
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String searchKey = null;
		if (obj == btnSearch) {
			String search = (String)cmbSearch.getSelectedItem();
			System.out.println(search);
			if (search.equals("�ݾ�")) {
				searchKey = "ledMoney";
			}
			
			if(search.equals("ī�װ�")) {
				searchKey = "ledname";
			}
			String searchTxt = txtSearch.getText();
			Vector<LegerVo> vec = dao.search(new SearchDto(searchKey, searchTxt));
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
							   + ledmemo  + "\t|";
				txaMessage.append(message);
			}
			txaMessage.append("======�˻��Ϸ�======");
//		} else if (obj == btnInsert) {
//			InputPanel pnl = new InputPanel();
		}
		
	}
	
} // class
