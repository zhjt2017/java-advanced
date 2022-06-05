package myspringboot.starter;

import myspringboot.starter.dto.Klass;
import myspringboot.starter.dto.School;
import myspringboot.starter.dto.Student;
import myspringboot.starter.prop.StudentProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * AutoConfiguration for myspringboot-starter
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 11:23:42
 */
@Configuration
@EnableConfigurationProperties(StudentProperties.class)
public class MyAutoConfiguration {

    @Bean
    public Student buildStudent(StudentProperties prop) {
        return new Student(prop.getId(), prop.getName());
    }

    @Bean
    public Klass buildKlassSingleStudent(StudentProperties prop) {
        final Student student = buildStudent(prop);
        return createKlass(student);
    }

    @Bean
    public School buildSchool(StudentProperties prop) {
        final Student student = buildStudent(prop);
        final Klass klass = createKlass(student);

        final School school = new School();
        school.setKlass(klass);
        school.setStudent(student);
        return school;
    }

    private Klass createKlass(Student student) {
        final List<Student> list = new LinkedList<>();
        list.add(student);

        final Klass klass = new Klass();
        klass.setStudents(list);
        return klass;
    }
}
