package dev.aung.rmi.client.service;

import dev.aung.rmi.api.service.StudentService;
import dev.aung.rmi.client.meta.Operation;
import dev.aung.rmi.client.utils.RmiUtils;

public class ViewStudentsOperation extends Operation {

    public ViewStudentsOperation() {
        super("View Students");
    }

    @Override
    public void doWork() {
        System.out.println("+++++++++++++++ Student List +++++++++++++++");

        var list = RmiUtils.safecall(StudentService.class, StudentService::getAll);
        System.out.printf("%-3s %-15s %-5s %-10s %n%n", "ID", "Name", "Age", "Year");
        for(var i : list) {
            System.out.printf("%-3d %-15s %-5d %-10s %n", i.id(), i.name(), i.age(), i.year());
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
    }
}
