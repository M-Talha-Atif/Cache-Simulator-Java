// TO DO: add your implementation and JavaDocs.

// ONLY need to implement these THREE METHODS: put(), get(), and delete()
// Keep other code AS IS but you will need to add JavaDocs

import java.util.Iterator;
/**
 * Basic map class.
 * This class represents a basic implementation of a hash map.
 * Each entry of the hash map is a <key, value> pair, where the key is of type K and the value is of type V.
 * @param <K> The type of keys stored in the map.
 * @param <V> The type of values stored in the map.
 */
public class BasicMap<K, V> {
	
	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	//each entry of the hash map would be a <key, value> pair, 
	//key of type K and value of type V

	/**
	 * Pair class
	 */
	private class Pair {
		/**
		 * Key variable
		 */
		private K key;
		/**
		 * value variable
		 */
		private V value;

		/**
		 * Constructs a Pair object with the specified key and value.
		 * @param key The key of the pair.
		 * @param value The value of the pair.
		 */
		public Pair(K key, V value){
			this.key = key;
			this.value = value;
		}
		/**
		 * Retrieves the key of the pair.
		 * @return The key of the pair.
		 */
		public K getKey(){ return key; }
		/**
		 * Retrieves the value of the pair.
		 * @return The value of the pair.
		 */
		public V getValue(){ return value; }
		/**
		 * Sets the value of the pair.
		 * @param value The new value to set.
		 */
		public void setValue(V value){ this.value = value; }
		/**
		 * Returns a string representation of the pair.
		 * @return A string representation of the pair in the format "<key:value>".
		 */
		@Override
		public String toString(){
			return "<"+key.toString()+":"+value.toString()+">";
		}

