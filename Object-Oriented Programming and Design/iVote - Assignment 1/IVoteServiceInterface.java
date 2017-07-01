package iVote;

/**
 * @author Ryan Tang
 *
 */
public interface IVoteServiceInterface {

	public void getQuestionType(QuestionInterface q);
	public void acceptAns();
	public String displayStats();
}
