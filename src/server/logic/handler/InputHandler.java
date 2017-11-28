package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandler {

	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
	public static final int CLERK = 2;
	public static final int CLERKLOGIN=3;
	public static final int STUDENT = 4;
	public static final int STUDENTLOGIN=5;
	public static final int CREATESTUDENT=6;
	public static final int CREATECOURSE=7;
	public static final int REGISTERSTUDENT=8;
	public static final int DELETESTUDENT=9;
	public static final int REGISTERCOURSE=10;
	public static final int DROPCOURSE=11;
	public static final int SELECTCOURSE=12;
	public static final int DEREGISTERCOURSE=13;
	public static final int LISTSTUDENTS=14;
	public static final int LISTCOURSES=15;
	public static final int DELETECOURSE=16;
	

	OutputHandler outputHandler=new OutputHandler();

	public ServerOutput processInput(String input, int state) {
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		if (state == WAITING) {
			output = "Who Are you?Clerk or Student?";
			state = FINISHWAITING;
			oo.setOutput(output);
			oo.setState(state);
		}else if (state == FINISHWAITING) {
			if (input.equalsIgnoreCase("clerk")) {
				output="Please Input The Password:";
				state=CLERKLOGIN;
				oo.setOutput(output);
				oo.setState(state);
			}else if (input.equalsIgnoreCase("student")) {
				output="Please Input Username and Password:'username,password'";
				state=STUDENTLOGIN;
				oo.setOutput(output);
				oo.setState(state);
			}else{
				output = "Who Are you?Clerk or Student?";
				state = FINISHWAITING;
				oo.setOutput(output);
				oo.setState(state);
			}
		}else if(state==CLERKLOGIN){
			o=outputHandler.clerkLogin(input);
			output=o.getOutput();
			state=o.getState();
			oo.setOutput(output);
			oo.setState(state);
		}else if(state==STUDENTLOGIN){
			o=outputHandler.studentLogin(input);
			output=o.getOutput();
			state=o.getState();
			oo.setOutput(output);
			oo.setState(state);
		}else if (state==CLERK){
			if (input.equalsIgnoreCase("create student")) {
				output = "Please Input User Info:'username,password,FT or PT'";
				state=CREATESTUDENT;
				oo.setOutput(output);
				oo.setState(state);
			}else if (input.equalsIgnoreCase("delete student")) {
            	output = "Please Input student Info:'student email'";
            	state=DELETESTUDENT;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("list students")) {
            	output = "Do you want to see the list of all students? (yes/no)";
            	state=LISTSTUDENTS;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("create course")) {
            	output = "Please Input Course Info:'name,code'";
            	state=CREATECOURSE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("delete course")) {
            	output = "Please Input Course Info:'course code'";
            	state=DELETECOURSE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("list courses")) {
            	output = "Do you want to see the list of all students? (yes/no)";
            	state=LISTCOURSES;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("register student")) {
	            output = "Please Input course code and student number:'course code, student number'";
	            state=REGISTERSTUDENT;
	            oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register/Cancel/Destroy Course, List /Students/Courses";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
            	output = "Please select from the menu.Menu:Create Student/Course, Delete Student/Course, Register/Cancel/Destroy Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
            }
		}else if (state==STUDENT){
        	if (input.equalsIgnoreCase("register course")) {
            	output = "Please Input course Info:'course code, student number'";
            	state=REGISTERCOURSE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("deregister course")) {
            	output = "Please Input course Info:'course code,student number'";
            	state=DEREGISTERCOURSE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("select course")) {
	            output = "Do you want to see the list of all courses? (yes/no)";
	            state=SELECTCOURSE;
	            oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
        		output = "Please select from the menu.Menu:Register/Deregister/Drop/Select Course";
                state = STUDENT;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if (input.equalsIgnoreCase("drop course")) {
            	output = "Please Input course and student Info:'course code,student number'";
            	state=DROPCOURSE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else{
            	output = "Please select from the menu.Menu:Register/Deregister/Drop/Select Course";
                state = STUDENT;
                oo.setOutput(output);
	            oo.setState(state);
            }
		}else if(state==CREATESTUDENT){
			if(input.equalsIgnoreCase("log out")){
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			}else if(input.equalsIgnoreCase("main menu")){
				output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			}else{
        		o=outputHandler.createStudent(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
		}else if(state==DELETESTUDENT){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.deleteStudent(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
		}else if(state==LISTSTUDENTS){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.listStudents(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==CREATECOURSE){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.createCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==DELETECOURSE){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.deleteCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==LISTCOURSES){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.listCourses(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==REGISTERSTUDENT){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register Student, Cancel Course, List /Students/Courses.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.registerStudentForCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==REGISTERCOURSE){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Register/Deregister/Drop/Select Course.";
                state = STUDENT;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.registerCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==DEREGISTERCOURSE){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Register/Deregister/Drop/Select Course";
                state = STUDENT;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.deregisterCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==SELECTCOURSE){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Register/Deregister/Drop/Select Course";
                state = STUDENT;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.selectCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==DROPCOURSE){
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Register/Deregister/Drop/Select Course.";
                state = STUDENT;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.dropCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }
		return oo;
	}
}
