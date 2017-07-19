package miniTwitter;

public class CountTotalMsg implements VisitInterface{

	private static int msg;
	public void visited() {
		msg++;		
	}
	
	public int getMsg() {
		return msg;
	}

}
