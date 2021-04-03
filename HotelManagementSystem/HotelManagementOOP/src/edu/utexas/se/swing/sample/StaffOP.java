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

public class StaffOP {

	private JFrame frmStaffOperatons;
	private JScrollPane pane;
	private JTable jTable1;
	private JTable jTable2;
	private JTableHeader header;
	private static Administrator ad;
	private ArrayList<Administrator> admins = new ArrayList<Administrator>();
	private ArrayList<Reception> receptions = new ArrayList<Reception>();
	private ArrayList<Accounting> accountings = new ArrayList<Accounting>();

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton buttonSearch;
	private JButton buttonDisplay;

	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtSocialID;

	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTextField textphone1;

	private JComboBox cmbx;
	private JComboBox cmbxGender;
	private JComboBox cmbxWorking;
	private JComboBox cmbxInsurance;
	private JComboBox cmbxStaff;
	private JPanel panel;

	private JLabel lblPhone;
	private JTextField textPhone2;
	private JTextField textPhone3;
	private JTextField textStaffID;
	private JTextField txtStreet;
	private JTextField txtTown;
	private JTextField txtCity;
	private JTextField textSalary;
	private JTextField textAge;
	private JTextField textBDay;
	private JTextField textEdu;
	private JTextField textSDay;
	private JTextField textDepartment;
	
