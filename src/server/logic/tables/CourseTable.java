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
    	logger.info(String.format("Operation:Initialize CourseTable;CourseTable: %s\n", courseList));
    };
    public static final CourseTable getInstance() {
        return CourseListHolder.INSTANCE;
    }
	
    public boolean findByCourseByCode(String courseCode){
    	
    	boolean found = false;
		
		for(int i=0;i<courseList.size();i++){
			if(courseList.get(i).getCourseCode().equalsIgnoreCase(courseCode)){
				found=true;
				logger.info(String.format("Operation:Finding Course;Course Info:[%s,%s];State:Success\n", courseList.get(i).getCourseCode(),courseList.get(i).getTitle()));
			}
		}
		
		if(!found){
			logger.info(String.format("Operation:There is no course with :[%s]\n", courseCode));
		}
		
		return found;
    }
    
    public Object createCourse(String name, String code) {		
    	boolean result=true;
    	boolean flag=false;
		flag = findByCourseByCode(code);
		if(!flag){
			Course newCourse = new Course(courseList.size(), name, code);
			result=courseList.add(newCourse);
			logger.info(String.format("Scenario: clerk creates course =====>>  [%s,%s];State:Success\n", name,code));
		}else{
			result=false;
			logger.info(String.format("Scenario: clerk creates course =====>>  [%s,%s];State:Fail;Reason:The Course already existed.\n", name,code));
		}
		return result;	
	}
    
    public String listCourses(){
    	String output = "\n";
		for (Course course : courseList) {
			output += course.getTitle() + " (" + course.getCourseCode() + ")\n\n";
		}
		logger.info(String.format("Scenario-: clerk list courses =====>> List all existing courses\n"));
		return output;
	}
    
    public Object destroyCourse(String courseCode){
    	
    	boolean result = false;
		for(int j=0;j<courseList.size();j++){
			if(courseList.get(j).getCourseCode().equalsIgnoreCase(courseCode)){
				result = true;
				logger.info(String.format("Scenario-: clerk deletes course =====>>  [%s];State:successfully deleted.\n", courseCode));
			}
		}
		return result;
    }
    
	public boolean isFull(String courseCode){
		boolean check = false;
		for(int i=0;i<courseList.size();i++){
			if(courseList.get(i).getCourseCode().equalsIgnoreCase(courseCode)){
				if(courseList.get(i).getCapSize() == UniversityTable.getInstance().totalStudentsOfACourse(courseCode)){
					check = true;
				}
			}
		}
		return check;
	}
}
