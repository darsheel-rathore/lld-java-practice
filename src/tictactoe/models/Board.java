package src.tictactoe.models;

import src.tictactoe.enums.Symbol;
import src.tictactoe.exceptions.InvalidMoveException;

public class Board {

    private int size;
    private Cell[][] grid;
    private int filledCells;

    public Board(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        // Grid creation
        for(int m = 0; m < size; m++) {
            for(int n = 0; n < size; n++) {
                grid[m][n] = new Cell(m, n);
            }
        }
    }

    public void makeMove(Cell cell, Symbol symbol) {
        if(!isValidMove(cell)) {
            // If not a valid move throw exception
            throw new InvalidMoveException(
                    "Cell (" + cell.getRow() + "," + cell.getColumn() + ") is already occupied!"
            );
        }
        // Pick the cell and set its symbol
        grid[cell.getRow()][cell.getColumn()].setSymbol(symbol);
        // Up
        filledCells++;
    }

    public void undoMove(Cell cell) {
        // remove the entry from the cell
        grid[cell.getRow()][cell.getColumn()].setSymbol(Symbol.NONE);
        // Decrement the filled cells
        filledCells--;
    }

    public boolean isValidMove(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();

        // Check the param cell is within the bounds of the grid
        if(row < 0 || row >= size || column < 0 || column >= size) return false;

        // Check whether the grid is empty for that position
        // Basically isValidMove checks whether the incoming cell can be placed
        // on the grid, by checking the grid is empty for that position
        return grid[row][column].isEmpty();
    }

    public boolean isFull() {
        return filledCells == size * size;
    }

    public void display() {
        System.out.println();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Symbol s = grid[i][j].getSymbol();
                System.out.print(s == Symbol.NONE ? " . " : " " + s + " ");
                if(j < size - 1) System.out.print("|");
            }
            System.out.println();
            if(i < size - 1) System.out.println("---+---+---");
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }
}
