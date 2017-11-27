package server.logic.model;

public class Course {
	
	boolean enforcePrereqs;
	int numberOfMidterms;
	int numberOfAssignments;
	boolean hasAFinal;
	int capSize;
	String name;
	String courseCode;
	int courseid;
	
	public Course(){
		
	}
	
	public Course(int courseid,
			String title, String courseCode) {
		this.courseid = courseid;
		this.name = title;
		this.courseCode = courseCode;
	
	}
	
	public Course(boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal, int capSize,
			String title, String courseCode) {
		this.enforcePrereqs = enforcePrereqs;
		this.numberOfMidterms = numberOfMidterms;
		this.numberOfAssignments = numberOfAssignments;
		this.hasAFinal = hasAFinal;
		this.capSize = capSize;
		this.name = title;
		this.courseCode = courseCode;
	}

	public boolean isEnforcePrereqs() {
		return enforcePrereqs;
	}

	public void setEnforcePrereqs(boolean enforcePrereqs) {
		this.enforcePrereqs = enforcePrereqs;
	}

	public int getNumberOfMidterms() {
		return numberOfMidterms;
	}

	public void setNumberOfMidterms(int numberOfMidterms) {
		this.numberOfMidterms = numberOfMidterms;
	}

	public int getNumberOfAssignments() {
		return numberOfAssignments;
	}

	public void setNumberOfAssignments(int numberOfAssignments) {
		this.numberOfAssignments = numberOfAssignments;
	}

	public boolean isHasAFinal() {
		return hasAFinal;
	}

	public void setHasAFinal(boolean hasAFinal) {
		this.hasAFinal = hasAFinal;
	}

	public int getCapSize() {
		return capSize;
	}

	public void setCapSize(int capSize) {
		this.capSize = capSize;
	}

	public String getTitle() {
		return name;
	}

	public void setTitle(String title) {
		this.name = title;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public int getUserid() {
		return courseid;
	}

	public void setUserid(int courseid) {
		this.courseid = courseid;
	}
}
