package dev.aung.rmi.utils;

import java.io.Serializable;

@FunctionalInterface
public interface RMIFunction<T, R> extends Serializable {
    R apply(T t);
}
