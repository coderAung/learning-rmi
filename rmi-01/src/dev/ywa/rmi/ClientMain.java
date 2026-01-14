package dev.ywa.rmi;

import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args) {
        try{
            var factory = (RmiObjectFactory) Naming.lookup("rmi://localhost:1099/factory");
            var c = factory.getCalculator();
            System.out.println(c.add(4, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
