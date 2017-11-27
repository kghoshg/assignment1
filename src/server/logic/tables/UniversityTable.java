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
    private static class RegisterListHolder {
        private static final UniversityTable INSTANCE = new UniversityTable();
    }
    private UniversityTable(){
    	//set up the default list with some instances
    	University register=new University("CO1234",978144266, new Date());
    	registerList.add(register);
    	logger.info(String.format("Operation:Initialize UniversityTable;UniversityTable: %s", registerList));
    };
    

}
