package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        ui.run();
    }
}
