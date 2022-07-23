package test;

import java.sql.SQLException;
import java.util.List;

import dbops.ProfessorManager;
import entities.Exam;
import entities.Student;

public class ProfessorManagerTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {		
		ProfessorManager m = new ProfessorManager();
		List<Exam> examList = m.getExams("jimbo@ozdekur.com");
		for (Exam exam : examList) {
			System.out.println(exam.getLesson().getLessonCode() + " " +
					exam.getLesson().getLessonName() + " " +
					exam.getDate());
		}
		
		List<Student> studentList = m.getStudents("jimbo@ozdekur.com");
		for (Student s : studentList) {
			System.out.println(s.getName() + " " +
					s.getSurname() + " " +
					s.getStudentNumber());
		}
	}
}
