package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}
