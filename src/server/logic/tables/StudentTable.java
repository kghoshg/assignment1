package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import server.logic.model.Student;
import server.logic.model.University;
import utilities.Trace;

public class StudentTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();
	List<University> registerList = new ArrayList<University>();
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
    	registerList = UniversityTable.getInstance().getRegInfo();
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
				logger.info(String.format("Operation:the student was found:[%s,%s];State:success.", "N/A","N/A"));
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
		logger.info(String.format("Operation:listing students:[%s,%s].", "N/A","N/A"));
		for (Student student: studentList) {
			output += student.getUsername() + " (" + student.getStudentNumber()+ ")\n\n";
		}
		return output;
	}
	
    public boolean deregisterCourse(String courseCode, int studentNo){
    	boolean result = false;
    	for(int i=0;i<registerList.size();i++){
			if(courseCode.equalsIgnoreCase(registerList.get(i).getCourseCode()) && (studentNo == registerList.get(i).getStudentNumber())){
				registerList.remove(i);
				result= true;
				logger.info(String.format("Operation:deregistration for course is a success:[%s,%s];State:success.", "N/A","N/A"));
			}			
		}
    	return result;
    }
    
    public boolean registerCourse(String courseCode, int studentNo){
    	boolean result = false;
    	try{
    		University newregistration=new University(courseCode,studentNo, new Date());
    		registerList.add(newregistration);
    		result = true;
    		logger.info(String.format("Operation:Registration for course sucessful:[%s,%s];State:success.", "N/A","N/A"));
    	}catch(Exception e){
    		logger.info(String.format("Operation:Registration for course failed:[%s,%s];State:failure.", "N/A","N/A"));
    	}
    	return result;
    }
    
    public boolean dropCourse(String courseCode, int studentNo){
    	boolean result = false;
    	for(int i=0;i<registerList.size();i++){
			if(courseCode.equalsIgnoreCase(registerList.get(i).getCourseCode()) && (studentNo == registerList.get(i).getStudentNumber())){
				registerList.get(i).setStatus("DR");
				result = true;
				logger.info(String.format("Operation:droping course was a success:[%s,%s];State:failure.", "N/A","N/A"));
			}			
		}
    	return result;
    }
    
	public boolean lookup(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<studentList.size();i++){
			int userid=(studentList.get(i)).getStudentNumber();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	
	public Student getStudent(int studentNo){
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getStudentNumber() == studentNo){
				return studentList.get(i);
			}
		}
		return null;
	}
	
}