		/**
		 * Indicates whether some other object is "equal to" this one.
		 * Two pairs are considered equal if their keys are equal.
		 * @param o The object to compare with.
		 * @return true if this pair is equal to the specified object, false otherwise.
		 */
		@Override
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			// return true if two pairs have matching keys
			// i.e. <"Alice", 1> is considered as equal to <"Alice", 2>
			if(o instanceof BasicMap<?,?>.Pair) {
				Pair pair = (Pair)o;
				return pair.key.equals(key);  
			}
			return false;
		}
		/**
		 * Returns a hash code value for the pair.
		 * @return A hash code value for the pair.
		 */
		@Override
		public int hashCode(){
			return key.hashCode();
		}
		
	}

	// This hash map implementation uses an array of BasicList
	// where each list will be composed of Node<Pair>
	private BasicList<Pair>[] buckets;

	// will fix the capacity to 7
	final static private int DEFAULT_CAPACITY = 7;

	// track how many elements in HashMap
	private int size;
	/**
	 * Constructs an empty BasicMap with the default capacity.
	 */
	@SuppressWarnings("unchecked")
	public BasicMap() {
		buckets = (BasicList<Pair>[])new BasicList[DEFAULT_CAPACITY];
		size = 0;
	}
	/**
	 * Returns the number of key-value mappings in this map.
	 * @return The number of key-value mappings in this map.
	 */
	public int size() {
		return size;
	}
	/**
	 * Returns the current capacity of the map.
	 * @return The current capacity of the map.
	 */
	private int capacity() {
		return buckets.length;
	}
	/**
	 * Returns the hash code for the specified key.
	 * @param key The key whose hash code to be computed.
	 * @return The hash code for the specified key.
	 */

	private int getHash(K key) {
		return Math.abs(key.hashCode());
	}

	/**
	 * Returns a string representation of this map, including null entries.
	 * @return A string representation of this map, including null entries.
	 */
	public String toStringDebug() {
		//print all entries of buckets, including null ones
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<buckets.length; i++) {		
			BasicList<Pair> list = buckets[i];	
			sb.append("[");	
			if (list != null) {
				sb.append(list.listToString());
			}
			sb.append("]");
			if (i!=buckets.length-1)
				sb.append(",");	  

		}
		return "{" + sb.toString() + "}";
	}
	/**
	 * Returns a string representation of this map, excluding null entries.
	 * @return A string representation of this map, excluding null entries.
	 */

	@Override
	public String toString() {
		//only display non-null entries of buckets
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<buckets.length; i++) {		
			BasicList<Pair> list = buckets[i];	
			if (list != null) {
				if (sb.length()>0)
					sb.append(",");	 
				sb.append(list.listToString());
			}
		}
		return sb.toString();
	}

	//******************************************************
	//*******     BELOW THIS LINE IS YOUR CODE       *******
	//******************************************************
	// ADD PRIVATE METHODS HERE IF NEEDED!
	// YOU CANNOT ADD MORE DATA MEMBERS
	/**
	 * Puts a new key-value pair into the map.
	 * @param key   The key to be inserted.
	 * @param value The value corresponding to the key.
	 * @throws IllegalArgumentException if the key or value is null.
	 */
	public void put(K key, V value) {
		// mapping key to value in the hashmap
		// - throws IllegalArgumentException for null key or null value (with any error msg)
		// - if key is new, add a new entry (key, value)
		// - if key is present, make sure (key, value) is the only mapping of key in hashtable
		
		// Note: Implement the hash table with separate chaining.
		// - when a new (key,value) pair is added, add it to the end of the chain
		// - if key is already present, you should just change its mapping. 
		//    do not remove the key then add it back, which is less efficient.
		if (key == null || value == null) {
			throw new IllegalArgumentException("Null key or value not allowed");
		}

		int index = getHash(key) % capacity();
		if (buckets[index] == null) {
			buckets[index] = new BasicList<>();
		}

		Pair newPair = new Pair(key, value);
		BasicList<Pair> list = buckets[index];
		Node<Pair> node = list.getNode(newPair);

		if (node == null) {
			list.addLast(newPair);
			size++;
		} else {
			node.setData(newPair);
		}
		
		// O(load) on average, and O(n) worst case

		
    }
	/**
	 * Retrieves the value associated with the given key.
	 * @param key The key to search for.
	 * @return The value associated with the key, or null if the key is not found or is null.
	 */
	public V get(K key) {
		// return the current mapping of key
		// if key is null or not present, return null
		//	
		// O(load) on average, and O(n) worst case

		//default return, remove or update as needed
		if (key == null) {
			return null;
		}

		int index = getHash(key) % capacity();
		BasicList<Pair> list = buckets[index];
		if (list == null) {
			return null;
		}

		Pair targetPair = new Pair(key, null);
		Node<Pair> node = list.getNode(targetPair);
		return node != null ? node.getData().getValue() : null;
	}
	/**
	 * Removes the key-value pair associated with the given key from the map.
	 * @param key The key to be deleted.
	 * @return The value associated with the key, or null if the key is not found or is null.
	 */
	public V delete(K key){
		if (key == null) {
			return null;
		}

		int index = getHash(key) % capacity();
		BasicList<Pair> list = buckets[index];
		if (list == null) {
			return null;
		}

		Pair targetPair = new Pair(key, null);
		Node<Pair> node = list.getNode(targetPair);
		if (node != null) {
			V value = node.getData().getValue();
			list.remove(targetPair);
			size--;
			return value;
		}
		return null;

		// return the current mapping of key from hashmap and delete it
		// -if key is null or not present, return null
		// 
	
		// O(load) on average, and O(n) worst case
	
		//default return, remove or update as needed

	
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	/**
	 * Main function
	 * @param args The arguments
	 */
	
	public static void main(String args[]) {
		BasicMap<String, String> map = new BasicMap<>();
		
		map.put("apple", "red");
		map.put("pear", "yellow");
		map.put("eggplant", "purple");
		
		if (map.get("apple").equals("red") && map.get("eggplant").equals("purple") && map.size() == 3){
			System.out.println("Yay1");
		}
		
		//change mapping, delete
		map.put("apple", "green");
		if (map.get("apple").equals("green") && map.size()==3 && map.delete("pear").equals("yellow") 
			&& map.size() == 2) {
			System.out.println("Yay2");
		}
		
		//key not present
		if (map.get("banana")==null && map.delete("pear")==null){
			System.out.println("Yay3");		
		}
		
		//add to tail
		map.put("cherry", "red");
		if (map.toStringDebug().equals("{[],[<apple:green> <cherry:red>],[],[],[],[<eggplant:purple>],[]}")){
			System.out.println("Yay4");		
		}
		
	}

}