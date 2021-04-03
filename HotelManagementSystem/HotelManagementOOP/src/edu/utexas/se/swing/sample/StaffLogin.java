package edu.utexas.se.swing.sample;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.source.Administrator;
import com.source.Date;
import com.source.Phone;
import com.source.Reception;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import com.source.*;
public class StaffLogin {

	private JFrame SL;
	private JTextField txtUserName;
	private JPasswordField passwordField;
    private JButton btnLogIn;
    private JLabel label;
    private JLabel lblNewLabel;
    private JButton btnBack;
	
	public static void main(String[] args) {
		//It requires a Runnable object as its argument.
		//You just create a Runnable object and pass it along to the invokeLater() method. 
		//Some time after the current event dispatching is done, this Runnable object will execute.
		EventQueue.invokeLater(new Runnable() { //invokeAndWait
			public void run() {
				try {
					StaffLogin window = new StaffLogin();
					window.SL.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public StaffLogin() 
	{
		SL = new JFrame();
		SL.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		SL.setTitle("STAFF LOGIN");
		SL.setBounds(100, 100, 316, 170);
		SL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SL.getContentPane().setLayout(null); //retrieves the content pane layer so that you can manage it. 
		
		JLabel lblUserName = new JLabel("Department: ");
		lblUserName.setBounds(22, 31, 78, 14);
		SL.getContentPane().add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setToolTipText("");
		txtUserName.setBounds(143, 28, 147, 20);
		txtUserName.setColumns(10);
		SL.getContentPane().add(txtUserName);
		
		
		JLabel lblPassword = new JLabel("Staff ID:");
		lblPassword.setBounds(22, 62, 78, 14);
		SL.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 59, 147, 20);
		SL.getContentPane().add(passwordField);
		
		btnLogIn = new JButton("Login");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogIn.addActionListener(new ActionListener()  //create an instance of the listener
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Reception rs = init(txtUserName.getText(),passwordField.getText());
				Administrator ad = init2(txtUserName.getText(),passwordField.getText());
				Accounting acc = init3(txtUserName.getText(),passwordField.getText());
				if(ad != null)
				{
					JOptionPane.showMessageDialog(SL, "Admin "+ad.getName().toUpperCase()+" "+
				ad.getSurname().toUpperCase()
					+" is logged successfully");
					Admin adminOnline = new Admin(ad);
					adminOnline.getAdmin().setVisible(true);
					SL.setVisible(false);
					
				}
				else if(rs != null) {
					JOptionPane.showMessageDialog(SL,"Reception "+rs.getName().toUpperCase()+" "+rs.getSurname().toUpperCase()
							+" is logged successfully");
					ReceptionSW re = new ReceptionSW(rs);
					re.getReception().setVisible(true);
					SL.setVisible(false);
				}
				else if(acc != null) {
					JOptionPane.showMessageDialog(SL,"Accountant "+acc.getName().toUpperCase()+" "+acc.getSurname().toUpperCase()
							+" is logged successfully");
					Accountant ACC = new Accountant(acc);
					ACC.getAccFrame().setVisible(true);
					SL.setVisible(false);
				}
				else
				{
					txtUserName.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(SL, "Wrong password or user name");
				}
			}
		});
		btnLogIn.setBounds(143, 90, 73, 23);
		SL.getContentPane().add(btnLogIn);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("img\\social.png"));
		label.setBounds(109, 17, 24, 31);
		SL.getContentPane().add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\tools-and-utensils.png"));
		lblNewLabel.setBounds(109, 48, 24, 31);
		SL.getContentPane().add(lblNewLabel);
		
		btnBack = new JButton("Cancel");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				m.getGS().setVisible(true);
				SL.setVisible(false);
			}
		});
		btnBack.setBounds(217, 90, 73, 23);
		SL.getContentPane().add(btnBack);
		
		
	}
	
	public Reception init(String department, String staffID) {
		try {
		      File myObj = new File("texts\\Reception.txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      while (myReader.hasNextLine()) {
		    	tempData = myReader.nextLine().split(",");
		        if (tempData[14].toLowerCase().equals(department) & tempData[3].equals(staffID)) {
		        	try {
		        	Date bDay = new Date(tempData[10]);
		        	Date startDay = new Date(tempData[12]);
		        	Phone phone = new Phone(tempData[4].substring(0, 3),Integer.valueOf(tempData[4].substring(3, 6)),Integer.valueOf(tempData[4].substring(6, 13)));
		        	Address address = new Address(" "," ",tempData[5]);
		        	Reception c = new Reception(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		        			Integer.valueOf(tempData[3]),phone,address,
		        			Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		        			Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		        	return c;
		        	}
		        	catch (NumberFormatException a) {
		        		JOptionPane.showMessageDialog(SL, "a problem in customer text file!");
		        	}
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return null;
	}
	public Administrator init2(String department, String adminID) {
		try {
		      File myObj = new File("texts\\Admin.txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      while (myReader.hasNextLine()) {
		    	tempData = myReader.nextLine().split(",");
		        if (tempData[14].toLowerCase().equals(department) & tempData[3].equals(adminID)) {
		        	try {
		        	Date bDay = new Date(tempData[10]);
		        	Date startDay = new Date(tempData[12]);
		        	Phone phone = new Phone(tempData[4].substring(0, 3),Integer.valueOf(tempData[4].substring(3, 6)),Integer.valueOf(tempData[4].substring(6, 13)));
		        	Address address = new Address(" "," ",tempData[5]);
		        	Administrator c = new Administrator(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		        			Integer.valueOf(tempData[3]),phone,address,
		        			Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		        			Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		        	return c;
		        	}
		        	catch (NumberFormatException a) {
		        		JOptionPane.showMessageDialog(SL, "a problem in customer text file!");
		        	}
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return null;
	}
	public Accounting init3(String department, String adminID) {
		try {
		      File myObj = new File("texts\\Accounting.txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      while (myReader.hasNextLine()) {
		    	tempData = myReader.nextLine().split(",");
		        if (tempData[14].toLowerCase().equals(department) & tempData[3].equals(adminID)) {
		        	try {
		        	Date bDay = new Date(tempData[10]);
		        	Date startDay = new Date(tempData[12]);
		        	Phone phone = new Phone(tempData[4].substring(0, 3),Integer.valueOf(tempData[4].substring(3, 6)),Integer.valueOf(tempData[4].substring(6, 13)));
		        	Address address = new Address(" "," ",tempData[5]);
		        	Accounting c = new Accounting(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		        			Integer.valueOf(tempData[3]),phone,address,
		        			Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		        			Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		        	return c;
		        	}
		        	catch (NumberFormatException a) {
		        		JOptionPane.showMessageDialog(SL, "a problem in customer text file!");
		        	}
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return null;
	}


	public JFrame getSL() {
		return SL;
	}


	public void setSL(JFrame sL) {
		SL = sL;
	}
	


}
