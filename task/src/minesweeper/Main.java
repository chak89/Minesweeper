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
            minesweeper.printField();

            while(!minesweeper.isFinished()) {
                System.out.print("Set/delete mine marks (x and y coordinates): ");
                int x = sc.nextInt();
                int y = sc.nextInt();

                minesweeper.markCell(x,y);
                minesweeper.printField();
                minesweeper.checkIfWon();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
