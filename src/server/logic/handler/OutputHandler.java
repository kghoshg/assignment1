package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.tables.CourseTable;
import server.logic.tables.StudentTable;
import utilities.Config;

public class OutputHandler {
	
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
	public static final int CLERKLOGIN=3;
	public static final int STUDENT = 4;
	public static final int STUDENTLOGIN=5;
	public static final int CREATESTUDENT=6;
	public static final int CREATECOURSE=7;
	public static final int DELETESTUDENT=9;
	public static final int LISTSTUDENTS=14;
	public static final int DELETECOURSE=16;
	
	public Output clerkLogin(String input) {
		Output output=new Output("",0);
		if(input.equalsIgnoreCase(Config.CLERK_PASSWORD)){
			output.setOutput("What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register/Cancel/Destroy Course, List /Students/Courses");
        	output.setState(CLERK);
		}else{
			output.setOutput("Wrong Password!Please Input The Password:");
        	output.setState(CLERKLOGIN);
		}
		return output;
	}
	
	public Output studentLogin(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int result=0;
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should in this format:'username,password'");
        	output.setState(STUDENTLOGIN);
        }else{
        	result=StudentTable.getInstance().checkStudent(strArray[0], strArray[1]);
        	if(result==0){
        		output.setOutput("Please select from the menu.Menu:Register/Deregister/Drop/Select Course.");
            	output.setState(STUDENT);
        	}else if(result==1){
        		output.setOutput("Wrong Password!Please Input Username and Password:'username,password'");
            	output.setState(STUDENTLOGIN);
        	}else{
        		output.setOutput("The User Does Not Exist!Please The Username and Password:'username,password'");
            	output.setState(STUDENTLOGIN);
        	}
        }
		return output;
	}
	
	public Output createStudent(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=3 || email!=true){
        	output.setOutput("Your input should in this format:'username,password,FT or PT'");
        	output.setState(CREATESTUDENT);
        }else{
        	result=StudentTable.getInstance().createStudent(strArray[0], strArray[1], strArray[2]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The User Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output deleteStudent(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        int userid=StudentTable.getInstance().lookup(strArray[0]);
        boolean email=strArray[0].contains("@");
        boolean result;
        if(strArray.length!=1 || email!=true){
        	output.setOutput("Your input should in this format:'student email'");
        	output.setState(DELETESTUDENT);
        }else if(userid==-1){
        	output.setOutput("The Student Does Not Exist!");
        	output.setState(DELETESTUDENT);
        }else{
        	result=StudentTable.getInstance().delete(userid);
        	if(result){
        		output.setOutput("The student successfully deleted!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output listStudents(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
		if(strArray.length < 1){
        	output.setOutput("Your input should be in this format:'yes/no'");
        	output.setState(LISTSTUDENTS);
        }else{
        	if(strArray[0].contains("no") || strArray[0].contains("No")){
        		output.setOutput("Alright, have an nice day!");
            	output.setState(LISTSTUDENTS);
        	}else{
        		output.setOutput(StudentTable.getInstance().listStudents());
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output createCourse(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        Object result="";
        if(strArray.length < 2 ){
        	output.setOutput("Your input should in this format:'course name, course code', and course code must be 6 digits: first 2 are the dept code; last 4 are the course code, first digit is NOT zero");
        	output.setState(CREATECOURSE);
        }else{
        	result=CourseTable.getInstance().createCourse(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Course Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output deleteCourse(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean found=CourseTable.getInstance().findByCourseByCode(strArray[0]);
        Object result;
        if(strArray.length < 1){
        	output.setOutput("Your input should be in this format:'course'");
        	output.setState(DELETECOURSE);
        }else if(!found){
        	output.setOutput("The Course Does Not Exist!");
        	output.setState(DELETECOURSE);
        }else{
        	result=CourseTable.getInstance().destroyCourse(strArray[0]);
        	if(result.equals(true)){
        		output.setOutput("The course successfully deleted!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}

}
