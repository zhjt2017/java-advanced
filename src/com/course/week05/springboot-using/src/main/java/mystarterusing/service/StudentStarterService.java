package mystarterusing.service;

/**
 * starter service (students)
 * 
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 11:39:03
 */

public class StudentStarterService {
    @Autowired
    private Student student;

    @Autowired
    private Klass klass;

    @Autowired
    private ISchool school;

    public String doAction() {
        System.out.println(student.toString());
        klass.dong();
        school.ding();
        return "complete";
    }
}
