// TO DO: add your implementation and JavaDocs.
import java.util.Iterator;

//Cache implementing LFU replacement policy
/**
 * Cache implementing FIFO (First-In-First-Out) replacement policy.
 */
public class LfuCache implements Cache { 

	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	//wrap each item we need to save in cache in a block and keep the access count
	/**
	 * Represents a block in the cache.
	 * Each block stores a data item and keeps track of its access count.
	 */
	private class Block implements Comparable<Block> {
	
		//data item to store in cache
		private String data;
		
		//how many times this item is accessed since it is loaded in
		private int count;
		
		//constructor
		/**
		 * Constructs a new block with the specified address.
		 * Initializes the count to 1.
		 *
		 * @param addr the address of the data item
		 */
		public Block(String addr){
			this.data = addr;
			count = 1;
		}
	
		//getter of count
		/**
		 * Gets the access count of this block.
		 *
		 * @return the access count
		 */
		public int getCount(){
			return count;
		}
		
		//increment count
		/**
		 * Increments the access count of this block.
		 */
		public void incCount(){
			count++;
		}
		
		//getter of data item
		/**
		 * Gets the data item of this block.
		 *
		 * @return the data item
		 */
		public String getData(){
			return data;
		}
		
		//used to determine whether an item is already in cache
		/**
		 * Checks whether this block is equal to another object.
		 * Two blocks are considered equal if their data items are equal.
		 *
		 * @param other the object to compare
		 * @return true if the objects are equal, false otherwise
		 */
		@Override
		public boolean equals(Object other){
			if (other instanceof Block){
				if ( ((Block)other).data.equals(this.data) ){
            		return true;
        		}
    		}
   			return false;		
		}
		/**
		 * Compares this block with another block based on their access counts.
		 *
		 * @param other the other block to compare
		 * @return a negative integer, zero, or a positive integer as this block is less than, equal to, or greater than the specified block
		 */
		//used to compare two items based on their access counts
		public int compareTo(Block other){
			return this.count - other.count;			
		}
	
		//string representation including access count
		/**
		 * Returns a string representation of this block, including the access count.
		 *
		 * @return a string representation of this block
		 */
		@Override
		public String toString(){
			return "<"+data.toString()+","+count+">";
		}
	}
	/**
	 * Cache capacity.
	 */
	private int capacity;
	/**
	 * Cache storage.
	 */
	private SortedList<Block> storage; //NOTE: SortedList! List of Blocks! 

	//******************************************************
	//*******    	END of PROVIDED Code 	 		 *******
	//*******    	Do NOT Change PROVIDED Code 	 *******
	//******************************************************

	// YOU CANNOT ADD MORE DATA MEMBERS!
	// ADD PRIVATE HELPER METHODS IF NEEDED!

	//constructor for a cache with capacity as cap	
	// - if cap is not positive, throw an IllegalArgumentException (with any error msg)
	//O(1)
	/**
	 * Constructor for a cache with the specified capacity.
	 *
	 * @param cap the capacity of the cache
	 * @throws IllegalArgumentException if the capacity is not a positive integer
	 */
	public LfuCache(int cap){
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity must be a positive integer.");
		}
		capacity = cap;
		storage = new SortedList<>();

	}

	//return true if cache is full; false otherwise
	//O(1)
	/**
	 * Checks if the cache is full.
	 *
	 * @return true if the cache is full, false otherwise
	 */
	public boolean isFull(){
		//default return; update or change as needed
		return storage.size()==capacity;

	}

	//report max number of items allowed in cache
	//O(1)
	/**
	 * Reports the maximum number of items allowed in the cache.
	 *
	 * @return the capacity of the cache
	 */
	public int capacity(){
		//default return; update or change as needed
		return capacity;

	}
	
	//report the number of items stored in cache
	//O(1)
	/**
	 * Reports the number of items stored in the cache.
	 *
	 * @return the number of items stored in the cache
	 */
	public int size(){
		//default return; update or change as needed
		return storage.size();

	}

	//return item that will be evicted if the next access is a miss
	//return null if no item will be evicted
	//O(1)
	/**
	 * Returns the item that will be evicted if the next access is a miss.
	 *
	 * @return the item to be evicted, or null if no item will be evicted
	 */
	public String nextToReplace() {
		if (storage.size() == 0) {
			return null; // Cache is empty, nothing to replace
		}

		// Initialize variables to track the item with the minimum frequency
		String itemToReplace = storage.getFirst().getData();
		int minFrequency = storage.getFirst().getCount();

		// Iterate through the cache to find the item with the minimum frequency
		for (Block block : storage) {
			if (block.getCount() < minFrequency) {
				minFrequency = block.getCount();
				itemToReplace = block.getData();
			}
		}

		return itemToReplace; // Return the item with the minimum frequency to replace
	}

	//determine whether the access to addr is a hit or miss
    //return true for a hit and false for a miss
    // perform necessary updating to maintain the LRU cache
    // - if addr is null, throw an IllegalArgumentException (with any error msg)
    //O(n) where n is the number of items in cache

	/**
	 * Determines whether the access to a specific address is a hit or miss in the LFU cache.
	 *
	 * @param addr The address being accessed.
	 * @return True if the access is a hit, false if it's a miss.
	 * @throws IllegalArgumentException if the address is null.
	 * @complexity O(n) where n is the number of items in the cache.
	 */
	public boolean access(String addr) {
		if (addr == null) {
			throw new IllegalArgumentException("Address cannot be null.");
		}

		// Check if the address is already in the cache
		Block block = new Block(addr);
		Node<Block> node = storage.getNode(block);
		if (node != null) {
			block = node.getData();
			block.incCount();
			// Increment the access count
			storage.remove(block);
			// Remove the block to re-insert it in the correct order
			storage.add(block);
			// Re-insert the block with the updated access count
			return true;
			// Cache hit
		}

		// Cache miss
		// If the cache is full, remove the least frequently used item
		if (isFull()) {
			storage.removeFirst();
		}

		// Add the new item to the cache
		storage.add(block);
		return false;
		// Cache miss
	}



	//return a string representing all items in cache
	// - follow the order from LFU to MFU
	// - if there is a tie, items should be included from LRU to MRU
	//return an empty string if cache is empty
	//O(n) where n is the number of items in cache
	/**
	 * Returns a string representing all items in the cache, following the order from LFU to MFU.
	 *
	 * @return a string representing all items in the cache
	 */
	@Override
	public String toString(){
		return storage.listToString();

	}

}