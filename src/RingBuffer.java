/*public class RingBuffer
-----------------------------------------------------------------------------------------
        RingBuffer(int capacity)  // create an empty ring buffer, with given max capacity
    int size()                    // return number of items currently in the buffer
boolean isEmpty()                 // is the buffer empty (size equals zero)?
boolean isFull()                  // is the buffer full  (size equals capacity)?
   void enqueue(double x)         // add item x to the end
 double dequeue()                 // delete and return item from the front
 double peek()                    // return (but do not delete) item from the front
 
 	Since the ring buffer has a known maximum capacity, implement it using a double array of that length. 
 For efficiency, use cyclic wrap-around: 
 
 Maintain one integer instance variable first that stores the index of the least recently inserted item
 maintain a second integer instance variable last that stores the index one beyond the most recently inserted item
 
 To insert an item, put it at index last and increment last. 
 To remove an item, take it from index first and increment first. When either index equals capacity, 
 make it wrap-around by changing the index to 0.
 
 	Implement RingBuffer to throw an exception if the client attempts to dequeue() from an empty buffer
 or enqueue() into a full buffer.
*/
public class RingBuffer {
	
	private double[] ring;
	private int capacity;
	private int first = 0;								// index of least recently inserted item
	private int last = 0;								// index of most recently inserted item plus 1

	public RingBuffer(int capacity){
		ring = new double[capacity];
		this.capacity = capacity;
	}
	
	public int size(){
		return last - first;
	}
	
	public int capacity(){
		return capacity;
	}
	
	public boolean isEmpty(){
		boolean returnVal = true;
		for(int i = 0; i < ring.length; i++){
			if(ring[i] != 0)
				returnVal = false;
		}
		return returnVal;
	}
	
	public boolean isFull(){
		boolean returnVal = true;
		for(int i = 0; i < ring.length; i++){
			if(ring[i] == 0)
				returnVal = false;
		}
		return returnVal;
	}
	
//	public boolean isEmpty(){
//		return ring[first] == 0;
//	}
//
//	public boolean isFull(){
//		return ring[last] != 0;
//	}
	
	public void enqueue(double x){
		ring[last] = x;
		if(first == last & !isEmpty()){
			if(last == capacity - 1){				// end of ring
				last = 0;
				first = 0;
			}
			else{
				first++;
				last++;
			}
		}else{
			if(last == capacity - 1){				// end of ring
				last = 0;
			}
			else last++;
		}

	}

	public double dequeue(){
		double retVal = ring[first];
		ring[first] = 0;
		if(first == capacity - 1)
			first = 0;
		else first++;
		return retVal;
	}
	
	
//	public double dequeue(){
//		double retVal = 0;
//		if(!isEmpty()){
//			retVal = ring[first];
//			ring[first] = 0;
//			if(first == capacity - 1)
//				first = 0;
//			else first++;
//		}
//		return retVal;
//	}
	
	public double peek(){
		return ring[first];
	}

	public void print() {
		for(int i = 0; i < ring.length; i++){
			System.out.println(ring[i]);
		}
		
	}

}
