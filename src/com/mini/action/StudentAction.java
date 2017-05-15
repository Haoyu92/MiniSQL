package com.mini.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mini.model.Student;
import com.mini.operation.DBOperation;

public class StudentAction {

	public void add(Student student) throws Exception{
		DBOperation operate=new DBOperation();
		student.setSex(1);
		student.setCreate_user("ADMIN");
		student.setUpdate_user("ADMIN");
		student.setIsdel(0);
		operate.addStudent(student);
	}
	
	public Student get(Integer id) throws SQLException{
		DBOperation operate=new DBOperation();
		return operate.get(id);
	}
	
	public void edit(Student student) throws Exception{
		DBOperation operate=new DBOperation();
		operate.updateStudent(student);
	}
	public void del(Integer id) throws SQLException{
		DBOperation operate=new DBOperation();
		operate.delStudent(id);
	}
	
	public List<Student>  query() throws Exception{
		DBOperation operate=new DBOperation();
		return operate.query();
	}
	public List<Student> query(List<Map<String, Object>> params) throws Exception{
		DBOperation operate=new DBOperation();
		return operate.query(params);
	}
}
