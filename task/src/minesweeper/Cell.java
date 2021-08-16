package minesweeper;

public class Cell {
    private String cell;
    private int adjMines;

    public Cell(String cell) {
        this.cell = cell;
        this.adjMines = 0;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        if (!cell.equals("0")) {
            this.cell = cell;
        }
    }

    public int getAdjMines() {
        return adjMines;
    }

    public void addMine() {
        this.adjMines++;
    }

    public boolean isMine(){
        return cell.equals("X");
    }

}
