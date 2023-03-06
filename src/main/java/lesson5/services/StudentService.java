package lesson5.services;


import lesson5.model.Student;
import lesson5.repository.StudentRepositoryImpl;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentService {

    private final StudentRepositoryImpl studentRepository;
    private final SessionFactory factory;

    public StudentService(StudentRepositoryImpl studentRepository, SessionFactory factory) {
        this.studentRepository = studentRepository;
        this.factory = factory;
    }

    public long getCount() {
        return studentRepository.countAll();
    }

    public Student findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }


    public boolean save(Student s)
    {
        return studentRepository.save(s);
    }

    public void removeAll() {
        System.out.println("Очистка таблицы Студенты");
        studentRepository.removeAll();
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }


    public Student renameStudent(Long id, String newName){
        if (studentRepository.countAll() == 0) return null;
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setName(newName);
            studentRepository.merge(student);
            return student;
        }
        return null;
    }


}
