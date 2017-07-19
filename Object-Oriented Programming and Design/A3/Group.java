package miniTwitter;

public class Group implements UserAndGroupInterface{
	
	private String groupId;
	private long creatationTime;
	public long getCreatationTime() {
		return creatationTime;
	}

	public void setCreatationTime(long creatationTime) {
		this.creatationTime = creatationTime;
	}

	public Group(String id) {
		groupId = id;
		creatationTime = System.currentTimeMillis();
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return groupId;
	}

}
