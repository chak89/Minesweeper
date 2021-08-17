package minesweeper;

import java.util.*;

public class Minesweeper {
    private final Cell[][] field;
    private final int mines;
    Status status;

    public enum Status {
        START,
        PROGRESS,
        WON,
        LOST
    }

    public Minesweeper(int mines) {
        field = new Cell[9][9];
        status = Status.START;
        this.mines = mines;
        populateField(mines);
        calculateAdjCells();
    }

    public Status status() {
        return status;
    }

    public void checkIfWon() {
        int matches = 0;
        //All mines are marked
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                Cell cell = field[y][x];
                if (cell.isMine() && cell.isMarked()) {
                    matches++;
                    if (matches == this.mines) {
                        this.status = Status.WON;
                    }
                }
            }
        }

        matches = 0;
        //All safe cells are opened
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                Cell cell = field[y][x];
                if (!cell.isMine() && cell.isExplored()) {
                    matches++;
                    if (matches == (field.length * field.length) - mines) {
                        this.status = Status.WON;
                    }
                }
            }
        }

    }


    public void inputCommand(int x, int y, String command) {
        //Explore a cell
        if (command.equals("free")) {
            exploreCell(x, y);
        }

        //Mark/unmark unexplored cells with *
        if (command.equals("mine")) {
            markAndUnmarkCell(x, y);
        }

//        if (status == Status.START) {
//            return;
//        }
//
        if(status == Status.LOST) {
            return;
        }
        checkIfWon();
    }


    public void exploreCell(int x, int y) {

        //If cell is empty and doesn't have mines around
        if (!field[y - 1][x - 1].isMine() && field[y - 1][x - 1].getAdjCellsWithMines() == 0) {

            status = Status.PROGRESS;
            Queue<Cell> emptyAdjCells = field[y - 1][x - 1].getAllAdjacentCells();
            field[y - 1][x - 1].exploreCell();

            for (Cell cell : emptyAdjCells) {
                exploreAdjacentCells(cell);
            }

        }
        //If cell is empty and has mines around it
        else if (!field[y - 1][x - 1].isMine() && field[y - 1][x - 1].isNumber()) {
            field[y - 1][x - 1].exploreCell();
            status = Status.PROGRESS;
        }
        //If cell contains mine
        else if (field[y - 1][x - 1].isMine()) {
            field[y - 1][x - 1].exploreCell();

//            if (status == Status.START) {
//                System.out.println("Hitted X on first run");
//                populateField(this.mines);
//                calculateAdjCells();
//                exploreCell(x,y);
//                return;
//            }

            this.status = Status.LOST;
        }
    }


    //Explore all adjacent empty cells.
    public void exploreAdjacentCells(Cell cell) {
        if (!cell.isMine() && cell.getAdjCellsWithMines() == 0) {
            cell.exploreCell();
            Queue<Cell> emptyAdjCells2 = cell.getAllAdjacentCells();

            for (Cell cell2 : emptyAdjCells2) {
                if (!cell2.isExplored()) {
                    exploreAdjacentCells(cell2);
                }
            }
        }
        cell.exploreCell();
    }


    //Mark/unmark unexplored cells with *
    public void markAndUnmarkCell(int x, int y) {

        //If cell is already explored, then return
        if (field[y - 1][x - 1].isExplored()) {
            System.out.println("Cell is already explored");
        }
        //If cell is marked
        else if (field[y - 1][x - 1].isMarked()) {
            field[y - 1][x - 1].unmarkCell();
        }
        //If cell is unmarked
        else if (!field[y - 1][x - 1].isMarked()) {
            field[y - 1][x - 1].markCell();
        }
    }


    //Loop as xy-axis
    private void calculateAdjCells() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {

                Cell cell = field[y][x];

                //As long as this cell does not contain a mine, do the adjacent mines check
                if (!cell.isMine()) {
                    checkCellRight(cell, x, y);
                    checkCellDiagonalDownRight(cell, x, y);
                    checkCellDown(cell, x, y);
                    checkCellDiagonalDownLeft(cell, x, y);
                    checkCellLeft(cell, x, y);
                    checkCellDiagonalUpLeft(cell, x, y);
                    checkCellUp(cell, x, y);
                    cellCellDiagonalUpRight(cell, x, y);
                }
            }
        }
    }

    //Check cell to the right
    private void checkCellRight(Cell cell, int x, int y) {
        if (x + 1 <= field.length - 1) {
            if (field[y][x + 1].isMine()) {
                cell.addMine(field[y][x + 1]);
            }
            cell.addAdjacentCell(field[y][x + 1]);
        }
    }

    //Check cell to the down diagonal-right
    private void checkCellDiagonalDownRight(Cell cell, int x, int y) {
        if ((x + 1) <= field.length - 1 && (y + 1) <= field.length - 1) {
            if (field[y + 1][x + 1].isMine()) {
                cell.addMine(field[y + 1][x + 1]);
            }
            cell.addAdjacentCell(field[y + 1][x + 1]);
        }
    }

    //Check cell down from this cell
    private void checkCellDown(Cell cell, int x, int y) {
        if (y + 1 <= field.length - 1) {
            if (field[y + 1][x].isMine()) {
                cell.addMine(field[y + 1][x]);
            }
            cell.addAdjacentCell(field[y + 1][x]);
        }
    }

    //Check cell to lower diagonal-left
    private void checkCellDiagonalDownLeft(Cell cell, int x, int y) {
        if ((y + 1) <= field.length - 1 && (x - 1) >= 0) {
            if (field[y + 1][x - 1].isMine()) {
                cell.addMine(field[y + 1][x - 1]);
            }
            cell.addAdjacentCell(field[y + 1][x - 1]);
        }
    }

    private void checkCellLeft(Cell cell, int x, int y) {
        if ((x - 1) >= 0) {
            if (field[y][x - 1].isMine()) {
                cell.addMine(field[y][x - 1]);
            }
            cell.addAdjacentCell(field[y][x - 1]);
        }
    }

    //Check cell to the upper diagonal-left
    private void checkCellDiagonalUpLeft(Cell cell, int x, int y) {
        if ((x - 1) >= 0 && (y - 1) >= 0) {
            if (field[y - 1][x - 1].isMine()) {
                cell.addMine(field[y - 1][x - 1]);
            }
            cell.addAdjacentCell(field[y - 1][x - 1]);
        }
    }

    //Check cell up
    private void checkCellUp(Cell cell, int x, int y) {
        if (y - 1 >= 0) {
            if (field[y - 1][x].isMine()) {
                cell.addMine(field[y - 1][x]);
            }
            cell.addAdjacentCell(field[y - 1][x]);
        }
    }

    private void cellCellDiagonalUpRight(Cell cell, int x, int y) {
        if ((x + 1) <= field.length - 1 && (y - 1) >= 0) {
            if (field[y - 1][x + 1].isMine()) {
                cell.addMine(field[y - 1][x + 1]);
            }
            cell.addAdjacentCell(field[y - 1][x + 1]);
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
        //set every cell to string .
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                Cell cell = stack.pop();
                field[y][x] = cell;
            }
        }
    }


    public void printField() {

        System.out.println(" |123456789|");
        System.out.println("-|---------|");

        int counter = 1;

        for (int i = 0; i < field.length; i++) {
            System.out.print((counter++) + "|");
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j].getDisplay());
            }
            System.out.println("|");
        }

        System.out.println("-|---------|");
    }
}
