package iVote;
import java.util.*;
public class IVoteService implements IVoteServiceInterface {
	
	private QuestionInterface question;
	private Map<String, ArrayList<String>> submissionMap; //keys are studentID and 
														//values are lists of answers
	private StudentInterface student;
	
	public void getQuestionType(QuestionInterface q) {		
		question = q;
		System.out.println(question.getQuestion());

	}
	
	public void acceptAns() {
		student = new Student();
		submissionMap = new HashMap<>();
		
		//get a random number of students
		int totalStudents = student.generateStudents();
		System.out.println("Number of students: " + totalStudents);
		
		//for each student, generate an ID for that student
		//let student submit random numbers as answer(s) based on the question type
		//numbers will be convert to corresponding answer as string, and put to map
		for (int i = 0; i < totalStudents; i++) {
			String stuID = student.generateID();
			int possibleAns = question.configureAnswer().size();
			String stuAnsCode = student.submit(question, possibleAns);
			//System.out.println("stuAnsCode: " + stuAnsCode);
			ArrayList<String> stuAns = new ArrayList<>();
			for (int j = 0; j < stuAnsCode.length(); j++ ){
				int intCode = Character.getNumericValue(stuAnsCode.charAt(j));
				//System.out.println("intCode: " + intCode);
				stuAns.add(question.configureAnswer().get(intCode));
			}		
			//System.out.println(stuAns);
			submissionMap.put(stuID, stuAns);
		}		
	}
	
	public String displayStats() {
		String str = "";
		int candidateAns = question.configureAnswer().size();
		int[] ansArr = countAns(submissionMap);
		
		for (int i = 1; i <= candidateAns; i++) {
			str += (question.configureAnswer().get(i) +": "+ ansArr[i-1]  + ", ");
		}
		
		str = str.substring(0, str.length() - 2);
		return str;
	}

	private int[] countAns(Map<String,ArrayList<String>> m ) {
		int[] countAns = new int[question.configureAnswer().size()];		
		for (String key : m.keySet()) {
			ArrayList<String> ans = m.get(key);
			Iterator<String> it = ans.iterator();
			while (it.hasNext()){
				String partOfAns = it.next();
				switch (partOfAns) {
				case "A": countAns[0]++; break;
				case "B": countAns[1]++; break;
				case "C": countAns[2]++; break;
				case "D": countAns[3]++; break;
				case "Right": countAns[4]++; break;
				case "Wrong": countAns[5]++; break;
				case "Free Response": countAns[6]++; break;
				default: break;	
				}				
			}
		}		
		return countAns;
	}
	
}
