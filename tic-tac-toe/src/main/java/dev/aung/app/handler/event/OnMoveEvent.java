package dev.aung.app.handler.event;

import dev.aung.app.game.PlayerType;

import java.io.Serial;
import java.io.Serializable;

public record OnMoveEvent(
        int row,
        int col,
        PlayerType type
) implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;
}
