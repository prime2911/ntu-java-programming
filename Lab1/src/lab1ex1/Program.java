package lab1ex1;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		System.out.print("Ho va ten: ");
		String name = userInput.nextLine();

		double avgScore;

		while (true) {
			try {
				do {
					System.out.print("Diem TB: ");
					avgScore = userInput.nextDouble();
				} while (avgScore < 0 && avgScore > 10);
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}
		System.out.printf("%s, %.2f diem", name, avgScore);
	}

}
