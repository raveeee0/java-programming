/*
Given a rectangle array n×m in size. Rotate it by 90 degrees clockwise, by recording the result into the new array m×n in size.

Input data format:
Input consists of the two numbers n and m, not exceeding 100, and then an array n×m in size.

Output data format:
Output the resulting array. Separate numbers by a single space in the output.
*/

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        
        int[][] array = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        
        for (int j = 0; j < col; j++) {
            for (int i = row - 1; i >= 0; i--) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        } 
    }
}
