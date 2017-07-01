package iVote;

import java.util.*;

public abstract class Question implements QuestionInterface{

	private AnswerConfiguration answers = new CandidateAns();
	
	public Map<Integer, String> configureAnswer() {		
		return answers.getAnswers();
	}
}
