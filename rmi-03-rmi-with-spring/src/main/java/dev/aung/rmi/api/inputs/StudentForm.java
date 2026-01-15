package dev.aung.rmi.api.inputs;

import java.io.Serial;
import java.io.Serializable;

public record StudentForm(
        String name,
        int age,
        String year
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
