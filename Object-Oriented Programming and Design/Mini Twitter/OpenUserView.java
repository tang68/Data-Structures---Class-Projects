package miniTwitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

@SuppressWarnings("serial")
public class OpenUserView extends JFrame implements ViewInterface{

	private JPanel contentPane;
	private JTextField followUserTextField, msgField;
	private JList<String> followingList, newsfeedList;
	private JScrollPane followingScrollPane, newsfeedScrollPane;	
	private User currentUser;	
	private List<String> followingIdList, newsfeed;
	private DefaultListModel<String> followingListModel, newsfeedListModel;
	private String msg, idToFollow;
	private Map<String, User> userMap = AdminControlPanel.getUserMap();
	
	@SuppressWarnings("unchecked")
	public OpenUserView (User user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		currentUser = user.getUser();
		newsfeedListModel = new DefaultListModel<String>();

		followUserTextField = new JTextField();
		followUserTextField.setBounds(10, 10, 270, 20);
		contentPane.add(followUserTextField);
		
		msgField = new JTextField();
		msgField.setBounds(10, 230, 270, 20);
		contentPane.add(msgField);
		
		JButton followUserButton = new JButton("Follow User");
		followUserButton.setBounds(290, 10, 115, 25);
		followUserButton.addActionListener(new FollowUserButtonListener());
		contentPane.add(followUserButton);
		
		JButton postMsgButton = new JButton("Post Message");
		postMsgButton.setBounds(290, 230, 115, 25);
		postMsgButton.addActionListener(new PostTweetButtonListener());
		contentPane.add(postMsgButton);
		
		JLabel currentlyFollowingLabel = new JLabel("Currently Following:");		
		currentlyFollowingLabel.setBounds(10, 45, 175, 15);
		contentPane.add(currentlyFollowingLabel);
		
		JLabel newsfeedLabel = new JLabel("Your News Feed:");
		newsfeedLabel.setBounds(10, 265, 210, 15);
		contentPane.add(newsfeedLabel);
		
		followingListModel = new DefaultListModel<String>();
		followingList = new JList<String>(followingListModel);
		followingList.setBounds(10, 65, 415, 145);
		contentPane.add(followingList);
		followingIdList = (List<String>)  ((ArrayList<String>) currentUser.getFollowings()).clone();	
		//Add followings to list
		for (int i = 0; i < followingIdList.size(); i++){
			followingListModel.addElement(followingIdList.get(i));
		}

		newsfeedList = new JList<String>(newsfeedListModel);
		newsfeedList.setBounds(10, 290, 415, 115);
		newsfeed = (List<String>) ((ArrayList<String>) currentUser.getNewsfeed()).clone();
		contentPane.add(newsfeedList);
		for (int i = 0; i < newsfeed.size(); i++){
			newsfeedListModel.addElement(newsfeed.get(i));
		}

		followingScrollPane = new JScrollPane(followingList);
		followingScrollPane.setBounds(10, 65, 415, 145);
		contentPane.add(followingScrollPane);

		newsfeedScrollPane = new JScrollPane(newsfeedList);
		newsfeedScrollPane.setBounds(10, 290, 415, 115);
		contentPane.add(newsfeedScrollPane);
	}
	
	private class FollowUserButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){	
			//Set the user ID of the user to follow
			idToFollow = followUserTextField.getText();
			
			if(!userMap.containsKey(idToFollow)) {
				JOptionPane.showMessageDialog(null, "User doesn't exist!");
			} else if (idToFollow.equals(currentUser.getId())) {
				JOptionPane.showMessageDialog(null, "You can't follow yourself!");
			} else if(currentUser.getFollowings().contains(idToFollow)){
				JOptionPane.showMessageDialog(null, "You already follow this user.");
			} else {
				follow(idToFollow);
				followingListModel.addElement(idToFollow);
			}
		}

		
	}
	
	private class PostTweetButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){				
			msg = msgField.getText();
			String toNewsFeed = currentUser.getId() + ": " + msg;
			notifyObservers(msg);
			currentUser.update(msg);
			newsfeedListModel.addElement(toNewsFeed);
			
			CountTotalMsg msgTotal = new CountTotalMsg();
			msgTotal.visited();
				
			String[] words = {"good", "great", "excellent"};
			
			CountPositiveMsg positiveTotal = new CountPositiveMsg();
			
			for (int i = 0; i < words.length; i++) {
				if (msg.contains(words[i])) {
					positiveTotal.visited();
				}
			}
		}
	}	
	@Override
	public void follow(String id) {
		currentUser.addToFollowings(id);
		userMap.get(id).addToFollowers(currentUser.getId());
	}

	@Override
	public void notifyObservers(String msg) {
		for (int i = 0; i < currentUser.getFollowers().size(); i++) {
			String follower = currentUser.getFollowers().get(i);
			userMap.get(follower).update(msg);	
			
			
			//User currentFollower = userMap.get(follower);
			//System.out.println(currentFollower.getNewsfeed());
			JOptionPane.showMessageDialog(null, "NewsFeed Notification for " + follower + "\n" 
					+ currentUser.getId() + " says " + msg);
			
		}	
	}

	//private void updatenewsfeedListModel(String msg){
	//	newsfeedListModel.addElement(msg);;
	//}
}
