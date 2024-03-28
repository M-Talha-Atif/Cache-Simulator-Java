// TO DO: add your implementation and JavaDocs.

//Reminder: no array allowed.

import java.util.Iterator;

// singly linked list, no dummy nodes
/**
 * Represents a basic linked list data structure.
 * @param <T> The type of elements stored in the list.
 */
public class BasicList<T> implements Iterable<T> { 

	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******         Remember to add JavaDoc        *******
	//******************************************************
	
	// start of the list; do NOT change the name/type of this
	/**
	 * Head of linked list.
	 */
	protected Node<T> head = null;
	/**
	 * Last node of linked list.
	 */
	protected Node<T>  last = null;
	/**
	 * Returns an iterator that traverses from the beginning to the end of the list.
	 * @return an iterator that traverses the elements of the list.
	 */

	public Iterator<T> iterator() {
		//Return an iterator that traverses from
		//the beginning to the end of the list.
		//This provided code would work if you have set up the list correctly.
		
		return new Iterator<T>() {
			Node<T> current = head;
			/**
			 * Checks if there is a next element in the list.
			 * @return true if there is a next element, otherwise false.
			 */
			public boolean hasNext(){
				return current!=null;
			}
				
			//next() would throw a NullPointerException
			//if you try to use next when there are no more items
			/**
			 * Retrieves the next element in the list.
			 * @return the next element in the list
			 */
			public T next(){
				T toReturn = current.getData();
				current = current.getNext();
				return toReturn;
			}
		};
	}
	//******************************************************
	//*******    	END of PROVIDED Code 	 		 *******
	//*******    	Do NOT Change PROVIDED Code 	 *******
	//******************************************************


	// -define additional instance variables as needed, only private or protected
	// -you can define additional helper methods but they must be private
	
	// initialize the list to be an empty list

	/**
	 * Make an empty linked list.
	 */
	public BasicList(){

        head = null;
		last = null;
	}
	
	// report number of items
	// O(1)

	/**
	 * Returns the number of items in the linked list.
	 * @return The number of items in the list.
	 * @complexity O(n) where n is the number of items in the list.
	 */
	public int size(){
		int count = 0;
		Node<T> current = head;
		while (current != null) {
			count++;
			current = current.getNext();
		}
	
		//default return; update or change as needed
		return count;
	}
	
	// return the first value from the beginning of the list
	// do not remove the value!
	// return null if list is empty
	// O(1)
	/**
	 * Returns the head of linked list.
	 * @return The head member.
	 * @complexity O(1)
	 */
	public T getFirst() {
		//default return; update or change as needed
		return head.getData();
	
	}

