package com.perisic.beds.questionnaire;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.perisic.beds.dbconnection.DBConnection;
import com.perisic.beds.peripherals.ShowImageFromURL;
import com.perisic.beds.rmiinterface.Question;
import com.perisic.beds.rmiinterface.RemoteQuestions; 
/**
 * Represents the questionnaire locally. All requests from peripherals will be 
 * through this class. 
 * @author Marc Conrad
 *
 */
public class QuestionSet {

	RemoteQuestions myQuestions; 
	/**
	 * Retrieves the questions from the server. 
	 * To do: connect to different servers, make configurable. 
	 */
	public QuestionSet() {
		super();
		try {
			myQuestions =   (RemoteQuestions) Naming.lookup("rmi://localhost/QuestionService1819");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Is your server running?");
		} 
	}
	/**
	 * How many questions are there in the questionnaire?
	 * @return number of questions.
	 */

	public int numberOfQuestions() { 
		try {
			return myQuestions.getNumberOfQuestions();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0; 
		} 
	}
	/**
	 * Retrieve a specific question. 		
	 * @param i the number of the question.
	 * @return text of the question. 
	 */
	public String getQuestion(int i) { 
		try {
			return myQuestions.getQuestion(i).getQuestionText();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "no connection to server"; 
		} 
	}
	/** 
	 * Gives a set of options for this question. 
	 * @param i the number of the question.
	 * @return an array of options. 
	 */
	public String [] getOptions(int i) { 
		try {
			return myQuestions.getQuestion(i).getAnswers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		} 
	}
	/**
	 * Submit the answer to a given question.
	 * @param i the question.
	 * @param answer the chosen answer.
	 */
	public void submitAnswer(int i, String answer) { 
		try {
			myQuestions.submitAnswer(i, answer);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/** 
	 * Reports the answers in various ways. 
	 */
	public void reportAnswers(String cookie, int questionId) { 

		try {
           Vector<Question> answers = myQuestions.getData(cookie); 
           if(answers == null ) { 
				System.out.println("You must be logged in to retrieve the Answers"); 
			} else { 
			Answers myAnswers = new Answers(answers);
			

				System.out.println("Basic analysis:");
				System.out.println(myAnswers.basicAnalysis());

				ShowImageFromURL.show(myAnswers.getBarChartURL(questionId));
			}
		} catch (RemoteException e) {
			System.out.println("Something went wrong: "+e.toString());
			e.printStackTrace();
		}  
	}
	
	public String[] analyzeResultsToSendEmail(String cookie) {
		try {
			Vector<Question> answers = myQuestions.getData(cookie); 
	           if(answers == null ) { 
					System.out.println("You must be logged in to retrieve the Answers"); 
				} else { 
				Answers myAnswers = new Answers(answers);
				return myAnswers.analayseQuestionsForEmail();
				}
			
		} catch (RemoteException e) {
			System.out.println("Something went wrong: "+e.toString());
			e.printStackTrace();
		} 
		return null;
	}
	
	public String login(String str) {
		try {
			return myQuestions.loginWithPassword(str);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// TODO Auto-generated method stub
		return null;
	}
	
	public String login(String username, String password) {
		try {
			return myQuestions.login(username, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// TODO Auto-generated method stub
		return null;
	}
	
	public void register(String username, String password){
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement("insert into users values(?, ?)"); 
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getLoggedInUserId(String username, String password) {
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement("select userId from users where username=? and password=?"); 
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				String userId = String.valueOf(rs.getInt("userId"));
				return userId; 
            }	
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}
	
	
}
