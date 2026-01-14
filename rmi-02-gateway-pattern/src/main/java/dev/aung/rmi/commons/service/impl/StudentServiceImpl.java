package dev.aung.rmi.commons.service.impl;

import dev.aung.rmi.commons.inputs.StudentRegisterForm;
import dev.aung.rmi.commons.service.StudentService;

public class StudentServiceImpl implements StudentService {

    @Override
    public boolean register(StudentRegisterForm form) {
        System.out.println("==========================");
        System.out.println("Registering the Student");
        System.out.printf("Name : %s%n", form.getName());
        System.out.printf("Age  : %s%n", form.getAge());

        System.out.println("==========================");
        return false;
    }
}
