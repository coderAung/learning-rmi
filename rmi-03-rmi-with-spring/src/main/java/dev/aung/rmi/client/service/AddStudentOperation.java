package dev.aung.rmi.client.service;

import dev.aung.rmi.api.inputs.StudentForm;
import dev.aung.rmi.api.service.StudentService;
import dev.aung.rmi.api.utils.BusinessException;
import dev.aung.rmi.client.meta.Operation;
import dev.aung.rmi.client.utils.Inputs;
import dev.aung.rmi.client.utils.RmiUtils;

public class AddStudentOperation extends Operation {

    public AddStudentOperation() {
        super("Add Student");
    }

    @Override
    public void doWork() {
        System.out.println("---------- Add New Student -------------");

        final var name = Inputs.getString("%-20s : ".formatted("Enter student name"));
        final var age = Inputs.getInt("%-20s : ".formatted("Enter student age"));
        final var year = Inputs.getInt("%-20s : ".formatted("Enter student year"));
        try {
            final var form = new StudentForm(name, age, mapIntToYear(year));
            final var id = RmiUtils.safecall(StudentService.class, s -> s.add(form));
            System.out.printf("++ Student with id : %s is created. ++\n", id);
        } catch (BusinessException | IllegalArgumentException e) {
            System.out.println("!!!!!!!  Something went wrong !!!!!!!");
            System.out.println(e.getMessage());
        }

        System.out.println("----------------------------------------");
    }

    private String mapIntToYear(int year) {
        return switch (year) {
            case 1 -> "First";
            case 2 -> "Second";
            case 3 -> "Third";
            case 4 -> "Fourth";
            case 5 -> "Fifth";
            default -> throw new IllegalArgumentException("Year must be only 1 to 5.");
        };
    }
}
