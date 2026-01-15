package dev.aung.rmi.api.service;

import dev.aung.rmi.api.inputs.StudentForm;
import dev.aung.rmi.api.outputs.StudentDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {

    int add(StudentForm form) throws RemoteException;

    boolean delete(int id) throws RemoteException;

    List<StudentDetail> getAll() throws RemoteException;
}
