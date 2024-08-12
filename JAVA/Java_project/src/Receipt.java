import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Receipt {

	
	//set variables
	private JFrame frame;
	private JLabel lblResult;
	private JTextArea textArea;
	private JButton btnShow;
	private String data = "";
	private String result = "";
	private JButton btnReceipt;
	private JLabel lblCalorie;
	Thread workThread;
	WorkerClass worker;
	private JLabel lblPrice;
	private JLabel lblTitle;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receipt window = new Receipt();
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
	public Receipt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); //set new frame
		frame.getContentPane().setBackground(new Color(183, 183, 255)); //frame color
		frame.setBounds(100, 100, 650, 550); ///frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Receipt"); //frame name
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //scroll enable
		scrollPane.setBounds(12, 115, 564, 295); //scroll size
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea(); //set textarea
		scrollPane.setViewportView(textArea);
		textArea.setColumns(10);
		
		JButton btnGet = new JButton("GET"); //button for get the text file
		//button color
		btnGet.setBackground(new Color(128, 128, 255));
		btnGet.setForeground(new Color(255, 255, 255));
		btnGet.setBounds(350, 65, 100, 40);
		frame.getContentPane().add(btnGet);
				
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//text file reader1
				FileReader fr1 = null;
				try {
					fr1 = new FileReader("data.txt"); //file reader
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            BufferedReader br1 = new BufferedReader(fr1); //buffered reader
	            
	            String sCurrentLine1;
	            try {
					while((sCurrentLine1 = br1.readLine()) != null) { //until it is null
					   data += sCurrentLine1; //add
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					br1.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            //text file reader2
				FileReader fr2 = null; 
				try {
					fr2 = new FileReader("result.txt"); //file reader
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	            BufferedReader br2 = new BufferedReader(fr2); //buffered reader
	            String sCurrentLine2;
	            try {
					while((sCurrentLine2 = br2.readLine()) != null) { //until it is null
					   result += sCurrentLine2;
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	            try {
					br2.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	            }
		});
		
		btnShow = new JButton("SHOW"); //button for show the receipt
		//button color
		btnShow.setBackground(new Color(128, 128, 255));
		btnShow.setForeground(new Color(255, 255, 255));
		btnShow.setBounds(473, 65, 100, 40);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				worker = new WorkerClass(); //set worker
				worker.execute(); //execute the worker
			}
		});
		frame.getContentPane().add(btnShow);
		
		btnReceipt = new JButton("RECEIPT"); //button for end the program and get the receipt txt.file
		//button color
		btnReceipt.setForeground(Color.WHITE);
		btnReceipt.setBackground(new Color(128, 128, 255));
		btnReceipt.setBounds(506, 454, 118, 49); //button size
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//FileWriter fileObject =new FileWriter("receipt.txt",true);
					FileOutputStream fileObject =new FileOutputStream("receipt.txt");
					PrintWriter x = new PrintWriter(fileObject);
					String[] result = textArea.getText().split("\n");
					ArrayList resultList = new ArrayList(50);
		            for (int i=0; i<result.length; i++) { //get the list item and put them to the list for print
		                Object o = result[i];
		                resultList.add(o);
		            }
					x.println(resultList); //print out the series
					x.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0); //end the program
			}
		});
		frame.getContentPane().add(btnReceipt);
		
		lblCalorie = new JLabel("Total Calorie"); //label for total calorie
		//label color
		lblCalorie.setForeground(new Color(255, 255, 255));
		lblCalorie.setBackground(new Color(255, 255, 255));
		lblCalorie.setBounds(12, 420, 188, 35); //label size
		frame.getContentPane().add(lblCalorie);
		
		lblPrice = new JLabel("Total Price"); //label for total price
		//label color
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBackground(Color.WHITE);
		lblPrice.setBounds(219, 420, 188, 35); //label size
		frame.getContentPane().add(lblPrice);
		
		lblTitle = new JLabel("Check and Create the RECIEPT!!"); //information about the page
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20)); //font
		lblTitle.setForeground(new Color(255, 255, 255)); //color
		lblTitle.setBounds(12, 10, 340, 49); //size
		frame.getContentPane().add(lblTitle);

		
	}
	
	//worker class
	public class WorkerClass extends SwingWorker<Integer, String> {


		public WorkerClass() {

		}

		@Override
		protected Integer doInBackground() throws Exception {

			//initialize the variables
			int totalCalorie = 0;
			int totalPrice = 0;
			
			String splitResult[] = result.split(","); //split the file by ,
			String totalPriceTemp = splitResult[splitResult.length-1]; //get the last part of the array
			String totalPriceResult = totalPriceTemp.substring(0,totalPriceTemp.length()-1); //remove ] from the text file
			
			try { //get the ice cream information by object, name, price, calorie and amount each.
	              String receipt = "";
	              
	              int last = data.length();
	              String subLine = data.substring(0,last-1);
	              
	              String split[] = subLine.split(",");
	              for (int j=0; j<split.length; j+=5) {

	                 String object = split[j].substring(1);

	                 String name = split[j+1].substring(1);

	                 double price = Double.parseDouble(split[j+2].substring(1));
	                 
	                 double calorie = Double.parseDouble(split[j+3].substring(1));

	                 int amount = Integer.parseInt(split[j+4].substring(1));
	                 
	                 Thread.sleep(150); //slow
	                 
	                 totalCalorie += calorie*(double)amount; //calculate the calorie
	                 publish(((j+1)/5+1)+ ". NAME: "+name+"\nPRICE: "+price+"\nAMOUNT: "+amount); //publish the essential information
	              }
	              lblCalorie.setText("Total calorie: "+ totalCalorie); //set text of total calorie
	              lblPrice.setText("Total price: "+ totalPriceResult); //set text of total price
			} catch (InterruptedException e) {
				// update the status
				lblCalorie.setText("Error performing computation.");
			}
			return totalCalorie; //return totalCalorie
		}

		// displays published values
		protected void process(List<String> publishedVals) {
			for (int i = 0; i < publishedVals.size(); i++) {
				textArea.append(publishedVals.get(i) + "\n");
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		} // end method process

		// code to execute when doInBackground completes
		protected void done() {
			// disable Start button and enable Cancel button

			
			int total = 0;
			try {
				total = get(); //which is totalCalorie
				lblCalorie.setText("Total Calorie: " + total); //print totalCalorie

			} catch (InterruptedException ex) {
				lblCalorie.setText("Interrupted while waiting for results.");
			}catch (ExecutionException ex) {
				lblCalorie.setText("Error performing computation.");
			} // end try catch
			
		}



	}

}
