package dev.aung.rmi.client;

import java.rmi.RemoteException;

interface RMIBiFunction<T, U, R> {
    R apply(T t, U u) throws RemoteException;
}
