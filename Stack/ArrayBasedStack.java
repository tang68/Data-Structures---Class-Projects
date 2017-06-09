package stack;

import java.util.EmptyStackException;

public final class ArrayBasedStack<T> implements StackInterface<T>

{
	private T[] arr;
	private final static int DEFAULT_CAPACITY = 10;
	private final static int MAX_CAPACITY = 10;
	private int topIndex;
	private boolean initialized = false;
	
	
	public ArrayBasedStack()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayBasedStack(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new SecurityException("size exceed max capacity");
		else
		{	
			@SuppressWarnings("unchecked")
			T[] tempStack = (T[]) new Object[capacity];
			arr = tempStack;
			topIndex = -1;
			initialized = true;
		}	
	}
	 /** Adds a new entry to the top of this stack.
    @param newEntry  An object to be added to the stack. */
	public void push(T newEntry)
	{
		checkInnitialization();
		arr[topIndex +1] = newEntry;
		topIndex++;
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
		{
			T top = arr[topIndex];
			arr[topIndex] = null;
			topIndex--;
			return top;
		}
		
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
			return arr[topIndex];
		
	}
	/** Detects whether this stack is empty.
    @return  True if the stack is empty. */
	public boolean isEmpty()
	{
		return topIndex < 0;
	}
	/** Removes all entries from this stack. */
	public void clear()
	{
		topIndex = -1;
	}
	private void checkInnitialization()
	{
		if (!initialized)
			throw new SecurityException("Array Stack is not initialized properly");
	}

}














