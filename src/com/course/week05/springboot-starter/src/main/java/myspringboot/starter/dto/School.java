package myspringboot.starter.dto;

public class School implements ISchool {
    
    private Klass klass;
    
    private Student student;
    
	public Klass getKlass() {
		return klass;
	}
	public void setKlass(Klass klass) {
		this.klass = klass;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
    public void ding(){
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
        
    }
    
}
