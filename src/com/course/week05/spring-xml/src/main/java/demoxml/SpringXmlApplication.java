package demoxml;

import demoxml.dto.Klass;
import demoxml.dto.School;
import demoxml.dto.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application of Spring by Spring Annotation - Bean
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 10:36:03
 */
public class SpringXmlApplication {

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final Student student1 = (Student) context.getBean("student1");
        System.out.println(student1.toString());

        final Student student2 = (Student) context.getBean("student2");
        System.out.println(student2.toString());

        final Klass class1 = context.getBean(Klass.class);
        class1.dong();

        final School school = context.getBean(School.class);
        school.ding();
    }
}
