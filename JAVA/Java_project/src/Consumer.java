import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.JTextComponent;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Consumer {
	//initialize the variables
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JTextArea contents;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consumer window = new Consumer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Consumer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); //set frame
		frame.getContentPane().setBackground(new Color(208, 208, 255)); //color of the frame
		frame.setBounds(100, 100, 650, 550); //size of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Consumer"); //title name
		frame.getContentPane().setLayout(null);
		frame.setVisible(true); //set visible, for the change the page
		
		JPanel result = new JPanel(); //set panel
		result.setBackground(new Color(183, 183, 255)); //color of the panel
		result.setBounds(12, 359, 612, 144); //size of the panel
		frame.getContentPane().add(result);
		result.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //scroll 
		scrollPane.setBounds(12, 54, 400, 81);
		result.add(scrollPane);
		
		textArea = new JTextArea(""); //set textarea
		scrollPane.setViewportView(textArea);
		textArea.setColumns(10);
		
		JButton btnDelete = new JButton("DELETE"); //button for delete
		btnDelete.setBackground(new Color(208, 208, 255)); //button color
		btnDelete.setBounds(460, 10, 121, 33); //button size
		btnDelete.addActionListener(new StutterListner()); //actionlistner
		result.add(btnDelete);
		
		JButton btnNext = new JButton("Next"); //button for move to the next page
		btnNext.setBackground(new Color(208, 208, 255)); //button color
		btnNext.setBounds(460, 102, 121, 33); //button size
		btnNext.addActionListener(new StutterListner()); //actionlistner
		result.add(btnNext);
		
		JLabel lblNewLabel_1 = new JLabel("object  name  price  calorie  amount"); //information for the list
		lblNewLabel_1.setBounds(12, 29, 400, 15); //size
		result.add(lblNewLabel_1);
		
		JPanel menu = new JPanel(); //set panel
		menu.setBackground(new Color(185, 185, 255)); //panel color
		menu.setBounds(12, 84, 612, 269); //panel sie
		frame.getContentPane().add(menu);
		menu.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //scroll
		scrollPane_1.setBounds(12, 95, 445, 164); //scroll size
		menu.add(scrollPane_1);
		
		contents = new JTextArea(""); //textarea for the ice cream list
		scrollPane_1.setViewportView(contents);
		contents.setColumns(10);
		
		textField = new JTextField(); //textfield for the number of ice cream
		textField.setBounds(469, 25, 131, 21); //textfield size
		menu.add(textField); 
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("ADD"); //button to add ice cream to the list
		//button color
		btnAdd.setBackground(new Color(128, 128, 255));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBounds(469, 97, 131, 23); //button size
		btnAdd.addActionListener(new StutterListner()); //actionlistner
		menu.add(btnAdd);
		
		textField_1 = new JTextField(); //textfield for the amount of ice cream
		textField_1.setColumns(10);
		textField_1.setBounds(469, 66, 131, 21); //textfield size
		menu.add(textField_1);
				
		JPanel title = new JPanel(); //set panel
		title.setBackground(new Color(128, 128, 255)); //panel color
		title.setBounds(12, 10, 612, 64); //panel size
		frame.getContentPane().add(title);
		title.setLayout(null);
		
		JLabel titlename = new JLabel("Welcome to our Ice Cream Store!!"); //message in the top of the GUI
		titlename.setFont(new Font("굴림", Font.BOLD, 20)); //font
		//color
		titlename.setForeground(new Color(255, 255, 255));
		titlename.setBackground(new Color(128, 128, 255));
		titlename.setBounds(137, 10, 341, 49); //size
		title.add(titlename);
		
		JButton btn1 = new JButton("1"); //number button 1
		btn1.setFont(new Font("굴림", Font.PLAIN, 10));
		btn1.setBackground(new Color(223, 223, 255));
		btn1.setBounds(465, 130, 40, 25);
		btn1.addActionListener(new StutterListner());
		menu.add(btn1);
		
		JButton btn2 = new JButton("2"); //number button 2
		btn2.setFont(new Font("굴림", Font.PLAIN, 10));
		btn2.setBackground(new Color(223, 223, 255));
		btn2.setBounds(510, 130, 40, 25);
		btn2.addActionListener(new StutterListner());
		menu.add(btn2);
		
		JButton btn3 = new JButton("3"); //number button 3
		btn3.setFont(new Font("굴림", Font.PLAIN, 10));
		btn3.setBackground(new Color(223, 223, 255));
		btn3.setBounds(555, 130, 40, 25);
		btn3.addActionListener(new StutterListner());
		menu.add(btn3);
		
		JButton btn4 = new JButton("4"); //number button 4
		btn4.setFont(new Font("굴림", Font.PLAIN, 10));
		btn4.setBackground(new Color(223, 223, 255));
		btn4.setBounds(465, 165, 40, 25);
		btn4.addActionListener(new StutterListner());
		menu.add(btn4);
		
		JButton btn5 = new JButton("5"); //number button 5
		btn5.setFont(new Font("굴림", Font.PLAIN, 10));
		btn5.setBackground(new Color(223, 223, 255));
		btn5.setBounds(510, 165, 40, 25);
		btn5.addActionListener(new StutterListner());
		menu.add(btn5);
		
		JButton btn6 = new JButton("6"); //number button 6
		btn6.setFont(new Font("굴림", Font.PLAIN, 10));
		btn6.setBackground(new Color(223, 223, 255));
		btn6.setBounds(555, 165, 40, 25);
		btn6.addActionListener(new StutterListner());
		menu.add(btn6);
		
		JButton btn7 = new JButton("7"); //number button 7
		btn7.setFont(new Font("굴림", Font.PLAIN, 10));
		btn7.setBackground(new Color(223, 223, 255));
		btn7.setBounds(465, 199, 40, 25);
		btn7.addActionListener(new StutterListner());
		menu.add(btn7);
		
		JButton btn8 = new JButton("8"); //number button 8
		btn8.setFont(new Font("굴림", Font.PLAIN, 10));
		btn8.setBackground(new Color(223, 223, 255));
		btn8.setBounds(510, 200, 40, 25);
		btn8.addActionListener(new StutterListner());
		menu.add(btn8);
		
		JButton btn9 = new JButton("9"); //number button 9
		btn9.setFont(new Font("굴림", Font.PLAIN, 10));
		btn9.setBackground(new Color(223, 223, 255));
		btn9.setBounds(555, 200, 40, 25);
		btn9.addActionListener(new StutterListner());
		menu.add(btn9);
		
		JButton btn0 = new JButton("0"); //number button 0
		btn0.setFont(new Font("굴림", Font.PLAIN, 10));
		btn0.setBackground(new Color(223, 223, 255));
		btn0.setBounds(510, 234, 40, 25);
		btn0.addActionListener(new StutterListner());
		menu.add(btn0);
		
		JButton btnX = new JButton("X"); //X button for clear the text field
		btnX.setBackground(new Color(128, 128, 255));
		btnX.setForeground(new Color(255, 0, 0));
		btnX.setFont(new Font("굴림", Font.BOLD, 6));
		btnX.setBounds(555, 234, 40, 25);
		btnX.addActionListener(new StutterListner());
		menu.add(btnX);
		
		JButton btnCone = new JButton("Cone"); //cone button for get the cone ice cream list
		btnCone.setFont(new Font("굴림", Font.BOLD, 15));
		btnCone.setForeground(new Color(255, 255, 255));
		btnCone.setBackground(new Color(128, 128, 255));
		btnCone.setBounds(12, 25, 120, 45);
		btnCone.addActionListener(new StutterListner());
		menu.add(btnCone);
		
		JButton btnFreezie = new JButton("Freezie"); //freezie button for get the freezie ice cream list
		btnFreezie.setFont(new Font("굴림", Font.BOLD, 15));
		btnFreezie.setForeground(new Color(255, 255, 255));
		btnFreezie.setBackground(new Color(128, 128, 255));
		btnFreezie.setBounds(180, 25, 120, 45);
		btnFreezie.addActionListener(new StutterListner());
		menu.add(btnFreezie);
		
		JButton btnIceCreamBar = new JButton("IceCreamBar"); //ice cream bar button for get the ice cream bar lsit
		btnIceCreamBar.setFont(new Font("굴림", Font.BOLD, 11));
		btnIceCreamBar.setForeground(new Color(255, 255, 255));
		btnIceCreamBar.setBackground(new Color(128, 128, 255));
		btnIceCreamBar.setBounds(337, 25, 120, 45);
		btnIceCreamBar.addActionListener(new StutterListner());
		menu.add(btnIceCreamBar);
		
		JLabel lblNewLabel = new JLabel("Amount"); //label for amount text field
		lblNewLabel.setBounds(469, 50, 81, 15);
		menu.add(lblNewLabel);
		
		JLabel lblNumber = new JLabel("Number"); //label for number text field
		lblNumber.setBounds(469, 10, 81, 15);
		menu.add(lblNumber);
		
		JButton btnO = new JButton("O"); //o button for change the textfield
		btnO.setBackground(new Color(128, 128, 255));
		btnO.setForeground(Color.RED);
		btnO.setFont(new Font("굴림", Font.BOLD, 6));
		btnO.setBounds(465, 234, 40, 25);
		btnO.addActionListener(new StutterListner());
		menu.add(btnO);
	

	}
	//initialize int variable
	int o = 1;
	int num;
	int amount;
	int money = 0;
	
	//initialize String variable
	String result = "";
	String tempResult = "";
	
	//initialize the Array List
	ArrayList calorieList = new ArrayList(30);
	ArrayList resultList = new ArrayList(30);
	ArrayList calculateList = new ArrayList(30);
	ArrayList tempList = new ArrayList(30);
	
	
	//set the Cone ice cream information
	ArrayList Coneobject = new ArrayList(Arrays.asList("Cone","Cone","Cone","Cone","Cone","Cone","Cone","Cone","Cone","Cone"));
	ArrayList Conename = new ArrayList(Arrays.asList("Cone_mint","Cone_strawberry","Cone_chocolate","Cone_yogurt","Cone_vanilla","Cone_melon","Cone_cherry","Cone_peach","Cone_mango","Cone_coconut"));
	ArrayList Coneprice = new ArrayList(Arrays.asList(1000,2000,3000,1000,2000,3000,1000,2000,3000,1500));
	ArrayList Conecalorie = new ArrayList(Arrays.asList(100,200,300,100,200,300,100,200,300,100));
	
	//set the Freezie ice cream information
	ArrayList Freezieobject = new ArrayList(Arrays.asList("Freezie","Freezie","Freezie","Freezie","Freezie","Freezie","Freezie","Freezie","Freezie","Freezie"));
	ArrayList Freeziename = new ArrayList(Arrays.asList("Freezie_mint","Freezie_strawberry","Freezie_chocolate","Freezie_yogurt","Freezie_vanilla","Freezie_melon","Freezie_cherry","Freezie_peach","Freezie_mango","Freezie_coconut"));
	ArrayList Freezieprice = new ArrayList(Arrays.asList(1000,2000,3000,1000,2000,3000,1000,2000,3000,1500));
	ArrayList Freeziecalorie = new ArrayList(Arrays.asList(100,200,300,100,200,300,100,200,300,100));
	
	//set the ice cream bar information
	ArrayList IceCreamBarobject = new ArrayList(Arrays.asList("IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar","IceCreamBar"));
	ArrayList IceCreamBarname = new ArrayList(Arrays.asList("IceCreamBar_mint","IceCreamBar_strawberry","IceCreamBar_chocolate","IceCreamBar_yogurt","IceCreamBar_vanilla","IceCreamBar_melon","IceCreamBar_cherry","IceCreamBar_peach","IceCreamBar_mango","IceCreamBar_coconut"));
	ArrayList IceCreamBarprice = new ArrayList(Arrays.asList(1000,2000,3000,1000,2000,3000,1000,2000,3000,1500));
	ArrayList IceCreamBarcalorie = new ArrayList(Arrays.asList(100,200,300,100,200,300,100,200,300,100));

	private class StutterListner implements ActionListener { //inner class for button event handling		
		public void actionPerformed(ActionEvent event) {
			String operation = event.getActionCommand();
			
			if (operation.equals("1")) { //number 1 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 1 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 1 to the Amount
				}
			} else if (operation.equals("2")) { //number 2 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 2 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 2 to the Amount
				}
			} else if (operation.equals("3")) { //number 3 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 3 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 3 to the Amount
				}
			} else if (operation.equals("4")) { //number 4 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 4 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 4 to the Amount
				}
			} else if (operation.equals("5")) { //number 5 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 5 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 5 to the Amount
				}
			} else if (operation.equals("6")) { //number 6 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 6 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 6 to the Amount
				}
			} else if (operation.equals("7")) { //number 7 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 7 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 7 to the Amount
				}
			} else if (operation.equals("8")) { //number 8 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 8 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 8 to the Amount
				}
			} else if (operation.equals("9")) { //number 9 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 9 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 9 to the Amount
				}
			} else if (operation.equals("0")) { //number 0 button
				if (o==1) {
					String text = textField.getText();
					textField.setText(text+event.getActionCommand()); //input 0 to the Number
				} else if (o==-1) {
					String text = textField_1.getText();
					textField_1.setText(text+event.getActionCommand()); //input 0 to the Amount
				}
			} else if (operation.equals("X")) { //x button for clear the textField
				if (o==1) {
					textField.setText(""); //clear the number textfield
				} else if (o==-1) {
					textField_1.setText(""); //clear the amount textfield
				}

			} else if (operation.equals("Cone")) { //print the Cone ice cream list
				String contents_text = "";
				for (int i=0; i<Coneobject.size(); i++) {
					contents_text += i+1+". Cone name: "+Conename.get(i)+"\nCone price: "+Coneprice.get(i)+"\nCone calorie: "+Conecalorie.get(i)+"\n"+"===============\n";
					contents.setText(contents_text);
				}
			} else if (operation.equals("Freezie")) { //print the Freezie ice cream list
				String contents_text = "";
				for (int i=0; i<Freezieobject.size(); i++) {
					contents_text += i+11+". Freezie name: "+Freeziename.get(i)+"\nFreezie price: "+Freezieprice.get(i)+"\nFreezie calorie: "+Freeziecalorie.get(i)+"\n"+"===============\n";
					contents.setText(contents_text);
				}
			} else if (operation.equals("IceCreamBar")) { //print the ice cream bar list
				String contents_text = "";
				for (int i=0; i<IceCreamBarobject.size(); i++) {
					contents_text += i+21+". Ice Cream Bar name: "+IceCreamBarname.get(i)+"\nIce Cream Bar price: "+IceCreamBarprice.get(i)+"\nIce Cream Bar calorie: "+IceCreamBarcalorie.get(i)+"\n"+"===============\n";
					contents.setText(contents_text);
				}
			} else if (operation.equals("ADD")) { //add the ice cream to the list
				String text = textField.getText(); //get text from the text field

				String textAmount = textField_1.getText(); //get text from the text field
				
				//initialize the textField
				textField.setText("");
				textField_1.setText("");

				try {
					if (text.equals("")) { //if number text field is empty
						throw new Case00();
					} else if (textAmount.equals("")) { //if amount text field is empty
						throw new Case01();
					}
				} catch(Case00 c00) { //warning message
					JOptionPane.showMessageDialog(null,"Please Enter the Ice Cream Number","Warning", 
							JOptionPane.INFORMATION_MESSAGE); 
					return;
				} catch(Case01 c01) { //warning message
					JOptionPane.showMessageDialog(null,"Please Enter the Ice Cream Amount","Warning", 
							JOptionPane.INFORMATION_MESSAGE); 
					return;
				};
				
				num = Integer.parseInt(text.replaceAll("[^0-9]","")); //make the input string to integer
				amount = Integer.parseInt(textAmount.replaceAll("[^0-9]",""));	//make the input string to integer
				
				try { //check the number is valid
					if (num == 0) { //if the number is 0 
						throw new Case1();
					} else if (num > 30) { //if the number is over 30 
						throw new Case1();
					}
				} catch(Case1 c1) { //warning message
					JOptionPane.showMessageDialog(null,"Enter valid input! Check the Ice Cream Number","Warning", 
							JOptionPane.INFORMATION_MESSAGE); 
					return;
				};
				
				if (((num >= 1)&&(num <= 10))) { //ice cream is cone
					//put the Cone information to the tempResult
					tempResult += Coneobject.get(num-1)+" ";
					tempResult += Conename.get(num-1)+" ";
					tempResult += Coneprice.get(num-1)+" ";
					tempResult += Conecalorie.get(num-1)+" ";
					tempResult += amount+"\n";
					
					//make new cone with this information
					Cone c = new Cone("",String.valueOf(Conename.get(num-1)),Double.parseDouble(String.valueOf(Coneprice.get(num-1))),
							Double.parseDouble(String.valueOf(Conecalorie.get(num-1))),amount);
					tempList.add(c); //add new cone to the tempList
					
					//put the Cone information to the calculateList
					calculateList.add("Cone");
					calculateList.add(String.valueOf(Conename.get(num-1)));
					calculateList.add(Double.parseDouble(String.valueOf(Coneprice.get(num-1))));
					calculateList.add(Double.parseDouble(String.valueOf(Conecalorie.get(num-1))));
					calculateList.add(amount);
					resultList.add(tempResult); //to show the list in the GUI
				} else if (((num >= 11)&&(num <= 20))) { //ice cream is Freezie
					//put the Freezie information to the tempResult
					tempResult += Freezieobject.get(num-11)+" ";
					tempResult += Freeziename.get(num-11)+" ";
					tempResult += Freezieprice.get(num-11)+" ";
					tempResult += Freeziecalorie.get(num-11)+" ";
					tempResult += amount+"\n";
					
					//make new freezie with this information
					Freezie f = new Freezie("",String.valueOf(Freeziename.get(num-11)),Double.parseDouble(String.valueOf(Freezieprice.get(num-11))),
							Double.parseDouble(String.valueOf(Freeziecalorie.get(num-11))),amount);
					tempList.add(f);
					
					//put the freezie information to the calculateList
					calculateList.add("Freezie");
					calculateList.add(String.valueOf(Freeziename.get(num-11)));
					calculateList.add(Double.parseDouble(String.valueOf(Freezieprice.get(num-11))));
					calculateList.add(Double.parseDouble(String.valueOf(Freeziecalorie.get(num-11))));
					calculateList.add(amount);
					resultList.add(tempResult); //to show the list in the GUI
					
				} else if (((num >= 21)&&(num <= 30))) { //ice cream is Ice cream bar
					//put the Ice cream bar information to the tempResult
					tempResult += IceCreamBarobject.get(num-21)+" ";
					tempResult += IceCreamBarname.get(num-21)+" ";
					tempResult += IceCreamBarprice.get(num-21)+" ";
					tempResult += IceCreamBarcalorie.get(num-21)+" ";
					tempResult += amount+"\n";
					
					//make new ice cream bar with this information
					IceCreamBar i = new IceCreamBar("",String.valueOf(IceCreamBarname.get(num-21)),Double.parseDouble(String.valueOf(IceCreamBarprice.get(num-21))),
							Double.parseDouble(String.valueOf(IceCreamBarcalorie.get(num-21))),amount);
					tempList.add(i);
					
					//put the ice cream bar information to the calculateList
					calculateList.add("IceCreamBar");
					calculateList.add(String.valueOf(IceCreamBarname.get(num-21)));
					calculateList.add(Double.parseDouble(String.valueOf(IceCreamBarprice.get(num-21))));
					calculateList.add(Double.parseDouble(String.valueOf(IceCreamBarcalorie.get(num-21))));
					calculateList.add(amount);
					resultList.add(tempResult); //to show the list in the GUI
				}
				for (int i=0; i<resultList.size();i++) {
					result = (String) resultList.get(i);
					textArea.setText(result);	//show the list in the textArea			
				}

				
				//resultList
				
			} else if (operation.equals("DELETE")) { 
				//delete the last list ice cream information
				int lastResultList = resultList.size()-1;
				resultList.remove(lastResultList);
				calculateList.remove((lastResultList+1)*5-1);
				calculateList.remove((lastResultList+1)*5-2);
				calculateList.remove((lastResultList+1)*5-3);
				calculateList.remove((lastResultList+1)*5-4);
				calculateList.remove((lastResultList+1)*5-5);
				tempList.remove(lastResultList);
				textArea.setText(""); //initialize the textArea
				
				for (int i=0; i<resultList.size();i++) {
					result = (String) resultList.get(i);
					textArea.setText(result); //show the list in the textArea
				}
			} else if (operation.equals("O")) { //button for change the input textfield 
				o *= -1; // it will repeat 1 and -1 when click the button
			} else if (operation.equals("Next")) { //go to the receipt page
				new Receipt(); //new page
				frame.setVisible(false); //set this page invisible
				
				//calculate the total money
				int totalmoney = 0;
				for (int i=0; i<tempList.size(); i++) {
					Icecream ice = (Icecream) tempList.get(i);
					ice.calculatePayment();
					double getCalorie = ice.getCalorie(); //get calorie of the ice cream
					calorieList.add(getCalorie); //add ice ream calorie to the List
					money = (int)ice.getTotalPrice();
					totalmoney += money;
				};
				
				calorieList.add(totalmoney); //add total money to the calorieList

				//write the text file
				FileOutputStream fileObjectData = null;
				FileOutputStream fileObjectResult = null;

				try {
					fileObjectData = new FileOutputStream("data.txt");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try{
					fileObjectResult = new FileOutputStream("result.txt");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				PrintWriter x = new PrintWriter(fileObjectData);
				x.println(calculateList); //print out the calculate list
				x.close();
				
				PrintWriter y = new PrintWriter(fileObjectResult);
				y.println(calorieList); //print out the calorie and total price
				y.close();
			}
		}

	}

}
