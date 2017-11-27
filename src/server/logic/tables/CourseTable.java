package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import server.logic.model.Course;
import utilities.Trace;

public class CourseTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Course> courseList=new ArrayList<Course>();
    private static class CourseListHolder {
        private static final CourseTable INSTANCE = new CourseTable();
    }
    private CourseTable(){
    	//set up the default list with some instances
    	String[] courseNameList=new String[]{"Object-Oriented Software Development","Computational Geometry","Parallel Algorithms and Applications in Data Science","Multimedia Communications","Natural Language Processing"};
    	String[] courseCodeList=new String[]{"CO5104","CO5008","CO5704","CO6605","CO5505"};
    	for(int i=0;i<courseNameList.length;i++){
    		Course course=new Course(false, 2, 3, true, 50, courseNameList[i],courseCodeList[i]);
    		courseList.add(course);
		}
    	logger.info(String.format("Operation:Initialize CourseTable;CourseTable: %s", courseList));
    };
    public static final CourseTable getInstance() {
        return CourseListHolder.INSTANCE;
    }
	
    public boolean findByCourseByCode(String courseCode){
    	
    	boolean found = false;
		
		for(int i=0;i<courseList.size();i++){
			if(courseList.get(i).getCourseCode().equalsIgnoreCase(courseCode)){
				found=true;
			}
		}
		
		return found;
    }
    
    public Object createCourse(String name, String code) {		

		return null;	
	}
}
