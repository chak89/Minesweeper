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

            while(minesweeper.status() == Minesweeper.Status.START || minesweeper.status() == Minesweeper.Status.PROGRESS) {
                System.out.print("Set/unset mines marks or claim a cell as free: ");
                int x = sc.nextInt();
                int y = sc.nextInt();
                String command = sc.next();

                minesweeper.inputCommand(x,y,command);
                minesweeper.printField();
            }

            if (minesweeper.status() == Minesweeper.Status.WON) {
                System.out.println("Congratulations! You found all the mines!");
            }
            else if(minesweeper.status() == Minesweeper.Status.LOST) {
                System.out.println("You stepped on a mine and failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
