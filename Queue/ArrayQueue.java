package queue;
public class ArrayQueue<T> implements QueueInterface<T>
{
	private T[] q;
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 50;
	
	public ArrayQueue()
	{
		this(DEFAULT_CAPACITY);
	}
	public ArrayQueue(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new SecurityException("size exceed max capacity");
		else
		{
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[capacity +1];
			q = tempQueue;
			frontIndex = 0;
			backIndex = capacity;
			initialized = true;
		}
	}
	/** Adds a new entry to the back of this queue.
   	@param newEntry  An object to be added. */
	public void enqueue(T newEntry)
	{
		checkInnitialization();
		backIndex = (backIndex + 1) % q.length;
		q[backIndex] = newEntry;
	}
	/** Removes and returns the entry at the front of this queue.
    @return  The object at the front of the queue. 
    @throws  EmptyQueueException if the queue is empty before the operation. */
	public T dequeue()
	{
		T front = q[frontIndex];
		q[frontIndex] = null;
		frontIndex = (frontIndex + 1) % q.length;
		return front;
	}
	/**  Retrieves the entry at the front of this queue.
    @return  The object at the front of the queue.*/
	public T getFront()
	{
		checkInnitialization();
		if (isEmpty())
			throw new EmptyQueueException();
		else
			return q[frontIndex];
	}
	/** Detects whether this queue is empty.
    @return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty()
	{
		return frontIndex == ((backIndex + 1) % q.length);
	}
	/** Removes all entries from this queue. */
	public void clear()
	{
		frontIndex = 0;
		backIndex = q.length - 1;
	}
	private void checkInnitialization()
	{
		if (!initialized)
			throw new SecurityException("Array Stack is not initialized properly");
	}
}
