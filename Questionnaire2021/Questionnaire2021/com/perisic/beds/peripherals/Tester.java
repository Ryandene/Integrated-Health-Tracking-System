package com.perisic.beds.peripherals;

import java.util.concurrent.ThreadLocalRandom;
import com.perisic.beds.questionnaire.QuestionSet;
/** 
 * A simple class that simulates answering questions to test the infrastructure.
 * @author Marc Conrad
 */
public class Tester {
	
	/**
	 * Simulates howMany participants filling in the questionnaire. 
	 * @param howMany
	 */

	public static void doQuestionnaire(QuestionSet questions, int howMany) { 

		System.out.println("Generating random answers...");	
		for(int k = 0; k < howMany; k++) { 
			for(int i=0; i < questions.numberOfQuestions(); i++) { 
				String [] options = questions.getOptions(i); 
				int randomNum = ThreadLocalRandom.current().nextInt(0, options.length);
				questions.submitAnswer(i, options[randomNum]);
			}
		}

	}
	
	/** 
	 * The tests starts here.
	 * @param args not used.
	 */
	public static void main(String [] args) {

//		System.out.println("Test 1: Answer 10 Questions.");
//		QuestionSet questions1 = new QuestionSet();
//		doQuestionnaire(questions1, 10); 	
//		
//		System.out.println("=== Login with wrong password === "); 
//		String cookie1 = questions1.login("something wrong"); 
//		questions1.reportAnswers(cookie1, 1);
//		
//		System.out.println("=== Login with correct password === "); 
//		String cookie2 = questions1.login("hello"); 
//		questions1.reportAnswers(cookie2, 2);
//		System.out.println(); 

	}
}
