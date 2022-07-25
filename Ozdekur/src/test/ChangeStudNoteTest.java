package test;

import java.sql.SQLException;

import dbops.StudentManager;

public class ChangeStudNoteTest {
	
	public static void main(String[] args) {
		
		StudentManager sm = new StudentManager();
		try {
			sm.changeStudentNotes(66.5, "123000000");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
