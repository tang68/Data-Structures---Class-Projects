package treeNode;

public class EmptyTreeException extends RuntimeException {
	public EmptyTreeException()
	{
		this(null);
	}
	public EmptyTreeException(String message) 
	{
        super("Tree is Empty");
    }
}
