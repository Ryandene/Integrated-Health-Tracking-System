package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.sql.*;

import com.perisic.beds.dbconnection.DBConnection;
import com.perisic.beds.rmiinterface.Question;
import com.perisic.beds.rmiinterface.RemoteQuestions;

/**
 * Implementation of the questionnaire. Note that chosen answers are collected in this
 * object as well. That means that if the object is destroyed, for instance server restart
 * the collected data is all gone. 
 * To do: make data persistent, for instance link collected data to a database or save data
 * in a text file.  
 * @author Marc Conrad
 *
 */
public class QuestionServerImplementation 
extends UnicastRemoteObject implements RemoteQuestions{
	private static final long serialVersionUID = -3763231206310491048L;
	
	private String storedCookie = "somerandomstring"; 
	Vector<Question> myQuestions = new Vector<Question>(); 
	/**
	 * All questions and answers are initialised in the constructor of this class. 
	 * To do: read questions and options from an external data file. 
	 * @throws RemoteException
	 */
	QuestionServerImplementation() throws RemoteException {
		super();
		System.out.println("QuestionServerImplementation Created");
		String[] answers = {"20-39", "40-59", "60-79" }; 

		Question question1 = new Question("Which Age Group Desribes", answers ); 
		myQuestions.add(question1); 
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers2 = {"65-69", "70-85", "72-88" }; 

		Question question2 = new Question("Which Weight Group Are You In (Kilogrames)?", answers2 );
		myQuestions.add(question2); 
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers3 = {"177cm", "180cm", "182cm" }; 

		Question question3 = new Question("Which Height group are you in?", answers3 );
		myQuestions.add(question3); 
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers4 = {"<93cm", "93cm-99cm", "99cm-119cm" }; 
		
		Question question4 = new Question("Which Waist group are you in?", answers4 );
		myQuestions.add(question4);
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers5 = {"<93cm", "93cm-99cm", "99cm-119cm" }; 
		
		Question question5 = new Question("Which Waist group are you in?", answers5 );
		myQuestions.add(question5);
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers6 = {"<93cm", "93cm-99cm", "99cm-119cm" }; 
		
		Question question6 = new Question("Which Waist group are you in?", answers6 );
		myQuestions.add(question6);
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers7 = {"<93cm", "93cm-99cm", "99cm-119cm" }; 
		
		Question question7 = new Question("Which Waist group are you in?", answers7 );
		myQuestions.add(question7);
		
		System.out.println("QuestionServerImplementation Created");
		String[] answers8 = {"Meat and Vegitables", "Organic foods", "Fast foods" }; 
		
		Question question8 = new Question("what foods you prefer?", answers8 );
		myQuestions.add(question8);
	}

	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public int getNumberOfQuestions() throws RemoteException {
		return myQuestions.size();
	}
	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public Question getQuestion(int i) throws RemoteException {
		return myQuestions.elementAt(i);
	}
	/**
	 * Implementation of remote interface method. 
	 */	
	@Override
	public void submitAnswer(int i, String answer) throws RemoteException {
		myQuestions.elementAt(i).addAnswer(answer);
	}
	/**
	 * Implementation of remote interface method. 
	 */	
	@Override
	public Vector<Question> getData(String cookie) {
		if( cookie.equals(storedCookie) ) { 
			return myQuestions; 
		} else { 
			return null; 
		}
	}
	
	/**
	 * Basic login implementation. Use for demo of cookies only; more sophistiacatd implementation shouls use
	 * encryption and database with username / password. 
	 */
	@Override
	public String login(String username, String password) throws RemoteException {
		// Todo: lookup password in a database; introduce username and link username to the password etc. 
		
		try {
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement("Select userId, username, password from users where username=? and password=?"); 
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				storedCookie =  "abc"+Math.random();   
				return storedCookie; 
            } else {
            	return "wrongpassword"; 
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "wrongpassword";
	}
	
	@Override
	public String loginWithPassword(String password) throws RemoteException {
		if( password.equals("hello" )) { 			
			storedCookie =  "abc"+Math.random(); // returns a random string. There are better ways to do that.  
			return storedCookie; 
		} else { 
			return "wrongpassword"; 
		}
	}

}
