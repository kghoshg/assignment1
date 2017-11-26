package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;
import utilities.Config;


public class InputHandlerTest {
	
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
	public static final int CLERKLOGIN=2;
	InputHandler inputHandler;
	ServerOutput serverOutput;
	Output output;
	
	@Before
	public void setUp(){
		output = new Output("",0);
		inputHandler = new InputHandler();
		serverOutput = new ServerOutput("",output.getState());
	}
	
	@Test
	public void testWaitingState() {
		serverOutput = inputHandler.processInput("", WAITING);
		assertTrue(serverOutput.getOutput().equalsIgnoreCase("Who Are you?Clerk or Student?"));
	}
	
	@Test
	public void testFinisihingState(){
		//when user is clerk
		serverOutput = inputHandler.processInput("clerk", FINISHWAITING);
		assertTrue(serverOutput.getOutput().equalsIgnoreCase("Please Input The Password:"));
		//when user is student
		serverOutput = inputHandler.processInput("student", FINISHWAITING);
		assertTrue(serverOutput.getOutput().equalsIgnoreCase("Please Input Username and Password:'username,password'"));
	}
	
	@Test
	public void testClerkLogin(){
		//with wrong password
		serverOutput = inputHandler.processInput("Notadmin", CLERKLOGIN);
		assertTrue(serverOutput.getOutput().contains("Wrong Password!Please Input The Password:"));
		//with correct password
		serverOutput = inputHandler.processInput(Config.CLERK_PASSWORD, CLERKLOGIN);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
	}

}
