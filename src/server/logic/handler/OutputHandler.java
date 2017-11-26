package server.logic.handler;

import server.logic.handler.model.Output;
import utilities.Config;

public class OutputHandler {
	
	public static final int CLERKLOGIN=2;
	 public static final int CLERK = 3;
	
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

}
