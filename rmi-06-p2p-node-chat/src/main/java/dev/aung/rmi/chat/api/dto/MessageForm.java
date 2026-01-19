package dev.aung.rmi.chat.api.dto;

import java.io.Serial;
import java.io.Serializable;

public record MessageForm(
        String from,
        String to,
        String message
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}
