package src.easy.tictactoe;

import src.easy.tictactoe.enums.GameStatus;
import src.easy.tictactoe.exceptions.InvalidBuildException;
import src.easy.tictactoe.exceptions.InvalidMoveException;
import src.easy.tictactoe.models.Board;
import src.easy.tictactoe.models.Cell;
import src.easy.tictactoe.models.Move;
import src.easy.tictactoe.models.Player;
import src.easy.tictactoe.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Board board;
    private List<Player> players;
    private List<Move> moveHistory;
    private WinningStrategy winningStrategy;
    private GameStatus status;
    private int currentPlayerIndex;
    private Player winner;

    // Use builder pattern
    private Game(Board board, List<Player> players, WinningStrategy winningStrategy) {
        this.board = board;
        this.players = players;
        this.winningStrategy = winningStrategy;
        this.moveHistory = new ArrayList<>();
        this.status = GameStatus.IN_PROGRESS;
        this.currentPlayerIndex = 0;
    }

    // Builder
    public static class Builder {
        private int boardSize;
        private List<Player> players = new ArrayList<>();
        private WinningStrategy winningStrategy;

        public Builder boardSize(int size) {
            this.boardSize = size;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy strategy) {
            this.winningStrategy = strategy;
            return this;
        }

        public Game build() {
            if(boardSize <= 0) {
                throw new InvalidBuildException("Please set the size of the board!!");
            } else if (winningStrategy == null) {
                throw new InvalidBuildException("Please set the winning strategy!!");
            } else if (boardSize <= 0 && winningStrategy == null) {
                throw new InvalidBuildException("Please set the board size and the winning strategy!!");
            }
            return new Game(new Board(boardSize), players, winningStrategy);
        }
    }

    // Core Methods
    public void makeMove(int row, int column) {
        if(status != GameStatus.IN_PROGRESS) {
            System.out.println("Game is already over!");
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        Cell cell = board.getCell(row, column);

        // Bot makes random move
        if(currentPlayer.isBot()) {
            cell = getRandomEmptyCell();
            System.out.println(currentPlayer.getName() + " (Bot) plays at (" + cell.getRow() + ","
                    + cell.getColumn() + ")");
        }

        try {
            board.makeMove(cell, currentPlayer.getSymbol());
        } catch (InvalidMoveException e) {
            System.out.println("Invalid move: " + e.getMessage());
            return;
        }

        Move move = new Move(currentPlayer, cell);
        moveHistory.add(move);
        board.display();

        if(winningStrategy.checkWin(board, move)) {
            status = GameStatus.WIN;
            winner = currentPlayer;
            System.out.println(winner.getName() + " wins!");
            return;
        }

        if(board.isFull()) {
            status = GameStatus.DRAW;
            System.out.println("It's a draw!");
            return;
        }

        switchPlayer();
    }

    public void undoLastMove() {
        if(moveHistory.isEmpty()) {
            System.out.println("No moves to undo!");
        }

        Move lastMove = moveHistory.remove(moveHistory.size()-1);
        board.undoMove(lastMove.getCell());

        // Switch back to previous player
        currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        System.out.println("Undo successful. " + lastMove.getPlayer().getName() + "'s move reversed");
        board.display();
    }

    public void replayGame() {
        System.out.println("\n---Replaying Game---");
        Board replayBoard = new Board(board.getSize());
        for(Move move : moveHistory) {
            replayBoard.makeMove(move.getCell(), move.getPlayer().getSymbol());
            System.out.println(move.getPlayer().getName()
            + " played at (" + move.getCell().getRow()
            + "," + move.getCell().getColumn() + ")");
            replayBoard.display();
        }
    }

    // Private helper
    private void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private Cell getRandomEmptyCell() {
        List<Cell> emptyCells = new ArrayList<>();
        Cell[][] grid = board.getGrid();

        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                if(grid[i][j].isEmpty()) {
                    emptyCells.add(grid[i][j]);
                }
            }
        }
        return emptyCells.get(new Random().nextInt(emptyCells.size()));
    }

    // Getters
    public GameStatus getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }
}
