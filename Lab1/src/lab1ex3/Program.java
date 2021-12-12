package lab1ex3;

import java.util.Scanner;

public class Program {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		double side;
		Scanner userInput = new Scanner(System.in);

		
		while (true) {
			try {
				do {
					System.out.print("Enter the length of a cube's side: ");
					side = userInput.nextDouble();
				} while (side <= 0);
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}

		System.out.printf("The cube's volume: %.2f", Math.pow(side, 3));
	}

}
