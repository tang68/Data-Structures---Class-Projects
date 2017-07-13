package miniTwitter;

public interface ShowStatsInterface {
	
	public void visit (CountTotalUser users);
	public void visit (CountTotalGroup groups);
	public void visit (CountTotalMsg msg);
	public void visit (CountPositiveMsg pos);

}
