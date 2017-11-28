package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Student;
import server.logic.tables.CourseTable;
import server.logic.tables.StudentTable;

public class StudentTableTest {
	int result;
	@Before
	public void setUp(){
		result = -1;
	}

	@Test
	public void testCheckStudent() {
		result=StudentTable.getInstance().checkStudent("James@carleton.ca", "James");
		assertTrue("Sdtudent and password matched", result == 0);
		result=StudentTable.getInstance().checkStudent("James@carleton.ca", "yyyy");
		assertTrue("password not matched", result == 1);
		result=StudentTable.getInstance().checkStudent("k@g.ca", "xxxx");
		assertTrue("student does not exist", result == 2);
	}
	
	@Test
	public void testCreateStudent(){
		Object student;
		//student already exist!
		student=StudentTable.getInstance().createStudent("James@carleton.ca", "James", "FT");
		assertTrue("Sdtudent has already existed", student.equals(false));
		//student successfully created
		student=StudentTable.getInstance().createStudent("Jeeve@carleton.ca", "Jeeve", "FT");
		assertTrue("Sdtudent has already existed", student.equals(true));
	}
	
	@Test
	public void testListStudent(){		
		assertTrue(StudentTable.getInstance().listStudents().length() > 5);
	}
	
	@Test
	public void testDeleteStudent(){
		//when the student exists
		assertTrue("the student was successfully deleted or deleted", StudentTable.getInstance().delete(2));
		//when the student does not exist
		assertTrue("the student does not exist, so the student could not be deleted", !StudentTable.getInstance().delete(10));
	}

	
	@Test
	public void testRegisterCourse(){
		boolean result = StudentTable.getInstance().registerCourse("co5104",9123874);
		assertTrue("Successfully registered with this course!", result);
	}
	
	@Test
	public void testDeregisterCourse(){
		boolean result = StudentTable.getInstance().deregisterCourse("CO4321",978144266);
		assertTrue("Successfully deregistered from this course!", result);
	}
	@Test
	public void testDropCourse(){
		boolean result = StudentTable.getInstance().dropCourse("CO1234", 978144266);
		assertTrue("Successfully dropped the course!", result);
	}
	
	@Test
	public void testLookUpByStudentId(){
		boolean result = StudentTable.getInstance().lookup(7654321);
		assertTrue("student found", result);
	}
	
	@Test
	public void testGetStudentObj(){
		Student result = StudentTable.getInstance().getStudent(7654321);
		assertTrue("student found", result.getStudentNumber() == 7654321);
	}

}
