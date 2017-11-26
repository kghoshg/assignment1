package server.logic.model;

import java.util.Random;

public class Student {
	
	int studentid;
	int studentNumber;
	String username;
	String password;
	//full time (ft) or part time (pt);
	String status;
	
	public Student(int userid,String username,String password, int studentNo, String status){
		this.studentid=userid;
		this.password=password;
		this.username=username;
		this.studentNumber=studentNo;
		this.status = status;
	}
	
	public String toString(){
		return "["+this.studentid+","+this.username+","+this.password+"]";
	}

	public int getUserid() {
		return studentid;
	}

	public void setUserid(int userid) {
		this.studentid = userid;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
