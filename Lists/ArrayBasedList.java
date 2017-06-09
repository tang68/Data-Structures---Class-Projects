package sortedList;

/** ADT List implemented using array with a fixed size of 10
 * 
 * @author Ryan Phat Tang
 *
 */
public class ArrayBasedList<T> implements ListInterface<T> {

	private T[] list; 
	private int numberOfEntries;
	private final static int MAX = 10;
	public ArrayBasedList(){		
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Object[MAX+1]; 
		list = tmp;
		numberOfEntries = 0;
	}
	/** Adds a new entry to the end of this list.
	   Entries currently in the list are unaffected.
	   The list's size is increased by 1.
	   @param newEntry  The object to be added as a new entry. */	
	public void add(T newEntry){
		list[numberOfEntries + 1] = newEntry;
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
		if (newPosition >= 1 && newPosition <= numberOfEntries + 1) {
			if (newPosition <= numberOfEntries)
				makeRoom(newPosition);
			list[newPosition] = newEntry;
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
		if (givenPosition >= 1 && givenPosition <= numberOfEntries )
		{
			T itemToRemove = list[givenPosition];
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return itemToRemove;
		}
		else
			throw new IndexOutOfBoundsException("Illegal Position");
		
	}

	/** Removes all entries from this list. */
	public void clear(){
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
		if ( givenPosition >= 1 && givenPosition <= numberOfEntries)
		{
			T itemToReplace = list[givenPosition];
			list[givenPosition] = newEntry;	
			return itemToReplace;
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
		if (givenPosition < 1 || givenPosition > 10)
			throw new IndexOutOfBoundsException("Illegal Position");
		else
			return list[givenPosition];
	}

	/** Retrieves all entries that are in this list in the order in which
	   they occur in the list.
	   @return  A newly allocated array of all the entries in the list.
	            If the list is empty, the returned array is empty. */
	public T[] toArray(){
		@SuppressWarnings("unchecked")
		T[] a = (T[]) new Object[numberOfEntries];
		for (int i = 0; i < numberOfEntries; i++)
			a[i] = list[i+1];
		return a;
	}

	/** Sees whether this list contains a given entry.
	   @param anEntry  The object that is the desired entry.
	   @return  True if the list contains anEntry, or false if not. */
	public boolean contains(T anEntry){
		boolean found = false;
		int i = 1;
		while (!found || i <= numberOfEntries){
			if (anEntry.equals(list[i]))
				found = true;
			i++;
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
	
	private void makeRoom(int pos){
		int newIndex = pos;
		int last = numberOfEntries;
		for (int i = last; i >= newIndex; i-- )
			list[i + 1] = list[i];
	}
	
	private void removeGap(int pos){
		int indexToRemove = pos;
		int last = numberOfEntries;
		for (int i = indexToRemove; i < last; i++)
			list[i] = list[i + 1];
		
	}
}














