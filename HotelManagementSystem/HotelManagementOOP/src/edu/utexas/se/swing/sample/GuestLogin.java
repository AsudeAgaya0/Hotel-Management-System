package edu.utexas.se.swing.sample;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.source.Customer;
import com.source.Date;
import com.source.Phone;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import com.source.*;
public class GuestLogin {

	private JFrame GL;
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
					GuestLogin window = new GuestLogin();
					window.GL.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	


	public GuestLogin() 
	{
		
		GL = new JFrame();
		GL.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		GL.setTitle("GUEST LOGIN");
		GL.setBounds(100, 100, 316, 170);
		GL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GL.getContentPane().setLayout(null); //retrieves the content pane layer so that you can manage it. 
		
		JLabel lblUserName = new JLabel("Social ID:");
		lblUserName.setBounds(22, 31, 78, 14);
		GL.getContentPane().add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setToolTipText("");
		txtUserName.setBounds(143, 28, 147, 20);
		txtUserName.setColumns(10);
		GL.getContentPane().add(txtUserName);
		
		
		JLabel lblPassword = new JLabel("Room No:");
		lblPassword.setBounds(22, 62, 78, 14);
		GL.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 59, 147, 20);
		GL.getContentPane().add(passwordField);
		
		btnLogIn = new JButton("Login");
		btnLogIn.addActionListener(new ActionListener()  //create an instance of the listener
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Customer c = init(txtUserName.getText(), passwordField.getText());
				if(c != null)
				{
					JOptionPane.showMessageDialog(GL, c.getName().toUpperCase()+" "+c.getSurname().toUpperCase()+" is logged successfully");
					Guest g = new Guest(c);
					g.getGuest().setVisible(true);
					GL.setVisible(false);
				}
				else
				{
					txtUserName.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(GL, "Wrong social ID or room number");
				}
			}
		});
		btnLogIn.setBounds(143, 90, 73, 23);
		GL.getContentPane().add(btnLogIn);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("img\\social.png"));
		label.setBounds(109, 17, 24, 31);
		GL.getContentPane().add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\tools-and-utensils.png"));
		lblNewLabel.setBounds(109, 48, 24, 31);
		GL.getContentPane().add(lblNewLabel);
		
		btnBack = new JButton("Cancel");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				m.getGS().setVisible(true);
				GL.setVisible(false);
			}
		});
		
		btnBack.setBounds(217, 90, 73, 23);
		GL.getContentPane().add(btnBack);
		
		
	}
	
	public Customer init(String socialID, String roomNO) {
		try {
		      File myObj = new File("texts\\Customer.txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      while (myReader.hasNextLine()) {
		    	tempData = myReader.nextLine().split(",");
		        if (tempData[2].equals(socialID) & tempData[7].equals(roomNO)) {
		        	try {
		        	Date arrival = new Date(tempData[5]);
		        	Date departure = new Date(tempData[4]);
		        	Phone phone = new Phone(tempData[8].substring(0, 3),Integer.valueOf(tempData[8].substring(3, 6)),Integer.valueOf(tempData[8].substring(6, 13)));
		        	Address address = new Address(" "," ",tempData[9]);
		        	Customer c = new Customer(tempData[0], tempData[1], Integer.valueOf(tempData[2]), Integer.valueOf(tempData[3]), departure,arrival, Float.valueOf(tempData[6]), Integer.valueOf(tempData[7]), phone, address,true);
		        	return c;
		        	}
		        	catch (NumberFormatException a) {
		        		JOptionPane.showMessageDialog(GL, "a problem in customer text file!");
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


	public JFrame getGL() {
		return GL;
	}


	public void setGL(JFrame gL) {
		GL = gL;
	}
}
