package dev.ywa.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiObjectFactoryImpl extends UnicastRemoteObject implements RmiObjectFactory {

    private final Calculator calculator;

    protected RmiObjectFactoryImpl() throws RemoteException {
        super();
        this.calculator = new CalculatorImpl();
    }


    public Calculator getCalculator() {
        return this.calculator;
    }
}
