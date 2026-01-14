package dev.ywa.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerMain {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            var factory = new RmiObjectFactoryImpl();
            Naming.bind("rmi://localhost:1099/factory", factory);
            System.out.println("System is running");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
