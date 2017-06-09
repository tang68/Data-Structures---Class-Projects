package stack;

import java.util.EmptyStackException;
import java.util.Vector;

public class VectorStack<T> implements StackInterface<T>
{
	private final static int DEFAULT_CAPACITY = 10;
	private final static int MAX_CAPACITY = 10000;
	private Vector<T> vecStack;
	private boolean initialized = false;
	
	public VectorStack()
	{
		this(DEFAULT_CAPACITY);
	}	
	public VectorStack(int capacity) 
	{
		if (capacity > MAX_CAPACITY)
			throw new SecurityException("size exceed max capacity");
		else
		{
			vecStack = new Vector<>(capacity);
			initialized = true;
		}
	}
	/** Adds a new entry to the top of this stack.
    @param newEntry  An object to be added to the stack. */
	public void push(T newEntry)
	{
		checkInnitialization();
		vecStack.add(newEntry);
	}
	/** Removes and returns this stack's top entry.
    @return  The object at the top of the stack. 
    @throws  EmptyStackException if the stack is empty before the operation. */
	public T pop()
	{
		checkInnitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else
			return vecStack.remove(vecStack.size() - 1);
	}
	/** Retrieves this stack's top entry.
    @return  The object at the top of the stack.
    @throws  EmptyStackException if the stack is empty. */
	public T peek()
	{
		checkInnitialization();
		if (isEmpty())
			throw new EmptyStackException();
		
		else 
			return vecStack.lastElement();
	}
	/** Detects whether this stack is empty.
    @return  True if the stack is empty. */
	public boolean isEmpty()
	{
		return vecStack.isEmpty();
	}
	/** Removes all entries from this stack. */
	public void clear()
	{
		vecStack.clear();
	}
	private void checkInnitialization()
	{
		if (!initialized)
			throw new SecurityException("Array Stack is not initialized properly");
	}
}
