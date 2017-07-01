package iVote;

import java.util.Random;

public class MultipleChoiceQuestion extends Question{

	private String question;
	
	public MultipleChoiceQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return "M.C. Question: " + question;
	}

	public String getAnswer(int maxAns) {
		Random rand = new Random();
		int answers = rand.nextInt(maxAns)+1;
		String returnStr = "";
		for (int i = 0; i < answers; i++) {
			int ansCode = rand.nextInt(maxAns)+1;
			returnStr += Integer.toString(ansCode);
		}
		return returnStr;
	}
	
	
}
