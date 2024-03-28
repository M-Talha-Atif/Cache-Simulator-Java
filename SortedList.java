// TO DO: add your implementation and JavaDocs.
import java.util.Iterator;

//sorted list extended from BasicList
public class SortedList<T extends Comparable<T>> extends BasicList<T> { 

	// add a value into current list;
	// make sure all values are sorted in an ascending order from start to end of the list

	// - you can assume before add() is called, all existing values are sorted	
	// - if there is a tie, insert the new value as the last (i.e. closest to 
	//   the end of the list) among all values that are tied.
	
	// - if value is null, throw an IllegalArgumentException (with any error msg)
	
	//O(n) where n is the number of items in list
	/**
	 * Adds a value into the sorted list, ensuring that all values are sorted in ascending order.
	 * If there is a tie, the new value is inserted as the last among all tied values.
	 *
	 * @param value The value to be added.
	 * @throws IllegalArgumentException if the value is null.
	 * @complexity O(n) where n is the number of items in the list.
	 */
	
	public void add(T value){
		if (value == null) {
			throw new IllegalArgumentException("Null value not allowed");
		}

		Node<T> newNode = new Node<>(value);

		// If the list is empty
		if (head == null) {
			head = newNode;
			return;
		}

		// If the new value is smaller than or equal to the first value
		if (value.compareTo(head.getData()) <= 0) {
			newNode.setNext(head);
			head = newNode;
			return;
		}

		// Traverse the list to find the appropriate position to insert the new value
		Node<T> current = head;
		Node<T> prev = null;
		while (current != null && value.compareTo(current.getData()) != 0) {

			prev = current;
			current = current.getNext();
		}
		if(current == null){
			newNode.setNext(current);
			last = newNode;
			if(prev!=null)
		    	prev.setNext(last);
			return;
		}
		newNode.setNext(current.getNext());
		current.setNext(newNode);

	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******        Remember to add JavaDoc         *******
	//******************************************************
	/**
	 * This is the main method as the entrance of Sortedlist.
	 * @param args command line args
	 */

	public static void main(String[] args) {
		SortedList<String> names = new SortedList<>();
		
		names.add("Mason");
		//System.out.println(names.listToString());
		names.add("George");
		//System.out.println(names.listToString());
		names.add("Washington");
		
		if (names.size()==3 && names.getFirst().equals("George") &&
			names.listToString().equals("George Mason Washington")){		
			System.out.println("Yay1");	
		}

		//System.out.println(names.listToString());
		/**
		 * Class representing SomeType objects, implementing the Comparable interface.
		 */
		class SomeType implements Comparable<SomeType> {
			private String value;
			/**
			 * Constructs a SomeType object with the specified value.
			 *
			 * @param value the value of the SomeType object
			 */
			public SomeType(String value) { this.value = value; }
			/**
			 * Compares this SomeType object with another SomeType object based on their lengths.
			 *
			 * @param other the SomeType object to compare with
			 * @return the difference in lengths between this object and the other object
			 */
			public int compareTo(SomeType other) {
				//use the length to compare
				return this.value.length() - other.value.length();
			}
			/**
			 * Returns the string representation of the SomeType object.
			 *
			 * @return the string representation of the value
			 */
			
			public String toString(){ return value;}
		}	
		
		SomeType item1 = new SomeType("123");
		SomeType item2 = new SomeType("1234");
		SomeType item3 = new SomeType("12345");
		SomeType item4 = new SomeType("7890");
		
		
		SortedList<SomeType> items = new SortedList<>();
		items.add(item2);
		items.add(item1);
		items.add(item3);
		
		boolean ok = items.listToString().equals("123 1234 12345");
		//System.out.println(items.listToString());
		items.add(item4);

		if ( ok && items.listToString().equals("123 1234 7890 12345")){
			System.out.println("Yay2");				
		}
		
	}	
}
