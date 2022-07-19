package entities;

import java.util.ArrayList;

public class Professor extends Person{
	
	private int IsAdmin = 0;
	private ArrayList<Student> ProfessorStudentList = new ArrayList<>();
	private ArrayList<Lesson> ProfessorLessonList = new ArrayList<>();
	
	public Professor(String Name, String Surname, String Id, int Age, String Email, String Gender) {
		super(Name, Surname, Id, Email, Age, Gender);
	}
	
	public Professor() {
		
	}
	
	public void addLessons() {
		
	}
	
	public void addStudents() {
		
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
    
    public int getIsAdmin() {
    	return IsAdmin;
    }
    
    public void setIsAdmin(int isadmin) {
    	IsAdmin = isadmin;
    }

	public ArrayList<Student> getProfessorStudentList() {
		return ProfessorStudentList;
	}

	public void setProfessorStudentList(ArrayList<Student> professorStudentList) {
		ProfessorStudentList = professorStudentList;
	}

	public ArrayList<Lesson> getProfessorLessonList() {
		return ProfessorLessonList;
	}

	public void setProfessorLessonList(ArrayList<Lesson> professorLessonList) {
		ProfessorLessonList = professorLessonList;
	}
    
	
}
