package com.mini.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mini.action.StudentAction;
import com.mini.model.Student;

public class View {

	private static final String CONTEXT="Welcome to Mini Project!\n" +
			"Choose functions:\n" +
			"[MAIN/M]:Main menu\n" +
			"[QUERY/Q]:Query all information\n" +
			"[GET/G]:Get given information\n" +
			"[ADD/A]:Add information\n" +
			"[UPDATE/U]:Update information\n" +
			"[DELETE/D]:Delete information\n" +
			"[SEARCH/S]:Search information(based on name, phone number)\n" +
			"[EXIT/E]:Quit project"; 
	
	private static final String OPERATION_MAIN="MAIN";
	private static final String OPERATION_QUERY="QUERY";
	private static final String OPERATION_GET="GET";
	private static final String OPERATION_ADD="ADD";
	private static final String OPERATION_UPDATE="UPDATE";
	private static final String OPERATION_DELETE="DELETE";
	private static final String OPERATION_SEARCH="SEARCH";
	private static final String OPERATION_EXIT="EXIT";
	
	public static void main(String[] args) throws Exception {
		System.out.println(CONTEXT);
		
		Scanner s=new Scanner(System.in);
		StudentAction action=new StudentAction();
		
		String pervious=null;
		Integer step=1;
		Student go=null;
		
		while(s.hasNext()){
			String in=s.next();
			if(OPERATION_EXIT.equals(in.toUpperCase())
					||OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())){
				System.out.println("Bye");
				break;
			}else if(OPERATION_MAIN.equals(in.toUpperCase())
					||OPERATION_MAIN.substring(0, 1).equals(in.toUpperCase())){
				step=1;
				pervious=null;
				go=null;
				System.out.println(CONTEXT);
			}else if(OPERATION_QUERY.equals(in.toUpperCase())
					||OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())){
				List<Student> list=action.query();
				for (Student student : list) {
					System.out.println(student.toString());
				}
			}else if(OPERATION_GET.equals(in.toUpperCase())
					||OPERATION_GET.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_GET.equals(pervious)){
				pervious=OPERATION_GET;
				if(1==step){
					System.out.println("Input ID: ");
				}else if(step>1){
					Integer id=null;
					Student g;
					try {
						id = Integer.valueOf(in);
						try {
							g = action.get(id);
							if(g==null){
								System.out.println("Fail");
							}else{
								System.out.println(g.toString());
							}
						} catch (Exception e) {
							System.out.println("Fail");
						}
					} catch (Exception e) {
						System.out.println("Input correct ID: ");
					}
				}
				step++;
			}else if(OPERATION_ADD.equals(in.toUpperCase())
					||OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_ADD.equals(pervious)){
				pervious=OPERATION_ADD;
				if(1==step){
					System.out.println("Input name: ");
				}else if(2==step){
					go=new Student();
					go.setUser_name(in);
					System.out.println("Input age: ");
				}else if(3==step){
					Integer age=null;
					try {
						age = Integer.valueOf(in);
						go.setAge(age);
						System.out.println("Input birthday, format as 2011-12-12: ");
					} catch (Exception e) {
						step=2;
						System.out.println("Input correct age: ");
					}
				}else if(4==step){
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					Date birthday=null;
					try {
						birthday = sf.parse(in);
						go.setBirthday(birthday);
						System.out.println("Input Email: ");
					} catch (Exception e) {
						step=3;
						System.out.println("Input correct birthday: ");
					}
				}else if(5==step){
					go.setEmail(in);
					System.out.println("Input phone number: ");
				}else if(6==step){
					go.setMobile(in);
					try {
						action.add(go);
					} catch (Exception e) {
						System.out.println("Fail to add information");
					}
					System.out.println("Success to add information");
					step=1;
					pervious=null;
				}
				if(OPERATION_ADD.equals(pervious)){
					step++;
				}
			}else if(OPERATION_UPDATE.equals(in.toUpperCase())
					||OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_UPDATE.equals(pervious)){
				pervious=OPERATION_UPDATE;
				if(1==step){
					System.out.println("Input update ID: ");
				}else if(2==step){
					Integer id=null;
					try {
						id = Integer.valueOf(in);
						try {
							go = action.get(id);
							if(go==null){
								System.out.println("Fail");
								step=1;
							}
						} catch (Exception e) {
							System.out.println("Fail");
							step=1;
						}
					} catch (Exception e) {
						System.out.println("Input correct ID: ");
						step=1;
					}
					System.out.println("Input new name, or input -1 to stay the same: ");
				}else if(3==step){
					if(-1!=Integer.valueOf(in)){
						go.setUser_name(in);
					}
					System.out.println("Input new age, or input -1 to stay the same: ");
				}else if(4==step){
					Integer age=null;
					try {
						age = Integer.valueOf(in);
						if(-1!=age){
							go.setAge(age);
						}
						System.out.println("Input new birthday, format as 2011-12-12: ");
					} catch (Exception e) {
						step=3;
						System.out.println("Input correct age: ");
					}
				}else if(5==step){
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					Date birthday=null;
					try {
							birthday = sf.parse(in);
							go.setBirthday(birthday);			
						System.out.println("Input new Email, or input -1 to stay the same: ");
					} catch (Exception e) {
						step=4;
						System.out.println("Input correct birthday: ");
					}
				}else if(6==step){
					if(-1!=Integer.valueOf(in)){
						go.setEmail(in);
					}
					System.out.println("Input new Phone number, or input -1 to stay the same: ");
				}else if(7==step){
					if(-1!=Integer.valueOf(in)){
						go.setMobile(in);
					}
					try {
						action.edit(go);
					} catch (Exception e) {
						System.out.println("Fail to update");
					}
					System.out.println("Success to update");
					step=1;
					pervious=null;
				}
				if(OPERATION_UPDATE.equals(pervious)){
					step++;
				}
			}else if(OPERATION_DELETE.equals(in.toUpperCase())
					||OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_DELETE.equals(pervious)){
				pervious=OPERATION_DELETE;
				if(1==step){
					System.out.println("Input delete ID: ");
				}else if(2==step){
					Integer id=null;
					try {
						id = Integer.valueOf(in);
						try {
							action.del(id);
							step=1;
							System.out.println("Success to delete");
						} catch (Exception e) {
							System.out.println("Fail to delete");
						}
					} catch (Exception e) {
						System.out.println("Input correct ID: ");
						step=1;
					}
				}
				if(OPERATION_DELETE.equals(pervious)){
					step++;
				}
			}else if(OPERATION_SEARCH.equals(in.toUpperCase())
					||OPERATION_SEARCH.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_SEARCH.equals(pervious)){
				pervious=OPERATION_SEARCH;
				if(1==step){
					System.out.println("Input name or number, if both, input like this [user_name=xx,mobile=xx]: ");
				}else if(2==step){
					if(in!=null&&in!=""){
						List<Map<String, Object>> params=new ArrayList<Map<String,Object>>();
						Map<String, Object> param=null;
						String[] strs=in.split(",");
						for (int i = 0; i < strs.length; i++) {
							String[] strs_s=strs[i].split("=");
							param=new HashMap<String, Object>();
							param.put("name", strs_s[0]);
							param.put("rela", "=");
							param.put("value", "'"+strs_s[1]+"'");
							params.add(param);
						}
						List<Student> list=action.query(params);
						if(list!=null&&list.size()>0){
							for (Student student : list) {
								System.out.println(student.toString());
							}
						}else{
							System.out.println("Fail");
						}
						step=1;
					}
				}
				if(OPERATION_SEARCH.equals(pervious)){
					step++;
				}
			}
		}
	}
}
