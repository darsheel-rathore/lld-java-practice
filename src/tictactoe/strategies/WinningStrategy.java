package src.tictactoe.strategies;

import src.tictactoe.models.Board;
import src.tictactoe.models.Move;

public interface WinningStrategy {

    boolean checkWin(Board board, Move lastMove);
}
