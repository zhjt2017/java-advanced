package mystarterusing.service;

import myspringboot.starter.dto.ISchool;
import myspringboot.starter.dto.Klass;
import myspringboot.starter.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * starter service (students)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 11:39:03
 */
@Service
public class StudentStarterService {

    @Autowired
    private Student student;

    @Autowired
    private Klass klass;

    @Autowired
    private ISchool school;

    public void doAction() {
        System.out.println("StudentStarterService student : " + student);
        klass.dong();
        school.ding();
    }
}
