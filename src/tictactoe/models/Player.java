package src.tictactoe.models;

import src.tictactoe.enums.Symbol;

public class Player {

    private String name;
    private Symbol symbol;
    private boolean isBot;

    public Player(String name, Symbol symbol, boolean isBot) {
        this.name = name;
        this.symbol = symbol;
        this.isBot = isBot;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public boolean isBot() {
        return isBot;
    }
}
