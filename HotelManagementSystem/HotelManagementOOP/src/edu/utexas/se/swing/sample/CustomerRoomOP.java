package edu.utexas.se.swing.sample;
import com.source.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import javax.swing.table.*;

public class CustomerRoomOP {

	private JFrame frmStaffOperatons;
	private JScrollPane pane;
	private JTable jTable1;
	private JTableHeader header;
	private JTableHeader header2;
	private static Administrator ad;
	private static Reception re;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Customer> checkOuts = new ArrayList<Customer>();

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnDisconnect;
	private JButton sButton;
	private JButton addRoom;
	private JButton updateRoom;
	private JButton deleteRoom;
	private JButton buttonSearchRoom;
	private JButton buttonDisplayRoom;

	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtSocialID;

	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTextField textphone1;

	private JComboBox cmbx;
	private JComboBox cmbxSearch;
	private JComboBox cmbxSW;
	private JPanel panel;

	private JComboBox cmbxGender;
	private JLabel lblPhone;
	private JTextField textPhone2;
	private JTextField textPhone3;
	private JTextField txtStreet;
	private JTextField txtTown;
	private JTextField txtCity;
	private JTextField textPrice;
	private JTextField textAge;
	private JTextField textDeparture;
	private JTextField textArrival;
	private JButton btnBack;
	private JLabel lblRoomd;
	private JTextField textRoomNO;
	private JLabel lblSearch;
	private JLabel searchWith;
	private JTextField sCustomerValue;
	private JButton btnDisplayAll;
	private JScrollPane pane2;
	private JTable JTable2;
	private JLabel sRoomID2;
	private JLabel lblRoom;
	private JLabel lblType;
	private JTextField textType;
	private JLabel lblRoomd_2;
	private JTextField textRoomID;
	private JLabel lblPrice;
	private JTextField textPriceRoom;
	private JLabel lblofbed;
	private JTextField TextBed;
	private JLabel lblofshower;
	private JTextField TextShower;
	private JLabel lblAvailable;
	private JLabel lblView;
	private JComboBox cmbxView;
	private JLabel label_1;
	private JButton btnNewButton;
	private JTextField txtSelectedCustomerroomOperations;
	private JTextField textSearchRoom;
	private JTextField textX;
	private JLabel lblForPrice;
	private JTextField textAvailable;
	private JLabel lblInitialValueIs;
	private JButton btnSaveChanges;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRoomOP window = new CustomerRoomOP(ad, re);
					window.frmStaffOperatons.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerRoomOP(Administrator AD, Reception REC) {
		
		if (AD != null)
			this.ad = AD;
		if (REC != null)
			this.re = REC;
	
		cmbxSW = new JComboBox();
		
		jTable1 = new JTable();
		JTable2 = new JTable();
		btnAdd = new JButton("Check-In");
		buttonSearchRoom = new JButton(" Search");
		btnDelete = new JButton("Check-Out selected");
		btnUpdate = new JButton("Update selected");
		lblSearch = new JLabel(" Search Customer");
		btnDisplayAll = new JButton(" Display All Customers");
		btnDisconnect = new JButton("Disconnect");
		btnNewButton = new JButton("Connect");
		sButton = new JButton(" Search");
		addRoom = new JButton(" Add a new room");
		addRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Room r = getRoom();
				r.setAvailable(true);
				if (checkRoom(r)) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "Room number has already been defined!");
					return;
				}

				rooms.add(r);
				updateRoomPanel();
				clearRoomText();
				
				JOptionPane.showMessageDialog(frmStaffOperatons, "A new room has been added successfully!");
				
				}
				catch (NumberFormatException a)
				{
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}
			}
		});
		deleteRoom = new JButton(" Delete selected");
		deleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Room r = getRoom();
				
				if (!r.isAvailable()) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "Room should disconnect its customer!");
					return;
				}
				
				if (!checkRoomRemove(r)) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "Room has been not found!");
					return;
				}
				
				updateRoomPanel();
				clearRoomText();
				JOptionPane.showMessageDialog(frmStaffOperatons, "Room has been deleted successfully!");
				}
				catch (NumberFormatException a)
				{
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}		
			}
		});
		updateRoom = new JButton(" Update selected");
		updateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Room r = getRoom();
					
					if (!checkRoomUpdate(r)) {
						JOptionPane.showMessageDialog(frmStaffOperatons, "RoomID cannot changed!");
						return;
					}
					
					updateRoomPanel();
					clearRoomText();
					JOptionPane.showMessageDialog(frmStaffOperatons, "Room has been updated successfully!");
					}
					catch (NumberFormatException a)
					{
						JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
					}	
			}
		});
		buttonDisplayRoom = new JButton("Display All Rooms");
		
		init();
			
		btnDisplayAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCustomerPanel();
			}
		});
		
		
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Disconnect();
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connect();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {		
			Customer c = getCustomer();
			
			if (c.getRoomID() != 0) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Customer should disconnect his room!");
				return;
			}
			
			if (!checkPersonRemove(c)) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Customer doesn't exits");
				return;
			}
			
			updateCustomerPanel();
			clearCustomerText();
			checkOuts.add(c);
			JOptionPane.showMessageDialog(frmStaffOperatons, "Customer has been deleted successfully!"
					+ "\nName: "+c.getName()
					+ "\nSurname: "+c.getSurname()
					+ "\nSocialID: "+c.getSocialID()
					+ "\nDepartureDate: "+c.getDepartureDay().getString()
					+ "\nArrivalDate: "+c.getArrivalDay().getString()
					+ "\n\nTotal Payment: "+c.getTotalPrice());
			
			}
			
			catch (NumberFormatException a)
			{
				JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
			}								
		}
	    });
										
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {	
				try {
					Customer c = getCustomer();
					
					c.setRoomID(0);
					if (checkPerson(c)) {
						JOptionPane.showMessageDialog(frmStaffOperatons, "Same SocialID has been found!");
						return;
					}
					
					customers.add(c);
					
					updateCustomerPanel();
					clearCustomerText();
					JOptionPane.showMessageDialog(frmStaffOperatons, "Customer has been added successfully!");
						
				}
				catch (NumberFormatException a)
				{
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}
		}
		});
						
		btnUpdate.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				try {
										
				Customer c = getCustomer();

				if (!checkPersonUpdate(c)) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "Customer SocialID cannot changed!");
					return;
				}
					
				updateCustomerPanel();
				clearCustomerText();
				JOptionPane.showMessageDialog(frmStaffOperatons, "Customer has been updated successfully!");
				
			}
				catch (NumberFormatException a)
				{
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}
			}
			});
		
		btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (ad != null) {
						Admin adminOnline = new Admin(ad);
						adminOnline.getAdmin().setVisible(true);
						frmStaffOperatons.setVisible(false);
					}
					if (re != null) {
						ReceptionSW reOnline = new ReceptionSW(re);
						reOnline.getReception().setVisible(true);
						frmStaffOperatons.setVisible(false);
					}
					
				}
				catch (NullPointerException a) {
					JOptionPane.showMessageDialog(frmStaffOperatons,"Admin OBJECT have a problem, start again in MAIN.java");
				}
				}
			});
		

		buttonSearchRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSearchPanelRoom();
				
			}
		});

		buttonDisplayRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRoomPanel();
			}
		});
		
		sButton.setHorizontalAlignment(SwingConstants.LEFT);
		sButton.setIcon(new ImageIcon("img\\search.png"));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label.setBounds(1179, 178, 223, 196);
		panel.add(label);
		
		btnSaveChanges = new JButton("Save All Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveChanges.setIcon(new ImageIcon("img\\interface.png"));
		btnSaveChanges.setHorizontalAlignment(SwingConstants.LEFT);
		btnSaveChanges.setBounds(116, 11, 182, 31);
		panel.add(btnSaveChanges);
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateSearchPanel();
			}
		});
		
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable1.rowAtPoint(evt.getPoint());
				
				txtName.setText(model.getValueAt(update_row, 0).toString());
				txtSurname.setText(model.getValueAt(update_row, 1).toString());
				txtSocialID.setText(model.getValueAt(update_row, 2).toString());
				textAge.setText(model.getValueAt(update_row, 3).toString());
				textDeparture.setText(model.getValueAt(update_row, 4).toString());
				textArrival.setText(model.getValueAt(update_row, 5).toString());
				textPrice.setText(model.getValueAt(update_row, 6).toString());
				textRoomNO.setText(model.getValueAt(update_row, 7).toString());
				textphone1.setText(model.getValueAt(update_row, 8).toString().substring(0,3));
				textPhone2.setText(model.getValueAt(update_row, 8).toString().substring(3,6));
				textPhone3.setText(model.getValueAt(update_row, 8).toString().substring(6,13));
				String[] temp;
				temp = model.getValueAt(update_row, 9).toString().split("-");
				txtTown.setText(temp[0]);
				txtCity.setText(temp[1]);
				txtStreet.setText(temp[2]);
				cmbxGender.setSelectedItem(model.getValueAt(update_row, 10).toString());
			}
		});
		
		
		JTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = JTable2.rowAtPoint(evt.getPoint());
				
				textType.setText(model2.getValueAt(update_row, 0).toString());
				textRoomID.setText(model2.getValueAt(update_row, 1).toString());
				textPriceRoom.setText(model2.getValueAt(update_row, 2).toString());
				TextBed.setText(model2.getValueAt(update_row, 3).toString());
				TextShower.setText(model2.getValueAt(update_row, 4).toString());
				cmbxView.setSelectedItem(model2.getValueAt(update_row, 5).toString());
				textAvailable.setText(model2.getValueAt(update_row, 6).toString());	
			}
		});
		
		frmStaffOperatons.setSize(1450, 797);
		frmStaffOperatons.setUndecorated(true);
		frmStaffOperatons.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frmStaffOperatons.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStaffOperatons.setVisible(true);
	}
	public void read(String textFile) {
		try {
		      File myObj = new File("texts\\"+textFile+".txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      int index = 0;
		      while (myReader.hasNextLine()) {
		    	  
		    	  try {
		    		  
		    	    tempData = myReader.nextLine().split(",");    	    
		        	Date departure = new Date(tempData[4]);
		        	Date arrival = new Date(tempData[5]);       	
		        	Phone phone = new Phone(tempData[8].substring(0, 3),Integer.valueOf(tempData[8].substring(3, 6)),Integer.valueOf(tempData[8].substring(6, 13)));
		        	String tempAddress[] = tempData[9].split("-");
		        	Address address = new Address(tempAddress[0],tempAddress[1],tempAddress[2]);
		        	if (textFile.equals("Customer")) {
		        		Customer c = new Customer(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		    		    Integer.valueOf(tempData[3]),departure,arrival,
		    		    Float.valueOf(tempData[6]), Integer.valueOf(tempData[7]),phone,
		    		    address,Boolean.valueOf(tempData[10]));
		        		
		        		customers.add(c);
		        	}
		        	else {
		        		System.out.println("Text File Name has a problem");
		        	}
		        	
		        	
		    	  }
		    	  catch (NumberFormatException a) {
		    	  }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void readRoom(String textFile) {
		try {
		      File myObj = new File("texts\\"+textFile+".txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      int index = 0;
		      while (myReader.hasNextLine()) {
		    	  
		    	  try {
		    		  tempData = myReader.nextLine().split(",");
		    		  Customer c = null;
		    		  if (!tempData[7].equals("null"))
		    			  for (Customer every : customers)
			    			  if (String.valueOf(every.getSocialID()).equals(tempData[7])) {
			    				  c = every;
			    				  break;
			    			  }
		    		  
		    		  Room r = new Room (tempData[0],Integer.valueOf(tempData[1]),Float.valueOf(tempData[2]),
		    				  Integer.valueOf(tempData[3]),Integer.valueOf(tempData[4]),tempData[5],Boolean.valueOf(tempData[6]), c);
		    		  
		    		  rooms.add(r);	    		  	        	
		    	  }
		    	  catch (NumberFormatException a) {
		    	  }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void updateCustomerPanel() {
		
		String col[] = {"Name","Surname","SocialID","Age","DepartureDay","ArrivalDay","TotalPrice","RoomID","Phone","Address","Gender"};

		model = new DefaultTableModel(col, 0);
		for (Customer every : customers) {
			model.addRow(every.getAll());
		}
		jTable1.setModel(model);
		
	}
    public void updateRoomPanel() {
		
	   String col2[] = {"Type","RoomID","Price","#ofBed","#ofShower","View","Available","CustomerSocialID"};
       model2 = new DefaultTableModel(col2, 0);
       for (Room each : rooms) {
			model2.addRow(each.getAll());
		}
       JTable2.setModel(model2);
		
	}
	
	public void updateSearchPanel() {

		String col[] = {"Name","Surname","SocialID","Age","DepartureDay","ArrivalDay","TotalPrice","RoomID","Phone","Address","Gender"};
		model = new DefaultTableModel(col, 0);
		for (Customer every : customers) {
			if (((String)cmbxSearch.getSelectedItem()).equals("Name"))
				if (sCustomerValue.getText().equals(every.getName()))
					model.addRow(every.getAll());	
			if (((String)cmbxSearch.getSelectedItem()).equals("Surname"))
				if (sCustomerValue.getText().equals(every.getSurname()))
					model.addRow(every.getAll());	
			if (((String)cmbxSearch.getSelectedItem()).equals("SocialID"))
				if (sCustomerValue.getText().equals(String.valueOf(every.getSocialID())))
					model.addRow(every.getAll());		
			if (((String)cmbxSearch.getSelectedItem()).equals("RoomID"))
				if (sCustomerValue.getText().equals(String.valueOf(every.getRoomID())))
					model.addRow(every.getAll());		
		}
		jTable1.setModel(model);
	}
	
	public void updateSearchPanelRoom() {
		
		String col2[] = {"Type","RoomID","Price","#ofBed","#ofShower","View","Available","CustomerSocialID"};
		model2 = new DefaultTableModel(col2, 0);

		for (Room every : rooms) {
			if (((String)cmbxSW.getSelectedItem()).equals("type"))
				if (textSearchRoom.getText().equals(every.getType()))
					model2.addRow(every.getAll());			
			if (((String)cmbxSW.getSelectedItem()).equals("roomID"))
				if (textSearchRoom.getText().equals(String.valueOf(every.getRoomID())))
					model2.addRow(every.getAll());					
			if (((String)cmbxSW.getSelectedItem()).equals("price range(-x , +x)"))
				if (Integer.valueOf(textSearchRoom.getText()) >= every.getPrice() - Integer.valueOf(textX.getText()) &
						Integer.valueOf(textSearchRoom.getText()) <= every.getPrice() + Integer.valueOf(textX.getText()) )
					model2.addRow(every.getAll());	
			if (((String)cmbxSW.getSelectedItem()).equals("view"))
				if (textSearchRoom.getText().equals(every.getView()))
					model2.addRow(every.getAll());	
			if (((String)cmbxSW.getSelectedItem()).equals("available"))
				if (textSearchRoom.getText().equals(String.valueOf(every.isAvailable())))
					model2.addRow(every.getAll());	
		}
		JTable2.setModel(model2);
		
	}
	
	public boolean checkPerson(Customer c) {
		for (Customer every: customers)
			if (every.getSocialID() == c.getSocialID())
				return true;
		return false;
	}
	
	public boolean checkRoom(Room r) {
		for (Room every: rooms)
			if (every.getRoomID() == r.getRoomID())
				return true;
		return false;
	}
	public boolean checkRoomRemove(Room r) {
		for (Room every: rooms)
			if (every.getRoomID() == r.getRoomID()) {
				rooms.remove(every);
				return true;
			}				
		return false;
	}
	public boolean checkRoomUpdate(Room r) {
		for (Room every: rooms)
			if (every.getRoomID() == r.getRoomID()) {
				rooms.remove(every);
				rooms.add(r);
				return true;
			}		
		return false;
	}
	
	public boolean checkPersonRemove(Customer c) {
		for (Customer every: customers)
			if (every.getSocialID() == c.getSocialID()) {
				customers.remove(every);
				return true;
			}
		return false;
	}
	
	public boolean checkPersonUpdate(Customer c) {
		for (Customer every: customers)
			if (every.getSocialID() == c.getSocialID()) {
				customers.remove(every);
				customers.add(c);
				return true;
			}
		return false;
	}
	
	public JFrame getSOP() {
		return frmStaffOperatons;
	}

	public void setFrmStaffOperatons(JFrame frmStaffOperatons) {
		this.frmStaffOperatons = frmStaffOperatons;
	}
	
	public void clearCustomerText() {
		
		txtName.setText("");
		txtSurname.setText("");
		txtSocialID.setText("");
		textAge.setText("");
		textDeparture.setText("");
		textArrival.setText("");
		textPrice.setText("");
		textRoomNO.setText("");
		textphone1.setText("");
		textPhone2.setText("");
		textPhone3.setText("");
		txtTown.setText("");
		txtCity.setText("");
		txtStreet.setText("");

	}
    public void clearRoomText() {
		
		textType.setText("");
		textRoomID.setText("");
		textPriceRoom.setText("");
		TextBed.setText("");
		TextShower.setText("");
	}
   
    public Customer getCustomer() {
	   Date departure = new Date(textDeparture.getText());
		Date arrival = new Date(textArrival.getText());
		Address address = new Address(txtStreet.getText(),txtTown.getText(),txtCity.getText());

		Phone phone = new Phone(textphone1.getText(),Integer.valueOf(textPhone2.getText()),Integer.valueOf(textPhone3.getText()));
		Customer c = new Customer(txtName.getText(),txtSurname.getText(),Integer.valueOf(txtSocialID.getText()),
				Integer.valueOf(textAge.getText()),departure,arrival,Float.valueOf(textPrice.getText()),
				Integer.valueOf(textRoomNO.getText()),phone,address,Boolean.valueOf(cmbxGender.getSelectedItem().toString()));	
		return c;
   }
   
    public Room getRoom() {
	   Room r = new Room(textType.getText(), Integer.valueOf(textRoomID.getText()), Float.valueOf(textPriceRoom.getText()),
				Integer.valueOf(TextBed.getText()),Integer.valueOf(TextShower.getText()),
				String.valueOf(cmbxView.getSelectedItem()),Boolean.valueOf(textAvailable.getText().toString()),null);
	   
	   return r;
   }
   
    public void clearAll() {
	   clearCustomerText();
	   clearRoomText();
	   updateCustomerPanel();
	   updateRoomPanel();
   }
	
    public void Connect() {
		
		try {
			
			Customer p = null;
			for (Customer person : customers)
				if (person.getSocialID() == Integer.valueOf(txtSocialID.getText()))
					p = person;
			
			if (p == null) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected person has not been found!");
				return;
			}
				
			if (p.getRoomID() != 0) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected person is not available!");
				return;
			}
			
			Room r = null;
			for (Room room : rooms)
				if (room.getRoomID() == Integer.valueOf(textRoomID.getText()))
					r = room;
			
			if (r == null) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected room has not been found!");
				return;
			}
			
			if (!r.isAvailable()) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected room is not available!");
				return;
			}
			
			p.setRoomID(r.getRoomID());
			p.Money(r.getPrice());
			r.setAvailable(false);
			r.setCustomer(p);	
			
			clearAll();
			JOptionPane.showMessageDialog(frmStaffOperatons, "Customer and room has been connected successfully!");
			}
			catch (NumberFormatException a)
			{
				JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
			}
	}
	
	public void Disconnect() {
		
		try {
			
			Customer p = null;
			for (Customer person : customers)
				if (person.getSocialID() == Integer.valueOf(txtSocialID.getText()))
					p = person;
			
			if (p == null) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected person has not been found!");
				return;
			}
				
			if (p.getRoomID() == 0) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected person is already available!");
				return;
			}
			
			Room r = null;
			for (Room room : rooms)
				if (room.getRoomID() == Integer.valueOf(textRoomID.getText()))
					r = room;
			
			if (r == null) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected room has not been found!");
				return;
			}
			
			if (r.getRoomID() != p.getRoomID()) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "These customer and room are not connected with each other!");
				return;
			}
			
			if (r.isAvailable()) {
				JOptionPane.showMessageDialog(frmStaffOperatons, "Selected room is already available!");
				return;
			}
			p.setRoomID(0);
			r.setAvailable(true);
			r.setCustomer(null);	
			
			clearAll();	
			JOptionPane.showMessageDialog(frmStaffOperatons, "Customer and room have been disconnected successfully!");
				
		}
		catch (NumberFormatException a)
		{
			JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
		}
		
	}
	

	
	public void save() throws IOException {
		
		
		try (FileWriter writer = new FileWriter("texts\\Customer.txt")) {
			writer.write("name,surname,socialid,age,departureday,arrivalday,totalprice,roomid,phonenumber,address,gender\n");
			for (Customer every : customers) {
				writer.write(every.toString()+"\n");
			}
			writer.close();	
	      
	    }
		try (FileWriter writer = new FileWriter("texts\\Room.txt")) {
			writer.write("type,roomID,price,numberOfBed,numberOfShower,view,available,customerSID\n");
			for (Room every : rooms) {
				writer.write(every.toString()+"\n");
			}
			writer.close();	
			
	    }
	    ArrayList<String> tempCheck = new ArrayList<String>();
	    File myObj = new File("texts\\CheckOut.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	    	  String data = myReader.nextLine();
	    	  tempCheck.add(data);
	      }
	    
		try (FileWriter writer = new FileWriter("texts\\CheckOut.txt")) {
			  for (String every : tempCheck)
				  writer.write(every+"\n");
		      for (Customer every : checkOuts)
		    	  writer.write(every.toString()+"\n");
		      myReader.close();
		      writer.close();	
		}
	}
	
	public void init() {
		frmStaffOperatons = new JFrame();
		frmStaffOperatons.setTitle("CUSTOMER&ROOM OPERATIONS");
		frmStaffOperatons.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		panel = new JPanel();

        read("Customer");
        
		String col[] = {"Name","Surname","SocialID","Age","DepartureDay","ArrivalDay","TotalPrice","RoomID","Phone","Address","Gender"};
		
		model = new DefaultTableModel(col, 0);
		for (Customer every : customers) {
			model.addRow(every.getAll());
		}
		
		jTable1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable1.setModel(model);

		header = jTable1.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);
		
		lblInitialValueIs = new JLabel("(initial value is true)");
		lblInitialValueIs.setBounds(968, 337, 128, 14);
		panel.add(lblInitialValueIs);
		
		textAvailable = new JTextField();
		textAvailable.setEditable(false);
		textAvailable.setColumns(10);
		textAvailable.setBounds(968, 315, 96, 20);
		panel.add(textAvailable);
		
		lblForPrice = new JLabel("Range (+x , -x)");
		lblForPrice.setBounds(1253, 261, 86, 14);
		panel.add(lblForPrice);
		
		textX = new JTextField();
		textX.setColumns(10);
		textX.setBounds(1349, 258, 42, 20);
		panel.add(textX);

		buttonDisplayRoom.setHorizontalAlignment(SwingConstants.LEFT);
		buttonDisplayRoom.setIcon(new ImageIcon("img\\technology.png"));
		buttonDisplayRoom.setBounds(1206, 320, 164, 31);
		panel.add(buttonDisplayRoom);
		
		
		buttonSearchRoom.setHorizontalAlignment(SwingConstants.LEFT);
		buttonSearchRoom.setIcon(new ImageIcon("img\\search.png"));
		buttonSearchRoom.setBounds(1206, 283, 164, 31);
		panel.add(buttonSearchRoom);
		
		textSearchRoom = new JTextField();
		textSearchRoom.setColumns(10);
		textSearchRoom.setBounds(1263, 230, 128, 20);
		panel.add(textSearchRoom);
		
		JLabel label_2_1 = new JLabel("Search:");
		label_2_1.setBounds(1189, 233, 76, 14);
		panel.add(label_2_1);
		
		cmbxSW.setModel(new DefaultComboBoxModel(new String[] {"type", "roomID", "price range(-x , +x)", "view", "available"}));
		cmbxSW.setBounds(1263, 194, 128, 22);
		panel.add(cmbxSW);
		
		JLabel label_2 = new JLabel("Search with:");
		label_2.setBounds(1189, 197, 76, 14);
		panel.add(label_2);
		
		btnDisconnect.setIcon(new ImageIcon("img\\disconnect.png"));
		btnDisconnect.setHorizontalAlignment(SwingConstants.LEFT);
		btnDisconnect.setBounds(700, 329, 128, 31);
		panel.add(btnDisconnect);
		
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon("img\\connect.png"));
		btnNewButton.setBounds(582, 329, 116, 31);
		panel.add(btnNewButton);
		
		lblView = new JLabel("View:");
		lblView.setBounds(896, 233, 76, 14);
		panel.add(lblView);
		
		cmbxView = new JComboBox();
		cmbxView.setModel(new DefaultComboBoxModel(new String[] {"normal", "sea"}));
		cmbxView.setBounds(968, 225, 96, 22);
		panel.add(cmbxView);
		
		JLabel lblSearchWith = new JLabel("Search with:");
		lblSearchWith.setBounds(585, 127, 76, 14);
		panel.add(lblSearchWith);
		
		cmbxSearch = new JComboBox();
		
		cmbxSearch.setModel(new DefaultComboBoxModel(new String[] {"Name", "Surname", "SocialID", "RoomID"}));
		cmbxSearch.setBounds(660, 123, 152, 22);
		panel.add(cmbxSearch);
		
		lblAvailable = new JLabel("Available:");
		lblAvailable.setBounds(896, 318, 76, 14);
		panel.add(lblAvailable);
		
		TextShower = new JTextField();
		TextShower.setColumns(10);
		TextShower.setBounds(968, 283, 96, 20);
		panel.add(TextShower);
		
		lblofshower = new JLabel("#ofShower:");
		lblofshower.setBounds(896, 289, 76, 14);
		panel.add(lblofshower);
		
		TextBed = new JTextField();
		TextBed.setColumns(10);
		TextBed.setBounds(968, 258, 96, 20);
		panel.add(TextBed);
		
		lblofbed = new JLabel("#ofBed:");
		lblofbed.setBounds(896, 264, 76, 14);
		panel.add(lblofbed);
		
		textPriceRoom = new JTextField();
		textPriceRoom.setText("0");
		textPriceRoom.setColumns(10);
		textPriceRoom.setBounds(968, 194, 86, 20);
		panel.add(textPriceRoom);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(896, 200, 76, 14);
		panel.add(lblPrice);
		
		textRoomID = new JTextField();
		textRoomID.setColumns(10);
		textRoomID.setBounds(968, 163, 182, 20);
		panel.add(textRoomID);
		
		textType = new JTextField();
		textType.setColumns(10);
		textType.setBounds(968, 124, 182, 20);
		panel.add(textType);
		btnDisplayAll.setIcon(new ImageIcon("img\\technology.png"));
		btnDisplayAll.setHorizontalAlignment(SwingConstants.LEFT);
		btnDisplayAll.setBounds(619, 253, 193, 31);
		panel.add(btnDisplayAll);

		pane = new JScrollPane(jTable1);
		pane.setBounds(10, 388, 828, 359);

		panel.add(pane);
		frmStaffOperatons.getContentPane().add(panel);
				
		textphone1 = new JTextField();
		textphone1.setText("+xx");
		panel.add(textphone1);
		textphone1.setColumns(10);
		textphone1.setBounds(134, 197, 33, 20);
								
	    lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(55, 200, 69, 14);
	    panel.add(lblPhone);
								
		textPhone2 = new JTextField();
		textPhone2.setText("xxx");
		textPhone2.setColumns(10);
		textPhone2.setBounds(177, 197, 53, 20);
		panel.add(textPhone2);
								
		textPhone3 = new JTextField();
		textPhone3.setText("xxxxxxx");
		textPhone3.setColumns(10);
		textPhone3.setBounds(240, 197, 76, 20);
		panel.add(textPhone3);
								
		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setBounds(55, 225, 69, 14);
		panel.add(lblAddress_1);
								
		txtStreet = new JTextField();
		txtStreet.setText("Street");
		txtStreet.setColumns(10);
		txtStreet.setBounds(134, 222, 96, 20);
		panel.add(txtStreet);
								
		txtTown = new JTextField();
		txtTown.setText("Town");
		txtTown.setColumns(10);
		txtTown.setBounds(134, 253, 96, 20);
		panel.add(txtTown);
								
		txtCity = new JTextField();
		txtCity.setText("City");
		txtCity.setColumns(10);
		txtCity.setBounds(134, 284, 96, 20);
		panel.add(txtCity);
								
		JLabel lblSalary = new JLabel("TotalPrice:");
		lblSalary.setBounds(55, 318, 69, 14);
		panel.add(lblSalary);
								
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(134, 315, 96, 20);
		panel.add(textPrice);
								
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(55, 169, 69, 14);
		panel.add(lblAge);
								
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(134, 166, 96, 20);
		panel.add(textAge);
								
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(265, 318, 69, 14);
		panel.add(lblGender);
								
        cmbxGender = new JComboBox();
		cmbxGender.setModel(new DefaultComboBoxModel(new String[] {"true", "false"}));
		cmbxGender.setBounds(355, 314, 96, 22);
		panel.add(cmbxGender);
								
		JLabel lblBirthdate = new JLabel("DepartureDate:");
		lblBirthdate.setBounds(265, 257, 69, 14);
		panel.add(lblBirthdate);
								
		textDeparture = new JTextField();
		textDeparture.setText("xx.xx.xxxx");
		textDeparture.setColumns(10);
		textDeparture.setBounds(355, 254, 96, 20);
		panel.add(textDeparture);
								
		JLabel lblStartdate = new JLabel("ArrivalDate:");
		lblStartdate.setBounds(265, 287, 69, 14);
		panel.add(lblStartdate);
								
		textArrival = new JTextField();
		textArrival.setText("xx.xx.xxxx");
		textArrival.setColumns(10);
		textArrival.setBounds(355, 284, 96, 20);
		panel.add(textArrival);
		
	    panel.add(btnDelete);
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setIcon(new ImageIcon("img\\delete.png"));
																				
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(327, 110, 182, 31);
		
		panel.add(btnAdd);
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd.setIcon(new ImageIcon("img\\add.png"));
																									
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(327, 73, 182, 31);	
	
		btnUpdate.setIcon(new ImageIcon("img\\freshen.png"));
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnUpdate);
																																		
		btnUpdate.setBounds(327, 152, 182, 31);
								
		btnBack = new JButton("Back");
		btnBack.setIcon(new ImageIcon("img\\arrows.png"));
		
	    btnBack.setHorizontalAlignment(SwingConstants.LEFT);
	    btnBack.setBounds(10, 11, 96, 31);
		panel.add(btnBack);
								
		JLabel lblLastName = new JLabel("SocialID:");
		panel.add(lblLastName);
		lblLastName.setBounds(55, 144, 69, 14);
										
		txtSocialID = new JTextField();
		panel.add(txtSocialID);
		txtSocialID.setBounds(134, 135, 182, 20);
		txtSocialID.setColumns(10);
												
		JLabel lblSurname = new JLabel("Surname:");
		panel.add(lblSurname);
		lblSurname.setBounds(55, 107, 69, 14);
														
	    txtSurname = new JTextField();
		panel.add(txtSurname);
		txtSurname.setBounds(134, 104, 182, 20);
		txtSurname.setColumns(10);
																
		JLabel lblname = new JLabel("Name:");
		panel.add(lblname);
		lblname.setBounds(55, 81, 46, 14);
																		
		txtName = new JTextField();
		panel.add(txtName);
		txtName.setBounds(134, 73, 182, 20);
		txtName.setColumns(10);
																				
		lblRoomd = new JLabel("RoomID:");
		lblRoomd.setBounds(55, 360, 69, 14);
		panel.add(lblRoomd);
																				
		textRoomNO = new JTextField();
		textRoomNO.setEditable(false);
		textRoomNO.setColumns(10);
		textRoomNO.setBounds(134, 357, 96, 20);
		panel.add(textRoomNO);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel.setBounds(20, 36, 512, 370);
		panel.add(lblNewLabel);
		
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearch.setIcon(new ImageIcon("img\\vacations.png"));
		lblSearch.setBounds(572, 73, 251, 31);
		panel.add(lblSearch);
		
		searchWith = new JLabel("Search:");
		searchWith.setBounds(585, 166, 76, 14);
		panel.add(searchWith);
		
		sCustomerValue = new JTextField();
		sCustomerValue.setColumns(10);
		sCustomerValue.setBounds(660, 163, 152, 20);
		panel.add(sCustomerValue);
		
		sButton.setBounds(619, 208, 193, 31);
		panel.add(sButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label.setBounds(572, 61, 266, 240);
		panel.add(label);
		
		pane2 = new JScrollPane((Component) null);
		pane2.setBounds(874, 388, 528, 359);
		panel.add(pane2);
		
		readRoom("Room");
		String col2[] = {"Type","RoomID","Price","#ofBed","#ofShower","View","Available","CustomerSocialID"};
        model2 = new DefaultTableModel(col2, 0);
        for (Room each : rooms) {
			model2.addRow(each.getAll());
		}
        
		header2 = jTable1.getTableHeader();
		header2.setBackground(Color.yellow);
		JTable2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		JTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane2.setViewportView(JTable2);
		
		JTable2.setModel(model2);
		
		lblRoom = new JLabel("Room Operations");
		lblRoom.setIcon(new ImageIcon("img\\color.png"));
		lblRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoom.setBounds(874, 73, 295, 31);
		panel.add(lblRoom);
		
		lblType = new JLabel("Type:");
		lblType.setBounds(896, 127, 76, 14);
		panel.add(lblType);
		
		lblRoomd_2 = new JLabel("RoomID:");
		lblRoomd_2.setBounds(896, 169, 76, 14);
		panel.add(lblRoomd_2);
		
		sRoomID2 = new JLabel("");
		sRoomID2.setIcon(new ImageIcon("img\\unnamed.jpg"));
		sRoomID2.setBounds(874, 61, 295, 313);
		panel.add(sRoomID2);
		
		addRoom.setIcon(new ImageIcon("img\\add.png"));
		addRoom.setHorizontalAlignment(SwingConstants.LEFT);
		addRoom.setBackground(Color.GREEN);
		addRoom.setBounds(1179, 61, 223, 31);
		panel.add(addRoom);
		
		deleteRoom.setIcon(new ImageIcon("img\\delete.png"));
		deleteRoom.setHorizontalAlignment(SwingConstants.LEFT);
		deleteRoom.setBackground(Color.ORANGE);
		deleteRoom.setBounds(1179, 99, 223, 31);
		panel.add(deleteRoom);
		
		updateRoom.setIcon(new ImageIcon("img\\freshen.png"));
		updateRoom.setHorizontalAlignment(SwingConstants.LEFT);
		updateRoom.setBounds(1179, 136, 223, 31);
		panel.add(updateRoom);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("img\\unnamed.jpg"));
		label_1.setBounds(572, 314, 266, 63);
		panel.add(label_1);
		
		txtSelectedCustomerroomOperations = new JTextField();
		txtSelectedCustomerroomOperations.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelectedCustomerroomOperations.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSelectedCustomerroomOperations.setText("Selected Customer&Room Operations");
		txtSelectedCustomerroomOperations.setBounds(572, 298, 266, 20);
		panel.add(txtSelectedCustomerroomOperations);
		txtSelectedCustomerroomOperations.setColumns(10);
	}
}
