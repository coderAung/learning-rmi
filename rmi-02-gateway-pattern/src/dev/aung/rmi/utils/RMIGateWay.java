package dev.aung.rmi.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIGateWay extends Remote {
    <T, R> R execute(Class<T> clz, RMIFunction<T, R> func) throws RemoteException;
}
