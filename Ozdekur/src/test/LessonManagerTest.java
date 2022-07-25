package test;

import java.sql.SQLException;

import dbops.LessonManager;
import entities.Lesson;

public class LessonManagerTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		LessonManager lm = new LessonManager();
		Lesson lesson = new Lesson();
		lesson.setLessonCode("CHE101");
		lesson.setLessonName("Chemistry");
		System.out.println(lm.exists(lesson));
		
	}
	
}
