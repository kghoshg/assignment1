package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import server.logic.model.Student;
import utilities.Trace;

public class StudentTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();
    private static class StudentListHolder {
        private static final StudentTable INSTANCE = new StudentTable();
    }
    private StudentTable(){
    	//set up the default list with some instances
    	String[] passwordList=new String[]{"James","Bond","Holmes","Watson","Kay"};
    	String[] usernameList=new String[]{"James@carleton.ca","Bond@carleton.ca","Holmes@carleton.ca","Watson@carleton.ca","Kay@carleton.ca"};
    	int [] studentNos = new int[]{1234567, 7654321, 9876543, 9123874, 124897934};
    	for(int i=0;i<usernameList.length;i++){
    		String status = (i%2 ==0) ? "ft" : "pt";
    		Student deuser=new Student(i,usernameList[i],passwordList[i], studentNos[i], status);
    		studentList.add(deuser);
		}
    	logger.info(String.format("Operation:Initialize StudentTable;StudentTable: %s", studentList));
    };
    public static final StudentTable getInstance() {
        return StudentListHolder.INSTANCE;
    }

	public int checkStudent(String string, String string2) {
		int result=-1;
		int flag=0;
		int index=0;
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getUsername().equalsIgnoreCase(string)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		boolean password=studentList.get(index).getPassword().equalsIgnoreCase(string2);
		if(flag!=0 && password){
			result=0;
			logger.info(String.format("Operation:the student and password matched:"));
		}else if(flag==0){
			result=2;
			logger.info(String.format("Operation:The student does not exist"));
		}else if(password==false){
			result=1;
			logger.info(String.format("Operation:the Password is wrong"));
		}
		return result;
	}
	
	public Object createStudent(String string, String string2, String status) {	
		boolean result=true;
		int flag=0;
		for(int i=0;i<studentList.size();i++){
			String email=(studentList.get(i)).getUsername();
			if(email.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			Student newuser=new Student(studentList.size(),string,string2, 100000 + new Random().nextInt(900000),status);
			result=studentList.add(newuser);
			logger.info(String.format("Operation:Create New Student;Student Info:[%s,%s];State:Success", string,string2));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Student;Student Info:[%s,%s];State:Fail;Reason:The User already existed.", string,string2));
		}
		return result;	
	}
	//find student by their email
	public int lookup(String string) {
		int userid=-1;
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getUsername().equalsIgnoreCase(string)){
				userid=i;
			}
		}
		return userid;
	}
	
	public boolean delete(int i) {
		
		boolean result = false;
		for(int j=0;j<studentList.size();j++){
			if(studentList.get(j).getUserid()==i){
				result = true;
				logger.info(String.format("Operation:Delete Student;Student Info:[%s,%s];State:successfully deleted.", "N/A","N/A"));
			}
		}
		return result;
	}
	
	public String listStudents(){
		String output = "\n";
		for (Student student: studentList) {
			output += student.getUsername() + " (" + student.getStudentNumber()+ ")\n\n";
		}
		return output;
	}
	
}