	// insert a new node with value at the beginning of the list
	// null value not allowed: 
	//    throw IllegalArgumentException (with any error msg) if value is null
	// O(1)
	/**
	 * Insert a new node with value at the beginning of the list.
	 * @param value The value to be inserted at the end of the list.
	 * @return void.
	 * @complexity O(1)
	 */
	public void addFirst(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Null value not allowed");
		}
		Node<T> newNode = new Node<>(value);
		newNode.setNext(head);
		head = newNode;
		// If the list was empty, update last to point to the new node
		if (last == null) {
			last = newNode;
		}

	}
	
	// remove and return the first value from the beginning of the list
	//return null if list is empty
	// O(1)
	/**
	 * Remove a new node from the beginning of the list.
	 * @return removed node.
	 * @complexity O(1)
	 */
	public T removeFirst(){

		if (head == null) {
			return null;
		}
		T removedData = head.getData();
		head = head.getNext();
		if (head == null) {
			last = null;
		}
		return removedData;
		//default return; update or change as needed
	
	}

	// return the last value from the end of the list
	// do not remove the value!
	// return null if list is empty
	// O(1)
	/**
	 * Get a new node with value at the end of the list.
	 * @return Node of generic type T.
	 * @complexity O(1)
	 */

	public T getLast() {
		if (head == null) {
			return null;
		}
		//default return; update or change as needed
		return last.getData();


	}

	// append a new value at the end of the list
	// null value not allowed:  see addFirst() above
	// O(1)
	/**
	 * Insert a new node with a value at the end of the list.
	 * @param value The value to be inserted at the end of the list.
	 * @return void
	 * @complexity O(1)
	 */

	public void addLast(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Null value not allowed");
		}
		Node<T> newNode = new Node<>(value);
		if (head == null) {
			head = newNode;
			last = newNode;
			return;
		}
		last.setNext(newNode);
		last = newNode;

	}

	
	// remove and return the last value from the end of the list
	//return null if list is empty
	// O(n) where n is the number of items in list
	/**
	 * Remove a new node  at the end of the list.
	 * @return Node of generic type T removed node.
	 * @complexity O(n)
	 */

	public T removeLast(){

		if (head == null) {
			return null;
		}
		if (head.getNext() == null) {
			T removedData = head.getData();
			head = null;
			last = null;
			return removedData;
		}
		Node<T> current = head;
		Node<T> prev = null;
		while (current.getNext() != null) {
			prev = current;
			current = current.getNext();
		}
		prev.setNext(null);
		last = prev;
		//default return; update or change as needed
		return current.getData();

	}
	
	//remove and return the first occurence of value 
	// (i.e. the occurence that is closest to start of the list)
	// - return null if value is null or not present
	// - note: must return the value from list, not the argument value
	//O(n) where n is the number of items in list
	/**
	 * Remove a new node with value at the end of the list.
	 * @param value The value to be removed.
	 * @return Node of generic type T.
	 * @complexity O(n)
	 */
	public T remove(T value){
		if (value == null || head == null) {
			return null;
		}
		// If the value is at the head of the list
		if (head.getData().equals(value)) {
			return removeFirst();
		}
		Node<T> current = head;
		Node<T> prev = null;
		while (current != null && !current.getData().equals(value)) {
			prev = current;
			current = current.getNext();
		}
		// If the value is not found in the list
		if (current == null) {
			return null;
		}
		// Remove the node containing the value
		prev.setNext(current.getNext());
		// Update last reference if the last node is removed
		if (current == last) {
			last = prev;
		}
		//default return; update or change as needed
		return current.getData();

	
	}

	//return the index of the first occurence of value 
	// (i.e. the occurence that is closest to the start of the list)
	// return -1 if value is null or not present
	//O(n) where n is the number of items in list
	/**
	 * Get an index of a node.
	 * @param value The value to be returned of given index.
	 * @return return the index of the first occurence of value.
	 * -1 if value is null or not present.
	 * @complexity O(n)
	 */
	public int indexOf(T value){
		if (value == null || head == null) {
			return -1;
		}
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			if (current.getData().equals(value)) {
				return index;
			}
			current = current.getNext();
			index++;
		}
		//default return; update or change as needed
		// If the value is not found in the list
		return -10;
	
	}


	// return a string representing all values in the list, from beginning to end,
	// seperated by a single space
	// return empty string for empty list
	// O(n) where n is the number of items in list
	// Warning: concatenating String objects will yield a O(n^2) solution
	/**
	 * Converts the linked list to a string representation.
	 * @return A string representing all values in the list, from beginning to end, separated by a single space.
	 *         Returns an empty string for an empty list.
	 * @complexity O(n) where n is the number of items in the list.
	 *              Warning: concatenating String objects will yield an O(n^2) solution.
	 */
	public String listToString() {
		StringBuilder sb = new StringBuilder();
		Node<T> current = head;
		while (current != null) {
			sb.append(current.getData());
			sb.append(" "); // Add space separator
			current = current.getNext();
		}
		// Remove trailing space if the list is not empty
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		//default return; update or change as needed
		return sb.toString();

	}

	//return first node from start that contains value
	//return null if value is null or not present
	// NOTE: the layout of nodes is typically hidden and not available to outside;
	//       we are declaring this public for testing purpose. 
	//       Do not use this method outside of BasicList/SortedList classes.
	//O(n) where n is the number of items in list
	/**
	 * Returns the first node from the start that contains the specified value.
	 * @param value The value to search for.
	 * @return The first node containing the value, or null if the value is null or not present.
	 *         Note: the layout of nodes is typically hidden and not available to outside;
	 *               this method is declared public for testing purposes only.
	 *               Do not use this method outside of BasicList/SortedList classes.
	 * @complexity O(n) where n is the number of items in the list.
	 */
	public Node<T> getNode(T value){
		if (value == null || head == null) {
			return null;
		}
		Node<T> current = head;
		while (current != null) {
			if (current.getData().equals(value)) {
				return current;
			}
			current = current.getNext();
		}
		//default return; update or change as needed
		return current;
	}
	
	//find the first node that contains the given value, and move the node to the start of list
	// i.e. indexOf(value)==0 after moving completes
	// - return true if move can be performed
	// 	- no change if value already at the start but still return true
	// - return false if value is null or not present
	// Note: You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node.  
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list
	/**
	 * Finds the first node that contains the given value and moves the node to the start of the list.
	 * i.e. indexOf(value) == 0 after the moving completes.
	 * - Returns true if the move can be performed:
	 *   - No change if the value is already at the start but still returns true.
	 * - Returns false if the value is null or not present.
	 * Note: You must reuse the existing node while moving its location in the list;
	 *       do NOT remove then add the value back using a new node.
	 *       Points will be deducted in grading if you do so.
	 * Time complexity: O(n) where n is the number of items in the list.
	 * @param value the value to move to the front of the list
	 * @return true if the move is successful, false otherwise
	 */
	public boolean moveToFront(T value){
		if (value == null || head == null) {
			return false;
		}
		// If the value is already at the start of the list, no need to move
		if (head.getData().equals(value)) {
			return true;
		}
		Node<T> current = head;
		Node<T> prev = null;
		while (current != null && !current.getData().equals(value)) {
			prev = current;
			current = current.getNext();
		}
		// If the value is not found in the list
		if (current == null) {
			return false;
		}
		if(prev != null) {
			prev.setNext(current.getNext()); // Remove the node from its current position
			current.setNext(head); // Set the node as the new head
			head = current; // Update the head reference
		}
		//default return; update or change as needed
		return true;

	
	}
	
	//find the first node that contains the given value, 
	// and move the node one location closer to start of the list
	// i.e. if indexOf(value)==x before the move, then after moving completes, indexOf(value)==x-1
	// - return true if move can be performed
	// 	- no change if value already at the start but still return true
	// - return false if value is null or not present
	// Note:  You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list
	/**
	 * Finds the first node that contains the given value and moves the node one location closer to the start of the list.
	 * If the index of the value before the move is x, then after the move completes, the index of the value is x-1.
	 * @param value The value to search for and move.
	 * @return True if the move can be performed:
	 *            - Returns true if the value is already at the start of the list but still returns true.
	 *          False if the value is null or not present.
	 *         Note: You must reuse the existing node while moving its location in the list;
	 *               do NOT remove then add the value back using a new node.
	 *               Points will be deducted in grading if you do so.
	 * @complexity O(n) where n is the number of items in the list.
	 */
	public boolean moveForward(T value){
		if (value == null || head == null) {
			return false;
		}
		// If the value is already at the start of the list, no need to move
		if (head.getData().equals(value)) {
			return true;
		}
		Node<T> current = head;
		Node<T> prev = null;
		Node<T> nprev = null;
		while (current != null && !current.getData().equals(value)) {
			nprev = prev;
			prev = current;
			current = current.getNext();
		}
		// If the value is not found in the list
		if (current == null) {
			return false;
		}
		if(last == current){
			last = prev;
		}
		// If the value is found and not already at the start, move it forward
		if (nprev != null) {
			prev.setNext(current.getNext()); // Update the previous node's next reference
			nprev.setNext(current); // Remove the node from its current position
			current.setNext(prev); // Insert the node one position forward
			return true;
		}
		//default return; update or change as needed
		return false;// If prev is null, the value is already at the start

	}
	
	//find the first node that contains the given value, and move the node to the end of list
	// i.e. if only one node contains value, then indexOf(value)==size()-1 after moving completes 
	// - return true if move can be performed
	// 	- no change if value already at the end but still return true
	// - return false if value is null or not present
	// Note:  You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list
	/**
	 * Finds the first node that contains the given value and moves the node to the end of the list.
	 * i.e. if only one node contains the value, then indexOf(value) == size() - 1 after moving completes.
	 * - Returns true if the move can be performed:
	 *   - No change if the value is already at the end but still returns true.
	 * - Returns false if the value is null or not present.
	 * Note: You must reuse the existing node while moving its location in the list;
	 *       do NOT remove then add the value back using a new node.
	 *       Points will be deducted in grading if you do so.
	 * Time complexity: O(n) where n is the number of items in the list.
	 * @param value the value to move to the end of the list
	 * @return true if the move is successful, false otherwise
	 */
	public boolean moveToBack(T value){
		if (value == null || head == null) {
			return false;
		}
		Node<T> current = head;
		Node<T> prev = null;
		Node<T> nodeToMove = null;
		Node<T> prevNodeToMove = null;

		// Find the node containing the given value and its previous node
		while (current != null && !current.getData().equals(value)) {
			prev = current;
			current = current.getNext();
		}

		// If the value is not found in the list
		if (current == null) {
			return false;
		}

		nodeToMove = current;
		prevNodeToMove = prev;

		// If the node to move is already at the end of the list, no need to move
		if (nodeToMove.getNext() == null) {
			return true;
		}

		// If the node to move is the head, update the head reference
		if (prevNodeToMove == null) {
			head = nodeToMove.getNext();
		} else {
			prevNodeToMove.setNext(nodeToMove.getNext());
		}

		// Move the node to the end of the list
		Node<T> tail = head;
		while (tail.getNext() != null) {
			tail = tail.getNext();
		}
		tail.setNext(nodeToMove);
		nodeToMove.setNext(null);

		return true; //default return; update or change as needed

	}
	
	//find the first node that contains the given value, 
	// and move the node one location closer to end of the list
	// i.e. if only one node contains value and indexOf(value)==x before the move, 
	//      then after moving completes, indexOf(value)==x+1
	// - return true if move can be performed
	// 	- no change if value already at the end but still return true
	// - return false if value is null or not present
	// Note:  You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list
	/**
	 * Finds the first node that contains the given value and moves the node one location closer to the end of the list.
	 * i.e. if only one node contains the value and indexOf(value)==x before the move,
	 *      then after moving completes, indexOf(value)==x+1
	 * - Returns true if the move can be performed:
	 *   - No change if the value is already at the end but still returns true.
	 * - Returns false if the value is null or not present.
	 * Note: You must reuse the existing node while moving its location in the list;
	 *       do NOT remove then add the value back using a new node.
	 *       Points will be deducted in grading if you do so.
	 * Time complexity: O(n) where n is the number of items in the list.
	 * @param value the value to move one location closer to the end of the list
	 * @return true if the move is successful, false otherwise
	 */
	public boolean moveBackward(T value){
		if (value == null || head == null) {
			return false;
		}
		// If the value is already at the end of the list, no need to move
		if (last.getData().equals(value)) {
			return true;
		}
		Node<T> current = head;
		Node<T> prev = null;
		Node<T> nprev = null;
		while (current != null && !current.getData().equals(value)) {

			prev = current;
			current = current.getNext();
			nprev = current.getNext();

		}
		// 1  -> 2 -> 3 -> 4
		// 1 -> 3 -> 2 -> 4
		// If the value is not found in the list
		if (current == null) {
			return false;
		}
		if(current.getNext() == last){
			return true;
		}
		// If the value is found and not already at the end, move it backward
		if (nprev != null) {
			prev.setNext(current.getNext());
			current.setNext(nprev.getNext());
			nprev.setNext(current);

			return true;
		}
		return false; // If prev is null, the value is already at the end
		
	}
	
	

	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******        Remember to add JavaDoc         *******
	//******************************************************
	/**
	 * Below this line is the testing code.
	 * Edit it as much as you'd like!
	 * Remember to add JavaDoc.
	 * This is main function
	 * @param args
	 */
	public static void main(String[] args) {

		//a list of integers	
		BasicList<Integer> nums = new BasicList<>();

		//basic operations
		nums.addLast(100);
		nums.addFirst(200);
		nums.addFirst(300);
		nums.addFirst(400);	
		
		if (nums.getFirst()==400 && nums.getLast()==100 &&
			nums.listToString().equals("400 300 200 100")) {
			System.out.println("Yay1");
		}
		//System.out.println(nums.listToString());	
		
		//a list of strings
		BasicList<String> names = new BasicList<>();
		names.addLast("apple");
		names.addLast("banana");
		names.addLast("blueberry");
		names.addLast("orange");
		names.addLast("blueberry");
		names.addLast("peach");

		if (names.removeFirst().equals("apple") && 
			names.remove("blueberry").equals("blueberry") && names.size() == 4 &&
			names.indexOf("blueberry") == 2 && 
			names.listToString().equals("banana orange blueberry peach")){
			System.out.println("Yay2");			
		}

		//getNode and move methods
		// -reminder: keep the original node but move it to a new location
		// - we will use getNode() to verify this as the examples below
		
		Node<String> node = names.getNode("orange");
		if (names.moveToFront("orange") && names.getNode("orange") == node &&
			names.listToString().equals("orange banana blueberry peach")){
			System.out.println("Yay3");						
		}

		node = names.getNode("peach");
		if (names.moveForward("peach") && names.getNode("peach") == node &&
			names.listToString().equals("orange banana peach blueberry")){
			System.out.println("Yay4");
		}

		//remove special case
		/**
		 * Class representing SomeType objects.
		 */
		class SomeType{
			private String value;
			/**
			 * Constructs a SomeType object with the specified value.
			 *
			 * @param value the value of the SomeType object
			 */
			public SomeType(String value) { this.value = value; }
			/**
			 * Indicates whether some other object is "equal to" this one.
			 * This method considers two SomeType objects equal if their values are either both null,
			 * both empty strings, or their leading characters are the same.
			 *
			 * @param o the object to compare
			 * @return true if the objects are equal, false otherwise
			 */
			
			public boolean equals(Object o) {
				if (!(o instanceof SomeType)) return false;
				
				//both null
				if (((SomeType)o).value == null && this.value==null) return true;
				
				//both empty string
				if (((SomeType)o).value.length() == 0 && this.value.length()==0) return true;
				
				//compare the leading chars
				return ((SomeType)o).value.charAt(0) == this.value.charAt(0);
			}
			/**
			 * Returns a string representation of the SomeType object.
			 *
			 * @return the string representation of the value
			 */
			public String toString(){ return value;}
		}
		
		SomeType item1 = new SomeType("Apple");
		SomeType item2 = new SomeType("Alligator");
		SomeType item3 = new SomeType("Bee");
		SomeType item4 = new SomeType("Alder");
		
		
		BasicList<SomeType> items = new BasicList<>();
		items.addLast(item1);
		items.addLast(item2);
		items.addLast(item3);
		
		SomeType deleted = items.remove(item4);
		if (deleted.toString().equals("Apple")){
			System.out.println("Yay5");
		}
	
		//add more test cases by yourself!

		// Added by MySelf

		// Test case for moveToBack method
		names = new BasicList<>();
		names.addLast("apple");
		names.addLast("banana");
		names.addLast("orange");
		names.addLast("blueberry");
		node = names.getNode("orange");
		if (names.moveToBack("orange") && names.getNode("orange") == node &&
				names.listToString().equals("apple banana blueberry orange")) {
			System.out.println("Yay6. moveToBack test passed!");
		} else {
			System.out.println("Yay6. moveToBack test failed!");
		}
		names = new BasicList<>();
		names.addLast("orange");
		names.addLast("banana");
		names.addLast("apple");
		names.addLast("blueberry");
		node = names.getNode("banana");
		if (names.moveBackward("banana") && names.getNode("banana") == node &&
				names.listToString().equals("orange apple banana blueberry")) {
			System.out.println("Yay7. moveBackward test passed!");
		} else {
			System.out.println("Yay7. moveBackward test failed!");
		}

	}
}