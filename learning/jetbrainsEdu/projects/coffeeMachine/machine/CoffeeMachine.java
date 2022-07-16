package machine;

import java.util.Scanner;


public class CoffeeMachine {

    final private Scanner scanner = new Scanner(System.in);

    final int waterPerEspresso = 250;       // ml
    final int beansPerEspresso = 16;        // gr
    final int costPerEspresso = 4;          // $

    final int waterPerLatte = 350;          // ml
    final int milkPerLatte = 75;
    final int beansPerLatte = 20;           // gr
    final int costPerLatte = 7;             // $

    final int waterPerCappuccino = 200;     // ml
    final int milkPerCappuccino = 100;
    final int beansPerCappuccino = 12;      // gr
    final int costPerCappuccino = 6;        // $




    private int waterInMachine;
    private int milkInMachine;
    private int beansInMachine;
    private int disposableCups;
    private int money;


    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        coffeeMachine.interact();
    }


    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.waterInMachine = water;
        this.milkInMachine = milk;
        this.beansInMachine = coffeeBeans;
        this.disposableCups = cups;
        this.money = money;

        //printStatus();
    }


    public void interact() {
        while (true) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
            String input = scanner.next();
            handler(input);
        }
    }


    private void handler(String input) {
        if ("buy".equals(input)) {
            buy();
        } else if ("fill".equals(input)) {
            fill();
        } else if ("take".equals(input)) {
            take();
        } else if ("remaining".equals(input)) {
            printStatus();
        } else if ("exit".equals(input)) {
            System.exit(0);
        } else {
            System.out.println("Command not recognized...\nExit...");
        }
    }


    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

        String input = scanner.next();
        int selected = 0;
        if (input.matches("\\d+")) {
            selected = Integer.parseInt(input);
        } else if ("back".equals(input)) {
            return;
        } else {
            System.out.println("Invalid input detected...\nAborting");
            System.exit(1);
        }

        if (isSupplyEnough(selected)) {
            System.out.println("I have enough resources, making you a coffee!");
            switch (selected) {
                case 1:
                    waterInMachine -= waterPerEspresso;
                    beansInMachine -= beansPerEspresso;
                    disposableCups--;
                    money += costPerEspresso;
                    break;
                case 2:
                    waterInMachine -= waterPerLatte;
                    milkInMachine -= milkPerLatte;
                    beansInMachine -= beansPerLatte;
                    disposableCups--;
                    money += costPerLatte;
                    break;
                case 3:
                    waterInMachine -= waterPerCappuccino;
                    milkInMachine -= milkPerCappuccino;
                    beansInMachine -= beansPerCappuccino;
                    disposableCups--;
                    money += costPerCappuccino;
                    break;
            }

        } else {
            System.out.println("Supply is not enough!");
        }

        System.out.println();
    }


    private void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        String input = scanner.next();
        if (input.matches("\\d+")) {
            waterInMachine += Integer.parseInt(input);
        } else {
            System.out.println("Invalid input detected...\nAborting");
            System.exit(1);
        }


        System.out.println("Write how many ml of milk you want to add: ");
        input = scanner.next();
        if (input.matches("\\d+")) {
            milkInMachine += Integer.parseInt(input);
        } else {
            System.out.println("Invalid input detected...\nAborting");
            System.exit(1);
        }


        System.out.println("Write how many gr of coffee beans you want to add: ");
        input = scanner.next();
        if (input.matches("\\d+")) {
            beansInMachine += Integer.parseInt(input);
        } else {
            System.out.println("Invalid input detected...\nAborting");
            System.exit(1);
        }


        System.out.println("Write how many disposable cups of coffee you want to add: ");
        input = scanner.next();
        if (input.matches("\\d+")) {
            disposableCups += Integer.parseInt(input);
        } else {
            System.out.println("Invalid input detected...\nAborting");
            System.exit(1);
        }

        System.out.println();
    }


    private void take() {
        System.out.printf("I gave you $%d\n\n", money);
        money = 0;
    }


    private boolean isSupplyEnough(int selected) {
        switch (selected) {
            case 1:
                return waterInMachine >= waterPerEspresso && beansInMachine >= beansPerEspresso
                        && disposableCups > 0;
            case 2:
                return waterInMachine >= waterPerLatte && beansInMachine >= beansPerLatte
                        && milkInMachine >= milkPerLatte && disposableCups > 0;
            case 3:
                return waterInMachine >= waterPerCappuccino && beansInMachine >= beansPerCappuccino
                        && milkInMachine >= milkPerCappuccino && disposableCups > 0;
            default:
                System.out.println("Invalid input detected...\nAborting");
                System.exit(1);
        }

        return false;
    }


    private void printStatus() {
        System.out.println("The coffee machine has:\n" +
                String.format("%d ml of water\n", waterInMachine) +
                String.format("%d ml of milk\n", milkInMachine) +
                String.format("%d g of coffee beans\n", beansInMachine) +
                String.format("%d disposable cups\n", disposableCups) +
                String.format("$%d of money\n", money)
        );
    }


    private void makeCoffee() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");
    }

}
