package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandler {
	
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
	public static final int CLERKLOGIN=2;
	public static final int STUDENTLOGIN=3;

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
	         }
		return oo;
	}
}
