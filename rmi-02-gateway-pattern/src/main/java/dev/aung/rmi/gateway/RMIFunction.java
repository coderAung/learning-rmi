package dev.aung.rmi.gateway;

import java.io.Serializable;

@FunctionalInterface
public interface RMIFunction<T, R> extends Serializable {
    R apply(T t);

    static <T, R> RMIFunction<T, R> of(RMIFunction<T, R> func) {
        return func;
    }
}
