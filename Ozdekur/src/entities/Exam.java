package entities;

import java.sql.Date;

public class Exam {
	private Date date;
	private String lesson;
	private Double MeanNote;
	private String Student;
	
	public Exam() {
		
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public Double getMeanNote() {
		return MeanNote;
	}

	public void setMeanNote(Double meanNote) {
		MeanNote = meanNote;
	}

	public String getStudent() {
		return Student;
	}

	public void setStudent(String student) {
		Student = student;
	}
		
	
}
