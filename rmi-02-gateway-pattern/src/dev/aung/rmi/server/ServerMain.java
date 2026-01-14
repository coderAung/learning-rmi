package dev.aung.rmi.server;

import dev.aung.rmi.commons.Constant;
import dev.aung.rmi.service.Calculator;
import dev.aung.rmi.service.impl.CalculatorImpl;
import dev.aung.rmi.utils.impl.RMIGateWayImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

public class ServerMain {

    private static final Map<String, Object> container = new HashMap<>();

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            container.put(Calculator.class.getSimpleName(), new CalculatorImpl());

            var gateway = new RMIGateWayImpl(container);
            Naming.bind(Constant.GATEWAY_URL, gateway);

            System.out.println("=====================");
            System.out.println("RMI server is running.");
            System.out.println("=====================");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
