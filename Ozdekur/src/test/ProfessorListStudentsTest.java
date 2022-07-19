package test;

import java.sql.SQLException;
import java.util.List;

import dbops.ProfessorManager;
import entities.Student;

public class ProfessorListStudentsTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		ProfessorManager m = new ProfessorManager();
		List<Student> sl1 = m.getStudents("jimbo@ozdekur.com");
		List<Student> sl2 = m.getStudents("john@ozdekur.com");
		List<Student> sl3 = m.getStudents("admin@ozdekur.com");
		
		for (Student s : sl1) {
			System.out.println(s.getName() + " " + s.getSurname() + " " + s.getStudentNumber());
			System.out.println();
		}
		
		System.out.println("..........");
		
		for (Student s : sl2) {
			System.out.println(s.getName() + " " + s.getSurname() + " " + s.getStudentNumber());
			System.out.println();
		}
		
		System.out.println("..........");
		
		for (Student s : sl3) {
			System.out.println(s.getName() + " " + s.getSurname() + " " + s.getStudentNumber());
			System.out.println();
		}
		
	}
	
}
