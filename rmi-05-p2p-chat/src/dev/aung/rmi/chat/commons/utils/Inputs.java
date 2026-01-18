package dev.aung.rmi.chat.commons.utils;

import java.util.Scanner;

public abstract class Inputs {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
