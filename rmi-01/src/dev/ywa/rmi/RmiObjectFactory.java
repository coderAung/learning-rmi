package dev.ywa.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiObjectFactory extends Remote {
    Calculator getCalculator() throws RemoteException;
}
