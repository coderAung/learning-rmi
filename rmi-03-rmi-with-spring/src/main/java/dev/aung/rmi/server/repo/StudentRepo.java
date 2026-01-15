package dev.aung.rmi.server.repo;

import dev.aung.rmi.api.outputs.StudentDetail;
import dev.aung.rmi.server.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private final List<Student> students;
    private int lastId = 0;

    public StudentRepo() {
        this.students = new ArrayList<>();
    }

    public Student save(Student student) {
        students.add(student);
        student.setId(getNextId());
        return student;
    }

    private int getNextId() {
        return ++ lastId;
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }
}
