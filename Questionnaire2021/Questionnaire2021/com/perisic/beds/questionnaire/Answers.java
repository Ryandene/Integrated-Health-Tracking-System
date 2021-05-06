package com.perisic.beds.questionnaire;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Vector;

import com.perisic.beds.rmiinterface.Question;

/**
 * This class represents a set of answered questions and methods are provided 
 * to display these answers in various formats: raw data, bar chart, JSON. 
 * To do: add more methods to systematically analyse the answers according to the needs 
 * of the project. 
 * @author Marc Conrad
 */

public class Answers {

	private Vector<Question> myData;
	private final static HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	
	/** 
	 * Initialises the object with the data to be analysed. 
	 * @param myData a vector with answered questions.
	 */
	public Answers(Vector<Question> myData) {
		super();
		this.myData = myData;
	}


	public String basicAnalysis() {	
		StringBuffer report = new StringBuffer(); 
		for(int i = 0; i < myData.size(); i++ ) { 
			Question qq = myData.elementAt(i); 

			report.append(qq.getQuestionText()); 
			String [] answers = qq.getAnswers(); 			
			for(int j = 0; j < answers.length; j++ ) { 
				report.append(" "+answers[j]+" - "+ qq.getFrequency(answers[j])+";");
			}
			report.append(System.lineSeparator()); 
		}
		return report.toString(); 


	}
	/**
	 * Accepts json data ad produces a jpg image of a bar chart, based on the data. 
	 * @return URL that links to the jpg image. 
	 */
	public String[] analayseQuestionsForEmail() {
		
		String[] tempUrl = new String[myData.size()];
		for(int i = 0; i < myData.size(); i++ ) { 
			tempUrl[i] = getBarChartURL(i);
		}
		
		return tempUrl;
	}
	
	public String getBarChartURL(int questionId) {
		String urlToRender = chartConnection(getJSON(questionId));
		String destinationUrl = "image" + questionId + ".jpg";
		saveImage(urlToRender, destinationUrl);
		return destinationUrl;
		
	}
	
	public static void saveImage(String imageUrl, String destinationFile) {
		try {
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		}
		catch(Exception e){
			System.out.println("Something went wrong with connecting to the server.");
			e.printStackTrace();
			//return null; 
		}
		
	}
	
	public String getJSON(int questionId) {
		String type = "type";
		String chartType = "bar";
		String dataAttribute = "data";
		String labelAttribute = "labels";
		String datasetsAttribute = "datasets";
		String colorArray = "\"backgorundColor\":[\"#FF3784\", \"#4BC0C0\", \"#F77825\"],";
		String report = "{\"chart\": {";
		
		Question qq = myData.elementAt(questionId);
		
		report+= "\"" + type + "\":\"" + chartType + "\",\"" + dataAttribute + "\": {\"" + labelAttribute + "\": [";

		String [] answers = qq.getAnswers(); 			
		for(int j = 0; j < answers.length; j++ ) { 
			if( j > 0 ) { report += ","; } 
			report += "\""+answers[j]+"\" ";
		}
		report += "],\"" + datasetsAttribute + "\":[{" + colorArray + "\"data\":[";
		for(int k = 0; k < answers.length; k++ ) {
			if( k > 0 ) { report += ","; }
			report += qq.getFrequency(answers[k]);
		}
		 
		report += "]}]}";
		//}
		report += "}}";
		return report; 
	}
	
	public static String chartConnection(String body) {
		String str = "{\"chart\": {\"type\": \"bar\", \"data\": {\"labels\": [\"Hello\", \"World\"], \"datasets\": [{\"label\": \"Foo\", \"data\": [1, 2]}]}}}";

		HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(body))
				.uri(URI.create("https://quickchart.io/chart/create")).setHeader("User-Agent", "Java 11 HttpClient Bot")																								// header
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		// print status code
		System.out.println(response.statusCode());

		// print response body
		System.out.println(response.body());
		String url=null;
		try {
			JSONObject jsonObject= (JSONObject) new JSONParser().parse(response.body());
			url=(String) jsonObject.get("url");
			System.out.println(url);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return url;
	}


}
