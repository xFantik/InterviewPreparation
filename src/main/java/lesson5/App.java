package lesson5;

import lesson5.model.Student;
import lesson5.repository.StudentRepositoryImpl;
import lesson5.services.StudentService;
import lesson5.utils.HibernateUtil;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

import java.util.List;

public class App {
    private static StudentService studentService;

    public static void main(String[] args) {
        // создаем или обновляем таблицу базы данных
        Flyway database = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/interview_preparation_lesson5", "root", "root")
                .load();
        database.migrate();

        // запускаем тесты


        SessionFactory factory = HibernateUtil.getSessionFactory();
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl(factory);
        studentService = new StudentService(studentRepository, factory);


        System.out.println("\nДобавляем 1000 студентов...");


        for (int i = 1; i <= 1000; i++) {
            studentService.save(new Student("Student_" + i, (int) (Math.random() * 10)));
        }

        System.out.println("Количество студентов: " + studentService.getCount());


        studentService.removeAll();

        System.out.println();

        System.out.println("Очистили БД, добавляем новых 10 записей...");

        for (int i = 1; i <= 10; i++) {
            studentService.save(new Student("Student_" + i, (int) (Math.random() * 10)));
        }


        List<Student> students= studentService.findAllStudents();
        for (Object student : students) {
            System.out.println(student);
        }

        System.out.println();


        List<Student> studentsList = studentService.findAllStudents();
        Student student = studentsList.get(1);
        Long id = student.getId();
        studentService.renameStudent(id, "NEW NAME");

        System.out.println("Изменённая запись:");
        System.out.println(studentService.findStudentById(id));


        System.out.println();

        System.out.println("Закрываем HibernateUtil...");
        HibernateUtil.shutdown();
    }

}


