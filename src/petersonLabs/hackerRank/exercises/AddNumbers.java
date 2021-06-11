package petersonLabs.hackerRank.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddNumbers {

	private static int add(List<Integer> values) {
		// Write your code here.
		return values.stream().mapToInt(x -> x).sum();
	}

	public static void main(String[] args) throws InterruptedException {
		// Used to debug the exercise code
		// Thread.sleep(20000);

		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		List<Integer> values = new ArrayList<>();

		while(t-- > 0) {
			values.add(scan.nextInt());
		}

		int result = add(values);
		System.out.print(result);

		scan.close();
	}
}
