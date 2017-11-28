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
	public static final int CREATECOURSE=7;
	public static final int DELETESTUDENT=9;
	public static final int REGISTERCOURSE=10;
    public static final int DEREGISTERCOURSE=13;
	public static final int LISTSTUDENTS=14;
	public static final int LISTCOURSES=15;
	public static final int DELETECOURSE=16;
	
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
		//testing logout option while list student
		serverOutput = inputHandler.processInput("log out", LISTSTUDENTS);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
		//testing logout option while create course
		serverOutput = inputHandler.processInput("log out", CREATECOURSE);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
		//testing logout option while create course
		serverOutput = inputHandler.processInput("log out", DELETECOURSE);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
		//testing logout option while list courses
		serverOutput = inputHandler.processInput("log out", LISTCOURSES);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
		//testing logout menu option while register course
		serverOutput = inputHandler.processInput("log out", REGISTERCOURSE);
		assertTrue(serverOutput.getOutput().contains("Successfully Log Out"));
		//testing logout menu option while register course
		serverOutput = inputHandler.processInput("log out", DEREGISTERCOURSE);
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
		//testing main menu option while list students
		serverOutput = inputHandler.processInput("main menu", LISTSTUDENTS);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
		//testing main menu option while creating course
		serverOutput = inputHandler.processInput("main menu", CREATECOURSE);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
		//testing main menu option while creating course
		serverOutput = inputHandler.processInput("main menu", DELETECOURSE);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
		//testing main menu option while list courses
		serverOutput = inputHandler.processInput("main menu", LISTCOURSES);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
		//testing main menu option while register course
		serverOutput = inputHandler.processInput("main menu", REGISTERCOURSE);
		assertTrue(serverOutput.getOutput().contains("What can I do for you?Menu:"));
		//testing main menu option while deregister course
		serverOutput = inputHandler.processInput("main menu", DEREGISTERCOURSE);
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
	
	@Test
	public void testListStudents(){
		//list student menu test
		serverOutput = inputHandler.processInput("list students", CLERK);
		assertTrue(serverOutput.getOutput().contains("Do you want to see the list of all students? (yes/no)"));
		// testing 'list student' using menu when the clerk wants to see it.
		serverOutput = inputHandler.processInput("yes", LISTSTUDENTS);
		assertTrue(serverOutput.getOutput().length() > 5);
		// testing 'list student' using menu when the clerk does not wants to see it.
		serverOutput = inputHandler.processInput("no", LISTSTUDENTS);
		assertTrue(serverOutput.getOutput().contains("Alright, have an nice day!"));
	}
	
	@Test
	public void testCreateCourse(){
		//create course menu test
		serverOutput = inputHandler.processInput("create course", CLERK);
		assertTrue(serverOutput.getOutput().contains("Please Input Course Info:'name,code'"));
		// testing 'create course' using menu and when course does not exist
		serverOutput = inputHandler.processInput("A new course,CO5555", CREATECOURSE);
		assertTrue(serverOutput.getOutput().contains("Success!"));
		// testing 'create course' using menu and when course does exist
		serverOutput = inputHandler.processInput("Object-Oriented Software Development,CO5104", CREATECOURSE);
		assertTrue(serverOutput.getOutput().contains("The Course Already Exists!"));
	}
	
	@Test
	public void testDestroyCourse(){
		//delete course menu test
		serverOutput = inputHandler.processInput("delete course", CLERK);
		assertTrue(serverOutput.getOutput().contains("Please Input Course Info:'course code'"));
		// testing 'deleting course' using menu and when course does not exist
		serverOutput = inputHandler.processInput("CO5505", DELETECOURSE);
		assertTrue(serverOutput.getOutput().contains("The course successfully deleted!"));
		// testing 'deleting course' using menu and when course does exist
		serverOutput = inputHandler.processInput("CO0000", DELETECOURSE);
		assertTrue(serverOutput.getOutput().contains("The Course Does Not Exist!"));
		
	}
	
	@Test
	public void testListCourse(){
		//list course menu test
		serverOutput = inputHandler.processInput("list courses", CLERK);
		assertTrue(serverOutput.getOutput().contains("Do you want to see the list of all students? (yes/no)"));
		// testing 'list course' using menu when the clerk wants to see it.
		serverOutput = inputHandler.processInput("yes", LISTSTUDENTS);
		assertTrue(serverOutput.getOutput().length() > 5);
		// testing 'list course' using menu when the clerk does not wants to see it.
		serverOutput = inputHandler.processInput("no", LISTSTUDENTS);
		assertTrue(serverOutput.getOutput().contains("Alright, have an nice day!"));
	}
	
	@Test
	public void testRegisterCourse(){
		//register course menu test
		serverOutput = inputHandler.processInput("register course", STUDENT);
		assertTrue(serverOutput.getOutput().contains("Please Input course Info:'course code, student number'"));
		// testing 'register course' using menu when course does not exist.
		serverOutput = inputHandler.processInput("CO4321,9123874", REGISTERCOURSE);
		assertTrue(serverOutput.getOutput().contains("The Course Does Not Exist"));
		// testing 'register course' using menu when course does exist.
		serverOutput = inputHandler.processInput("CO5008,7654321", REGISTERCOURSE);
		assertTrue(serverOutput.getOutput().contains("registration successful"));
	}
	
	@Test
	public void testDeregisterCourse(){
		//register course menu test
		serverOutput = inputHandler.processInput("deregister course", STUDENT);
		assertTrue(serverOutput.getOutput().contains("Please Input course Info:'course code'"));
		// testing 'register course' using menu when course does not exist.
		serverOutput = inputHandler.processInput("CO4321,9123874", DEREGISTERCOURSE);
		assertTrue(serverOutput.getOutput().contains("The Course Does Not Exist"));
		// testing 'register course' using menu when course does exist.
		serverOutput = inputHandler.processInput("CO5008,7654321", DEREGISTERCOURSE);
		assertTrue(serverOutput.getOutput().contains("Successfully deregistered!!"));
	}

}
