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
		Object course;
		//course already exist!
		course=CourseTable.getInstance().createCourse("Object-Oriented Software Development", "CO5104");
		assertTrue("Course has already existed", course.equals(false));
		//course successfully created
		course=CourseTable.getInstance().createCourse("New course", "CO9999");
		assertTrue("Course has successfully been created!", course.equals(true));
	}
	
	@Test
	public void testListCourse(){		
		assertTrue(CourseTable.getInstance().listCourses().length() > 5);
	}
	
	@Test
	public void testDeleteCourse(){
		//when the course exists
		assertTrue("the course was successfully deleted or destroyed", CourseTable.getInstance().destroyCourse("CO5008").equals(true));
		//when the course does not exist
		assertTrue("the course does not exist, so it could not be deleted", CourseTable.getInstance().destroyCourse("COxxxx").equals(false));
	}


}
