package dev.aung.rmi.commons.service.impl;

import dev.aung.rmi.commons.service.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int add(int n1, int n2) {
        return n1 + n2;
    }

}
