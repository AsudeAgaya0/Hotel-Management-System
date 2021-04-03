package edu.utexas.se.swing.sample;
import com.source.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.source.Administrator;
import com.source.Customer;
import com.source.Date;
import com.source.Phone;

import javax.swing.JToggleButton;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.Color;

public class Admin extends JFrame {
	private JPanel contentPane;
	private JFrame admin;
	private static Administrator ad;
	private ArrayList<Customer> customers = new ArrayList<Customer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin(ad);
					frame.admin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin(Administrator ADMIN) {
		
		this.ad = ADMIN;
		
		if (ad == null) {
			JOptionPane.showMessageDialog(admin, "Enter with your department and staff id on the login screen. You can find them in texts\n"
					+ "FOR INITIAL TEXT\ndep: admin staff id: 1461\ndep: reception staff id: 101\ndep: accounting staff id: 102");
			System.exit(1);
		}
		getContentPane().setLayout(null);
		

		admin = new JFrame();
		admin.getContentPane().setBackground(Color.WHITE);
		admin.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		admin.setTitle("ADMINISTRATOR");
		admin.setBounds(100, 100, 367, 655);
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("ID:");
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setEditable(false);
		textPane.setBounds(201, 569, 63, 20);
		admin.getContentPane().add(textPane);
		
		JLabel label_2 = new JLabel(String.valueOf(ad.getStaffID()));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(264, 569, 67, 20);
		admin.getContentPane().add(label_2);
		
		JLabel label_1 = new JLabel(ad.getSurname().toUpperCase());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(264, 539, 67, 20);
		admin.getContentPane().add(label_1);
		
		JLabel label = new JLabel(ad.getName().toUpperCase());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(264, 508, 67, 20);
		admin.getContentPane().add(label);
		
		JLabel lblStaffOperations = new JLabel("Staff Operations");
		lblStaffOperations.setFont(lblStaffOperations.getFont().deriveFont(lblStaffOperations.getFont().getStyle() | Font.BOLD));
		lblStaffOperations.setIcon(new ImageIcon("img\\administrator.png"));
		lblStaffOperations.setBounds(10, 96, 140, 37);
		admin.getContentPane().add(lblStaffOperations);
		
		JLabel lblCustomerOperations = new JLabel("Customer&Room Operations");
		lblCustomerOperations.setIcon(new ImageIcon("img\\vacations.png"));
		lblCustomerOperations.setFont(lblCustomerOperations.getFont().deriveFont(lblCustomerOperations.getFont().getStyle() | Font.BOLD));
		lblCustomerOperations.setBounds(10, 261, 205, 37);
		admin.getContentPane().add(lblCustomerOperations);
		
		JLabel lblBudgetOperations = new JLabel("Budget Operations");
		lblBudgetOperations.setIcon(new ImageIcon("img\\business-and-finance.png"));
		lblBudgetOperations.setFont(lblBudgetOperations.getFont().deriveFont(lblBudgetOperations.getFont().getStyle() | Font.BOLD));
		lblBudgetOperations.setBounds(201, 96, 140, 37);
		admin.getContentPane().add(lblBudgetOperations);
		
		JButton btnExit = new JButton(" Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLogin SL = new StaffLogin();
				SL.getSL().setVisible(true);
				admin.setVisible(false);
			}
		});
		
		init("texts\\Customer.txt");
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		btnExit.setIcon(new ImageIcon("img\\signs.png"));
		btnExit.setBounds(10, 568, 140, 37);
		admin.getContentPane().add(btnExit);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Surname:");
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane_1.setEditable(false);
		textPane_1.setBounds(201, 539, 63, 20);
		admin.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("Name:");
		textPane_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane_2.setEditable(false);
		textPane_2.setBounds(201, 508, 63, 20);
		admin.getContentPane().add(textPane_2);
		
		JLabel lblAdministrator = new JLabel("Administrator");
		lblAdministrator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdministrator.setBounds(244, 470, 87, 20);
		admin.getContentPane().add(lblAdministrator);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("img\\avatars.png"));
		label_4.setBounds(203, 460, 32, 37);
		admin.getContentPane().add(label_4);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_3.setBounds(196, 457, 135, 148);
		admin.getContentPane().add(label_3);
		
		JButton btnNewButton = new JButton("Enter Operations");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffOP SOP = new StaffOP(ad);
				SOP.getSOP().setVisible(true);
				admin.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 192, 140, 58);
		admin.getContentPane().add(btnNewButton);
		
		JButton btnEnterOperations_1 = new JButton("Enter Operations");
		btnEnterOperations_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BudgetOP B = new BudgetOP(ad, null);
				B.getBOP().setVisible(true);
				admin.setVisible(false);
			}
		});
		btnEnterOperations_1.setBounds(191, 192, 140, 58);
		admin.getContentPane().add(btnEnterOperations_1);
		
		JButton btnEnterOperations = new JButton("Enter Operations");
		btnEnterOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerRoomOP SOP = new CustomerRoomOP(ad, null);
				SOP.getSOP().setVisible(true);
				admin.setVisible(false);
			}
		});
		btnEnterOperations.setBounds(10, 355, 140, 58);
		admin.getContentPane().add(btnEnterOperations);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\add.png"));
		lblNewLabel.setBounds(10, 144, 24, 37);
		admin.getContentPane().add(lblNewLabel);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon("img\\delete.png"));
		label_5.setBounds(44, 144, 24, 37);
		admin.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon("img\\search.png"));
		label_6.setBounds(78, 144, 24, 37);
		admin.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon("img\\technology.png"));
		label_7.setBounds(112, 144, 24, 37);
		admin.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon("img\\add.png"));
		label_8.setBounds(10, 307, 24, 37);
		admin.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("img\\delete.png"));
		label_9.setBounds(44, 307, 24, 37);
		admin.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon("img\\search.png"));
		label_10.setBounds(78, 307, 24, 37);
		admin.getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon("img\\technology.png"));
		label_11.setBounds(112, 309, 24, 37);
		admin.getContentPane().add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon("img\\calculators.png"));
		label_12.setBounds(191, 144, 24, 37);
		admin.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("");
		label_13.setIcon(new ImageIcon("img\\dollar.png"));
		label_13.setBounds(225, 147, 24, 37);
		admin.getContentPane().add(label_13);
		
		JLabel label_14 = new JLabel("");
		label_14.setIcon(new ImageIcon("img\\hands-and-gestures.png"));
		label_14.setBounds(264, 144, 24, 37);
		admin.getContentPane().add(label_14);
		
		JLabel label_17 = new JLabel("");
		label_17.setIcon(new ImageIcon("img\\signal.png"));
		label_17.setBounds(44, 417, 26, 37);
		admin.getContentPane().add(label_17);
		
		JLabel label_18 = new JLabel("");
		label_18.setIcon(new ImageIcon("img\\up-arrow.png"));
		label_18.setBounds(298, 144, 24, 37);
		admin.getContentPane().add(label_18);
		
		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon("img\\humanpictos.png"));
		label_15.setBounds(10, 417, 92, 37);
		admin.getContentPane().add(label_15);
		
		JTextPane txtpnTime = new JTextPane();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		txtpnTime.setText(String.valueOf(now));
		txtpnTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnTime.setEditable(false);
		txtpnTime.setBounds(10, 539, 160, 20);
		admin.getContentPane().add(txtpnTime);
		
		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_16.setBounds(10, 309, 140, 37);
		admin.getContentPane().add(label_16);
		
		JLabel lblEnteredDate = new JLabel("Entered Date");
		lblEnteredDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnteredDate.setBounds(49, 521, 87, 20);
		admin.getContentPane().add(lblEnteredDate);
		
		JLabel label_19 = new JLabel("");
		label_19.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_19.setBounds(10, 147, 140, 37);
		admin.getContentPane().add(label_19);
		
		JLabel label_20 = new JLabel("");
		label_20.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_20.setBounds(191, 147, 140, 37);
		admin.getContentPane().add(label_20);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img\\hotel-management-logo-300x69.png"));
		lblNewLabel_1.setBounds(20, 11, 300, 74);
		admin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblRoomOperations = new JLabel("");
		lblRoomOperations.setIcon(new ImageIcon("img\\color.png"));
		lblRoomOperations.setFont(lblRoomOperations.getFont().deriveFont(lblRoomOperations.getFont().getStyle() | Font.BOLD));
		lblRoomOperations.setBounds(78, 417, 32, 37);
		admin.getContentPane().add(lblRoomOperations);
		
		JLabel label_21 = new JLabel("");
		label_21.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_21.setBounds(10, 417, 65, 37);
		admin.getContentPane().add(label_21);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("img\\hotel-management-logo-300x69.png"));
		lblNewLabel_2.setBounds(10, 11, 331, 63);
		admin.getContentPane().add(lblNewLabel_2);
		
		

	
	}
	
	public void init(String fileName) {
	
		try {
		      File myObj = new File("texts\\Customer.txt");
		      Scanner myReader = new Scanner(myObj);
		      String[] tempData;
		      int index = 0;
		      while (myReader.hasNextLine()) {
		    	tempData = myReader.nextLine().split(","); 
		        	try {
		        	if (index == 0) {
		        		index++;
		        		continue;
		        	}
		        	Date arrival = new Date(tempData[5]);
		        	Date departure = new Date(tempData[4]);
		        	Phone phone = new Phone(tempData[8].substring(0, 3),Integer.valueOf(tempData[8].substring(3, 6)),Integer.valueOf(tempData[8].substring(6, 13)));
		        	Address address = new Address(" "," ",tempData[9]);
		        	Customer c = new Customer(tempData[0], tempData[1], Integer.valueOf(tempData[2]), Integer.valueOf(tempData[3]), departure,arrival, Float.valueOf(tempData[6]), Integer.valueOf(tempData[7]), phone, address,true);
		        	customers.add(c);
		        	}
		        	catch (NumberFormatException a) {
		        		JOptionPane.showMessageDialog(admin, "A problem in customer text file!");
		        	}
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	
	}

	public JFrame getAdmin() {
		return admin;
	}

	public void setAdmin(JFrame admin) {
		this.admin = admin;
	}
}
