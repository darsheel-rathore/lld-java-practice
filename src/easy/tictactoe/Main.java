package src.easy.tictactoe;

import src.easy.tictactoe.enums.GameStatus;
import src.easy.tictactoe.enums.Symbol;
import src.easy.tictactoe.models.Player;
import src.easy.tictactoe.strategies.DefaultWinningStrategy;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // All scenarios are added
        // Uncomment them if you want to check.
        scenario1_PlayerOneWins();
//        scenario4_PlayerVsBot();
//        scenario2_DrawGame();
//        scenario3_UndoMove();
    }

    static void scenario1_PlayerOneWins() {
        System.out.println("════════════════════════════════");
        System.out.println("  SCENARIO 1: Player 1 Wins");
        System.out.println("════════════════════════════════");

        Player p1 = new Player("Alice", Symbol.X, false);
        Player p2 = new Player("Darsheel", Symbol.O, false);

        Game game = new Game.Builder()
                .boardSize(3)
                .addPlayer(p1)
                .addPlayer(p2)
                .addWinningStrategy(new DefaultWinningStrategy())
                .build();

        game.makeMove(0, 0); // Alice
        game.makeMove(1, 0); // Darsheel
        game.makeMove(0, 1); // Alice
        game.makeMove(1, 1); // Darsheel
        game.makeMove(0, 2); // Alice wins (top row)
    }

    static void scenario4_PlayerVsBot() {
        System.out.println("════════════════════════════════");
        System.out.println("  SCENARIO 1: Player vs Bot");
        System.out.println("════════════════════════════════");

        Player p1 = new Player("Alice", Symbol.X, false);
        Player p2 = new Player("Darsheel", Symbol.O, true); // Bot Player
        int boardSize = 3;
        Game game = new Game.Builder()
                .boardSize(boardSize)
                .addPlayer(p1)
                .addPlayer(p2)
                .addWinningStrategy(new DefaultWinningStrategy())
                .build();

        while(game.getStatus() == GameStatus.IN_PROGRESS) {
            game.makeMove(new Random().nextInt(boardSize), new Random().nextInt(boardSize));
        }
    }

    static void scenario2_DrawGame() {
        System.out.println("════════════════════════════════");
        System.out.println("  SCENARIO 2: Draw Game");
        System.out.println("════════════════════════════════");

        Player p1 = new Player("Alice", Symbol.X, false);
        Player p2 = new Player("Bob", Symbol.O, false);

        Game game = new Game.Builder()
                .boardSize(3)
                .addPlayer(p1)
                .addPlayer(p2)
                .addWinningStrategy(new DefaultWinningStrategy())
                .build();

        // X O X
        // X X O
        // O X O  → Draw
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 2); // O
        game.makeMove(1, 0); // X
        game.makeMove(2, 0); // O
        game.makeMove(1, 1); // X
        game.makeMove(2, 2); // O
        game.makeMove(2, 1); // X → Draw
    }

    static void scenario3_UndoMove() {
        System.out.println("════════════════════════════════");
        System.out.println("  SCENARIO 3: Undo Move");
        System.out.println("════════════════════════════════");

        Player p1 = new Player("Alice", Symbol.X, false);
        Player p2 = new Player("Bob", Symbol.O, false);

        Game game = new Game.Builder()
                .boardSize(3)
                .addPlayer(p1)
                .addPlayer(p2)
                .addWinningStrategy(new DefaultWinningStrategy())
                .build();

        game.makeMove(0, 0); // Alice
        game.makeMove(1, 1); // Bob
        game.makeMove(0, 1); // Alice
        game.undoLastMove(); // Undo Alice's last move
        game.makeMove(2, 2); // Alice plays again
    }

}
