package com.perisic.beds.usermanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.perisic.beds.dbconnection.DBConnection;
import com.perisic.beds.questionnaire.QuestionSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class RegisterUI {

	private JFrame frmRegisterAdult;
	private JTextField txtUserName;
	private JTextField txtPwd;
	private JTextField txtRPwd;
	private QuestionSet questionnaire = new QuestionSet();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI window = new RegisterUI();
					window.frmRegisterAdult.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegisterAdult = new JFrame();
		frmRegisterAdult.setTitle("Register | Adult Health Checker");
		frmRegisterAdult.getContentPane().setBackground(new Color(51, 204, 204));
		frmRegisterAdult.setBounds(500, 200, 419, 307);
		frmRegisterAdult.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmRegisterAdult.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		lblNewLabel.setBounds(141, 11, 140, 39);
		frmRegisterAdult.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(60, 61, 99, 14);
		frmRegisterAdult.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(60, 100, 99, 14);
		frmRegisterAdult.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Re-type Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(62, 142, 115, 14);
		frmRegisterAdult.getContentPane().add(lblNewLabel_3);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(200, 58, 149, 20);
		frmRegisterAdult.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPwd = new JTextField();
		txtPwd.setColumns(10);
		txtPwd.setBounds(200, 97, 149, 20);
		frmRegisterAdult.getContentPane().add(txtPwd);
		
		txtRPwd = new JTextField();
		txtRPwd.setColumns(10);
		txtRPwd.setBounds(200, 139, 149, 20);
		frmRegisterAdult.getContentPane().add(txtRPwd);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(255, 204, 0));
		btnClear.setBounds(109, 196, 89, 34);
		frmRegisterAdult.getContentPane().add(btnClear);
		
		JButton bttnRegister = new JButton("REGISTER");
		bttnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		bttnRegister.setForeground(new Color(255, 255, 255));
		bttnRegister.setBackground(new Color(51, 153, 153));
		
		bttnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUserName.getText();
				String password = txtPwd.getText();
				String rePassword = txtRPwd.getText();
				
				if(password.equals(rePassword)) {
						register(username, password);
						
				}
				else {
					JOptionPane.showMessageDialog(bttnRegister, "Passwords does not match");
				}
			}
		});
		bttnRegister.setBounds(208, 196, 101, 34);
		frmRegisterAdult.getContentPane().add(bttnRegister);
		frmRegisterAdult.setVisible(true);
	}
	
	public void register(String username, String password){
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement("insert into users values(?, ?)"); 
			statement.setString(1, username);
			statement.setString(2, password);
			statement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
