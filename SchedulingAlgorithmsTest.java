
public class SchedulingAlgorithmsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SchedulingAlgorithms sa = new SchedulingAlgorithms(5000, 0, 1000);
		System.out.println(sa.FCFS(53));
		System.out.println(sa.SSTF(53));
		System.out.println(sa.SCAN(53));
	}

}
