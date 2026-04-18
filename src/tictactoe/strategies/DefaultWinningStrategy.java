package src.tictactoe.strategies;

import src.tictactoe.enums.Symbol;
import src.tictactoe.models.Board;
import src.tictactoe.models.Cell;
import src.tictactoe.models.Move;

public class DefaultWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, Move lastMove) {

        int row = lastMove.getCell().getRow();
        int column = lastMove.getCell().getColumn();
        Symbol symbol = lastMove.getPlayer().getSymbol();
        int size = board.getSize();
        Cell[][] grid = board.getGrid();

        // Check row
        boolean rowWin = true;
        for(int c = 0; c < size; c++) {
            if(grid[row][c].getSymbol() != symbol) {
                rowWin = false;
                break;
            }
        }
        if(rowWin) return true;

        // Check column
        boolean columnWin = true;
        for(int r = 0; r < size; r++) {
            if(grid[r][column].getSymbol() != symbol) {
                columnWin = false;
                break;
            }
        }
        if(columnWin) return true;

        // Check diagonals
        if(row == column) {
            boolean diagWin = true;
            for(int i = 0; i < size; i++) {
                if(grid[i][i].getSymbol() != symbol) {
                    diagWin = false;
                    break;
                }
            }
            if(diagWin) return true;
        }

        // Check anti-diagonals
        if(row + column == size - 1) {
            boolean antiDiagWin = true;
            for(int i = 0; i < size ; i++) {
                if(grid[i][size-i-1].getSymbol() != symbol) {
                    antiDiagWin = false;
                    break;
                }
            }
            if(antiDiagWin) return true;
        }

        return false;
    }
}
