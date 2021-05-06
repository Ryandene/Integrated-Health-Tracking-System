package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import com.perisic.beds.questionnaire.QuestionSet;
import com.perisic.beds.usermanagement.Login;
import java.awt.Label;

public class QuestionGUI {
	private static final long serialVersionUID = -1077856539035586635L;
	// The GUI only interacts with the QuestionnairePanel class. 
		private QuestionSet questionnaire = new QuestionSet();
		private String myCookie = "not yet set"; 

	private JFrame frmAdultHealthCheck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionGUI window = new QuestionGUI();
					window.frmAdultHealthCheck.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuestionGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdultHealthCheck = new JFrame();
		frmAdultHealthCheck.getContentPane().setBackground(new Color(0, 204, 204));
		frmAdultHealthCheck.setTitle("Home | Adult Health Checker");
		frmAdultHealthCheck.setBounds(420, 200, 523, 300);
		frmAdultHealthCheck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdultHealthCheck.getContentPane().setLayout(null);
		
		JButton btnSQuestion = new JButton("Start Questionaaire");
		btnSQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < questionnaire.numberOfQuestions(); i++) {

					String message = questionnaire.getQuestion(i); 
					String [] options = questionnaire.getOptions(i); 

					int selectedValue = JOptionPane.showOptionDialog(null, 
							message, "Question "+i, JOptionPane.DEFAULT_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
					
					questionnaire.submitAnswer(i, options[selectedValue]);
				}
			}
		});
		btnSQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSQuestion.setBackground(new Color(0, 153, 153));
		btnSQuestion.setForeground(Color.WHITE);
		btnSQuestion.setBounds(309, 108, 188, 62);
		frmAdultHealthCheck.getContentPane().add(btnSQuestion);
		
		JButton btnLogin = new JButton("Login to Analyse");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
			}
		});
		btnLogin.setBackground(new Color(0, 102, 102));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBounds(309, 181, 188, 48);
		frmAdultHealthCheck.getContentPane().add(btnLogin);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("interfaceBackground1.jpg").getImage().getScaledInstance(296, 261, Image.SCALE_DEFAULT));

		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(0, 0, 296, 261);
		
		frmAdultHealthCheck.getContentPane().add(lblNewLabel);
		
		Label label = new Label("Welcome...");
		label.setForeground(new Color(224, 255, 255));
		label.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 24));
		label.setAlignment(Label.CENTER);
		label.setBounds(326, 41, 158, 36);
		frmAdultHealthCheck.getContentPane().add(label);
	}
}
