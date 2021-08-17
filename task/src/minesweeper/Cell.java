package minesweeper;

public class Cell {
    private String display;
    private int adjMines;
    private Status status;
    private final boolean mine;

    public Cell(String display) {
        this.display = display;
        this.adjMines = 0;

        if (display.equals("X")) {
            status = Status.MINE;
            this.mine = true;
            setDisplay(".");
        } else {
            status = Status.UNMARKED;
            this.mine = false;
        }
    }

    public void setStatus(String display) {
        if (!display.equals("0")) {
            setDisplay(display);
            status = Status.NUMBER;
        }
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getAdjMines() {
        return adjMines;
    }

    public void addMine() {
        this.adjMines++;
    }

    public boolean isMine(){
        return this.mine;
    }

    public boolean isMarked() {
        return status == Status.MARKED;
    }

    public boolean isNumber() {
        return status == Status.NUMBER;
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
    MINE,
    NUMBER,
    UNMARKED,
    MARKED
}

