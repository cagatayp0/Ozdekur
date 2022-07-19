package test;

import java.sql.SQLException;

import dbops.StudentManager;

public class InsertStudentToLessonTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		StudentManager m = new StudentManager();
		m.insertStudentToLesson("170501033", "Physics");	
		
	}
	
}
