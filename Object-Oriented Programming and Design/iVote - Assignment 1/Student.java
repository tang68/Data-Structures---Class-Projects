/**
 * 
 */
package iVote;

import java.util.*;


/**
 * @author Ryan Tang
 *
 */
public class Student implements StudentInterface{

	private final int IDlength = 5;
	private Random rand = new Random();
	private Set<String> studentList;
	
	//Generate a random number of students 
	//Assuming the min number of students is 10 and max is 15
	public int generateStudents() {		
		return rand.nextInt(16) + 10;
	}

	
	public String generateID() {		
		   StringBuilder id = new StringBuilder( IDlength );
		   for( int i = 0; i < IDlength; i++ ) 
		      id.append(rand.nextInt(10) );
		   return id.toString();			
	}
	
	public Set<String> studentList() {
		studentList = new HashSet<>();
		int numOfStudents = generateStudents();
		for (int i = 0; i < numOfStudents; i++) {
			studentList.add(generateID());
		}
		return studentList;
	}
	
	public String submit(QuestionInterface Qtype, int possibleAnswers) {
		
		return Qtype.getAnswer(possibleAnswers);
	}
}
