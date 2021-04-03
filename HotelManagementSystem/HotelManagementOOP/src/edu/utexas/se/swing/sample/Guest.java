package edu.utexas.se.swing.sample;
import com.source.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.source.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Guest extends JFrame {

	private JPanel contentPane;
	private JTextField txtReceptionNo;
	private JFrame guest;
	private JLabel name, surname, socialID, roomNO, phone, departureDate, arrivalDate;
	private static Customer customer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guest frame = new Guest(customer);
					frame.guest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Guest(Customer c) {
		this.customer = c;
		
		if (customer == null) {
			JOptionPane.showMessageDialog(guest, "Enter with your social ID and room no on the login screen. You can find them in texts\n"
					+ "FOR INITIAL TEXT\nsocialID: 75839213 roomNO: 0\nsocialID: 32449549 roomNO: 402");
			System.exit(1);
		}
		guest = new JFrame();
		guest.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		guest.setTitle("GUEST");
		guest.setBounds(100, 100, 477, 325);
		guest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guest.getContentPane().setLayout(null); //retrieves the content pane layer so that you can manage it. 
		

		name = new JLabel(c.getName());
		name.setBounds(330, 23, 115, 14); 
		guest.getContentPane().add(name);
		
		surname = new JLabel(c.getSurname());
		surname.setBounds(330, 49, 115, 14); 
		guest.getContentPane().add(surname);
		
		socialID = new JLabel(String.valueOf(c.getSocialID()));
		socialID.setBounds(330, 74, 115, 14);
		guest.getContentPane().add(socialID);
		
		roomNO = new JLabel(String.valueOf(c.getRoomID()));
		roomNO.setBounds(330, 99, 115, 14);
		guest.getContentPane().add(roomNO);
		
		String phoneString = c.getPhoneNumber().getCountryCode()+" "+c.getPhoneNumber().getCode()+" "+c.getPhoneNumber().getNumber();
		phone = new JLabel(phoneString);
		phone.setBounds(330, 124, 115, 14);
		guest.getContentPane().add(phone);
		
		departureDate = new JLabel(c.getArrivalDay().getString());
		departureDate.setHorizontalAlignment(SwingConstants.CENTER);
		departureDate.setBounds(260, 241, 71, 14);
		guest.getContentPane().add(departureDate);
		
		arrivalDate = new JLabel(c.getDepartureDay().getString());
		arrivalDate.setHorizontalAlignment(SwingConstants.CENTER);
		arrivalDate.setBounds(346, 241, 99, 14);
		guest.getContentPane().add(arrivalDate);

		JLabel lblDepartureDate = new JLabel("Departure Date:");
		lblDepartureDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepartureDate.setBounds(346, 216, 99, 14);
		guest.getContentPane().add(lblDepartureDate);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img\\transportation (1).png"));
		lblNewLabel_1.setBounds(267, 156, 64, 57);
		guest.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("img\\transportation.png"));
		lblNewLabel_3.setBounds(359, 156, 64, 64);
		guest.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Arrival Date:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(260, 216, 71, 14);
		guest.getContentPane().add(lblNewLabel_2);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(264, 23, 46, 14);
		guest.getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSurname.setForeground(Color.BLACK);
		lblSurname.setBounds(264, 49, 67, 14);
		guest.getContentPane().add(lblSurname);
		
		JLabel lblSocial = new JLabel("SocialID:");
		lblSocial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSocial.setForeground(Color.BLACK);
		lblSocial.setBounds(264, 74, 67, 14);
		guest.getContentPane().add(lblSocial);
		
		JLabel lblRoomNo = new JLabel("Room NO:");
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoomNo.setForeground(Color.BLACK);
		lblRoomNo.setBounds(264, 99, 67, 14);
		guest.getContentPane().add(lblRoomNo);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setBounds(264, 124, 67, 14);
		guest.getContentPane().add(lblPhone);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(250, 11, 195, 257);
		guest.getContentPane().add(lblNewLabel);
		
		JButton btnLogout = new JButton(" Exit");
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setIcon(new ImageIcon("img\\signs.png"));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuestLogin GL = new GuestLogin();
				GL.getGL().setVisible(true);
				guest.setVisible(false);
			}
		});
		btnLogout.setBounds(10, 233, 105, 35);
		guest.getContentPane().add(btnLogout);
		
		JTextPane txtpnWifi = new JTextPane();
		txtpnWifi.setEditable(false);
		txtpnWifi.setBackground(Color.LIGHT_GRAY);
		txtpnWifi.setText("Wi-Fi Password: 2020HotelM");
		txtpnWifi.setBounds(36, 193, 169, 20);
		guest.getContentPane().add(txtpnWifi);
		
		JButton btnComplaints = new JButton("Feedback");
		btnComplaints.setIcon(new ImageIcon("img\\communications.png"));
		btnComplaints.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnComplaints.setHorizontalAlignment(SwingConstants.LEFT);
		btnComplaints.setBounds(125, 233, 115, 35);
		guest.getContentPane().add(btnComplaints);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img\\hotel-managementmini-logo-300x69.png"));
		lblNewLabel_4.setBounds(91, 23, 58, 90);
		guest.getContentPane().add(lblNewLabel_4);
		
		txtReceptionNo = new JTextField();
		txtReceptionNo.setEditable(false);
		txtReceptionNo.setBackground(Color.LIGHT_GRAY);
		txtReceptionNo.setText("Reception No: 187");
		txtReceptionNo.setBounds(36, 156, 169, 20);
		guest.getContentPane().add(txtReceptionNo);
		txtReceptionNo.setColumns(10);
		
		JTextPane txtpnHaveANice = new JTextPane();
		txtpnHaveANice.setEditable(false);
		txtpnHaveANice.setText("   Have a nice holiday");
		txtpnHaveANice.setBounds(60, 118, 124, 20);
		guest.getContentPane().add(txtpnHaveANice);
	}

	public JFrame getGuest() {
		return guest;
	}

	public void setGuest(JFrame guest) {
		this.guest = guest;
	}
	
	
}
