package lab1ex2;

import java.util.Scanner;

public class Program {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		double length, width;
		Scanner userInput = new Scanner(System.in);

		while (true) {
			try {
				do {
					System.out.print("Enter rectangle's length: ");
					length = userInput.nextDouble();
				} while (length <= 0);
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}
		
		while (true) {
			try {
				do {
					System.out.print("Enter rectangle's width: ");
					width = userInput.nextDouble();
				} while (width <= 0);
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}
		
		System.out.printf("\nRectangle's perimeter: %.2f", (length + width) * 2);
		System.out.printf("\nRectangle's area: %.2f", length * width);
		System.out.printf("\nThe length of the shortest edge: %.2f", Math.min(length, width));
	}

}
