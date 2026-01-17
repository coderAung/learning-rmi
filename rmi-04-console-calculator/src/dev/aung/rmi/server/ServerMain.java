package dev.aung.rmi.server;

import dev.aung.rmi.commons.Calculator;
import dev.aung.rmi.server.service.CalculatorImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerMain {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        LocateRegistry.createRegistry(1099);

        Calculator calculator = new CalculatorImpl();
        Naming.rebind("rmi://localhost:1099/calculator", calculator);
        System.out.println("====================================");
        System.out.println("RMI Server is running ...");
        System.out.println("====================================");
    }
}
