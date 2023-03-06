package lesson5.repository;

import lesson5.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentRepositoryImpl {

    private SessionFactory factory;

    public StudentRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }


    public Student findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    public List<Student> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentsList = session.createQuery("from Student").list();
            session.getTransaction().commit();
            return studentsList;
        }
    }



    public boolean save(Student student) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(student);

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Student entity) {
        return deleteById(entity.getId());
    }

    public boolean deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.getNamedQuery("deleteById")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    public boolean removeAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Student").executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    public Long countAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Long countResult = (Long) session
                    .getNamedQuery("countAll")
                    .uniqueResult();
            session.getTransaction().commit();
            return countResult;
        }
    }
    public Student merge(Student entity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = (Student) session.merge(entity);
            session.getTransaction().commit();
            return student;
        }
    }

}