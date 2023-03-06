package lesson5.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
@NamedQueries({
        @NamedQuery(name = "countAll", query = "select count(s) from Student s"),
        @NamedQuery(name = "findByName", query = "from Student s where s.name = :name"),
        @NamedQuery(name = "deleteById", query = "delete from Student s where s.id = :id")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private Integer mark;

    public Student() {
    }

    public Student(Long id, String name, Integer mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student {id= " + id + ", name= " + name + ", mark= " + mark + "}";
    }
}