package miniTwitter;

public class CountTotalGroup implements VisitInterface{

	private static int totalGroups;
	@Override
	public void visited() {
		// TODO Auto-generated method stub
		totalGroups++;
	}
	
	public int getGroups(){
		return totalGroups;
	}

}
