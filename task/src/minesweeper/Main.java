package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");

        try {
            int mines = sc.nextInt();
            Minesweeper minesweeper = new Minesweeper(mines);
            minesweeper.calculateAdjCells();
            minesweeper.printField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
