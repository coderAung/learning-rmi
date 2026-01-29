package dev.aung.app.game;

import java.io.Serial;
import java.io.Serializable;

public enum PlayerType implements Serializable {
    You("O"), Opponent("X");

    @Serial
    private static final long serialVersionUID = 1L;

    private final String symbol;

    PlayerType(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
