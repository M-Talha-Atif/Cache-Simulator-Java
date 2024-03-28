// TO DO: add your implementation and JavaDocs.
import java.util.Iterator;

//Cache implementing LRU replacement policy
/**
 * Cache implementing LRU (Least Recently Used) replacement policy.
 */
public class LruCache implements Cache {
	/**
	 * Cache capacity.
	 */
	private int capacity;
	/**
	 * Cache storage.
	 */
	private BasicList<String> storage; //each address is a string

	// YOU CANNOT ADD MORE DATA MEMBERS!
	// ADD PRIVATE HELPER METHODS IF NEEDED!
	
	//constructor for a cache with capacity as cap	
	// - if cap is not positive, throw an IllegalArgumentException (with any error msg)
	//O(1)

	/**
	 * Constructs an LRU cache with the specified capacity.
	 *
	 * @param cap the maximum capacity of the cache
	 * @throws IllegalArgumentException if the capacity is not positive
	 */
	public LruCache(int cap){
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity must be a positive integer.");
		}
		capacity = cap;
		storage = new BasicList<>();

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
		return size()==capacity;

	}


	//report max number of items allowed in cache
	//O(1)
	/**
	 * Returns the maximum capacity of the cache.
	 *
	 * @return the maximum capacity
	 */
	public int capacity(){
		//default return; update or change as needed
		return capacity;

	}

	//report the number of items stored in cache
	//O(1)
	/**
	 * Returns the number of items stored in the cache.
	 *
	 * @return the number of items stored
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
	 * @return the item to replace, or null if no item will be evicted
	 */
	public String nextToReplace() {
		if (storage.size() == capacity) {
			return storage.getFirst(); // Cache is empty, nothing to replace
		}
		return null; // Return the least recently used item (last item in the cache)
	}

	//determine whether the access to addr is a hit or miss
	//return true for a hit and false for a miss
	// perform necessary updating to maintain the LRU cache
	// - if addr is null, throw an IllegalArgumentException (with any error msg)
	//O(n) where n is the number of items in cache
	/**
	 * Determines whether the access to a specific address is a hit or miss in the LRU cache.
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
		Node<String> node = storage.getNode(addr);
		if (node != null) {
			// Cache hit, move the accessed item to the front
			storage.moveToFront(node.getData());
			return true;
		}
		// If the cache is full, remove the least recently used item
		if (isFull()) {
			// Remove the last item (least recently used)
			storage.removeLast();
		}

		// Cache miss, add the address to the front
		storage.addFirst(addr);



		return false;
	}



	//return a string representing all items in cache
	// - follow the order from LRU to MRU
	//return an empty string if cache is empty
	//O(n) where n is the number of items in cache
	/**
	 * Returns a string representing all items in cache.
	 *
	 * @return a string representation of the cache
	 */
	@Override
	public String toString(){
		//default return; update or change as needed
		return storage.listToString();

	}
	
	

}
