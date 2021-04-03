package edu.utexas.se.swing.sample;
import com.source.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

public class Main extends JFrame {

	private JPanel contentPane;
	private JFrame GS;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.GS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		getContentPane().setLayout(null);
		


		GS = new JFrame();
		GS.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		GS.getContentPane().setBackground(Color.WHITE);
		GS.setTitle("HOTEL MANAGEMENT");
		GS.setBounds(100, 100, 388, 405);
		GS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GS.getContentPane().setLayout(null); //retrieves the content pane layer so that you can manage it. 
		
		JButton btnGuest = new JButton(" Guest");
		btnGuest.setBackground(Color.LIGHT_GRAY);
		btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGuest.setHorizontalAlignment(SwingConstants.LEFT);
		btnGuest.setIcon(new ImageIcon("img\\vacations.png"));
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestLogin GL = new GuestLogin();
				GL.getGL().setVisible(true);
				GS.setVisible(false);
			}
		});
		btnGuest.setBounds(100, 110, 164, 46);
		GS.getContentPane().add(btnGuest);
		
		JButton btnStaff = new JButton(" Staff");
		btnStaff.setBackground(Color.LIGHT_GRAY);
		btnStaff.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStaff.setIcon(new ImageIcon("img\\administrator.png"));
		btnStaff.setHorizontalAlignment(SwingConstants.LEFT);
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLogin SL = new StaffLogin();
				SL.getSL().setVisible(true);
				GS.setVisible(false);
			}
		});
		btnStaff.setBounds(100, 193, 164, 46);
		GS.getContentPane().add(btnStaff);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img\\hotel-management-logo-300x69.png"));
		label.setBounds(36, 11, 300, 63);
		GS.getContentPane().add(label);
		
		JButton btnHotelInformation = new JButton(" Information");
		btnHotelInformation.setIcon(new ImageIcon("img\\signaling.png"));
		btnHotelInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HotelInf hi = new HotelInf();
				hi.getHi().setVisible(true);
				GS.setVisible(false);
			}
		});
		
		btnHotelInformation.setHorizontalAlignment(SwingConstants.LEFT);
		btnHotelInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHotelInformation.setBackground(Color.LIGHT_GRAY);
		btnHotelInformation.setBounds(100, 278, 164, 46);
		GS.getContentPane().add(btnHotelInformation);
		
		
	}
	

	public JFrame getGS() {
		return GS;
	}

	public void setGS(JFrame gS) {
		GS = gS;
	}
}
