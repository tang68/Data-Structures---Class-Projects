package miniTwitter;

public class Group implements UserAndGroupInterface{
	
	private String groupId;

	public Group(String id) {
		groupId = id;
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return groupId;
	}

}
