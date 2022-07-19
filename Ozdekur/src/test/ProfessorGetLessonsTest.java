package test;

import java.sql.SQLException;
import java.util.List;
import dbops.ProfessorManager;
import entities.Lesson;
import entities.Professor;

public class ProfessorGetLessonsTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ProfessorManager m = new ProfessorManager();
		Professor prof = m.find("12345678526");
		System.out.println(prof.getEmail());
		List<Lesson> lessons = m.getLessons(prof.getEmail());
		
		for (Lesson lesson : lessons) {
			System.out.println(lesson.getLessonName() + " " + 
					lesson.getLessonCode());
		}
	}
}
