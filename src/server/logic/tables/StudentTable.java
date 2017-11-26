package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import server.logic.model.Student;
import utilities.Trace;

public class StudentTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();
    private static class StudentListHolder {
        private static final StudentTable INSTANCE = new StudentTable();
    }
    private StudentTable(){
    	//set up the default list with some instances
    	String[] passwordList=new String[]{"James","Bond","Holmes","Watson","Kay"};
    	String[] usernameList=new String[]{"James@carleton.ca","Bond@carleton.ca","Holmes@carleton.ca","Watson@carleton.ca","Kay@carleton.ca"};
    	int [] studentNos = new int[]{1234567, 7654321, 9876543, 9123874, 124897934};
    	for(int i=0;i<usernameList.length;i++){
    		String status = (i%2 ==0) ? "ft" : "pt";
    		Student deuser=new Student(i,usernameList[i],passwordList[i], studentNos[i], status);
    		studentList.add(deuser);
		}
    	logger.info(String.format("Operation:Initialize StudentTable;StudentTable: %s", studentList));
    };
    public static final StudentTable getInstance() {
        return StudentListHolder.INSTANCE;
    }

	public int checkStudent(String string, String string2) {
		int result=0;

		return result;
	}
	
}
