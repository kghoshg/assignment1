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
    public static final int CLERK = 2;
	public static final int CLERKLOGIN=3;
	public static final int STUDENT = 4;
	public static final int STUDENTLOGIN=5;
	public static final int CREATESTUDENT=6;
	public static final int DELETESTUDENT=9;
	
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
	
	@Test
	public void testStudentLogin() {
		//with wrong password
		serverOutput = inputHandler.processInput("James@carleton.ca,xxxx", STUDENTLOGIN);
		assertTrue(serverOutput.getOutput().contains("Wrong Password!Please Input Username and Password:'username,password'"));
		//when does not exist
		serverOutput = inputHandler.processInput("a@b,xxxx", STUDENTLOGIN);
		assertTrue(serverOutput.getOutput().contains("The User Does Not Exist!"));
		//with correct password
		serverOutput = inputHandler.processInput("James@carleton.ca,James", STUDENTLOGIN);
		assertTrue(serverOutput.getOutput().contains("Please select from the menu.Menu:"));
	}
	
	@Test
	public void testCreateStudent(){
		//create student menu test
		serverOutput = inputHandler.processInput("create student", CLERK);
		assertTrue(serverOutput.getOutput().contains("Please Input User Info:'username,password,FT or PT'"));
		// testing 'create student' using menu and when student does not exist
		serverOutput = inputHandler.processInput("a@b.ca,xxxx,FT", CREATESTUDENT);
		assertTrue(serverOutput.getOutput().contains("Success!"));
		// testing 'create student' using menu and when student does exist
		serverOutput = inputHandler.processInput("James@carleton.ca,James,FT", CREATESTUDENT);
		assertTrue(serverOutput.getOutput().contains("The User Already Exists"));
	}
	
	@Test
	public void testLougout(){
		//test login-out while creating student
		serverOutput = inputHandler.processInput("log out", CREATESTUDENT);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
		//testing log-out while deleting a student
		serverOutput = inputHandler.processInput("log out", DELETESTUDENT);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
	}
	
	@Test
	public void testReturningMainMenu(){
		//test returning to main menu while creating student
		serverOutput = inputHandler.processInput("main menu", CREATESTUDENT);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
		//test returning to main menu while deleting student
		serverOutput = inputHandler.processInput("main menu", DELETESTUDENT);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
	}
	
	@Test
	public void testDeleteStudent(){
		//deleting student menu test
		serverOutput = inputHandler.processInput("delete student", CLERK);
		assertTrue(serverOutput.getOutput().contains("Please Input student Info:'student email'"));
		// testing 'deleting student' using menu and when student does not exist
		serverOutput = inputHandler.processInput("Kay@carleton.ca", DELETESTUDENT);
		assertTrue(serverOutput.getOutput().contains("The student successfully deleted!"));
		// testing 'deleting student' using menu and when student does exist
		serverOutput = inputHandler.processInput("x@y.ca", DELETESTUDENT);
		assertTrue(serverOutput.getOutput().contains("The Student Does Not Exist!"));
	}

}
