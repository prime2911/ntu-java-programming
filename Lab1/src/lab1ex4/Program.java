package lab1ex4;

import java.util.Scanner;

public class Program {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		double a, b, c;
		Scanner userInput = new Scanner(System.in);

		System.out.println("Given the quadratic equation ax^2 + bx + c = 0.");

		while (true) {
			try {
				System.out.print("Enter the coefficient a: ");
				a = userInput.nextDouble();
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}
		
		while (true) {
			try {
				System.out.print("Enter the coefficient b: ");
				b = userInput.nextDouble();
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}
		
		while (true) {
			try {
				System.out.print("Enter the constant: ");
				c = userInput.nextDouble();
				break;
			} catch (Exception e) {
				System.out.print("Please enter a number\n");
				userInput.next();
				continue;
			}
		}

		double delta = Math.pow(b, 2) - 4 * a * c;
		
		if (delta < 0)
			System.out.printf("Negative discriminant (%.2f), unable to find square root", delta);
		else
			System.out.printf("The equation's discriminant: %.2f\nThe discriminant's square root: %.2f", delta, Math.sqrt(delta));
	}

}
