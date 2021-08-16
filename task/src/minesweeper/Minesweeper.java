package minesweeper;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Minesweeper {
    private final String[][] field;
    private int mines;
    private Random rnd;

    public Minesweeper(int mines) {
        field = new String[9][9];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = ".";
            }
        }

        this.mines = mines;

        rnd = new Random();
        populateField(mines);
    }

    private void populateField(int mines) {

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < field.length* field.length - mines; i++) {
            stack.push(".");
        }

        for (int i = 0; i < mines; i++) {
            stack.push("X");
        }

        //Shuffle elements order
        Collections.shuffle(stack);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = stack.pop();
            }
        }
    }


    public void printField() {
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
