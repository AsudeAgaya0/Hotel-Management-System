package edu.utexas.se.swing.sample;
import com.source.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class HotelInf extends JFrame {

	private JPanel contentPane;
	private JFrame hi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelInf frame = new HotelInf();
					frame.hi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HotelInf() {
		getContentPane().setLayout(null);
		
		hi = new JFrame();
		hi.getContentPane().setBackground(Color.WHITE);
		hi.getContentPane().setForeground(Color.WHITE);
		hi.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		hi.setTitle("INFORMATION");
		hi.setBounds(100, 100, 1291, 916);
		hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGeri = new JButton(" Back");
		btnGeri.setIcon(new ImageIcon("img\\arrows.png"));
		btnGeri.setBounds(10, 11, 135, 33);
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main GS = new Main();
				GS.getGS().setVisible(true);
				hi.setVisible(false);
			}
		});
		hi.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("img\\hotel-managementmini-logo-300x69.png"));
		lblNewLabel_2.setBounds(265, 0, 65, 173);
		hi.getContentPane().add(lblNewLabel_2);
		hi.getContentPane().add(btnGeri);
		
		JLabel lblNewLabel = new JLabel(" HOTEL ROOMS");
		lblNewLabel.setIcon(new ImageIcon("img\\color.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 150, 1255, 23);
		hi.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("img\\architecture-and-city.png"));
		lblNewLabel_1.setBounds(74, 11, 976, 128);
		hi.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img\\r1.jpg"));
		lblNewLabel_4.setBounds(339, 509, 900, 343);
		hi.getContentPane().add(lblNewLabel_4);
		
		JLabel lblRestaurant = new JLabel(" RESTAURANT");
		lblRestaurant.setIcon(new ImageIcon("img\\chefs-hat.png"));
		lblRestaurant.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestaurant.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRestaurant.setBounds(339, 475, 711, 23);
		hi.getContentPane().add(lblRestaurant);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("img\\1.jpg"));
		lblNewLabel_3.setBounds(38, 192, 292, 267);
		hi.getContentPane().add(lblNewLabel_3);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img\\2.jpg"));
		label.setBounds(340, 192, 292, 267);
		hi.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("img\\3.jpg"));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(948, 192, 291, 267);
		hi.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("img\\4.jpg"));
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setBounds(642, 192, 296, 267);
		hi.getContentPane().add(label_2);
		
		JTextPane txtpnGrandAbantHotel = new JTextPane();
		txtpnGrandAbantHotel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		txtpnGrandAbantHotel.setText("Grand Abant Hotel is the renewed face of a radical notion of service with its rooms featuring satellite TV, minibar, hot tubs in suites, central heating, international direct telephones and hair dryers.                                     You will enjoy your holiday with 4 different bars, 400 seated Main Restaurant, the special G\u00F6l Cafe where the magnificent view of Abant emraces you while drinking coffee, the Restaurant, the Bar, the Deep Lake Disco and various activities accompanied by live music.");
		txtpnGrandAbantHotel.setBounds(38, 509, 278, 343);
		hi.getContentPane().add(txtpnGrandAbantHotel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon("img\\hotel-management-logo-300x69.png"));
		lblNewLabel_5.setBounds(642, 11, 597, 162);
		hi.getContentPane().add(lblNewLabel_5);
	
		

	}

	public JFrame getHi() {
		return hi;
	}

	public void setHi(JFrame hi) {
		this.hi = hi;
	}
}
