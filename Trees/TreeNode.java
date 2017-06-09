package treeNode;

import java.util.Stack;

public class TreeNode<T> {
	
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	
	public TreeNode() {
		this(null);
	}
	
	public TreeNode(T newData) {
		this(newData, null, null);
	}
	
	public TreeNode(T newData, TreeNode<T> left, TreeNode<T> right) {
		data = newData;
		leftChild = left;
		rightChild = right;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T newData) {
		data = newData;
	}
	
	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	
	public TreeNode<T> getRightChild() {
		return rightChild;
	}
	
	public void setLeftChild(TreeNode<T> leftNode) {
		leftChild = leftNode;
	}
	
	public void setRightChild(TreeNode<T> rightNode) {
		rightChild = rightNode;
	}
	
	public boolean hasLeftChild() {
		return leftChild != null;
	}
	
	public boolean hasRightChild() {
		return rightChild != null;
	}
	
	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}
	
	public int getHeight() {
		return getHeight(this);
	}
	
	private int getHeight(TreeNode<T> node) {
		int height = 0;
		if (node != null)
		height = 1 + Math.max(getHeight(node.getLeftChild()),
				getHeight(node.getRightChild()));
		return height;	
	}
	
	public int getNumberOfNodes() {
		int leftNodes = 0;
		int rightNodes = 0;
		if (leftChild != null)
			leftNodes = leftChild.getNumberOfNodes();
		if (rightChild != null)
			rightNodes = rightChild.getNumberOfNodes();
		return 1 + leftNodes + rightNodes;
	}
	
	public TreeNode<T> copy() {
		TreeNode<T> newRoot = new TreeNode<>();
		if (leftChild != null)
			newRoot.setLeftChild(leftChild.copy());
		if (rightChild != null)
			newRoot.setRightChild(rightChild.copy());
		return newRoot;
	}
	
	public TreeNode<T> getLeftMostNode() {
		if (leftChild == null)
			return this;
		else
			return leftChild.getLeftMostNode();
	}
	
	public TreeNode<T> getRightMostNode() {
		if (rightChild == null)
			return this;
		else
			return rightChild.getRightMostNode();
	}
	
	public TreeNode<T> removeRightmost(){
		if (rightChild == null){
			return leftChild;
		}
		else {
			rightChild = rightChild.removeRightmost();
			return this;
		}
	}
	
	public TreeNode<T> removeLeftmost() {
		if (leftChild == null){
			return rightChild;
		}
		else {
			leftChild = leftChild.removeLeftmost();
			return this;
		}
	}
	
	public void preorderTraverse() {
		preorderTraverse(this);
	}
	private void preorderTraverse(TreeNode<T> node) {
		if (node != null ) {
			System.out.print(node.getData() + " ");
			preorderTraverse(node.getLeftChild());
			preorderTraverse(node.getRightChild());
		}	
	}
	
	public void inorderTraver() {
		inorderTraverse(this);
	}
	private void inorderTraverse(TreeNode<T> node) {
		if (node != null) {
			inorderTraverse(node.getLeftChild());
			System.out.print(node.getData() + " ");
			inorderTraverse(node.getRightChild());
		}
	}
	
	public void desc() {
		desc(this);
	}
	private void desc(TreeNode<T> node) {
		if (node != null) {
			desc(node.getRightChild());
			
			System.out.print(node.getData() + " ");
			desc(node.getLeftChild());
		}
	}
	
/*	public void inorderTraverseNonRec(){
		inorderTraverseNonRec(this);
	}
	
	private void inorderTraverseNonRec(TreeNode<T> rootNode) {
		if (rootNode == null)
			return;
		
		Stack<TreeNode<T>> stack = new Stack<>();
		TreeNode<T> currentNode = rootNode;
		
		while (currentNode != null) {
			stack.push(currentNode);
			currentNode = currentNode.getLeftChild();
		}
		
		while (stack.size() > 0) {
			currentNode = stack.pop();
			System.out.print(currentNode.getData() + " ");
			
			if (currentNode.getRightChild() != null){
				 currentNode = currentNode.getRightChild();
				
				 while (currentNode != null) {
	                 stack.push(currentNode);
	                 currentNode = currentNode.getLeftChild();
				 }
			}
		}
			
		
	}
	*/
	
	public void postorderTraverse() {
		postorderTraverse(this);
	}
	private void postorderTraverse(TreeNode<T> node) {
		if (node != null) {
			postorderTraverse(node.getLeftChild());
			postorderTraverse(node.getRightChild());
			System.out.print(node.getData() + " ");
		}
	}
	
	public boolean hasRight() {
		return rightChild != null;
	}
	
	public boolean hasLeft() {
		return leftChild != null;
	}
	
	

}






