package myspringboot.starter.dto;

public class Student {
	
    private int id = 0;
    private String name = "student1";
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id:" + id + ", name:" + this.name;
	}
	
	/**
	 * 构造函数
	 * @param id 学生ID
	 * @param name 学生姓名
	 */
    public Student(int id, String name) {
    	if(id > 0) {
    		this.id = id;
    	}
    	
    	if(name != null && !"".equals(name.trim())) {
    		this.name = name;
    	}
    }
    
    /**
     * 构造函数
     */
    public Student() {
    	
    }
}
