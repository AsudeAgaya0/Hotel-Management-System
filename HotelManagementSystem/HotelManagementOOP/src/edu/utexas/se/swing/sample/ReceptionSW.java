package edu.utexas.se.swing.sample;
import com.source.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.source.Reception;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextPane;

public class ReceptionSW extends JFrame {

	private JPanel contentPane;
	private JFrame reception;
	private static Reception RS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionSW frame = new ReceptionSW(RS);
					frame.reception.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReceptionSW(Reception rs) {
		this.RS = rs;
		
		if (rs == null) {
			JOptionPane.showMessageDialog(reception, "Enter with your department and staff id on the login screen. You can find them in texts\n"
					+ "FOR INITIAL TEXT\ndep: admin staff id: 1461\ndep: reception staff id: 101\ndep: accounting staff id: 102");
			System.exit(1);
		}
		getContentPane().setLayout(null);
		

		reception = new JFrame();
		reception.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		reception.setTitle("RECEPTION");
		reception.setBounds(100, 100, 367, 611);
		reception.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reception.getContentPane().setLayout(null);
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon("img\\color.png"));
		label_11.setBounds(180, 76, 24, 28);
		reception.getContentPane().add(label_11);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon("img\\signal.png"));
		label_10.setBounds(146, 76, 24, 28);
		reception.getContentPane().add(label_10);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("img\\technology.png"));
		label_3.setBounds(112, 76, 24, 28);
		reception.getContentPane().add(label_3);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("img\\search.png"));
		label_2.setBounds(78, 76, 24, 28);
		reception.getContentPane().add(label_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("img\\delete.png"));
		lblNewLabel_6.setBounds(44, 76, 24, 28);
		reception.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("img\\add.png"));
		lblNewLabel_5.setBounds(10, 76, 24, 28);
		reception.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Reception");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(244, 425, 87, 20);
		reception.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("img\\man.png"));
		lblNewLabel_2.setBounds(203, 415, 32, 37);
		reception.getContentPane().add(lblNewLabel_2);
		
		JLabel label_1 = new JLabel(RS.getName().toUpperCase());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(264, 463, 67, 20);
		reception.getContentPane().add(label_1);

		
		JLabel label = new JLabel(RS.getSurname().toUpperCase());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(264, 494, 67, 20);
		reception.getContentPane().add(label);
		
		JTextPane txtpnSurname = new JTextPane();
		txtpnSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnSurname.setText("Surname:");
		txtpnSurname.setEditable(false);
		txtpnSurname.setBounds(201, 494, 63, 20);
		reception.getContentPane().add(txtpnSurname);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnName.setText("Name:");
		txtpnName.setEditable(false);
		txtpnName.setBounds(201, 463, 63, 20);
		reception.getContentPane().add(txtpnName);
		
		JLabel lblCustomerOperations = new JLabel("Customer&Room Operations");
		lblCustomerOperations.setIcon(new ImageIcon("img\\vacations.png"));
		lblCustomerOperations.setFont(lblCustomerOperations.getFont().deriveFont(lblCustomerOperations.getFont().getStyle() | Font.BOLD));
		lblCustomerOperations.setBounds(10, 30, 276, 37);
		reception.getContentPane().add(lblCustomerOperations);
		
		JButton btnExit = new JButton(" Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLogin SL = new StaffLogin();
				SL.getSL().setVisible(true);
				reception.setVisible(false);
			}
		});
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		btnExit.setIcon(new ImageIcon("img\\signs.png"));
		btnExit.setBounds(10, 524, 140, 37);
		reception.getContentPane().add(btnExit);
		
		JTextPane txtpnReceptionId = new JTextPane();
		txtpnReceptionId.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnReceptionId.setEditable(false);
		txtpnReceptionId.setText("ID:");
		txtpnReceptionId.setBounds(201, 524, 63, 20);
		reception.getContentPane().add(txtpnReceptionId);
		
		JLabel lblNewLabel = new JLabel(String.valueOf(RS.getStaffID()));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(264, 524, 67, 20);
		reception.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel_1.setBounds(191, 399, 140, 162);
		reception.getContentPane().add(lblNewLabel_1);
		
		JButton btnEnterOperitation = new JButton("Enter Operations");
		btnEnterOperitation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerRoomOP SOP = new CustomerRoomOP(null, RS);
				SOP.getSOP().setVisible(true);
				reception.setVisible(false);
			}
		});
		btnEnterOperitation.setBounds(10, 131, 140, 69);
		reception.getContentPane().add(btnEnterOperitation);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel_4.setBounds(10, 76, 140, 28);
		reception.getContentPane().add(lblNewLabel_4);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_9.setBounds(146, 76, 67, 28);
		reception.getContentPane().add(label_9);
		
		JLabel label_12 = new JLabel("Entered Date");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(44, 466, 87, 20);
		reception.getContentPane().add(label_12);
		
		JTextPane txtpnTime = new JTextPane();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		txtpnTime.setText(String.valueOf(now));
		txtpnTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnTime.setEditable(false);
		txtpnTime.setBounds(10, 494, 160, 20);
		reception.getContentPane().add(txtpnTime);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("img\\hotel-managementmini-logo-300x69.png"));
		lblNewLabel_7.setBounds(55, 376, 58, 69);
		reception.getContentPane().add(lblNewLabel_7);
		

	
	}

	public JFrame getReception() {
		return reception;
	}

	public void setReception(JFrame reception) {
		this.reception = reception;
	}
}
