package demoannotation;

import demoannotation.dto.Klass;
import demoannotation.dto.School;
import demoannotation.dto.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application of Spring by Spring Annotation - Bean
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 10:36:03
 */
public class SpringAnnotationApplication {

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final Student student = context.getBean(Student.class);
        // Spring容器中的类通过无参构造函数创建，因此字段为默认值
        System.out.println(student.toString());

        // 对List字段注解@Autowired，会将同类型或所有实现指定接口的类增加至列表
        final Klass class1 = context.getBean(Klass.class);
        class1.dong();

        final School school = context.getBean(School.class);
        school.ding();
    }
}
