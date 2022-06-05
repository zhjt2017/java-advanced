package demoxml.dto;

import org.springframework.stereotype.Component;

@Component
public class School implements ISchool {

    private Klass class1;

    private Student student;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);

    }

    public void setClass1(Klass class1) {
        this.class1 = class1;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
