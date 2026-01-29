package dev.aung.app.game;

import java.io.Serial;
import java.io.Serializable;

public record GamePlayer(
        PlayerType playerType,
        String ipAddress
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
