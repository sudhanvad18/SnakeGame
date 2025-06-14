import java.util.NoSuchElementException;

import javax.lang.model.util.ElementScanner14;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	Sudhanva Deshpande
 *	@since	May 1, 2023
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() 
	{
		head = null;
		tail = null;
	}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) 
	{
		SinglyLinkedList<E> newList = new SinglyLinkedList<E>();
		for(int i = 0; i < oldList.size(); i++)
		{
			newList.add(oldList.get(i).getValue());
		}
	}
	
	/**	Clears the list of elements */
	public void clear() 
	{
		ListNode<E> cur = head;
		while(cur!= null && cur.getNext() != null)
		{
			ListNode<E> next = cur.getNext();
			ListNode<E> twoNext = next.getNext();

			cur.setNext(twoNext);
		}

		head = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) 
	{
		if(head == null)
			head = new ListNode<E>(obj);
		else
		{
			if(head.getNext() == null)
				head.setNext(new ListNode<E>(obj));
			else
			{
				ListNode<E> tmp = head;

				while(tmp.getNext() != null)
				{
					tmp = tmp.getNext();
					if(tmp.getNext() == null)
					{
						tmp.setNext(new ListNode<E>(obj));
						return true;
					}
				}
			}
		}

		return false;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) 
	{
		boolean isFirst = false;
		if(head == null && index == 0)
		{
			System.out.println(index);
			head = new ListNode<E>(obj);
			isFirst = true;
		}
		
		else if(index < size())
		{
			ListNode<E> prev = head;
			ListNode<E> cur = prev.getNext();
			int count = 0;

			while(count < index-1 && prev!=null)
			{
				prev = cur;
				cur = cur.getNext();
				count++;
			}

			if(index == 0 && size() == 1)
			{
				ListNode<E> tmp = new ListNode<E>(head.getValue());
				head.setValue(obj);
				head.setNext(tmp);
				System.out.println("size 1; index: "+index);
				return true;
			}

			else if(count == index-1 && prev!=null)
			{
				ListNode<E> tmp = new ListNode<E>(obj);
				prev.setNext(tmp);
				tmp.setNext(cur);
				System.out.println("size any; index: "+index);
				return true;
			}

			else 
				return false;
			
		}

		if(!isFirst)
			throw new NoSuchElementException("no such element at index: "+index);
		else
		{
			System.out.println("ret; index: "+index);
			return true;
		}
	}
	
	/**	@return the number of elements in this list */
	public int size() 
	{
		int count = 0;
		ListNode<E> tmp = head;

		while(tmp!= null && tmp.getNext() != null)
		{
			count++;
			tmp = tmp.getNext();
		}

		if(tmp!=null)
			count++;

		return count;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) 
	{
		int count = 0;
		ListNode<E> prev = head;
		ListNode<E> cur = prev.getNext();

		while(prev!=null && count <= index)
		{
			if(count != index && cur!=null)
			{
				prev = cur;
				cur = cur.getNext();
				count++;
			}

			else if(count != index)
			{
				prev = null;
				count++;
			}

			else if(prev!=null)
				return prev;
		}

		throw new NoSuchElementException("no such element at index: "+index);
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) 
	{
		int count = 0;
		ListNode<E> prev = head;
		ListNode<E> cur = prev.getNext();

		while(prev!=null && count<=index)
		{
			if(count!=index && cur!=null)
			{
				prev = cur;
				cur = cur.getNext();
				count++;
			}

			else if(count != index)
			{
				prev = cur;
				count++;
			}

			else if(prev!=null)
			{
				ListNode<E> tmp = new ListNode<E>(prev.getValue());
				prev.setValue(obj);
				return tmp.getValue();
			}
		}
		throw new NoSuchElementException("index: "+index+" is not accessible");
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) 
	{
		int count = 0;
		ListNode<E> prev = head;
		ListNode<E> cur = prev.getNext();

		while(count < index-1 && index < size())
		{
			if(cur!=null)
			{
				prev = cur; 
				cur = cur.getNext();
				count++;
			}
		}

		if(index == 0 && size() == 1)
		{
			ListNode<E> tmp = new ListNode<E>(head.getValue());
			head = head.getNext();
			return tmp.getValue();
		}

		else if(prev!=null && cur!=null && index < size())
		{
			ListNode<E> tmp = new ListNode<E>(cur.getValue());
			prev.setNext(cur.getNext());
			return tmp.getValue();
		}

		throw new NoSuchElementException("no such element at index: "+index);
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() 
	{
		if(head == null)
			return true;
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) 
	{
		ListNode<E> prev = head;
		ListNode<E> cur = prev.getNext();

		while(prev!=null && cur!=null && cur.getNext()!=null)
		{
			if(prev.getValue().equals(object) || cur.getValue().equals(object))
				return true;
			else
			{
				prev = cur;
				cur = cur.getNext();
			}
		}

		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) 
	{
		int index = 0;
		ListNode<E> prev = head;
		ListNode<E> cur = prev.getNext();

		while(prev!=null && cur!=null && cur.getNext()!=null)
		{
			if(prev.getValue().equals(element))
				return index;
			else
			{
				prev = cur;
				cur = cur.getNext();
				index++;
			}
		}

		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}