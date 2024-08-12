import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import javax.swing.Action;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class Start {

	private JFrame frame; //set variable

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
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
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); //set new frame
		frame.getContentPane().setBackground(new Color(208, 208, 255)); //frame color
		frame.setBounds(100, 100, 650, 550); //frame size
		frame.setTitle("Ice Cream Store"); //title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Go Shopping!"); //set button for start 
		btnNewButton.setBackground(new Color(128, 128, 255)); //button color 
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		btnNewButton.setBounds(175, 350, 300, 70);
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Consumer(); //get new page
				frame.setVisible(false); //make this page invisible
			}
		});
			
		frame.getContentPane().add(btnNewButton);
		//set the Title for the start page
		JLabel lblNewLabel = new JLabel("WELCOME TO");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(220, 122, 202, 111);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblJavasIceCream = new JLabel("Java's ICE CREAM STORE");
		lblJavasIceCream.setForeground(Color.WHITE);
		lblJavasIceCream.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 36));
		lblJavasIceCream.setBounds(142, 206, 482, 111);
		frame.getContentPane().add(lblJavasIceCream);
	}
}
