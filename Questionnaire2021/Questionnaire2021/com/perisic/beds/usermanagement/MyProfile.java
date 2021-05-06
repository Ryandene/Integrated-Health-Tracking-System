package com.perisic.beds.usermanagement;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.perisic.beds.dbconnection.DBConnection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MyProfile {

	private String myLoggedInUserId;
	private JFrame frame;
	private JTextField txtCurrentPassword;
	private JTextField txtNewPassword;
	private JTextField txtReenterPw;
	private JTextField txtUsername;
	private String currentUsername, currentPassword;
	private final JLabel lblMyProfile = new JLabel("My Profile");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyProfile window = new MyProfile();
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
	public MyProfile() {
		initialize();
	}
	
	public MyProfile(String loggedInUserId) {
		getLoggedInUserDetails(loggedInUserId);
		myLoggedInUserId = loggedInUserId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 204, 204));
		frame.setBounds(420, 200, 612, 351);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(114, 75, 71, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Current Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(114, 147, 161, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(114, 176, 161, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Re-enter New Password");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(114, 201, 161, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Change Password");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(114, 115, 116, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		txtCurrentPassword = new JTextField();
		txtCurrentPassword.setBounds(341, 148, 135, 20);
		frame.getContentPane().add(txtCurrentPassword);
		txtCurrentPassword.setColumns(10);
		txtCurrentPassword.setText(currentPassword);
		txtCurrentPassword.setEditable(false);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setBounds(341, 173, 135, 20);
		frame.getContentPane().add(txtNewPassword);
		txtNewPassword.setColumns(10);
		
		txtReenterPw = new JTextField();
		txtReenterPw.setBounds(341, 198, 135, 20);
		frame.getContentPane().add(txtReenterPw);
		txtReenterPw.setColumns(10);
		
		txtUsername = new JTextField();		
		txtUsername.setBounds(341, 72, 135, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setText(currentUsername);
		txtUsername.setEditable(false);
		
		JButton btnUpdate = new JButton("Update Password");
		btnUpdate.setBackground(new Color(255, 204, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String username = txtUserName.getText();
				String password = txtNewPassword.getText();
				String rePassword = txtReenterPw.getText();
				
				if(password.equals(rePassword)) {
					updateUserPassword(password);					
				}
				else {
					JOptionPane.showMessageDialog(btnUpdate, "Passwords does not match");
				}
			}
		});
		btnUpdate.setBounds(341, 229, 135, 23);
		frame.getContentPane().add(btnUpdate);
		lblMyProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyProfile.setForeground(new Color(204, 255, 255));
		lblMyProfile.setBounds(237, 21, 129, 31);
		frame.getContentPane().add(lblMyProfile);
		lblMyProfile.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(114, 135, 362, 2);
		frame.getContentPane().add(separator);

		frame.setVisible(true);
		
		
	}
	
	public void updateUserPassword(String password) {
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement("update users set password=? where userId=?"); 
			statement.setString(1, password);
			statement.setString(2, myLoggedInUserId);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Password updated successfully!");
			
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public void getLoggedInUserDetails(String userId) {
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement("select userId, username, password from users where userId=?"); 
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				
				currentUsername = rs.getString("username");
				currentPassword = rs.getString("password");
				
				System.out.println("Username == " + currentUsername);
				System.out.println("Passowrd == " + currentPassword);
            }	
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
	}
}
