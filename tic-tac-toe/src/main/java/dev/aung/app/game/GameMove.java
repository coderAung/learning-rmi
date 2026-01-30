package dev.aung.app.game;

import java.io.Serial;
import java.io.Serializable;

public record GameMove(
        PlayerType type,
        TicTacToeCell cell
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
