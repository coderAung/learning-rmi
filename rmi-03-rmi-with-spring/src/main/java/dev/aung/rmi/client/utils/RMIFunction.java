package dev.aung.rmi.client.utils;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface RMIFunction<T, R> extends Serializable {
    R apply(T t) throws RemoteException;
}
