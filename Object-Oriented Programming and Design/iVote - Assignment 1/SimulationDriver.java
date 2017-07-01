package iVote;
import java.util.*;
public class SimulationDriver {

	public static void main(String[] args) {

		QuestionInterface question;
		IVoteServiceInterface IV;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter 1 for single choice question OR 2 for multiple choices question >> ");
		int questionType = scan.nextInt();
		scan.nextLine();
		
		if (questionType != 1 && questionType != 2) {
			System.out.println("Wrong command! Exit.");
			System.exit(0);
		}
		
		System.out.print("Enter the question >> ");		
		String q = scan.nextLine();
		IV = new IVoteService();
		
		if (questionType == 1 ) {			
			question = new SingleChoiceQuestion(q);			
			IV.getQuestionType(question);
			IV.acceptAns();			
			System.out.println(IV.displayStats());		
		}
		else  {
			question = new MultipleChoiceQuestion(q);
			IV.getQuestionType(question);
			IV.acceptAns();
			System.out.println(IV.displayStats());	
		}
		
		scan.close();
	}

}
