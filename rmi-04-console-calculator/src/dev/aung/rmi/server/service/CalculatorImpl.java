package dev.aung.rmi.server.service;

import dev.aung.rmi.commons.Calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    public CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double add(double n1, double n2) throws RemoteException {
        return n1 + n2;
    }

    @Override
    public double subtract(double n1, double n2) throws RemoteException {
        return n1 - n2;
    }

    @Override
    public double multiply(double n1, double n2) throws RemoteException {
        return n1 * n2;
    }

    @Override
    public double divide(double n1, double n2) throws RemoteException {
        if(n2 == 0) {
            throw new IllegalArgumentException(n1 + " cannot be divided by " + n2);
        }
        return n1 / n2;
    }
}
