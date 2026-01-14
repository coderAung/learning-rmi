package dev.aung.rmi.client;

import dev.aung.rmi.commons.Constant;
import dev.aung.rmi.service.Calculator;
import dev.aung.rmi.utils.RMIFunction;
import dev.aung.rmi.utils.RMIGateWay;

import java.rmi.Naming;

public class ClientMain {

    public static void main(String[] args) {
        try {
            var gateway = (RMIGateWay) Naming.lookup(Constant.GATEWAY_URL);
            var result = gateway.execute(Calculator.class, c -> c.add(10, 20));
            System.out.println("===== result from remote object");
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
