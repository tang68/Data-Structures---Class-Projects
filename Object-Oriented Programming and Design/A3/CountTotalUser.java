package miniTwitter;

public class CountTotalUser implements VisitInterface{

	private static int totalUsers;
	
	@Override
	public void visited() {		
		totalUsers++;
	}
	
	public int getUsers() {
		return totalUsers;
	}

}
