package entities;

abstract public class Person {

    protected String Name;
    protected String Surname;
    protected String Id;
    protected String Email;
    protected int Age;
    protected String Gender;

    public Person(String Name, String Surname, String Id, String Email, int Age, String Gender) {
    	this.Name = Name;
    	this.Surname = Surname;
    	this.Id = Id;
    	this.Email = Email;
    	this.Age = Age;
    	this.Gender = Gender;
    }

    public Person() {

    }
    
    public boolean checkId(String s) {
        for (Character c : s.toCharArray()) {
            if (Character.isDigit(c) && s.length() == 11) {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
    	return Name;
    }
    
    public void setName(String name) {
    	Name = name;
    }
    
    public String getSurname() {
    	return Surname;
    }
    
    public void setSurname(String surname) {
    	Surname = surname;
    }
    
    public String getId() {
    	return Id;
    }
    
    public void setId(String id) {
    	Id = id;
    }
    
    public String getEmail() {
    	return Email;
    }
    
    public void setEmail(String email) {
    	Email = email;
    }
    
    public int getAge() {
    	return Age;
    }
    
    public void setAge(int age) {
    	Age = age;
    }
    
    public String getGender() {
    	return Gender;
    }
    
    public void setGender(String gender) {
    	Gender = gender;
    }
    
}
