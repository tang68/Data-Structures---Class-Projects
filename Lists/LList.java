package sortedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class LList<T> implements ListInterface<T> {
	private Node firstNode;
	private int numberOfEntries;
	public LList() {
		firstNode = null;
		numberOfEntries = 0;
	}
	/** Adds a new entry to the end of this list.
	   Entries currently in the list are unaffected.
	   The list's size is increased by 1.
	   @param newEntry  The object to be added as a new entry. */
	public void add(T newEntry){
		Node newNode = new Node(newEntry);
		if (isEmpty())
			firstNode = newNode;
		else 
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);
		}
		numberOfEntries++;
	}

	/** Adds a new entry at a specified position within this list.
	   Entries originally at and above the specified position
	   are at the next higher position within the list.
	   The list's size is increased by 1.
	   @param newPosition  An integer that specifies the desired
	                       position of the new entry.
	   @param newEntry     The object to be added as a new entry.
	   @throws  IndexOutOfBoundsException if either
	            newPosition < 1 or newPosition > getLength() + 1. */
	public void add(int newPosition, T newEntry){
		if ( (newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			Node newNode = new Node(newEntry);
			if (newPosition == 1){
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else {
			Node nodeBefore = getNodeAt(newPosition - 1);
			Node nodeAfter =  nodeBefore.getNextNode();
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode(newNode);			
			}
			numberOfEntries++;
		}
		else
			throw new IndexOutOfBoundsException("Illegal Position");
		
	}

	/** Removes the entry at a given position from this list.
	   Entries originally at positions higher than the given
	   position are at the next lower position within the list,
	   and the list's size is decreased by 1.
	   @param givenPosition  An integer that indicates the position of
	                         the entry to be removed.
	   @return  A reference to the removed entry.
	   @throws  IndexOutOfBoundsException if either 
	            givenPosition < 1 or givenPosition > getLength(). */
	public T remove(int givenPosition){
		if ( givenPosition >= 1 && givenPosition <= numberOfEntries ) {
			T result = null;
			if (givenPosition == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
			}
			numberOfEntries--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal Position");
		
	}

	/** Removes all entries from this list. */
	public final void clear(){
		firstNode = null;
		numberOfEntries = 0;
	}

	/** Replaces the entry at a given position in this list.
	   @param givenPosition  An integer that indicates the position of
	                         the entry to be replaced.
	   @param newEntry  The object that will replace the entry at the
	                    position givenPosition.
	   @return  The original entry that was replaced.
	   @throws  IndexOutOfBoundsException if either
	            givenPosition < 1 or givenPosition > getLength(). */
	public T replace(int givenPosition, T newEntry){
		if ( givenPosition >= 1 && givenPosition <= numberOfEntries) {
			T result = null;
			Node nodeToReplace = getNodeAt(givenPosition);
			result = nodeToReplace.getData();
			nodeToReplace.setData(newEntry);
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal Position");
		
	}

	/** Retrieves the entry at a given position in this list.
	   @param givenPosition  An integer that indicates the position of
	                         the desired entry.
	   @return  A reference to the indicated entry.
	   @throws  IndexOutOfBoundsException if either
	            givenPosition < 1 or givenPosition > getLength(). */
	public T getEntry(int givenPosition){
		if ( givenPosition >= 1 && givenPosition <= numberOfEntries) {
			Node result = getNodeAt(givenPosition);
			return result.getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal Position");
	}

	/** Retrieves all entries that are in this list in the order in which
	   they occur in the list.
	   @return  A newly allocated array of all the entries in the list.
	            If the list is empty, the returned array is empty. */
	public T[] toArray(){
		@SuppressWarnings("unchecked")
		T[] a = (T[]) new Object[numberOfEntries];
		Node currentNode = firstNode;
		for (int i = 0; (i < numberOfEntries) && (currentNode != null); i++) {
			a[i] = currentNode.getData();
			currentNode = currentNode.getNextNode();
		}
		return a;
	}

	/** Sees whether this list contains a given entry.
	   @param anEntry  The object that is the desired entry.
	   @return  True if the list contains anEntry, or false if not. */
	public boolean contains(T anEntry){
		Node currentNode = firstNode; 
		boolean found = false;
		while (!found && currentNode != null) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();	
		}
		return found;
	}

	/** Gets the length of this list.
	   @return  The integer number of entries currently in the list. */
	public int getLength(){
		return numberOfEntries;
	}
	   
	/** Sees whether this list is empty.
	   @return  True if the list is empty, or false if not. */
	public boolean isEmpty(){
		return numberOfEntries == 0;
	}
	private Node getNodeAt(int pos) {
		Node currentNode = firstNode;
		for (int i = 1; i < pos; i++)
			currentNode = currentNode.getNextNode();
		return currentNode;
	}
	private class Node
	{
		private T data;
		private Node next;
		private Node (T dataPortion)
		{
			this(dataPortion, null);
		}
		private Node(T dataPortion, Node nextNode) 
		{
			data = dataPortion;
			next = nextNode;
		}
		private T getData()
		{
			return data;
		}
		private void setData(T newData)
		{
			data = newData;
		}
		private Node getNextNode()
		{
			return next;
		}
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}
		
	}

	/* public Iterator<T> iterator()
	  	{
	  		return new IteratorForLinkedList();
	  	} 
	  public Iterator<T> getIterator() {
			return iterator();
		}
	  private class IteratorForLinkedList implements Iterator<T>
		{
			private Node nextNode;
			private IteratorForLinkedList()
			{
				nextNode = firstNode;
			}

			public T next() 
			{
				if (hasNext())
				{
					Node returnNode = nextNode;
					nextNode = nextNode.getNextNode();
					return returnNode.getData();
				}
				else
					throw new NoSuchElementException("Illegal call to next()" + 
								"Iterator is at the end of the list");
			}
			
			public boolean hasNext()
			{
				return nextNode != null;
			}

		}*/
}













