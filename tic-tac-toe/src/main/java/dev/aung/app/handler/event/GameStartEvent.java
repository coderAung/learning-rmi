package dev.aung.app.handler.event;

import dev.aung.app.game.PlayerType;
import dev.aung.app.rmi.GameServer;

import java.io.Serial;
import java.io.Serializable;

public record GameStartEvent(
        PlayerType playerType,
        String ipAddress,
        GameServer server
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
