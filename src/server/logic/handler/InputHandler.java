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
	public static final int DELETESTUDENT=9;
	public static final int LISTSTUDENTS=14;

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
            }
		}else if(state==CREATESTUDENT){
			if(input.equalsIgnoreCase("log out")){
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			}else if(input.equalsIgnoreCase("main menu")){
				output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register/Cancel/Destroy Course, List /Students/Courses.";
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
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register/Cancel/Destroy Course, List /Students/Courses.";
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
        		output = "What can I do for you?Menu:Create Student/Course, Delete Student/Course, Register/Cancel/Destroy Course, List /Students/Courses.";
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
        }
		return oo;
	}
}
