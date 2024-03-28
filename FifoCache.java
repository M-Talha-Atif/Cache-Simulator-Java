// TO DO: add your implementation and JavaDocs.
import java.util.Iterator;

//Cache implementing FIFO replacement policy
/**
 * Cache implementing FIFO (First-In-First-Out) replacement policy.
 */
public class FifoCache implements Cache {
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
	 * Constructor for a cache with the specified capacity.
	 *
	 * @param cap the capacity of the cache
	 * @throws IllegalArgumentException if the capacity is not a positive integer
	 */
	public FifoCache(int cap){
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
		return storage.size() == capacity;

	}

	//report max number of items allowed in cache
	//O(1)
	/**
	 * Reports the maximum number of items allowed in the cache.
	 *
	 * @return the capacity of the cache
	 */
	public int capacity(){
		return capacity;   //default return; update or change as needed

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
	public String nextToReplace(){
		//default return; update or change as needed
		if (storage.size()==capacity) {
			return storage.getFirst();
		}
		return null;

	}

	//determine whether the access to addr is a hit or miss
	//return true for a hit and false for a miss
	// perform necessary updating to maintain the FIFO cache
	// - if addr is null, throw an IllegalArgumentException (with any error msg)
	//O(n) where n is the number of items in cache
	/**
	 * Determines whether the access to the specified address is a hit or miss.
	 *
	 * @param addr the address being accessed
	 * @return true if the access is a hit, false if it's a miss
	 * @throws IllegalArgumentException if the address is null
	 */
	public boolean access(String addr){
		//default return; update or change as needed
		if (addr == null) {
			throw new IllegalArgumentException("Address cannot be null.");
		}
		if (storage.getNode(addr) != null) {
			return true; // Cache hit
		}
		if (isFull()) {
			storage.removeFirst(); // Evict the oldest item
		}
		storage.addLast(addr); // Add the new item
		return false; // Cache miss

	}
	
	//return a string representing all items in cache
	// - follow the order from first in to last in
	//return an empty string if cache is empty
	//O(n) where n is the number of items in cache
	/**
	 * Returns a string representing all items in the cache, following the order from first in to last in.
	 *
	 * @return a string representing all items in the cache
	 */
	@Override
	public String toString(){
		//default return; update or change as needed
		return storage.listToString();
	}

}	
