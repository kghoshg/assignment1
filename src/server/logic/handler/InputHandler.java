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
		            }
		     }
		return oo;
	}
}
