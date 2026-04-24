package src.easy.tictactoe.models;

import src.easy.tictactoe.enums.Symbol;

public class Cell {

    private int row;
    private int column;
    private Symbol symbol;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.symbol = Symbol.NONE; // Initially, all the symbols will be empty
    }

    public boolean isEmpty() {
        return this.symbol == Symbol.NONE;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setSymbol(Symbol type) {
        this.symbol = type;
    }
}
