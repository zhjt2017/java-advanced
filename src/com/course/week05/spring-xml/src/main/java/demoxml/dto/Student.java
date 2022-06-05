package demoxml.dto;

import org.springframework.stereotype.Component;

@Component
public class Student {
	
    private int id;
    private String name;
    
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
    	this.id = id;
    	this.name = name;
    }
    
    /**
     * 构造函数
     */
    public Student() {
    	
    }
}
