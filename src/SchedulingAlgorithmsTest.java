import java.util.Scanner;

public class SchedulingAlgorithmsTest {

	public static void main(String[] args) {
		SchedulingAlgorithms sa = new SchedulingAlgorithms(5000, 0, 1000);	//Initialize the disk scheduling algorithms class
		
		
		System.out.println(sa.FCFS(50));	//First Come, First Serve algorithm total head movement
		System.out.println(sa.SSTF(50));	//Shortest Seek Time First algorithm total head movement
		System.out.println(sa.SCAN(50));	//Scan algorithm total head movement

	}

}
