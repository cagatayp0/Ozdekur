package entities;

import java.sql.Date;
import java.util.List;

public class Exam {
	private Date date;
	private Lesson lesson;
	private Double MeanNote;
	private List<Student> studentList;
	
	public Exam() {
		
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMeanNote() {
		return MeanNote;
	}

	public void setMeanNote(Double meanNote) {
		MeanNote = meanNote;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
		
	
}
