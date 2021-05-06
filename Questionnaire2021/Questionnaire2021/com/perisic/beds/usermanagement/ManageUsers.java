package com.perisic.beds.usermanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.perisic.beds.dbconnection.DBConnection;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ManageUsers {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUsers window = new ManageUsers();
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
	public ManageUsers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 204, 204));
		frame.setBounds(420, 200, 450, 363);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGetUsers = new JButton("Display Users");
		btnGetUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				if(tableModel.getRowCount() > 0) {
					tableModel.setRowCount(0);
				}
				try {
					Connection conn = DBConnection.getDBConnection();
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement("select userId, username from users"); 
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						String userId = String.valueOf(rs.getInt("userId"));
						String username = rs.getString("username");
						
						String tableData[] = {userId, username};
						tableModel.addRow(tableData);
					}	
				}catch(Exception sqlException) {
					sqlException.printStackTrace();
				}
			}
		});
		btnGetUsers.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGetUsers.setBackground(new Color(51, 102, 102));
		btnGetUsers.setForeground(new Color(255, 255, 255));
		btnGetUsers.setBounds(20, 76, 128, 45);
		frame.getContentPane().add(btnGetUsers);
		
		JLabel lblNewLabel = new JLabel("Questionnaire Admins");
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));
		lblNewLabel.setBounds(100, 24, 253, 29);
		frame.getContentPane().add(lblNewLabel);
		
		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(102, 204, 204));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User ID", "Username"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(64);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		table.setBounds(171, 76, 253, 237);
		frame.getContentPane().add(table);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				
				//delete a row
				if(table.getSelectedRowCount() == 1) {
					int row = table.getSelectedRow();
					String userIdToDelete = (String) tableModel.getValueAt(row, 0);
					
					tableModel.removeRow(table.getSelectedRow());
					
					try {
						Connection conn = DBConnection.getDBConnection();
						PreparedStatement statement;
						statement = (PreparedStatement) conn.prepareStatement("delete from users where userId = ?");
						statement.setString(1, userIdToDelete);
						statement.executeUpdate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
				}
				else {
					if(table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(btnDeleteUser, "Empty Table");
					}
					else {
						JOptionPane.showMessageDialog(btnDeleteUser, "Please select a single user to be deleted");
					}
				}
			}
		});
		btnDeleteUser.setForeground(new Color(255, 255, 255));
		btnDeleteUser.setBackground(new Color(204, 51, 51));
		btnDeleteUser.setBounds(20, 132, 128, 23);
		frame.getContentPane().add(btnDeleteUser);
		frame.setVisible(true);
	}
}
