package demoxml.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Klass {

    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void dong() {
        System.out.println(this.students);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
