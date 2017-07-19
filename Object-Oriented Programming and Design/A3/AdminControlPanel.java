package miniTwitter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;


@SuppressWarnings("serial")
public class AdminControlPanel extends JPanel {

	private static AdminControlPanel instance = null;

	private JTextField userIDTxt, groupIDTxt;
	private static Map<String, User> userMap = new HashMap<>();
	private static Map<String, Group> groupMap = new HashMap<>();
	private JTree tree;
	private TreeModel model;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode selectedNode;
	private JScrollPane treeScrollPane;

	public static AdminControlPanel getAdControlInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		return instance;
	}

	private AdminControlPanel() {
		super(new BorderLayout());
		userIDTxt = new JTextField(8);
		groupIDTxt = new JTextField(8);

		JButton addUserButton = new JButton("Add User");
		addUserButton.addActionListener(new addUserListener());

		JButton addGroupButton = new JButton("Add Group");
		addGroupButton.addActionListener(new addGroupListener());

		JButton openUserView = new JButton("Open User View");
		openUserView.addActionListener(new openUserListener());

		JButton showUser = new JButton("Show Total Users");
		showUser.addActionListener(new showUserListener());

		JButton showGroup = new JButton("Show Total Groups");
		showGroup.addActionListener(new showGroupListener());

		JButton showmsg = new JButton("Show Total Messages");
		showmsg.addActionListener(new showmsgListener());

		JButton showPosPer = new JButton("Show Total Positive Msg");
		showPosPer.addActionListener(new showPosPerListener());

		JButton verifyID = new JButton("Verify ID");
		verifyID.addActionListener(new verifyIDListener());
		
		JButton lastUpdatedUser = new JButton("Last Updated User");
		lastUpdatedUser.addActionListener(new lastUpdatedUser());
		
		root = new DefaultMutableTreeNode("Tree View Root---------");
		JPanel treePanel = new JPanel();
		treePanel.setLayout(null);
		tree = new JTree(root);
		treePanel.setPreferredSize(new Dimension(200, 250));
		tree.setBounds(10, 25, 300, 250);

		treePanel.add(tree);

		model = tree.getModel();

		treeScrollPane = new JScrollPane(tree);
		treeScrollPane.setBounds(10, 6, 150, 300);
		treePanel.add(treeScrollPane);

		JPanel Bntpanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		Bntpanel.add(userIDTxt, c);

		c.gridy = 1;
		Bntpanel.add(groupIDTxt, c);

		c.gridy = 0;
		c.gridx = 1;
		Bntpanel.add(addUserButton, c);

		c.gridy = 1;
		Bntpanel.add(addGroupButton, c);

		c.gridx = 0;
		c.gridy = 4;
		Bntpanel.add(showUser, c);

		c.gridx = 1;
		c.gridy = 4;
		Bntpanel.add(showGroup, c);

		c.gridx = 0;
		c.gridy = 5;
		Bntpanel.add(showmsg, c);

		c.gridx = 1;
		c.gridy = 5;
		Bntpanel.add(showPosPer, c);

		c.gridx = 0;
		c.gridy = 6;
		Bntpanel.add(verifyID, c);

		c.gridx = 1;
		c.gridy = 6;
		Bntpanel.add(lastUpdatedUser, c);
		
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 3;
		Bntpanel.add(openUserView, c);
		
		
		add(Bntpanel, BorderLayout.EAST);
		add(treePanel, BorderLayout.WEST);
	}

	private class lastUpdatedUser implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (!userMap.isEmpty()) {
				Map.Entry<String,User> entry = userMap.entrySet().iterator().next();				
				User lastUser = entry.getValue();
				for (User val : userMap.values()) {
						lastUser = val;
				}   
				JOptionPane.showMessageDialog(null, "Last Updated User: " + lastUser.getId());
			}
			else {
				JOptionPane.showMessageDialog(null, "No user or msg to show");
			}
			
		}
		
	}
	private class verifyIDListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String userID = userIDTxt.getText();
			String groupID = groupIDTxt.getText();
			
			if (userID.equals("") && !groupID.equals("")) {
				if (groupMap.containsKey(groupID)) {
					JOptionPane.showMessageDialog(null, "Group name already exists");
				} else if (groupID.contains(" ")){
					JOptionPane.showMessageDialog(null, "ID cannot contain space");
				} else
					JOptionPane.showMessageDialog(null, "Good ID");
			}
			if (groupID.equals("") && !userID.equals("")) {
				if (userMap.containsKey(userID)) {
					JOptionPane.showMessageDialog(null, "User name already exists");
				} else if (userID.contains(" ")){
					JOptionPane.showMessageDialog(null, "ID cannot contain space");
				} else
					JOptionPane.showMessageDialog(null, "Good ID");
			}			
		}		
	}
	
	private class addUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String userID = userIDTxt.getText();
			selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (userID.isEmpty() == true) {
				JOptionPane.showMessageDialog(null, "User ID can not be empty!");
			} else if (userMap.containsKey(userID)) {
				JOptionPane.showMessageDialog(null, "User already exists");
			} else if (selectedNode == null) {
				addUser(userID, root);
			} else if (userMap.containsKey(selectedNode.toString())) {
				JOptionPane.showMessageDialog(null, "Can not create user under a user");
			} else {
				addUser(userID, selectedNode);
			}
		}
	}

	private void addUser(String id, DefaultMutableTreeNode node) {
		User newUser = new User(id);
		userMap.put(id, newUser);
		CountTotalUser totalUser = new CountTotalUser();
		visit(totalUser);
		node.add(new DefaultMutableTreeNode(id));
		userIDTxt.setText("");
		((DefaultTreeModel) model).reload();
	}

	private class addGroupListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String groupID = groupIDTxt.getText();
			selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

			if (groupID.isEmpty() == true) {
				JOptionPane.showMessageDialog(null, "Group ID can not be empty!");
			} else if (groupMap.containsKey(groupID)) {
				JOptionPane.showMessageDialog(null, "Group name already exists");
			} else if (selectedNode == null) {
				addGroup(groupID, root);
			} else if (userMap.containsKey(selectedNode.toString())) {
				JOptionPane.showMessageDialog(null, "Cannot create group under a user");
			} else if (groupID.contains(" ")){
				JOptionPane.showMessageDialog(null, "ID cannot contain space");
			} else {
				addGroup(groupID, selectedNode);
			}

		}

	}

	private void addGroup(String id, DefaultMutableTreeNode node) {
		Group group = new Group(id);
		groupMap.put(id, group);
		CountTotalGroup totalGroups = new CountTotalGroup();
		visit(totalGroups);
		node.add(new DefaultMutableTreeNode(id));
		groupIDTxt.setText("");
		((DefaultTreeModel) model).reload();
	}

	private class openUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode == null || groupMap.containsKey(selectedNode.toString())) {
				JOptionPane.showMessageDialog(null, "Select a user to open the user's profile");
			} else {
				User user = userMap.get(selectedNode.toString());
				System.out.println("Creatation time of <" + user.getId() + "> is " + user.getCreatationTime());
				OpenUserView userView = new OpenUserView(user);
				userView.setVisible(true);
			}
		}

	}

	private class showUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CountTotalUser userTotal = new CountTotalUser();
			JOptionPane.showMessageDialog(null, "Total number of users: " + userTotal.getUsers());
		}
	}

	private class showGroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CountTotalGroup groupTotal = new CountTotalGroup();
			JOptionPane.showMessageDialog(null, "Total number of groups: " + groupTotal.getGroups());
		}

	}

	private class showmsgListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CountTotalMsg msgTotal = new CountTotalMsg();
			JOptionPane.showMessageDialog(null, "Total number of messages: " + msgTotal.getMsg());

		}

	}

	private class showPosPerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CountTotalMsg msgTotal = new CountTotalMsg();
			double total = msgTotal.getMsg();

			CountPositiveMsg posTotal = new CountPositiveMsg();
			double positiveTotal = posTotal.getPosMsg();
			if (total == 0)
				JOptionPane.showMessageDialog(null, "Percent of positive messages: 0%");
			else {
				double percent = (positiveTotal / total) * 100;
				JOptionPane.showMessageDialog(null, "Percent of positive messages: " + percent + "%");
			}
			
		}

	}

	public void visit(CountTotalUser totaluser) {
		totaluser.visited();
	}

	public void visit(CountTotalGroup totalgroup) {
		totalgroup.visited();
	}
	public static Map<String, User> getUserMap () {
		return userMap;
	}

	

}
