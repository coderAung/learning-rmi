package dev.aung.app.handler.event;

import dev.aung.app.game.PlayerType;

import java.io.Serial;
import java.io.Serializable;

public record GameEndEvent(
        PlayerType winner,
        String ipAddress
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
