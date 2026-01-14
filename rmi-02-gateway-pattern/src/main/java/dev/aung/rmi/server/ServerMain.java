package dev.aung.rmi.server;

import dev.aung.rmi.commons.Constant;
import dev.aung.rmi.commons.service.Calculator;
import dev.aung.rmi.commons.service.StudentService;
import dev.aung.rmi.commons.service.impl.CalculatorImpl;
import dev.aung.rmi.commons.service.impl.StudentServiceImpl;
import dev.aung.rmi.gateway.impl.RMIGateWayImpl;

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
            container.put(StudentService.class.getSimpleName(), new StudentServiceImpl());
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
