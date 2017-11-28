package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import server.logic.model.University;
import utilities.Trace;

public class UniversityTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<University> registerList=new ArrayList<University>();
	private static final University aUniversity = new University();
    private static class RegisterListHolder {
        private static final UniversityTable INSTANCE = new UniversityTable();
    }
    private UniversityTable(){
    	//setting up university start time
    	aUniversity.setUniversityStarttime(new Date().getTime());
    	//set up the default list with some instances
    	University register=new University("CO1234",978144266, new Date());
    	University register1=new University("CO4321",978144266, new Date());
    	registerList.add(register);
    	registerList.add(register1);
    	logger.info(String.format("Operation:Initialize UniversityTable;UniversityTable: %s", registerList));
    };
    
    public List<University> getRegInfo(){
    	return registerList;
    }
    
    public int findCoursesTakenByAStudent(int studentNo){
    	int counter = 0 ;
    	for(int i=0;i<registerList.size();i++){
			if(studentNo == registerList.get(i).getStudentNumber()){
				counter++;
			}			
		}
    	return counter;
    }
    
    public int totalStudentsOfACourse(String courseCode){    	
    	int counter = 0 ;
    	for(int i=0;i<registerList.size();i++){
			if(courseCode == registerList.get(i).getCourseCode()){
				counter++;
			}			
		}
    	return counter;
    }
    
    public boolean alreadyRegistered(String courseCode, int studentNo){
    	boolean flag = false;
    	for(int i=0;i<registerList.size();i++){
			if(courseCode.equalsIgnoreCase(registerList.get(i).getCourseCode()) && (studentNo == registerList.get(i).getStudentNumber())){
				flag = true;
			}			
		}
    	return flag;
    }
    
    public static final UniversityTable getInstance() {
        return RegisterListHolder.INSTANCE;
    }
    
    public long getUniversityElapseTime(){
    	return new Date().getTime() - aUniversity.getUniversityStarttime();
    }

}
