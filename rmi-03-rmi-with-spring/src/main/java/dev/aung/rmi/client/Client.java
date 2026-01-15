package dev.aung.rmi.client;

import dev.aung.rmi.client.meta.Operation;
import dev.aung.rmi.client.service.AddStudentOperation;
import dev.aung.rmi.client.service.ExitOperation;
import dev.aung.rmi.client.service.ViewStudentsOperation;

import java.util.List;
import java.util.Scanner;

public class Client {

    private static final Scanner scanner = new Scanner(System.in);
    private final List<Operation> operations;

    public Client(List<Operation> operations) {
        this.operations = operations;
    }

    public static void main(String[] args) {
        Client.run(new Client(List.of(
                new ViewStudentsOperation(),
                new AddStudentOperation(),
                new ExitOperation()
        )));
    }

    private static void run(Client client) {
        System.out.println("===================================");
        System.out.println("Welcome to rmi demo app.");
        System.out.println("===================================");

        do {
            for(int i = 0; i < client.operations.size(); i ++) {
                System.out.printf("%-2s - %s%n", i + 1, client.operations.get(i).getTitle());
            }
            System.out.print(">>> ");

            var input = scanner.nextLine();

            try {
                var op = client.operations.get(Integer.parseInt(input) - 1);
                op.doWork();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println("Invalid Input.");
                System.out.println(e.getMessage());
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
            }
        } while (true);

    }

    private boolean willContinue() {
        System.out.print("Do you want to continue ? (Y/N) : ");
        var input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

}
