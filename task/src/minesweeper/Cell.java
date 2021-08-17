package minesweeper;

import java.util.ArrayDeque;
import java.util.Queue;

public class Cell {
    private String display;
    private final Queue<Cell> allAdjacentCells;
    private final Queue<Cell> adjCellsWithMines;
    private Status status;
    private final boolean mine;

    public Cell(String display) {
        this.display = display;
        this.allAdjacentCells = new ArrayDeque<>();
        this.adjCellsWithMines = new ArrayDeque<>();

        if (display.equals("X")) {
            status = Status.UNMARKED;
            this.mine = true;
            setDisplay(".");
        } else {
            status = Status.UNMARKED;
            this.mine = false;
        }
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getAdjCellsWithMines() {
        return adjCellsWithMines.size();
    }

    public void addMine(Cell cellWithMines) {
        this.adjCellsWithMines.add(cellWithMines);
    }

    public void addAdjacentCell(Cell adjacentCell) {
        this.allAdjacentCells.add(adjacentCell);
    }

    public Queue<Cell> getAllAdjacentCells() {
        return allAdjacentCells;
    }

    public boolean isMine() {
        return this.mine;
    }

    public boolean isMarked() {
        return status == Status.MARKED;
    }

    public boolean isNumber() {
        return adjCellsWithMines.size() > 0;
    }

    public boolean isExplored() {
        return status == Status.EXPLORED;
    }

    public void exploreCell() {
        if (status == Status.EXPLORED) {
            return;
        }
        status = Status.EXPLORED;

        if (isMine()) {
            setDisplay("X");
        } else if (getAdjCellsWithMines() == 0) {
            setDisplay("/");
        } else {
            setDisplay(String.valueOf(getAdjCellsWithMines()));
        }
    }

    public void markCell() {
        status = Status.MARKED;
        setDisplay("*");
    }

    public void unmarkCell() {
        status = Status.UNMARKED;
        setDisplay(".");
    }
}

enum Status {
    UNMARKED,
    MARKED,
    EXPLORED
}

