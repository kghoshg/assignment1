package test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.CourseTable;

public class CourseTableTest {

	@Test
	public void testFindByCourseByCode() {
		//when course does not exist
		boolean result = CourseTable.getInstance().findByCourseByCode("XXXX");
		assertTrue("Course does not exist", !result);
		//when course does exist
		result = CourseTable.getInstance().findByCourseByCode("co5104");
		assertTrue("Course does not exist", result);
	}
	
	@Test
	public void testCreateCourse() {
		fail("Not yet implemented");
	}


}
