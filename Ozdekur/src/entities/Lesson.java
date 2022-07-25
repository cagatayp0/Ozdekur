package entities;

import java.util.List;

public class Lesson {
	
	private String LessonName;
	private String LessonCode;
	private String TempProfessorName;
	private String TempProfessorSurname;
	private String TempProfessorEmail;
	private Professor prof;
	private List<Student> studentList;
	private List<Exam> examList;
	
	public Lesson(String LessonName, String LessonCode) {
		this.LessonName = LessonName;
		this.LessonCode = LessonCode;
	}

	public Lesson() {

	}

	public String getLessonName() {
		return LessonName;
	}
	
	public void setLessonName(String lessonName) {
		LessonName = lessonName;
	}
	
	public String getLessonCode() {
		return LessonCode;
	}
	
	public void setLessonCode(String lessonCode) {
		LessonCode = lessonCode;
	}

	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Exam> getExamList() {
		return examList;
	}

	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	public String getTempProfessorName() {
		return TempProfessorName;
	}

	public void setTempProfessorName(String tempProfessorName) {
		TempProfessorName = tempProfessorName;
	}

	public String getTempProfessorEmail() {
		return TempProfessorEmail;
	}

	public void setTempProfessorEmail(String tempProfessorEmail) {
		TempProfessorEmail = tempProfessorEmail;
	}

	public String getTempProfessorSurname() {
		return TempProfessorSurname;
	}

	public void setTempProfessorSurname(String tempProfessorSurname) {
		TempProfessorSurname = tempProfessorSurname;
	}
	
}
