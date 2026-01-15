package dev.aung.rmi.api.outputs;

import java.io.Serial;
import java.io.Serializable;

public record StudentDetail(
        int id,
        String name,
        int age,
        String year
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
