package dev.ywa.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    int add(int n1, int n2) throws RemoteException;
}
