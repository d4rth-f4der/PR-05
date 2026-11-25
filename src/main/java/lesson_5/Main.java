package lesson_5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a line of text:");
            if (!scanner.hasNextLine()) {
                System.out.println("No input line received.");
                return;
            }

            State state = new TestAutomaton().recognize(scanner.nextLine());
            System.out.println("Final state: " + state.label());
        }
    }
}
