package miniTwitter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class User implements ObserveInterface, UserAndGroupInterface {

	private String userID;
	private ArrayList<String> followers;
	private ArrayList<String> followings;
	private ArrayList<String> newsfeed;
//	private DefaultListModel<String>  newsfeedListMdel;
	public User(String id) {
		userID = id;
		followings = new ArrayList<String>();
		followers = new ArrayList<String>();
		newsfeed = new ArrayList<String>();
	}

	public User getUser() {
		return User.this;
	}

	public ArrayList<String> getFollowings() {
		return followings;
	}

	public ArrayList<String> getFollowers() {
		return followers;
	}

	public ArrayList<String> getNewsfeed() {

		return newsfeed;
	}

	public void addToFollowings(String string) {
		followings.add(string);
	}

	public void addToFollowers(String string) {
		followers.add(string);
	}

	@Override
	public void update(String string) {
		newsfeed.add(string);
	}

//	public void updateListModel(String string) {
//		newsfeedListMdel.addElement(string);
//	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return userID;
	}

}
