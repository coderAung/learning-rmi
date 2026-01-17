package dev.aung.rmi.client;

import dev.aung.rmi.commons.Calculator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Calculator calculator = (Calculator) Naming.lookup("rmi://localhost:1099/calculator");
        ClientMain.run(calculator);
    }

    static void run(Calculator calculator) throws RemoteException {
        System.out.println("+++++ Welcome to Calculator +++++");
        do {
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.print(" >>> ");
            String input = scanner.nextLine();

            try {
                RMIBiFunction<Double, Double, Double> func = switch (input) {
                    case "1" -> calculator::add;
                    case "2" -> calculator::subtract;
                    case "3" -> calculator::multiply;
                    case "4" -> calculator::divide;
                    default -> throw new IllegalArgumentException("Invalid Input");
                };
                System.out.print("Enter First Number  : ");
                double n1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter Second Number : ");
                double n2 = Double.parseDouble(scanner.nextLine());
                double result = func.apply(n1, n2);
                System.out.println("Result = " + result);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while(askContinue());

        System.out.println("+++++ Goodbye from Calculator +++++");
    }

    static boolean askContinue() {
        System.out.print("Do you want to continue? (Y/N) : ");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }
}
