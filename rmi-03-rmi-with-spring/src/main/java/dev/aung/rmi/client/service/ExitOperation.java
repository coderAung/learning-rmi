package dev.aung.rmi.client.service;

import dev.aung.rmi.client.meta.Operation;

public class ExitOperation extends Operation {

    public ExitOperation() {
        super("Exit");
    }

    @Override
    public void doWork() {
        System.out.println("===================================");
        System.out.println("Good bye from rmi demo app.");
        System.out.println("===================================");
        System.exit(0);
    }
}
