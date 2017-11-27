package server.logic.model;

import java.util.Date;

public class University {

	int maxCoursesForFTStudents = 4;
	int maxCoursesForpTStudents = 2;
	int passRate= 70;
	
	int studentNumber;
	String courseCode;
	Date registrationDate;
	String status;
	
	public int getMaxCoursesForFTStudents() {
		return maxCoursesForFTStudents;
	}
	public void setMaxCoursesForFTStudents(int maxCoursesForFTStudents) {
		this.maxCoursesForFTStudents = maxCoursesForFTStudents;
	}
	public int getMaxCoursesForpTStudents() {
		return maxCoursesForpTStudents;
	}
	public void setMaxCoursesForpTStudents(int maxCoursesForpTStudents) {
		this.maxCoursesForpTStudents = maxCoursesForpTStudents;
	}
	public int getPassRate() {
		return passRate;
	}
	public void setPassRate(int passRate) {
		this.passRate = passRate;
	}
	
	public University(){
		
	}
	
	public University(String courseCode, int studentNo, Date dt){
		this.courseCode = courseCode;
		this.studentNumber = studentNo;
		this.registrationDate = dt;
		this.status = "Doing";
	}
	
	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date date) {
		this.registrationDate = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
