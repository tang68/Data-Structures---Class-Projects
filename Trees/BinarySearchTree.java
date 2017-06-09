package treeNode;

import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

	private TreeNode<T> root;
	public BinarySearchTree(){
		this(null);
	}
	
	public BinarySearchTree(TreeNode<T> newRoot){
		root = newRoot;
	}
	
	
	public void preorderTraverse(){
		root.preorderTraverse();
	}

	public void inorderTraverse(){
		root.inorderTraver();
	}
	
	public void postorderTraverse() {
		root.postorderTraverse();
	}
	
	public void desc(){
		root.desc();
	}
	
	public TreeNode<T> searchElement(T dataToSearch){
		return searchElement(root, dataToSearch);
	}
	
	private TreeNode<T> searchElement(TreeNode<T> newRoot, T dataToSearch){
		if (newRoot == null)
			return null;
		if (dataToSearch == root.getData())
			return root;
		if (dataToSearch.compareTo(root.getData()) <= 0) {
			return searchElement(root.getLeftChild(), dataToSearch);
		} else {
			return searchElement(root.getRightChild(), dataToSearch);
		}
	}
	
	public TreeNode<T> searchElementNonRec(T dataToSearch){
		return searchElementNonRec(root, dataToSearch);
	}
	
	private TreeNode<T> searchElementNonRec(TreeNode<T> newRoot, T dataToSearch){
		TreeNode<T> currentNode = newRoot;
		while (currentNode != null) {
			if (dataToSearch == currentNode.getData())
				return currentNode;;
			if (dataToSearch.compareTo(newRoot.getData()) <= 0)
				currentNode = currentNode.getLeftChild();
			else
				currentNode = currentNode.getRightChild();
		}	
		return null;
	}
	
	public void addElement(T dataToAdd) {
		if (root == null)
			root = new TreeNode<>(dataToAdd);
		else
			addElement(root, dataToAdd);
	}
	private void addElement(TreeNode<T> newRoot, T dataToAdd) {
		if (dataToAdd.compareTo(newRoot.getData()) == 0) {
			System.out.println("Entry already exist. Ignore");
			return;
		}
		
		if (dataToAdd.compareTo(newRoot.getData()) < 0) {
			if (newRoot.getLeftChild() == null) {
				TreeNode<T> nodeToAdd = new TreeNode<>(dataToAdd);
				newRoot.setLeftChild(nodeToAdd);
			}
			else
				addElement(newRoot.getLeftChild(), dataToAdd);
		} 
		
		else {
			if (newRoot.getRightChild() == null) {
				TreeNode<T> nodeToAdd = new TreeNode<>(dataToAdd);
				newRoot.setRightChild(nodeToAdd);
			}
			else
				addElement(newRoot.getRightChild(), dataToAdd);		
		}		
	}
	
	public void addNonRec(T dataToAdd) {
		if (root == null)
			root = new TreeNode<>(dataToAdd);
		else
			addNonRec(root, dataToAdd);
	}
	
	private void addNonRec(TreeNode<T> newRoot, T dataToAdd) {
		TreeNode<T> nodeToAdd = new TreeNode<>(dataToAdd);
		TreeNode<T> currentNode = newRoot;
		TreeNode<T> parent = null;
		
		while (currentNode != null) {
			parent = currentNode;
			if (dataToAdd.compareTo(currentNode.getData()) <= 0 ) {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}
		}
		//after while loop, currentNode is null, parent node now decide to add to left of right
		if (parent == null)
			root = nodeToAdd;
		else {
			if (dataToAdd.compareTo(parent.getData()) <= 0 )
				parent.setLeftChild(nodeToAdd);
			else
				parent.setRightChild(nodeToAdd);
		}
	}
	
	public T removeNonRec(T dataToRemove) {
		return removeNonRec(root, dataToRemove);
	}
	
	private T removeNonRec(TreeNode<T> startRoot, T dataToRemove) {
		TreeNode<T> nodeToRemove = startRoot;
		TreeNode<T> parent = null;
		
		while (nodeToRemove != null && (dataToRemove != nodeToRemove.getData())) {
			parent = nodeToRemove;
			if (dataToRemove.compareTo(nodeToRemove.getData()) <= 0)
				nodeToRemove = nodeToRemove.getLeftChild();
			else
				nodeToRemove = nodeToRemove.getRightChild();
		}
		
		if (nodeToRemove != null) {
			if (nodeToRemove.isLeaf()) {
				if (dataToRemove.compareTo(parent.getData()) <= 0)
					parent.setLeftChild(null);
				else
					parent.setRightChild(null);
			}
			
			if ((nodeToRemove.hasLeft() && !nodeToRemove.hasRight())) {
				if (parent.getLeftChild() == nodeToRemove){					
					parent.setLeftChild(nodeToRemove.getLeftChild());				
				}
				else if (parent.getRightChild() == nodeToRemove) {
					parent.setRightChild(nodeToRemove.getLeftChild());				
				}
			}
			
			if (nodeToRemove.hasRight() && !nodeToRemove.hasLeft()) {
				if (parent.getLeftChild() == nodeToRemove)
					parent.setLeftChild(nodeToRemove.getRightChild());
				else
					parent.setRightChild(nodeToRemove.getRightChild());
			}
			
			if (nodeToRemove.hasLeft() && nodeToRemove.hasRight()) {
				TreeNode<T> nodeToSwap = nodeToRemove.getLeftChild().getRightMostNode();
					nodeToRemove.setData(nodeToSwap.getData());
					nodeToRemove.setLeftChild((nodeToRemove.getLeftChild()).removeRightmost());
			}	
		}	
		return nodeToRemove.getData();
	}
	
	public T remove(T dataToRemove) {
		ReturnObj oldItem = new ReturnObj(null);
		TreeNode<T> newRoot = remove(getRootNode(), dataToRemove, oldItem);
		setRootNode(newRoot);
		if (oldItem.get() == null){
			System.out.println(dataToRemove + " does not exist");
			return dataToRemove;
		}
		else
			return oldItem.get();
	}
	
	private TreeNode<T> remove(TreeNode<T> rootNode,
			T dataToRemove, ReturnObj oldItem){
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int compare = dataToRemove.compareTo(rootData);
			
			if (compare == 0){
				oldItem.set(rootData);
				rootNode = removeFromRoot(rootNode);
			}
			
			if (compare < 0){
				TreeNode<T> leftChild = rootNode.getLeftChild();
				TreeNode<T> subtreeRoot = remove(leftChild, dataToRemove, oldItem);
				rootNode.setLeftChild(subtreeRoot);
			}
			
			else {
				TreeNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(remove(rightChild, dataToRemove, oldItem));
			}
		}	
		return rootNode;
	}
	
	private TreeNode<T> removeFromRoot(TreeNode<T> rootNode) {
		if (rootNode.hasLeft() && rootNode.hasRight()){
			TreeNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			TreeNode<T> largestNode = findLargest(leftSubtreeRoot);
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		}
		
		else if (rootNode.hasRight())
			rootNode = rootNode.getRightChild();
		else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	}
	
	private TreeNode<T> findLargest (TreeNode<T> rootNode) {
		if (rootNode.hasRight())
			rootNode = findLargest(rootNode.getRightChild());
		return rootNode;
	}
	
	private TreeNode<T> removeLargest(TreeNode<T> rootNode) {
		if (rootNode.hasRight()) {
			TreeNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);		
		}
			
		else
			rootNode = rootNode.getLeftChild();
		
		return rootNode;
	}
	private TreeNode<T> getRootNode() {
		return root;
	}
	
	private void setRootNode(TreeNode<T> rootNode) {
		root = rootNode;
	}
	
	private class ReturnObj {
		private T item;
		private ReturnObj(T data){
			item = data;
		}
		
		private T get() {
			return item;
		}
		private void set(T data) {
			item = data;
		}
		
	}
	
	private TreeNode<T> findNode(TreeNode<T> root, T data) {
		if (root.getData() == data)
			return root;
		else if (root.getData().compareTo(data) < 0)
			return findNode(root.getRightChild(), data);
		else
			return findNode(root.getLeftChild(), data);
	}
	
	public TreeNode<T>  predecessor(T data){
		TreeNode<T> node = findNode(root, data);
		 return predecessor(root, node );
	}
	
	private TreeNode<T>  predecessor(TreeNode<T> rootNode, TreeNode<T> node) {

		if (node.getLeftChild() != null)
			return max(node.getLeftChild());
	
		TreeNode<T> predecessor = null;
		
		while (rootNode != null) {
		
			if (node.getData() == rootNode.getData()) {
				// by now we might found our predecessor
				break;
			} else if (node.getData().compareTo(rootNode.getData()) < 0) {
				rootNode = rootNode.getLeftChild();
			} else if (node.getData().compareTo(rootNode.getData()) > 0) {
				predecessor = rootNode;
				rootNode = rootNode.getRightChild();
			}
		}
		if (predecessor == null) 
			throw new RuntimeException("No Such Entry");
		return predecessor;
		
		
	
	}
	
	private TreeNode<T> max(TreeNode<T> root) {
		// we found the max node
		if (root.getRightChild()== null) {
			return root;
		}
		return max(root.getRightChild());
	}
	
	public TreeNode<T> successor(T data){
		TreeNode<T> givenNode = new TreeNode<>(data);
		 return  successor(root, givenNode );
	}
	
	private TreeNode<T> successor(TreeNode<T> rootNode, TreeNode<T> givenNode) {
		if(rootNode == null)
	        return null;
	 
	    TreeNode<T> nodeBefore = null;
	    TreeNode<T> returnNode = rootNode;
	    while(returnNode != null && returnNode.getData() != givenNode.getData()){
	        if(returnNode.getData().compareTo(givenNode.getData()) > 0){
	        	nodeBefore = returnNode;
	            returnNode = returnNode.getLeftChild();
	        }else{
	        	returnNode = returnNode.getRightChild();
	        }
	    }
	 
	    if(returnNode == null)        
	        throw new RuntimeException("No such Entry");
	 
	    if(returnNode.getRightChild() == null)
	        return nodeBefore;
	 
	    returnNode = returnNode.getRightChild();
	    while(returnNode.getLeftChild() != null)
	    	returnNode = returnNode.getLeftChild();
	 
	    return returnNode;
	}
	
}










