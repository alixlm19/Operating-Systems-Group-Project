import java.util.Arrays;
import java.util.Random;

public class SchedulingAlgorithms {
	
	private int[] cylinderRequests;
	private int requests;
	private int cylinders;
	private int base;
	private int limit;	
	
	private static Random rand = new Random();
	
	public SchedulingAlgorithms(int cylinders, int base, int requests) {
		this.cylinders = cylinders;
		this.base = base;
		this.limit = cylinders + base;
		this.requests = requests;
		cylinderRequests = new int[] {98, 183, 37, 122, 14, 124, 65, 67};
		//generateRandomRequests(requests);
		
	}
	
	public int FCFS(int headPtr) {
		/**
		 * Schedules the cylinder requests from the cylinderRequests queue using the 
		 * First Come, First Serve (FCFS) algorithm
		 * @param headPtr integer indicating the position of the head pointer
		 * @return integer representing the total amount of head movement
		 */
		
		//Check if the head pointer is valid
		if(isInvalidPtr(headPtr)) {
			 System.err.println("Invalid head pointer");
			 return -1;
		}
		
		int[] queue = cylinderRequests.clone();							//Clone the cylinderRequests array
		return calculateDistance(queue, headPtr);
	}
	
	public int SSTF(int headPtr) {
		/**
		 * Schedules the cylinder requests from the cylinderRequests queue using the 
		 * Shortest Seek Time First (SSTF) algorithm
		 * @param headPtr integer indicating the position of the head pointer
		 * @return integer representing the total amount of head movement
		 */
		
		//Check if the head pointer is valid
		if(isInvalidPtr(headPtr)) {
			 System.err.println("Invalid head pointer");
			 return -1;
		}
		
		
		int l = cylinderRequests.length;
		int[][] queue = new int[l][2];										//Two-dimensional array containing the requests absolute seek times and their seek time signs
		
		for(int i = 0; i < l; i++) {
			queue[i][0] = Math.abs(cylinderRequests[i] - headPtr);			//Absolute seek time
			queue[i][1] = Integer.signum(cylinderRequests[i] - headPtr);	//Seek time sign
		}
		
		//Sort the array by seek time
		Arrays.sort(queue, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Integer.compare(a[0], b[0]);
		    }
		});

		int[] newQueue = new int[l];										//Array containing the requests queue sorted by seek time
		for(int i = 0; i < l; i++) {
			newQueue[i] = Math.abs(queue[i][0] + headPtr * queue[i][1]);    //Covert the seek times in the new requests queue to absolute seek times
		}

		return calculateDistance(newQueue, headPtr);
	}

	public int SCAN(int headPtr) {
		/**
		 * Schedules the cylinder requests from the cylinderRequests queue using the 
		 * SCAN algorithm. This implementation of the SCAN algorithm will always move left first.
		 * @param headPtr integer indicating the position of the head pointer
		 * @return integer representing the total amount of head movement
		 */
		
		//Check if the head pointer is valid
		if(isInvalidPtr(headPtr)) {
			 System.err.println("Invalid head pointer");
			 return -1;
		}
		
		
		int[] queue = new int[cylinderRequests.length + 2];
		
		System.arraycopy(cylinderRequests, 0, queue, 0, cylinderRequests.length);	//Copy the cylinderRequests queue to the queue array
		
		
		queue[queue.length - 2] = 0;
		queue[queue.length - 1] = headPtr;
		Arrays.sort(queue);
		
		int index = Arrays.binarySearch(queue, headPtr);
		int[] left = new int[index];
		
		
		//Gets the left part of the queue WRT the head pointer
		for(int i = index - 1; i > 0; i--) {
			left[index - i - 1] = queue[i];
		}
		
		for(int i = 0; i < left.length; i++) {
			queue[i] = left[i];
		}
		
		return calculateDistance(queue, headPtr);
	}
	
	public void generateRandomRequests(int n) {
		/**
		 * Generates a sequence of random cylinder requests and stores them in the
		 * cylinderRequests array
		 * @param n integer indicating the total amount of requests to be generated
		 */
		cylinderRequests = new int[n];
		for(int i = 0; i < n; i++) {
			cylinderRequests[i] = base + rand.nextInt(cylinders); //Generates a number between the base (inclusive) and the limit (exclusive)
		}
	}
	
	private int calculateDistance(int [] queue, int headPtr) {
		/** 
		 * Calculates the total amount of head movement WRT the cylinder requests queue
		 * @param queue array representing the cylinder requests queue
		 * @param headPtr integer indicating the position of the head pointer
		 * @return integer representing the total amount of head movement
		 */
		
		int headMovement = Math.abs(queue[0] - headPtr);
		//Calculate the absolute distances moved by the head
		for(int i = 1; i < queue.length; i++) {
			headMovement += Math.abs(queue[i] - queue[i - 1]);
		}
		
		return headMovement;
	}
	
	private boolean isInvalidPtr(int headPtr) {
		/**
		 * Checks if the passed head pointer is within the disk cylinders' boundaries
		 * @param headPtr integer indicating the position of the head pointer
		 * @return Boolean
		 */
		if(headPtr < 0 || headPtr >= limit) {
			return true;
		}
		return false;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}
}
