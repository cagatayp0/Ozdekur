package entities;

import java.util.List;

public class Student extends Person {

    private String StudentNumber;
    private Double TotalNote;
    private List<Lesson> lessonList;

    public Student(String Name, String Surname, String Id, String Email, int Age, String Gender, String StudentNumber) {
        super(Name, Surname, Id, Email, Age, Gender);
        this.StudentNumber = StudentNumber;
    }
    
    public Student(String Name, String Surname, String StudentNumber) {
        this.Name = Name;
        this.Surname = Surname;
        this.StudentNumber = StudentNumber;
    }
    
    public Student() {
    	
    }
    
    // the method below checks if the student number consists of digits and has a length of 9
    public boolean checkStudentNumber(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c) && s.length() == 9) {
                return true;
            }
        }
        //System.out.println("False student id entered");
        return false;
    }
    
    @Override
    public String getName() {
    	return Name;
    }
    
    @Override
    public void setName(String name) {
    	Name = name;
    }
    
    @Override
    public String getSurname() {
    	return Surname;
    }
    
    @Override
    public void setSurname(String surname) {
    	Surname = surname;
    }
    
    @Override
    public String getId() {
    	return Id;
    }
    
    @Override
    public void setId(String id) {
    	Id = id;
    }
    
    @Override
    public String getEmail() {
    	return Email;
    }
    
    @Override
    public void setEmail(String email) {
    	Email = email;
    }
    
    @Override
    public int getAge() {
    	return Age;
    }
    
    @Override
    public void setAge(int age) {
    	Age = age;
    }
    
    @Override
    public String getGender() {
    	return Gender;
    }
    
    @Override
    public void setGender(String gender) {
    	Gender = gender;
    }

	public String getStudentNumber() {
		return StudentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		StudentNumber = studentNumber;
	}

	public Double getTotalNote() {
		return TotalNote;
	}

	public void setTotalNote(Double totalNote) {
		TotalNote = totalNote;
	}


	public List<Lesson> getLessonList() {
		return lessonList;
	}

	public void setLessonList(List<Lesson> lessonList) {
		this.lessonList = lessonList;
	}
}
