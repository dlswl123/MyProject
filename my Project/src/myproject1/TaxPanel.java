package myproject1;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TaxPanel extends JPanel{
	
	JPanel pnlYear = new JPanel();
	
	public TaxPanel() {
		// TODO Auto-generated constructor stub
	}
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object obj = e.getSource();
////		System.out.println(obj);
//		sal = Integer.parseInt(txtsal.getText());
//		con = Integer.parseInt(txtcon.getText());
//			if (obj == btnCal) {
//				if (sal < 5000000) {
//					result = (int)(sal * 0.7);
//					result = sal - result - basicDeduction - con;
//					if (result < 0) {
//						result = 0;
//					}
//					txttax.setText(String.valueOf(result));
//					
//				} else if( sal <= 15000000 && sal > 5000000) {
//					 result = (int)((float)(sal - 5000000) * 0.4) + 3500000;
//					 result = sal - result - basicDeduction - con;
//					 System.out.println(result);
//					 if( result <= 12000000) {
//							result = (int)((float)(result * 0.06));
//						} else if (result > 12000000 && result <= 46000000) {
//							result = (int)((float)(result * 0.15) - 1080000);
//						} else if (result > 46000000 && result <= 88000000) {
//							result = (int)((float)(result * 0.24) - 5220000);
//						} else if (result > 88000000 && result <= 150000000) {
//							result = (int)((float)(result * 0.35) - 14900000);
//						} else if (result > 150000000 && result <= 300000000) {
//							result = (int)((float)(result * 0.38) - 19400000);
//						} else if (result > 300000000 && result <= 500000000) {
//							result = (int)((float)(result * 0.40) - 25400000);
//						} else if (result > 500000000) {
//							result = (int)((float)(result * 0.42) - 35400000);
//						}
//					 txttax.setText(String.valueOf(result));
////					 System.out.println(result);
//				} else if(sal <= 45000000 && sal > 15000000) {
//					
//				} else if(sal <= 100000000 && sal > 45000000) {
//					
//				} else if(sal > 100000000) {
//					
//				}
//					
////				System.out.println("계산");
//			} else if (obj == btnResult) {
//				
//				System.out.println("조회");
//			}
//		
//			
//			
//	}
	
} // class
