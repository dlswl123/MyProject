package myproject;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LegerDialog extends JDialog{
	JButton btnClose = new JButton("Á¾·á");
	
	public LegerDialog(JFrame frame, String title, boolean modal, JPanel panel) {
		super(frame, title, modal);
		setSize(550, 500);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		JPanel pnl = new JPanel();
		pnl.add(btnClose);
		add(pnl, BorderLayout.SOUTH);
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
	}
}
