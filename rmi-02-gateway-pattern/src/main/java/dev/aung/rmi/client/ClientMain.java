package dev.aung.rmi.client;

import dev.aung.rmi.commons.Constant;
import dev.aung.rmi.commons.inputs.StudentRegisterForm;
import dev.aung.rmi.commons.service.Calculator;
import dev.aung.rmi.commons.service.StudentService;
import dev.aung.rmi.gateway.RMIFunction;
import dev.aung.rmi.gateway.RMIGateWay;

import java.rmi.Naming;


public class ClientMain {

    public static void main(String[] args) {
        try {
            var gateway = (RMIGateWay) Naming.lookup(Constant.GATEWAY_URL);
            var result = gateway.execute(Calculator.class, c -> c.add(10, 20));
            System.out.println("===== result from remote object");
            System.out.println(result);
            var form = new StudentRegisterForm("Aung Aung", 22);
            gateway.execute(StudentService.class, RMIFunction.of(s -> s.register(form)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
