package queue;

public class EmptyQueueException extends RuntimeException
{
	public EmptyQueueException()
	{
		this(null);
	}
	public EmptyQueueException(String message) 
	{
        super("Queue is Empty");
    }
	
}
