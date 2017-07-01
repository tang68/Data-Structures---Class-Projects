package iVote;

import java.util.HashMap;
import java.util.Map;

public class CandidateAns implements AnswerConfiguration{

	private Map<Integer, String> answerMap = new HashMap<>();

	
	public Map<Integer, String> getAnswers() {
		answers();
		return answerMap;
	}

	//Assume these are the possible answers for both types of question
	private void answers() {
		answerMap.put(1, "A" );
		answerMap.put(2, "B");
		answerMap.put(3, "C");
		answerMap.put(4, "D");
		answerMap.put(5, "Right");
		answerMap.put(6, "Wrong");
		answerMap.put(7, "Free Response");
	}

}
