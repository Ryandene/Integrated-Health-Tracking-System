package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import com.perisic.beds.rmiinterface.AnalyseQuestionsImplementation;
import com.perisic.beds.rmiinterface.IAnalyzeQuestions;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SendEmailScreen {

	private JFrame frame;
	private JTextField subjectText;
	private JTextField toEmailText;
	private JTextField loginEmailText;
	private JPasswordField loginPwdText;
	private IAnalyzeQuestions iAnalyzeQuestions = new AnalyseQuestionsImplementation();
	private String[] attachmentSet;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendEmailScreen window = new SendEmailScreen();
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
	public SendEmailScreen() {
		initialize();
	}
	
	public SendEmailScreen(String[] attachments) {
		attachmentSet = attachments;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 204, 204));
		frame.setBounds(100, 100, 602, 453);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Subject");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 65, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		subjectText = new JTextField();
		subjectText.setBounds(160, 62, 391, 20);
		frame.getContentPane().add(subjectText);
		subjectText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("To");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 96, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		toEmailText = new JTextField();
		toEmailText.setBounds(160, 93, 391, 20);
		frame.getContentPane().add(toEmailText);
		toEmailText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Message");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(29, 124, 98, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JTextPane messageText = new JTextPane();
		messageText.setBounds(161, 124, 391, 128);
		frame.getContentPane().add(messageText);
		
		JLabel lblNewLabel_3 = new JLabel("Enter you email login credentials to send email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(29, 291, 333, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(29, 316, 80, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		loginEmailText = new JTextField();
		loginEmailText.setBounds(160, 313, 391, 20);
		frame.getContentPane().add(loginEmailText);
		loginEmailText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Note: Images will be attached with the email.");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(340, 257, 217, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(29, 339, 66, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 281, 524, 2);
		frame.getContentPane().add(separator);
		
		loginPwdText = new JPasswordField();
		loginPwdText.setBounds(160, 336, 391, 20);
		frame.getContentPane().add(loginPwdText);
		
		JButton btnNewButton = new JButton("Login and Send Email");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 204, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String subject = subjectText.getText();
				String recipient = toEmailText.getText();
				String message = messageText.getText();
				
				String emailUsername = loginEmailText.getText();
				char[] passwordChar = loginPwdText.getPassword();				
				String emailPassword = new String(passwordChar);
				
				try {
					iAnalyzeQuestions.sendEmailWithAnalayzedAttachments(recipient, emailUsername, emailPassword, subject, message, attachmentSet);
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (AuthenticationFailedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Email username or password is invalid!");
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(393, 367, 158, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_7 = new JLabel("Email Report");
		lblNewLabel_7.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		lblNewLabel_7.setForeground(new Color(204, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(206, 11, 217, 29);
		frame.getContentPane().add(lblNewLabel_7);
		frame.setVisible(true);
	}
}
