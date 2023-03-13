package lesson7.services;

import lesson7.models.Student;
import lesson7.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student findById(Long id) {
        Student student = studentRepository.findById(id).get();
        return student;
    }

    public List<Student> getAll() {
        return new ArrayList<>(studentRepository.findAll());
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(new Student(student.getName(), student.getAge()));
    }

}