	private JButton btnBack;
	private JTextField textSearchStaff;
	private JButton btnSaveAllChanges;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffOP window = new StaffOP(ad);
					window.frmStaffOperatons.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffOP(Administrator AD) {
		this.ad = AD;

		jTable1 = new JTable();	
		btnUpdate = new JButton("Update");
		btnBack = new JButton("Back");
		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete selected");
		
		init();

		jTable1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable1.rowAtPoint(evt.getPoint());

				txtName.setText(model.getValueAt(update_row, 0).toString());
				txtSurname.setText(model.getValueAt(update_row, 1).toString());
				txtSocialID.setText(model.getValueAt(update_row, 2).toString());
				textStaffID.setText(model.getValueAt(update_row, 3).toString());
				textphone1.setText(model.getValueAt(update_row, 4).toString().substring(0,3));
				textPhone2.setText(model.getValueAt(update_row, 4).toString().substring(3,6));
				textPhone3.setText(model.getValueAt(update_row, 4).toString().substring(6,13));
				String[] temp;
				temp = model.getValueAt(update_row, 5).toString().split("-");
				txtTown.setText(temp[0]);
				txtCity.setText(temp[1]);
				txtStreet.setText(temp[2]);
				textAge.setText(model.getValueAt(update_row, 8).toString());
				textSalary.setText(model.getValueAt(update_row, 7).toString());
				textBDay.setText(model.getValueAt(update_row, 10).toString());
				textEdu.setText(model.getValueAt(update_row, 11).toString());
				textSDay.setText(model.getValueAt(update_row, 12).toString());
				textDepartment.setText(model.getValueAt(update_row, 14).toString());

			}
		});
		jTable1.setModel(model);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textDepartment.getText().equals("admin")) {
						
						Administrator c = getAdmin();
						
						if (!checkAdmin(c, "r")) {
							JOptionPane.showMessageDialog(frmStaffOperatons,"Staff has not been found!");
							return;
						}
					}
					else if (textDepartment.getText().equals("reception")) {
						Reception c = getReception();
						
						if (!checkReception(c, "r")) {
							JOptionPane.showMessageDialog(frmStaffOperatons,"Staff has not been found!");
							return;
						}
					}
					else if (textDepartment.getText().equals("accounting")) {
						Accounting c = getAccounting();
						
						if (checkAcc(c, "r")) {
							JOptionPane.showMessageDialog(frmStaffOperatons,"SocialID or StaffID does exits!");
							return;
						}
					}
					updatePanel();
					clearText();
					}
				catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}
			}
		});
										
		panel.add(btnDelete);
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setIcon(new ImageIcon("img\\delete.png"));
																	
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(327, 73, 165, 31);
				
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				if (textDepartment.getText().equals("admin")) {
					Administrator c = getAdmin();
					
					if (checkAdmin(c , "a")) {
						JOptionPane.showMessageDialog(frmStaffOperatons,"SocialID or StaffID does exits!");
						return;
					}
				}
				else if (textDepartment.getText().equals("reception")) {
					Reception c = getReception();
					
					if (checkReception(c, "a")) {
						JOptionPane.showMessageDialog(frmStaffOperatons,"SocialID or StaffID does exits!");
						return;
					};
				}
				else if (textDepartment.getText().equals("accounting")) {
					Accounting c = getAccounting();
					
					if (checkAcc(c, "a")) {
						JOptionPane.showMessageDialog(frmStaffOperatons,"SocialID or StaffID does exits!");
						return;
					}
				}
				
				updatePanel();
				clearText();
				}
				catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}
			}
		});
		panel.add(btnAdd);
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd.setIcon(new ImageIcon("img\\add.png"));
												
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(327, 33, 165, 31);
																
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				if (textDepartment.getText().equals("admin")) {
					Administrator c = getAdmin();
					
					if (!checkAdmin(c, "u")) {
						JOptionPane.showMessageDialog(frmStaffOperatons,"Staff ID cannot be changed!");
						return;
					}
				}
				else if (textDepartment.getText().equals("reception")) {
					
					Reception c = getReception();
					
					if (!checkReception(c, "u")) {
						JOptionPane.showMessageDialog(frmStaffOperatons,"Staff ID cannot be changed!");
						return;
					}
				}
				else if (textDepartment.getText().equals("accounting")) {
					
                    Accounting c = getAccounting();
					
					if (!checkAcc(c, "u")) {
						JOptionPane.showMessageDialog(frmStaffOperatons,"Staff ID cannot be changed!");
						return;
					}
				}
				updatePanel();
				clearText();
				
				}
				catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frmStaffOperatons, "All parameter should've been filled completely with correct format!");
				}	
			}
	});
	btnUpdate.setIcon(new ImageIcon("img\\freshen.png"));
	btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
	panel.add(btnUpdate);
																																		
	btnUpdate.setBounds(326, 115, 166, 31);
								
	
	btnBack.setIcon(new ImageIcon("img\\arrows.png"));
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
			Admin adminOnline = new Admin(ad);
			adminOnline.getAdmin().setVisible(true);
			frmStaffOperatons.setVisible(false);
			}
			catch (NullPointerException a) {
				JOptionPane.showMessageDialog(frmStaffOperatons,"Admin OBJECT have a problem, start again in MAIN.java");
			}
		}
	});
	btnBack.setHorizontalAlignment(SwingConstants.LEFT);
	btnBack.setBounds(0, 0, 96, 31);
	panel.add(btnBack);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon("img\\unnamed.jpg"));
	lblNewLabel.setBounds(542, 33, 239, 307);
	panel.add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("");
	lblNewLabel_1.setIcon(new ImageIcon("img\\hotel-management-logo-300x69.png"));
	lblNewLabel_1.setBounds(819, 33, 300, 79);
	panel.add(lblNewLabel_1);
	
	btnSaveAllChanges = new JButton("Save All Changes");
	btnSaveAllChanges.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnSaveAllChanges.setIcon(new ImageIcon("img\\interface.png"));
	btnSaveAllChanges.setHorizontalAlignment(SwingConstants.LEFT);
	btnSaveAllChanges.setBounds(106, 0, 172, 31);
	panel.add(btnSaveAllChanges);
												
	frmStaffOperatons.setSize(1165, 797);
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
		        	Date bDay = new Date(tempData[10]);
		        	Date startDay = new Date(tempData[12]);
		        	Phone phone = new Phone(tempData[4].substring(0, 3),Integer.valueOf(tempData[4].substring(3, 6)),Integer.valueOf(tempData[4].substring(6, 13)));
		        	String tempAddress[] = tempData[5].split("-");
		        	Address address = new Address(tempAddress[0],tempAddress[1],tempAddress[2]);
		        	if (textFile.equals("Admin")) {
		        		Administrator c = new Administrator(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		    		    Integer.valueOf(tempData[3]),phone,address,
		    		    Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		    		    Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		        		admins.add(c);
		        	}
		        	else if (textFile.equals("Reception")) {
		        		Reception c = new Reception(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		    		    Integer.valueOf(tempData[3]),phone,address,
		    		    Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		    		    Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		        	    receptions.add(c);
		        	}
		        	else if (textFile.equals("Accounting")) {
		        		Accounting c = new Accounting(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		    		    Integer.valueOf(tempData[3]),phone,address,
		    		    Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		    		    Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		        	    accountings.add(c);
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
	
     public void updatePanel() {
		
    	String col[] = {"Name","Surname","SocialID","StaffID","Phone","Address","Insurance","Salary","Age","Gender","BirthDate","EducationStatus","StartDate","Working","Department"};

		model = new DefaultTableModel(col, 0);
		for (Administrator every : admins) {
			model.addRow(every.getAll());
		}
		for (Reception every : receptions) {
			model.addRow(every.getAll());
		}
		for (Accounting every : accountings) {
			model.addRow(every.getAll());
		}
		jTable1.setModel(model);
		
	}
    public void clearText() {
    	txtName.setText("");
    	txtSurname.setText("");
    	txtSocialID.setText("");
    	textStaffID.setText("");
    	textphone1.setText("+xx");
    	textPhone2.setText("xxx");
    	textPhone3.setText("xxxxxxx");
    	txtStreet.setText("Street");
    	txtCity.setText("City");
    	txtTown.setText("Town");
    	textSalary.setText("");
    	textAge.setText("");
    	textEdu.setText("");
    	textSDay.setText("xx.xx.xxxx");
    	textBDay.setText("xx.xx.xxxx");
    	textDepartment.setText("");
    }
    public void updateSearchPanel() {
    	
    	String col[] = {"Name","Surname","SocialID","StaffID","Phone","Address","Insurance","Salary","Age","Gender","BirthDate","EducationStatus","StartDate","Working","Department"};
    	
		model = new DefaultTableModel(col, 0);
		for (Administrator every : admins) {
			if (cmbxStaff.getSelectedItem().toString().equals("name") & textSearchStaff.getText().equals(every.getName()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("surname") & textSearchStaff.getText().equals(every.getSurname()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("socialID") & textSearchStaff.getText().equals(String.valueOf(every.getSocialID())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("staffID") & textSearchStaff.getText().equals(String.valueOf(every.getStaffID())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("working") & textSearchStaff.getText().equals(String.valueOf(every.isWorking())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("department") & textSearchStaff.getText().equals(every.getDepartment()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("insurance") & textSearchStaff.getText().equals(String.valueOf(every.isInsurance())))
				model.addRow(every.getAll());	
		}
		for (Reception every : receptions) {
			if (cmbxStaff.getSelectedItem().toString().equals("name") & textSearchStaff.getText().equals(every.getName()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("surname") & textSearchStaff.getText().equals(every.getSurname()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("socialID") & textSearchStaff.getText().equals(String.valueOf(every.getSocialID())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("stafflID") & textSearchStaff.getText().equals(String.valueOf(every.getStaffID())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("working") & textSearchStaff.getText().equals(String.valueOf(every.isWorking())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("department") & textSearchStaff.getText().equals(every.getDepartment()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("insurance") & textSearchStaff.getText().equals(String.valueOf(every.isInsurance())))
				model.addRow(every.getAll());	
		}
		for (Accounting every : accountings) {
			if (cmbxStaff.getSelectedItem().toString().equals("name") & textSearchStaff.getText().equals(every.getName()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("surname") & textSearchStaff.getText().equals(every.getSurname()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("socialID") & textSearchStaff.getText().equals(String.valueOf(every.getSocialID())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("staffID") & textSearchStaff.getText().equals(String.valueOf(every.getStaffID())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("working") & textSearchStaff.getText().equals(String.valueOf(every.isWorking())))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("department") & textSearchStaff.getText().equals(every.getDepartment()))
				model.addRow(every.getAll());
			if (cmbxStaff.getSelectedItem().toString().equals("insurance") & textSearchStaff.getText().equals(String.valueOf(every.isInsurance())))
				model.addRow(every.getAll());
		}
		jTable1.setModel(model);	
    }
     
    public Administrator getAdmin() {
    	Date bDay = new Date(textBDay.getText());
    	Date startDay = new Date(textSDay.getText());
    	Phone phone = new Phone(textphone1.getText(),
    			Integer.valueOf(textPhone2.getText()),Integer.valueOf(textPhone3.getText()));
    	Address address = new Address(txtStreet.getText(),txtTown.getText(),txtCity.getText());
    	Administrator a = new Administrator(txtName.getText(), txtSurname.getText(), Integer.valueOf(txtSocialID.getText()), 
    		    Integer.valueOf(textStaffID.getText()),phone,address,
    		    Boolean.valueOf(cmbxInsurance.getSelectedItem().toString()), Float.valueOf(textSalary.getText()),
    		    Integer.valueOf(textAge.getText()),
    		    Boolean.valueOf(cmbxGender.getSelectedItem().toString()),
    		    bDay,textEdu.getText(),startDay,Boolean.valueOf(cmbxWorking.getSelectedItem().toString()),
    		    textDepartment.getText());
    	return a;	
    }
    public Reception getReception() {
    	Date bDay = new Date(textBDay.getText());
    	Date startDay = new Date(textSDay.getText());
    	Phone phone = new Phone(textphone1.getText(),
    			Integer.valueOf(textPhone2.getText()),Integer.valueOf(textPhone3.getText()));
    	Address address = new Address(txtStreet.getText(),txtTown.getText(),txtCity.getText());
    	Reception r = new Reception(txtName.getText(), txtSurname.getText(), Integer.valueOf(txtSocialID.getText()), 
    		    Integer.valueOf(textStaffID.getText()),phone,address,
    		    Boolean.valueOf(cmbxInsurance.getSelectedItem().toString()), Float.valueOf(textSalary.getText()),
    		    Integer.valueOf(textAge.getText()),
    		    Boolean.valueOf(cmbxGender.getSelectedItem().toString()),
    		    bDay,textEdu.getText(),startDay,Boolean.valueOf(cmbxWorking.getSelectedItem().toString()),
    		    textDepartment.getText());
    	return r;
    }
    public Accounting getAccounting() {
    	Date bDay = new Date(textBDay.getText());
    	Date startDay = new Date(textSDay.getText());
    	Phone phone = new Phone(textphone1.getText(),
    			Integer.valueOf(textPhone2.getText()),Integer.valueOf(textPhone3.getText()));
    	Address address = new Address(txtStreet.getText(),txtTown.getText(),txtCity.getText());
    	Accounting ac = new Accounting(txtName.getText(), txtSurname.getText(), Integer.valueOf(txtSocialID.getText()), 
    		    Integer.valueOf(textStaffID.getText()),phone,address,
    		    Boolean.valueOf(cmbxInsurance.getSelectedItem().toString()), Float.valueOf(textSalary.getText()),
    		    Integer.valueOf(textAge.getText()),
    		    Boolean.valueOf(cmbxGender.getSelectedItem().toString()),
    		    bDay,textEdu.getText(),startDay,Boolean.valueOf(cmbxWorking.getSelectedItem().toString()),
    		    textDepartment.getText());
    	return ac;
    }
	

    public boolean checkReception(Reception a, String select) {
		
		for (Reception every : receptions) {
			if (a.getStaffID() == every.getStaffID() & select == "a") {
				return true;
			}
			else if (a.getStaffID() == every.getStaffID() & select == "r") {
				receptions.remove(every);
				return true;
			}
            else if (a.getStaffID() == every.getStaffID() & select == "u") {
            	receptions.remove(every);
				receptions.add(a);
				return true;
			}		
		}	
		return false;	
	}
    public boolean checkAdmin(Administrator a, String select) {
		
		for (Administrator every : admins) {
			if (a.getStaffID() == every.getStaffID() & select == "a") {
				return true;
			}
			else if (a.getStaffID() == every.getStaffID() & select == "r") {
				admins.remove(every);
				return true;
			}
            else if (a.getStaffID() == every.getStaffID() & select == "u") {
            	admins.remove(every);
				admins.add(a);
				return true;
			}		
		}	
		return false;	
	}
    public boolean checkAcc(Accounting a, String select) {
    	for (Accounting every : accountings) {
    		if (a.getStaffID() == every.getStaffID() & select == "a") {
    			return true;
    		}
    		else if (a.getStaffID() == every.getStaffID() & select == "r") {
    			accountings.remove(every);
    			return true;
    		}
            else if (a.getStaffID() == every.getStaffID() & select == "u") {
            	accountings.remove(every);
            	accountings.add(a);
    			return true;
    		}	
	}	
	return false;	
   }
    
    public void init() {
    	frmStaffOperatons = new JFrame();
		frmStaffOperatons.setTitle("STAFF OPERATIONS");
		frmStaffOperatons.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));

		txtName = new JTextField();
		txtName.setBounds(135, 33, 182, 20);
		txtName.setColumns(10);
		frmStaffOperatons.getContentPane().add(txtName);

		JLabel lblname = new JLabel("Name:");
		lblname.setBounds(56, 36, 46, 14);
		frmStaffOperatons.getContentPane().add(lblname);

		txtSurname = new JTextField();
		txtSurname.setBounds(135, 64, 182, 20);
		txtSurname.setColumns(10);
		frmStaffOperatons.getContentPane().add(txtSurname);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(56, 67, 69, 14);
		frmStaffOperatons.getContentPane().add(lblSurname);

		txtSocialID = new JTextField();
		txtSocialID.setBounds(135, 95, 182, 20);
		txtSocialID.setColumns(10);
		frmStaffOperatons.getContentPane().add(txtSocialID);

		JLabel lblLastName = new JLabel("SocialID:");
		lblLastName.setBounds(56, 98, 69, 14);
		frmStaffOperatons.getContentPane().add(lblLastName);

		JLabel lblAddress = new JLabel("StaffID:");
		lblAddress.setBounds(56, 129, 69, 14);
		frmStaffOperatons.getContentPane().add(lblAddress);
		  
		panel = new JPanel();

        read("Admin");
        read("Reception");
        read("Accounting");
		String col[] = {"Name","Surname","SocialID","StaffID","Phone","Address","Insurance","Salary","Age","Gender","BirthDate","EducationStatus","StartDate","Working","Department"};

		model = new DefaultTableModel(col, 0);
		for (Administrator every : admins) {
			model.addRow(every.getAll());
		}
		for (Reception every : receptions) {
			model.addRow(every.getAll());
		}
		for (Accounting every : accountings) {
			model.addRow(every.getAll());
		}
		header = jTable1.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);
		
		JLabel lblSearchStaff = new JLabel("Search Staff");
		lblSearchStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchStaff.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearchStaff.setIcon(new ImageIcon("img\\administrator.png"));
		lblSearchStaff.setBounds(542, 63, 239, 30);
		panel.add(lblSearchStaff);

		buttonDisplay = new JButton(" Display All Staffs");
		buttonDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel();
			}
		});
		buttonDisplay.setIcon(new ImageIcon("img\\technology.png"));
		buttonDisplay.setHorizontalAlignment(SwingConstants.LEFT);
		buttonDisplay.setBackground(Color.WHITE);
		buttonDisplay.setBounds(582, 276, 165, 31);
		panel.add(buttonDisplay);
		
		buttonSearch = new JButton(" Search");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSearchPanel();
			}
		});
		buttonSearch.setIcon(new ImageIcon("img\\search.png"));
		buttonSearch.setHorizontalAlignment(SwingConstants.LEFT);
		buttonSearch.setBackground(Color.WHITE);
		buttonSearch.setBounds(582, 234, 165, 31);
		panel.add(buttonSearch);
		
		cmbxStaff = new JComboBox();
		cmbxStaff.setModel(new DefaultComboBoxModel(new String[] {"name", "surname", "socialID", "staffID", "working", "department", "insurance"}));
		cmbxStaff.setBounds(636, 125, 132, 22);
		panel.add(cmbxStaff);
		
		textSearchStaff = new JTextField();
		textSearchStaff.setColumns(10);
		textSearchStaff.setBounds(636, 174, 132, 20);
		panel.add(textSearchStaff);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(553, 174, 96, 20);
		panel.add(lblSearch);
		
		JLabel lblNewLabel_2 = new JLabel("Search with:");
		lblNewLabel_2.setBounds(553, 126, 96, 20);
		panel.add(lblNewLabel_2);

		pane = new JScrollPane(jTable1);
		pane.setBounds(10, 417, 1128, 359);

		panel.add(pane);
		frmStaffOperatons.getContentPane().add(panel);
				
						textphone1 = new JTextField();
						textphone1.setText("+xx");
						panel.add(textphone1);
						textphone1.setColumns(10);
						textphone1.setBounds(134, 161, 33, 20);
						
								cmbxInsurance = new JComboBox();
								panel.add(cmbxInsurance);
								cmbxInsurance.setModel(
								new DefaultComboBoxModel(new String[] {"true", "false"}));
								cmbxInsurance.setBounds(134, 285, 96, 22);
								
								lblPhone = new JLabel("Phone:");
								lblPhone.setBounds(55, 164, 69, 14);
								panel.add(lblPhone);
								
								textPhone2 = new JTextField();
								textPhone2.setText("xxx");
								textPhone2.setColumns(10);
								textPhone2.setBounds(177, 161, 53, 20);
								panel.add(textPhone2);
								
								textPhone3 = new JTextField();
								textPhone3.setText("xxxxxxx");
								textPhone3.setColumns(10);
								textPhone3.setBounds(240, 161, 76, 20);
								panel.add(textPhone3);
								
								textStaffID = new JTextField();
								textStaffID.setColumns(10);
								textStaffID.setBounds(134, 126, 182, 20);
								panel.add(textStaffID);
								
								JLabel lblAddress_1 = new JLabel("Address:");
								lblAddress_1.setBounds(55, 195, 69, 14);
								panel.add(lblAddress_1);
								
								txtStreet = new JTextField();
								txtStreet.setText("Street");
								txtStreet.setColumns(10);
								txtStreet.setBounds(134, 192, 96, 20);
								panel.add(txtStreet);
								
								txtTown = new JTextField();
								txtTown.setText("Town");
								txtTown.setColumns(10);
								txtTown.setBounds(134, 223, 96, 20);
								panel.add(txtTown);
								
								txtCity = new JTextField();
								txtCity.setText("City");
								txtCity.setColumns(10);
								txtCity.setBounds(134, 254, 96, 20);
								panel.add(txtCity);
								
								JLabel lblInsurance = new JLabel("Insurance:");
								lblInsurance.setBounds(55, 289, 69, 14);
								panel.add(lblInsurance);
								
								JLabel lblSalary = new JLabel("Salary:");
								lblSalary.setBounds(55, 318, 69, 14);
								panel.add(lblSalary);
								
								textSalary = new JTextField();
								textSalary.setColumns(10);
								textSalary.setBounds(134, 318, 96, 20);
								panel.add(textSalary);
								
								JLabel lblAge = new JLabel("Age:");
								lblAge.setBounds(55, 353, 69, 14);
								panel.add(lblAge);
								
								textAge = new JTextField();
								textAge.setColumns(10);
								textAge.setBounds(134, 349, 96, 20);
								panel.add(textAge);
								
								JLabel lblGender = new JLabel("Gender:");
								lblGender.setBounds(55, 384, 69, 14);
								panel.add(lblGender);
								
								cmbxGender = new JComboBox();
								cmbxGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
								cmbxGender.setBounds(134, 380, 96, 22);
								panel.add(cmbxGender);
								
								JLabel lblBirthdate = new JLabel("BirthDate:");
								lblBirthdate.setBounds(265, 257, 69, 14);
								panel.add(lblBirthdate);
								
								textBDay = new JTextField();
								textBDay.setText("xx.xx.xxxx");
								textBDay.setColumns(10);
								textBDay.setBounds(355, 254, 96, 20);
								panel.add(textBDay);
								
								JLabel lblEducationstatus = new JLabel("EducationStatus:");
								lblEducationstatus.setBounds(265, 289, 82, 14);
								panel.add(lblEducationstatus);
								
								textEdu = new JTextField();
								textEdu.setColumns(10);
								textEdu.setBounds(355, 286, 96, 20);
								panel.add(textEdu);
								
								JLabel lblStartdate = new JLabel("startDate:");
								lblStartdate.setBounds(265, 318, 69, 14);
								panel.add(lblStartdate);
								
								textSDay = new JTextField();
								textSDay.setText("xx.xx.xxxx");
								textSDay.setColumns(10);
								textSDay.setBounds(355, 315, 96, 20);
								panel.add(textSDay);
								
								JLabel lblWorking = new JLabel("Working:");
								lblWorking.setBounds(265, 353, 69, 14);
								panel.add(lblWorking);
								
								cmbxWorking = new JComboBox();
								cmbxWorking.setModel(new DefaultComboBoxModel(new String[] {"true", "false"}));
								cmbxWorking.setBounds(355, 350, 96, 22);
								panel.add(cmbxWorking);
								
								JLabel lblDepartment = new JLabel("Department:");
								lblDepartment.setBounds(265, 384, 82, 14);
								panel.add(lblDepartment);
								
								textDepartment = new JTextField();
								textDepartment.setColumns(10);
								textDepartment.setBounds(355, 381, 96, 20);
								panel.add(textDepartment);
    	
    }
    
    public void save() throws IOException {
    	
    	try (FileWriter writer = new FileWriter("texts\\Admin.txt")) {
			writer.write("name,surname,socialID,staffID,phoneNumber,address,insurance,salary,age,gender,bDay,educationstatus,startday,working,department\n");
			for (Administrator every : admins) 
				writer.write(every.toString()+"\n");		
    	}
    	try (FileWriter writer = new FileWriter("texts\\Reception.txt")) {
			writer.write("name,surname,socialID,staffID,phoneNumber,address,insurance,salary,age,gender,bDay,educationstatus,startday,working,department\n");
			for (Reception every : receptions) 
				writer.write(every.toString()+"\n");
    	}
			try (FileWriter writer = new FileWriter("texts\\Accounting.txt")) {
				writer.write("name,surname,socialID,staffID,phoneNumber,address,insurance,salary,age,gender,bDay,educationstatus,startday,working,department\n");
				for (Accounting every : accountings) 
					writer.write(every.toString()+"\n");		      
	    }
	  JOptionPane.showMessageDialog(frmStaffOperatons, "All changes has been saved successfully!");    	
    	
    }



	public JFrame getSOP() {
		return frmStaffOperatons;
	}

	public void setFrmStaffOperatons(JFrame frmStaffOperatons) {
		this.frmStaffOperatons = frmStaffOperatons;
	}
}
