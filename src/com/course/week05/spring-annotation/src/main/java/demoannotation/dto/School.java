package demoannotation.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class School implements ISchool {
    
	@Autowired
    private Klass class1;
    
	@Autowired
    private Student student;
    
	@Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);
        
    }
    
}
