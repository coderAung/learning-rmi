package dev.aung.rmi.commons;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    double add(double n1, double n2) throws RemoteException;

    double subtract(double n1, double n2) throws RemoteException;

    double multiply(double n1, double n2) throws RemoteException;

    double divide(double n1, double n2) throws RemoteException;

}
