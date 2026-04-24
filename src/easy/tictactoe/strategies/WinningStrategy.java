package src.easy.tictactoe.strategies;

import src.easy.tictactoe.models.Board;
import src.easy.tictactoe.models.Move;

public interface WinningStrategy {

    boolean checkWin(Board board, Move lastMove);
}
