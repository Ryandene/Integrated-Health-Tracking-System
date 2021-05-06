package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.perisic.beds.questionnaire.QuestionSet;
import com.perisic.beds.rmiinterface.AnalyseQuestionsImplementation;
import com.perisic.beds.rmiinterface.IAnalyzeQuestions;
import com.perisic.beds.usermanagement.ManageUsers;
import com.perisic.beds.usermanagement.MyProfile;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AnalyzeQuestions {

	private JFrame frame;
	private String myCookie;
	private QuestionSet questionnaire = new QuestionSet();
	private String myLoggedInUserId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyzeQuestions window = new AnalyzeQuestions();
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
	public AnalyzeQuestions(String cookie, String loggedInUserId) {
		myCookie = cookie;
		myLoggedInUserId = loggedInUserId;
		initialize();
	}
	
	public AnalyzeQuestions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(51, 204, 204));
		frame.setBounds(420, 200, 586, 387);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Analyze Questions");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		lblNewLabel.setBounds(156, 47, 291, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Question 01");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 0);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 153, 255));
		btnNewButton.setBounds(122, 113, 170, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnQuestion = new JButton("Question 02");
		btnQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 1);
			}
		});
		btnQuestion.setForeground(new Color(255, 255, 255));
		btnQuestion.setBackground(new Color(0, 153, 255));
		btnQuestion.setBounds(122, 157, 170, 33);
		frame.getContentPane().add(btnQuestion);
		
		JButton btnQuestion_1 = new JButton("Question 03");
		btnQuestion_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 2);
			}
		});
		btnQuestion_1.setForeground(Color.WHITE);
		btnQuestion_1.setBackground(new Color(0, 153, 255));
		btnQuestion_1.setBounds(122, 201, 170, 33);
		frame.getContentPane().add(btnQuestion_1);
		
		JButton btnQuestion_2 = new JButton("Question 04");
		btnQuestion_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 3);
			}
		});
		btnQuestion_2.setForeground(Color.WHITE);
		btnQuestion_2.setBackground(new Color(0, 153, 255));
		btnQuestion_2.setBounds(122, 245, 170, 33);
		frame.getContentPane().add(btnQuestion_2);
		
		JButton btnQuestion_3 = new JButton("Question 05");
		btnQuestion_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 4);
			}
		});
		btnQuestion_3.setForeground(Color.WHITE);
		btnQuestion_3.setBackground(new Color(0, 153, 255));
		btnQuestion_3.setBounds(314, 113, 170, 33);
		frame.getContentPane().add(btnQuestion_3);
		
		JButton btnQuestion_4 = new JButton("Question 06");
		btnQuestion_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 5);
			}
		});
		btnQuestion_4.setForeground(Color.WHITE);
		btnQuestion_4.setBackground(new Color(0, 153, 255));
		btnQuestion_4.setBounds(314, 157, 170, 33);
		frame.getContentPane().add(btnQuestion_4);
		
		JButton btnQuestion_5 = new JButton("Question 07");
		btnQuestion_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 6);
			}
		});
		btnQuestion_5.setForeground(Color.WHITE);
		btnQuestion_5.setBackground(new Color(0, 153, 255));
		btnQuestion_5.setBounds(314, 201, 170, 33);
		frame.getContentPane().add(btnQuestion_5);
		
		JButton btnQuestion_6 = new JButton("Question 08");
		btnQuestion_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionnaire.reportAnswers(myCookie, 7);
			}
		});
		btnQuestion_6.setForeground(Color.WHITE);
		btnQuestion_6.setBackground(new Color(0, 153, 255));
		btnQuestion_6.setBounds(314, 245, 170, 33);
		frame.getContentPane().add(btnQuestion_6);
		
		JMenuBar File = new JMenuBar();
		File.setBackground(Color.LIGHT_GRAY);
		File.setBounds(0, 0, 51, 22);
		frame.getContentPane().add(File);
		
		JMenu mnNewMenu = new JMenu("Options");
		mnNewMenu.setBackground(new Color(51, 204, 204));
		File.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Manage users");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageUsers();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("My Profile");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyProfile(myLoggedInUserId);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("LogOut");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JButton btnNewButton_1 = new JButton("Send Report via Email");
		btnNewButton_1.setBackground(new Color(255, 204, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] urlSet = questionnaire.analyzeResultsToSendEmail(myCookie);
				new SendEmailScreen(urlSet);
			}
		});
		btnNewButton_1.setBounds(172, 309, 266, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
	

}
