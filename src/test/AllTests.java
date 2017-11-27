package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InputHandlerTest.class, StudentTableTest.class, CourseTableTest.class })
public class AllTests {

}
