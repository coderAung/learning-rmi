package dev.aung.rmi.server.service.impl;

import dev.aung.rmi.api.inputs.StudentForm;
import dev.aung.rmi.api.outputs.StudentDetail;
import dev.aung.rmi.api.service.StudentService;
import dev.aung.rmi.api.utils.BusinessException;
import dev.aung.rmi.server.entities.Student;
import dev.aung.rmi.server.repo.StudentRepo;
import dev.aung.rmi.server.utils.RmiObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

@Service
@RmiObj(name = "StudentService")
public class StudentServiceImpl extends UnicastRemoteObject implements StudentService {

    @Autowired
    private StudentRepo repo;

    public StudentServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(StudentForm form) throws RemoteException {
        if (form.age() < 0) {
            throw new BusinessException("Age must not be negative value.");
        }
        if (form.age() < 5 || form.age() > 90) {
            throw new BusinessException("Age must be between 5 and 90");
        }

        var student = new Student();
        student.setName(form.name());
        student.setAge(form.age());
        student.setYear(Student.Year.valueOf(form.year()));
        student = repo.save(student);
        return student.getId();
    }

    @Override
    public boolean delete(int id) throws RemoteException {
        return false;
    }

    @Override
    public List<StudentDetail> getAll() throws RemoteException {
        return repo.getAll().stream()
                .map(a -> new StudentDetail(a.getId(), a.getName(), a.getAge(), a.getYear().name()))
                .toList();
    }
}
