package minesweeper;

import java.util.Random;

public class Minesweeper {
    private final String[][] field;
    Random rnd;

    public Minesweeper(int size) {
        field = new String[9][9];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = ".";
            }
        }

        rnd = new Random();
        populateField();
    }

    private void populateField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                int num = rnd.nextInt(6);

                if (num == 0 ) {
                    field[i][j] = "X";
                } else {
                    field[i][j] = ".";
                }

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
