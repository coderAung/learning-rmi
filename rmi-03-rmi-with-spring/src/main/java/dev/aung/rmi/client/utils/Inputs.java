package dev.aung.rmi.client.utils;

import java.util.Scanner;

public abstract class Inputs {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int getInt(String message) {
        return Integer.parseInt(getString(message));
    }
}
