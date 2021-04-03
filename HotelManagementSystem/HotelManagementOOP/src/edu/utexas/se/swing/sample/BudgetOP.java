package edu.utexas.se.swing.sample;
import com.source.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BudgetOP extends JFrame {
	
	private JComboBox cmbxMonth;
	private JTextPane textTotal;
	private JButton btnTotal;
	private JFrame BOP;
	private JFrame BOP_1;
	
	private static Accounting acc;
	private static Administrator ad;
	
	private JButton btnExpense;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnAdd;
	private JTextPane textExpense;
	
	private JTextPane txtIncome; 
	private JButton btnBack;
	private JScrollPane pane;
	private JScrollPane pane2;
	private JTable jTable1;
	private JTable jTable2;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JButton btnIncome;
	
	private JPanel contentPane;
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Staff> staffs = new ArrayList<Staff>();
	private ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	private JTextField textInNO;
	private JTextField textDate;
	private JTextField textCost;
	private JTextField textAbout;
	private JTextField textBy;
	private JLabel lblNewLabel_5;
	private JButton btnSaveAllChanges;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetOP frame = new BudgetOP(ad,acc);
					frame.BOP_1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BudgetOP(Administrator AD, Accounting ACC) {
		this.ad = AD;
		this.acc = ACC;
		
		btnBack = new JButton("Back");
		btnIncome = new JButton(" Calculate");
		btnTotal = new JButton(" Calculate");
		btnExpense = new JButton(" Calculate");
		
		read("Accounting");
		read("Admin");
		read("Reception");
		
		BOP = new JFrame();
		jTable1 = new JTable();
		jTable2 = new JTable();
		
		init();
		
		btnExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateExpense();
			}
		});
		
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateTotal();
			}
		});
		
		
		
		btnIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateIncome();
			}
		});
		
		
		
		jTable1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("RECEIPT OPERATION");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setIcon(new ImageIcon("img\\business.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(509, 11, 435, 31);
		BOP_1.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Invoice NO:");
		lblNewLabel_3.setBounds(567, 53, 87, 14);
		BOP_1.getContentPane().add(lblNewLabel_3);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(567, 81, 87, 14);
		BOP_1.getContentPane().add(lblDate);
		
		JLabel lblPrice = new JLabel("Cost:");
		lblPrice.setBounds(567, 111, 87, 14);
		BOP_1.getContentPane().add(lblPrice);
		
		JLabel lblAbout = new JLabel("About:");
		lblAbout.setBounds(567, 136, 87, 14);
		BOP_1.getContentPane().add(lblAbout);
		
		JLabel lblBy = new JLabel("By:");
		lblBy.setBounds(567, 162, 87, 14);
		BOP_1.getContentPane().add(lblBy);
		
		textInNO = new JTextField();
		textInNO.setBounds(664, 50, 134, 20);
		BOP_1.getContentPane().add(textInNO);
		textInNO.setColumns(10);
		
		textDate = new JTextField();
		textDate.setText("XX.XX.XXXX");
		textDate.setColumns(10);
		textDate.setBounds(664, 78, 134, 20);
		BOP_1.getContentPane().add(textDate);
		
		textCost = new JTextField();
		textCost.setColumns(10);
		textCost.setBounds(664, 108, 134, 20);
		BOP_1.getContentPane().add(textCost);
		
		textAbout = new JTextField();
		textAbout.setColumns(10);
		textAbout.setBounds(664, 133, 134, 20);
		BOP_1.getContentPane().add(textAbout);
		
		textBy = new JTextField();
		textBy.setColumns(10);
		textBy.setBounds(664, 159, 134, 20);
		BOP_1.getContentPane().add(textBy);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addReceipt();
			}
		});
		btnAdd.setBounds(567, 200, 89, 23);
		BOP_1.getContentPane().add(btnAdd);
		
		btnDelete = new JButton("Delete Selected");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteReceipt();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDelete.setBounds(664, 200, 119, 23);
		BOP_1.getContentPane().add(btnDelete);
		
		btnUpdate = new JButton("Update Selected");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateReceipt();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnUpdate.setBounds(793, 200, 119, 23);
		BOP_1.getContentPane().add(btnUpdate);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel_4.setBounds(547, 11, 379, 228);
		BOP_1.getContentPane().add(lblNewLabel_4);
		
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
		btnSaveAllChanges.setBounds(116, 11, 186, 31);
		BOP_1.getContentPane().add(btnSaveAllChanges);
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable1.rowAtPoint(evt.getPoint());


			}
		});
		
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable2.rowAtPoint(evt.getPoint());
				
				textInNO.setText(model2.getValueAt(update_row, 0).toString());
				textDate.setText(model2.getValueAt(update_row, 1).toString());
				textCost.setText(model2.getValueAt(update_row, 2).toString());
				textAbout.setText(model2.getValueAt(update_row, 3).toString());
				textBy.setText(model2.getValueAt(update_row, 4).toString());
			}
		});
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if (ad != null) {
						Admin adminOnline = new Admin(ad);
						adminOnline.getAdmin().setVisible(true);
						BOP.setVisible(false);
					}
					else if (acc != null) {
						Accountant reOnline = new Accountant(acc);
						reOnline.getAccFrame().setVisible(true);
						BOP.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(BOP,"OBJECT have a problem, start again in MAIN.java");
					}
					
				}
				catch (NullPointerException a) {
					JOptionPane.showMessageDialog(BOP,"OBJECT have a problem, start again in MAIN.java");
				}
				}
			
		});
		
		
	}
	
	public double calculateIncome(){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String now = LocalDateTime.now().format(dtf);

		String tempNow[] = now.split("/");

		long nowDay = (Integer.valueOf(tempNow[0]) + Integer.valueOf(tempNow[1])*30 + Integer.valueOf(tempNow[2])*365);
		
		long day = 0;
		if (cmbxMonth.getSelectedItem().toString() != "all")
			day = Integer.valueOf(cmbxMonth.getSelectedItem().toString())*30;
		
		double income = 0;
		for (Customer each : customers) {
			if (cmbxMonth.getSelectedItem().toString() == "all") {
				income += each.getTotalPrice();
				continue;
			}
				
			String date = each.getArrivalDay().getString().replace('.', '/');
			String personTemp[] = date.split("/");
			long personDay = (Integer.valueOf(personTemp[0]) + Integer.valueOf(personTemp[1])*30 + Integer.valueOf(personTemp[2])*365);
			
			if (nowDay - personDay <= day) {
				income += each.getTotalPrice();
			}
		}
		txtIncome.setText(String.valueOf(income));
		return income;
	}
	
	public double getReceiptCosts() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String now = LocalDateTime.now().format(dtf);

		String tempNow[] = now.split("/");

		long nowDay = (Integer.valueOf(tempNow[0]) + Integer.valueOf(tempNow[1])*30 + Integer.valueOf(tempNow[2])*365);
	
        double cost = 0;
        long day = 0;
        if (cmbxMonth.getSelectedItem().toString() != "all")
			day = Integer.valueOf(cmbxMonth.getSelectedItem().toString())*30;
        
        for (Receipt each : receipts) {
				
			String date = each.getDate().getString().replace('.', '/');
			String personTemp[] = date.split("/");
			long personDay = (Integer.valueOf(personTemp[0]) + Integer.valueOf(personTemp[1])*30 + Integer.valueOf(personTemp[2])*365);
			
			if (nowDay - personDay <= day) {
				cost += each.getCost();
			}
		}
        return cost;
	}
	
	public void calculateTotal() {
		
		if (cmbxMonth.getSelectedItem().toString() == "all") {
			JOptionPane.showMessageDialog(BOP_1, "Cannot calculate total for all time!");
			return;
		}
		double total = calculateIncome() - calculateExpense();
		textTotal.setText(String.valueOf(total));
	}
	
	public double calculateExpense() {
		

		double expense = 0;
		if (cmbxMonth.getSelectedItem().toString() == "all") {
			JOptionPane.showMessageDialog(BOP_1, "Cannot calculate expense for all time!");
			return -1;
		}
		int month = Integer.valueOf(cmbxMonth.getSelectedItem().toString());
		for (Staff each : staffs) {
			expense += each.getSalary()*month;
		}
		for (Receipt each : receipts) {
			expense += each.getCost();
		}
		expense += getReceiptCosts();
		
		textExpense.setText(String.valueOf(expense));
		return expense;
	}
	
	public void init(){
        getContentPane().setLayout(null);

		BOP_1 = new JFrame();
		BOP_1.getContentPane().setBackground(Color.WHITE);
		BOP_1.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\hotel-managementmini-logo-300x69.png"));
		BOP_1.setTitle("BUDGET OPERATIONS");
		BOP_1.setBounds(100, 100, 963, 827);
		BOP_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BOP_1.getContentPane().setLayout(null);
		
		readCheckOut("CheckOut");
		String col[] = {"Name","Surname","SocialID","Age","DepartureDay","ArrivalDay","TotalPrice","RoomID","Phone","Address","Gender"};
		
		model = new DefaultTableModel(col, 0);
		for (Customer every : customers) {
			model.addRow(every.getAll());
		}
		
		jTable1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable1.setModel(model);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("img\\hotel-managementmini-logo-300x69.png"));
		lblNewLabel_5.setBounds(836, 53, 58, 89);
		BOP_1.getContentPane().add(lblNewLabel_5);
		
		cmbxMonth = new JComboBox();
		cmbxMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "4", "6", "8", "12", "24", "36", "all"}));
		cmbxMonth.setBounds(162, 78, 87, 20);
		BOP_1.getContentPane().add(cmbxMonth);
		
		readReceipt("Receipt");
		String col2[] = {"invoiceNO","date","cost","about","by"};
		
		model2 = new DefaultTableModel(col2, 0);
		for (Receipt every : receipts) {
			model2.addRow(every.getAll());
		}
		
		jTable2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable2.setModel(model2);
		
		JLabel lblLastXMonth = new JLabel(" For Last X Month");
		lblLastXMonth.setIcon(new ImageIcon("img\\holidays.png"));
		lblLastXMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastXMonth.setBounds(23, 73, 186, 31);
		BOP_1.getContentPane().add(lblLastXMonth);
		
		textTotal = new JTextPane();
		textTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTotal.setBounds(162, 321, 221, 31);
		BOP_1.getContentPane().add(textTotal);
		
		btnTotal.setIcon(new ImageIcon("img\\calculator.png"));
		btnTotal.setHorizontalAlignment(SwingConstants.LEFT);
		btnTotal.setBounds(23, 321, 129, 31);
		BOP_1.getContentPane().add(btnTotal);
		
		JLabel lblCalculateTotal = new JLabel(" Calculate Total");
		lblCalculateTotal.setIcon(new ImageIcon("img\\bill.png"));
		lblCalculateTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCalculateTotal.setBounds(23, 279, 186, 31);
		BOP_1.getContentPane().add(lblCalculateTotal);
		
		textExpense = new JTextPane();
		textExpense.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textExpense.setBounds(162, 238, 221, 31);
		BOP_1.getContentPane().add(textExpense);
		
		
		btnExpense.setIcon(new ImageIcon("img\\calculator.png"));
		btnExpense.setHorizontalAlignment(SwingConstants.LEFT);
		btnExpense.setBounds(23, 237, 129, 31);
		BOP_1.getContentPane().add(btnExpense);
		
		JLabel lblCalculateEx = new JLabel(" Calculate Expense");
		lblCalculateEx.setIcon(new ImageIcon("img\\hands-and-gestures.png"));
		lblCalculateEx.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCalculateEx.setBounds(23, 196, 186, 31);
		BOP_1.getContentPane().add(lblCalculateEx);
		
		txtIncome = new JTextPane();
		txtIncome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIncome.setBounds(162, 154, 221, 31);
		BOP_1.getContentPane().add(txtIncome);
		
		btnBack.setIcon(new ImageIcon("img\\arrows.png"));
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setBounds(10, 11, 96, 31);
		BOP_1.getContentPane().add(btnBack);
		
		pane = new JScrollPane(jTable1);
		pane.setToolTipText("");
		pane.setBounds(10, 400, 527, 377);
		BOP_1.getContentPane().add(pane);
		
		
		pane2 = new JScrollPane(jTable2);
		pane2.setToolTipText("");
		pane2.setBounds(547, 250, 379, 527);
		BOP_1.getContentPane().add(pane2);
		
		JLabel lblNewLabel = new JLabel(" Calculate Income");
		lblNewLabel.setIcon(new ImageIcon("img\\calculators.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(23, 111, 186, 31);
		BOP_1.getContentPane().add(lblNewLabel);

		btnIncome.setIcon(new ImageIcon("img\\calculator.png"));
		btnIncome.setHorizontalAlignment(SwingConstants.LEFT);
		btnIncome.setBounds(23, 154, 129, 31);
		BOP_1.getContentPane().add(btnIncome);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img\\unnamed.jpg"));
		lblNewLabel_1.setBounds(10, 53, 527, 320);
		BOP_1.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCheckoutList = new JLabel(" Check-out List");
		lblCheckoutList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckoutList.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCheckoutList.setBounds(10, 369, 527, 31);
		BOP_1.getContentPane().add(lblCheckoutList);	
	}
	
	public void readCheckOut(String textFile) {
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
		        	if (textFile.equals("CheckOut")) {
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
		        	Staff c = new Staff(tempData[0], tempData[1], Integer.valueOf(tempData[2]), 
		    		Integer.valueOf(tempData[3]),phone,address,
		    		Boolean.valueOf(tempData[6]), Float.valueOf(tempData[7]),Integer.valueOf(tempData[8]),
		    		Boolean.valueOf(tempData[9]),bDay,tempData[11],startDay,Boolean.valueOf(tempData[13]),tempData[14]);
		            staffs.add(c);  	
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
	
	public void readReceipt(String textFile) {
		
		try {
		      File myObj = new File("texts\\"+textFile+".txt");
		      Scanner myReader = new Scanner(myObj);
		      myReader.hasNextLine();
		      String[] tempData;
		      while (myReader.hasNextLine()) {
		    	  try {
		    	    tempData = myReader.nextLine().split(",");
		    	    Date date = new Date(tempData[1]);
		    	    Receipt c = new Receipt(Integer.valueOf(tempData[0]), date, Float.valueOf(tempData[2]),
		    	    		tempData[3],tempData[4]);
		    	    
		            receipts.add(c);  	
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
	
	public void addReceipt() {
		
		for (Receipt every : receipts)
	    	if (every.getInvoiceNO() == getReceipt().getInvoiceNO()) {
	    		JOptionPane.showMessageDialog(BOP, "InvoiceNO cannot be same!");
	    		return;
	    	}
		receipts.add(getReceipt());
		updateText();
		clearText();
		JOptionPane.showMessageDialog(BOP, "Receipt has been added successfully!");
	}
	public void deleteReceipt() {
		try {
		Receipt textR = getReceipt();
		for (Receipt r : receipts)
			if (r.getInvoiceNO() == textR.getInvoiceNO()) {
				receipts.remove(r);
				clearText();
				updateText();
				JOptionPane.showMessageDialog(BOP, "Receipt has been deleted successfully!");
				return;
				
			}
		JOptionPane.showMessageDialog(BOP, "InvoiceNO has not been found!");
				
		}
		catch(ConcurrentModificationException a) {
			System.out.println("");
		}
		
	}
	public void updateReceipt() {
		Receipt textR = getReceipt();
		
		for (Receipt every : receipts)
	    	if (every.getInvoiceNO() == textR.getInvoiceNO()) {
	    		receipts.remove(every);
	    		receipts.add(textR);
	    		clearText();
	    		updateText();
	    		JOptionPane.showMessageDialog(BOP, "Receipt has been updated successfully!");
	    		return;
	    	}
		JOptionPane.showMessageDialog(BOP, "Receipt cannot be found! Don't change ReceiptNO");
		
	}
	public Receipt getReceipt() {
		try {
		Date date = new Date(textDate.getText());
	    Receipt c = new Receipt(Integer.valueOf(textInNO.getText()), date, Float.valueOf(textCost.getText()),
	    		textAbout.getText(),textBy.getText());		
	    return c;
		}
		catch(NumberFormatException a) {
			JOptionPane.showMessageDialog(BOP, "All parameter should've been filled completely with correct format!");
			return null;
		}

	}
	
	public void save() throws IOException {
		
		try (FileWriter writer = new FileWriter("texts\\Receipt.txt")) {
			writer.write("invoiceNO,date,cost,about,by\n");
			for (Receipt every : receipts) {
				writer.write(every.toString()+"\n");
			}
			writer.close();	
	      
	    }
		
		JOptionPane.showMessageDialog(BOP, "All changes has been saved!");
		
	}
	
	 public void clearText() {
			
			textDate.setText("");
			textInNO.setText("");
			textCost.setText("");
			textAbout.setText("");
			textBy.setText("");
		}
	 public void updateText() {	
		    String col2[] = {"invoiceNO","date","cost","about","by"};
			
			model2 = new DefaultTableModel(col2, 0);
			for (Receipt every : receipts) {
				model2.addRow(every.getAll());
			}
			
			jTable2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTable2.setModel(model2);
		}

	public JFrame getBOP() {
		return BOP_1;
	}

	public void setBOP(JFrame bOP) {
		BOP_1 = bOP;
	}
}
