package numbers;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printInitialMessage() {
        System.out.println("Welcome to Amazing Numbers!\n\n"+
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    public void run() {
        printInitialMessage();

        while (true) {
            System.out.print("Enter a request: ");
            String input = scanner.nextLine();
            input = input.trim();
            System.out.println();

            if (input.equals("0")) {
                break;
            } else if (!input.isEmpty()) {
                new Request(input);
            }
        }
        System.out.println("Goodbye!");
    }

}