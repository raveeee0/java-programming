package cinema;

import java.util.Scanner;
import java.util.Arrays;


public class Cinema {
    final static public Scanner scanner = new Scanner(System.in);
    
    private int rows;
    private int cols;
    private int totalSeats;
    private char[][] seats;
    
    final static int smallSize = 60;
    final static int firstHalfCost = 10;
    final static int secondHalfCost = 8;
    

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.interaction();

        scanner.close();
    }
    
    
    public Cinema() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = scanner.nextInt();
        
        seats = new char[rows][cols];
        for (char[] subarr : seats) {
            Arrays.fill(subarr, 'S');
        }
        
        totalSeats = cols * rows;
        
        showSeats();
    }
    
    
    public void interaction() {
        boolean stop = false;
        while(!stop) {
            System.out.println();
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            System.out.print("> ");
            switch(scanner.nextInt()) {
                case 0:
                    stop = true;
                    break;
                case 1:
                    System.out.println();
                    showSeats();
                    break;
                case 2:
                    System.out.println();
                    bookSeat();
                    break;
                case 3:
                    System.out.println();
                    statistics();
                    break;
                default:
                    System.out.println("Option not recognized!");
            }
        }
    }
    
    
    private int totalIncome() {
        int total = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total += seatPrice(i + 1, j + 1);
            }
        }
        
        return total;
    }
    
    
    private int seatPrice(int row, int col) {
        int price;
        if (totalSeats <= smallSize) {
            price = Cinema.firstHalfCost;
        } else {
            if (row > rows / 2) {
                price = Cinema.secondHalfCost;
            } else {
                price = Cinema.firstHalfCost;
            }
        }
        
        return price;
    }
    
    
    private void statistics() {
        int booked = 0;
        int currentIncome = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seats[i][j] == 'B') {
                    booked++;
                    currentIncome += seatPrice(i + 1, j + 1);
                }
            }
        }
        
        System.out.println("Number of purchased tickets: " + booked +
                            "\nPercentage: " + String.format("%.2f", booked * 100.00f /totalSeats) + "%" +
                            "\nCurrent income: $" + currentIncome +
                            "\nTotal income: $" + totalIncome());        
        
        
    }
    
    
    private void bookSeat() {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int col = scanner.nextInt();
        
        int colSize = seats != null ? seats.length : 0;
        int rowSize = seats != null && seats[0] != null ? seats[0].length : 0;
        
        if (row <= 0 || col <= 0 || rowSize < row || colSize < col) {
            System.out.println("Wrong input!");
            bookSeat();
            return;
        }
        
        int price = seatPrice(row, col);
        
        if (seats[row - 1][col - 1] == 'B') {
            System.out.println("That ticket has already been purchased");
            bookSeat();
            return;
        }
        
        seats[row - 1][col - 1] = 'B';
        
        System.out.println("Ticket price: $" + price);
    }
    
    
    private void showSeats() {
        System.out.println("Cinema:");
        
        for (int i = -1; i < rows; i++) {
            if (i == -1) {
                System.out.print("  ");
                for (int j = 1; j < cols + 1; j++) {
                    System.out.print(j + " ");
                }
                
                System.out.println();
            } else {
                System.out.print(i + 1 + " ");
                for (int j = 0; j < cols; j++) {
                    System.out.print(seats[i][j] + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
        
    
}
