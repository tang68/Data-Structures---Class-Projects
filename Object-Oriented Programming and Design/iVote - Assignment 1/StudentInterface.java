/**
 * 
 */
package iVote;

import java.util.Set;

/**
 * @author Ryan Tang
 *
 */
public interface StudentInterface {
	
	public int generateStudents();
	public String generateID();
	public Set<String> studentList();

	public String submit(QuestionInterface Qtype, int possibleAnswers);
	
}
