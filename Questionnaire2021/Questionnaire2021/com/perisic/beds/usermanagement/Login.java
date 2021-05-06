package com.perisic.beds.usermanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.perisic.beds.peripherals.AnalyzeQuestions;
import com.perisic.beds.questionnaire.QuestionSet;

import jdk.nashorn.internal.objects.annotations.Getter;
import sun.java2d.Disposer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField pwd;
	private QuestionSet questionnaire = new QuestionSet();
	private String myCookie = "not yet set"; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(51, 204, 204));
		frmLogin.setTitle("Login | Adult Health Checker");
		frmLogin.setBounds(420, 200, 525, 330);
		frmLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		lblNewLabel.setBounds(189, 21, 144, 31);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(116, 66, 78, 14);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(116, 103, 78, 14);
		frmLogin.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(280, 63, 153, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				pwd.setText("");
			}
		});
		btnClear.setBackground(new Color(255, 204, 0));
		btnClear.setForeground(Color.WHITE);
		btnClear.setBounds(251, 147, 89, 34);
		frmLogin.getContentPane().add(btnClear);
		
		JButton btnSignin = new JButton("LOGIN");
		
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Checking Password..."); 				
				String username = textField.getText();
				
				char[] passwordChar = pwd.getPassword();				
				String password = new String(passwordChar);
				
				String result = questionnaire.login(username, password); 
				
				if( result.equals("wrongpassword") ) { 
					System.out.println("Wrong Password");
					JOptionPane.showMessageDialog(null, "Invalid username or password!");
				} else { 
					String loggedInUserId = questionnaire.getLoggedInUserId(username, password);
					System.out.println("Logged user: " + loggedInUserId);
					myCookie = result;
					System.out.println("Successful Login!");
					new AnalyzeQuestions(myCookie, loggedInUserId);
				}
			}
		});
		btnSignin.setBackground(new Color(51, 102, 102));
		btnSignin.setForeground(Color.WHITE);
		btnSignin.setBounds(344, 147, 89, 34);
		frmLogin.getContentPane().add(btnSignin);
		
		pwd = new JPasswordField();
		pwd.setBounds(280, 100, 153, 20);
		frmLogin.getContentPane().add(pwd);
		
		JButton btnRegister = new JButton("Create Account");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterUI();
			}
		});
		btnRegister.setBackground(new Color(0, 0, 51));
		btnRegister.setBounds(172, 232, 153, 23);
		frmLogin.getContentPane().add(btnRegister);
		
		JLabel lblNewLabel_3 = new JLabel("Don't have an account?");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(189, 207, 151, 14);
		frmLogin.getContentPane().add(lblNewLabel_3);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("loginBackground.png").getImage().getScaledInstance(169, 134, Image.SCALE_DEFAULT));
		frmLogin.setVisible(true);
	}
}
