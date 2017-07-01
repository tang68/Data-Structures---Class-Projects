package iVote;


import java.util.Map;

/**
 * @author Ryan Tang
 *
 */
public interface QuestionInterface {
	
	public  Map<Integer, String> configureAnswer();
	public String getQuestion();
	public String getAnswer(int maxAns);
	
}
