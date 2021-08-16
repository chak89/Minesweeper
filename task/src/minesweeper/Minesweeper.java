package minesweeper;

import java.util.Collections;
import java.util.Stack;

public class Minesweeper {
    private final Cell[][] field;

    public Minesweeper(int mines) {
        field = new Cell[9][9];
        populateField(mines);
    }

    //Loop as xy-axis
    public void calculateAdjCells() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {

                Cell cell = field[y][x];

                if (!cell.isMine()) {
                    checkCellRight(cell, x, y);
                    checkCellDiagonalDownRight(cell, x, y);
                    checkCellDown(cell, x, y);
                    checkCellDiagonalDownLeft(cell, x, y);
                    checkCellLeft(cell, x, y);
                    checkCellDiagonalUpLeft(cell, x, y);
                    checkCellUp(cell, x, y);
                    cellCellDiagonalUpRight(cell, x, y);
                    cell.setCell(String.valueOf(cell.getAdjMines()));
                }

            }
        }
    }

    //Check cell to the right
    private void checkCellRight(Cell cell, int x, int y) {
        if (x + 1 <= field.length - 1 && field[y][x + 1].isMine()) {
            cell.addMine();
            return;
        }
        //System.out.println("Cell to the right is out of bound");
    }

    //Check cell to the down diagonal-right
    private void checkCellDiagonalDownRight(Cell cell, int x, int y) {
        if ((x + 1) <= field.length - 1 && (y + 1) <= field.length - 1) {
            if (field[y + 1][x + 1].isMine()) {
                cell.addMine();
                return;
            }
            //System.out.println("Cell to the down diagonal-right is out of bound");
        }
    }

    //Check cell down from this cell
    private void checkCellDown(Cell cell, int x, int y) {
        if (y + 1 <= field.length - 1 && field[y + 1][x].isMine()) {
            cell.addMine();
            return;
        }
        //System.out.println("Cell down is out of bound");
    }

    //Check cell to lower diagonal-left
    private void checkCellDiagonalDownLeft(Cell cell, int x, int y) {
        if ((y + 1) <= field.length - 1 && (x - 1) >= 0) {
            if (field[y + 1][x - 1].isMine()) {
                cell.addMine();
                return;
            }
            //System.out.println("Cell to the down diagonal-left is out of bound");
        }
    }

    private void checkCellLeft(Cell cell, int x, int y) {
        if ((x - 1) >= 0 && field[y][x - 1].isMine()) {
            cell.addMine();
            return;
        }
        //System.out.println("Cell to the left is out of bound");
    }

    //Check cell to the upper diagonal-left
    private void checkCellDiagonalUpLeft(Cell cell, int x, int y) {
        if ((x - 1) >= 0 && (y - 1) >= 0) {
            if (field[y - 1][x - 1].isMine()) {
                cell.addMine();
                return;
            }
            //System.out.println("Cell to the upper diagonal-left is out of bound");
        }
    }

    //Check cell up
    private void checkCellUp(Cell cell, int x, int y) {
        if (y - 1 >= 0 && field[y - 1][x].isMine()) {
            cell.addMine();
            return;
        }
        //System.out.println("Cell up is out of bound");
    }

    private void cellCellDiagonalUpRight(Cell cell, int x, int y) {
        if ((x + 1) <= field.length - 1 && (y - 1) >= 0) {
            if (field[y - 1][x + 1].isMine()) {
                cell.addMine();
                return;
            }
            //System.out.println("Cell to the up diagonal-right is out of bound");
        }
    }


    private void populateField(int mines) {
        Stack<Cell> stack = new Stack<>();

        for (int i = 0; i < (field.length * field.length) - mines; i++) {
            stack.push(new Cell("."));
        }

        for (int i = 0; i < mines; i++) {
            stack.push(new Cell("X"));
        }

        //Shuffle elements order
        Collections.shuffle(stack);

        //populate the field
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                Cell cell = stack.pop();
                field[y][x] = cell;
            }
        }
    }


    public void printField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j].getCell());
            }
            System.out.println();
        }
    }
}
