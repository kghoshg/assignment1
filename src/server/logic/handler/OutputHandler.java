package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.tables.StudentTable;
import utilities.Config;

public class OutputHandler {
	
	public static final int WAITING = 0;
	public static final int CLERKLOGIN=2;
	public static final int CLERK = 1;
	public static final int STUDENTLOGIN=3;
	public static final int STUDENT = 4;
	
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

}
