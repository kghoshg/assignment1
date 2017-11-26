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
		assertTrue("password not matched", result == 1);
		assertTrue("student does not exist", result == 2);
	}

}
