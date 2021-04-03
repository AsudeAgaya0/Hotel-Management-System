package edu.utexas.se.swing.sample;
import com.source.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class Accountant extends JFrame {

	private JPanel contentPane;
	private JFrame accFrame;
	private static Accounting acc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accountant frame = new Accountant(acc);
					frame.accFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Accountant(Accounting ACC) {
		accFrame = new JFrame();
		accFrame.setTitle("ACCOUNTANT");
		accFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		this.acc = ACC;
		
		if (acc == null) {
			JOptionPane.showMessageDialog(accFrame, "Enter with your department and staff id on the login screen. You can find them in texts\n"
					+ "FOR INITIAL TEXT\ndep: admin staff id: 1461\ndep: reception staff id: 101\ndep: accounting staff id: 102");
			System.exit(1);
		}
		
		
		accFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accFrame.setBounds(100, 100, 421, 474);
		accFrame.getContentPane().setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("img\\up-arrow.png"));
		label_3.setBounds(136, 59, 32, 37);
		accFrame.getContentPane().add(label_3);
		
		JLabel id = new JLabel("<DYNAM\u0130C>");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(296, 371, 87, 20);
		accFrame.getContentPane().add(id);
		
		JLabel surname = new JLabel("<DYNAM\u0130C>");
		surname.setHorizontalAlignment(SwingConstants.CENTER);
		surname.setBounds(296, 340, 87, 20);
		accFrame.getContentPane().add(surname);
		
		JLabel name = new JLabel("<DYNAM\u0130C>");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(296, 309, 87, 20);
		accFrame.getContentPane().add(name);
		
		JTextPane txtpnId_1 = new JTextPane();
		txtpnId_1.setText("ID:");
		txtpnId_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnId_1.setEditable(false);
		txtpnId_1.setBounds(223, 371, 63, 20);
		accFrame.getContentPane().add(txtpnId_1);
		
		JTextPane txtpnSurname = new JTextPane();
		txtpnSurname.setText("Surname:");
		txtpnSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnSurname.setEditable(false);
		txtpnSurname.setBounds(223, 340, 63, 20);
		accFrame.getContentPane().add(txtpnSurname);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setText("ID:");
		txtpnId.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnId.setEditable(false);
		txtpnId.setBounds(223, 309, 63, 20);
		accFrame.getContentPane().add(txtpnId);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("img\\hands-and-gestures.png"));
		label_1.setBounds(94, 59, 32, 37);
		accFrame.getContentPane().add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img\\dollar.png"));
		label.setBounds(52, 59, 32, 37);
		accFrame.getContentPane().add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img\\calculators.png"));
		lblNewLabel_1.setBounds(10, 59, 32, 37);
		accFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblBudgerOperations = new JLabel("Budget Operations");
		lblBudgerOperations.setIcon(new ImageIcon("img\\business-and-finance.png"));
		lblBudgerOperations.setFont(lblBudgerOperations.getFont().deriveFont(lblBudgerOperations.getFont().getStyle() | Font.BOLD));
		lblBudgerOperations.setBounds(10, 11, 276, 37);
		accFrame.getContentPane().add(lblBudgerOperations);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel.setBounds(10, 59, 160, 37);
		accFrame.getContentPane().add(lblNewLabel);
		
		JButton btnEnterOP = new JButton("Enter Operation");
		btnEnterOP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BudgetOP B = new BudgetOP(null, acc);
				B.getBOP().setVisible(true);
				accFrame.setVisible(false);
			}
		});
		btnEnterOP.setBounds(10, 107, 160, 45);
		accFrame.getContentPane().add(btnEnterOP);
		
		JButton button = new JButton(" Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLogin SL = new StaffLogin();
				SL.getSL().setVisible(true);
				accFrame.setVisible(false);
			}
		});
		button.setIcon(new ImageIcon("img\\signs.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(10, 387, 140, 37);
		accFrame.getContentPane().add(button);
		
		JTextPane textDate = new JTextPane();
		LocalDateTime now = LocalDateTime.now();
		textDate.setText(String.valueOf(now));
		textDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		textDate.setEditable(false);
		textDate.setBounds(10, 360, 160, 20);
		accFrame.getContentPane().add(textDate);
		
		JLabel label_2 = new JLabel("Entered Date");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(39, 329, 87, 20);
		accFrame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_2 = new JLabel("Accountant");
		lblNewLabel_2.setIcon(new ImageIcon("img\\administrator.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(213, 272, 182, 26);
		accFrame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel_3.setBounds(213, 259, 182, 152);
		accFrame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img\\hotel-managementmini-logo-300x69.png"));
		lblNewLabel_4.setBounds(49, 249, 58, 69);
		accFrame.getContentPane().add(lblNewLabel_4);
		accFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		name.setText(acc.getName());
        surname.setText(acc.getSurname());
        id.setText(String.valueOf(acc.getStaffID()));
		
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}
	

	public JFrame getAccFrame() {
		return accFrame;
	}

	public void setAccFrame(JFrame accFrame) {
		this.accFrame = accFrame;
	}

	public Accounting getAcc() {
		return acc;
	}

	public void setAcc(Accounting acc) {
		this.acc = acc;
	}
	

}
