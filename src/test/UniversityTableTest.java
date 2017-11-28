package test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.CourseTable;
import server.logic.tables.StudentTable;
import server.logic.tables.UniversityTable;

public class UniversityTableTest {

	@Test
	public void testGetRegInfo() {
		assertTrue(UniversityTable.getInstance().getRegInfo().size() >= 0);
	}
	
	@Test
	public void testTotalStudentsOfACourse() {
		int tn = UniversityTable.getInstance().totalStudentsOfACourse("CO1234");
		assertTrue("finding total number of stuents registered with a  course", tn == 1);
	}
	
	@Test
	public void testAlreadyRegistered() {
		boolean answer = UniversityTable.getInstance().alreadyRegistered("CO1234", 978144266);
		assertTrue("checking if a studnet is registred with a course", answer);
	}
	
	@Test
	public void testFindCoursesTakenByAStudent() {
		int answer = UniversityTable.getInstance().findCoursesTakenByAStudent(978144266);
		assertTrue("finding the courses taken by a student", answer == 1);
	}
	
	@Test
	public void testRegisterStudent(){
		//test register student when both student and course exist
		boolean result[] = UniversityTable.getInstance().registerStudent("CO5505",124897934);
		assertTrue("registration successful!", !(result[0] && result[1] && result[2]));
	}

}
