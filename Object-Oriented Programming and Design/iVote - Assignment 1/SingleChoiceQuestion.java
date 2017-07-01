package iVote;

import java.util.Random;

public class SingleChoiceQuestion extends Question{

	private String question;
	
	public SingleChoiceQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return "S.C. Question: " + question;
	}

	public String getAnswer(int maxAns) {
		Random rand = new Random();
		return Integer.toString(rand.nextInt(maxAns)+1);
	}

}
