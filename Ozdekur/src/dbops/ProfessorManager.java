package dbops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Exam;
import entities.Lesson;
import entities.Professor;
import entities.Student;

public class ProfessorManager {
	
    public int checkAdminStatus(String email) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "select IsAdmin from professors where Email=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultset = statement.executeQuery();
        while (resultset.next())
        	return resultset.getInt("IsAdmin");
        return 0;
    }
    
    public boolean insert(Professor prof) throws ClassNotFoundException, SQLException {
        int affected;
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "insert into professors (Name, Surname, ID, Age, Email, Gender, IsAdmin) "
        		+ "values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, prof.getName());
        statement.setString(2, prof.getSurname());
        statement.setString(3, prof.getId());
        statement.setInt(4, prof.getAge());
        statement.setString(5, prof.getEmail());
        statement.setString(6, prof.getGender());
        statement.setInt(7, prof.getIsAdmin());
        affected = statement.executeUpdate();
        return affected >= 1;
    }

    public Professor find(String Email) throws ClassNotFoundException, SQLException {
        Professor prof = null;
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "select * from professors where Email=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Email);
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
        	prof = new Professor();
        	prof.setName(resultset.getString(1));
        	prof.setSurname(resultset.getString(2));
        	prof.setId(resultset.getString(3));
        	prof.setAge(resultset.getInt(4));
        	prof.setEmail(resultset.getString(5));
        	prof.setGender(resultset.getString(6));
        	prof.setIsAdmin(resultset.getInt(7));
        }
        connection.close();
        return prof;
    }

    public boolean delete(String idNumber) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "delete from professors where ID=?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, idNumber);
        int affected = statement.executeUpdate();
        connection.close();
        return affected == 1;
    }
    
    public List<Professor> list() throws ClassNotFoundException, SQLException {
        List<Professor> profList = new ArrayList<Professor>();
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "select * from professors";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultset = statement.executeQuery();

        while (resultset.next()) {
        	Professor prof = new Professor();
        	prof.setName(resultset.getString(1));
        	prof.setSurname(resultset.getString(2));
        	prof.setId(resultset.getString(3));
        	prof.setAge(resultset.getInt(4));
        	prof.setEmail(resultset.getString(5));
        	prof.setGender(resultset.getString(6));
        	prof.setIsAdmin(resultset.getInt(7));
        	profList.add(prof);
        }
        connection.close();       
        return profList;
    }
    
    public boolean checkEmailInProfessors(String email) throws ClassNotFoundException, SQLException {
    	Connection connection = DatabaseUtilities.getConnection();
    	String sql = "select Email from professors";
    	PreparedStatement statement = connection.prepareStatement(sql);
    	ResultSet resultset = statement.executeQuery();
    	
    	while (resultset.next()) {
    		if (email.equals(resultset.getString(1))) {
    			connection.close();
    			return true;
    		}
    	}
    	connection.close();
    	return false;
    }
    
    public boolean checkEmailInCredentials(String email) throws ClassNotFoundException, SQLException {
    	Connection connection = DatabaseUtilities.getConnection();
    	String sql = "select Email from credentials";
    	PreparedStatement statement = connection.prepareStatement(sql);
    	ResultSet resultset = statement.executeQuery();
    	
    	while (resultset.next()) {
    		if (email.equals(resultset.getString(1))) {
    			connection.close();
    			return true;
    		}
    	}
    	connection.close();
    	return false;
    }
    
    public boolean createProfessorAccount(String email, String password) throws ClassNotFoundException, SQLException {
    	int affected;
    	Connection connection = DatabaseUtilities.getConnection();
    	String sql = "insert into credentials values (?,?)";
    	PreparedStatement statement = connection.prepareStatement(sql);
    	statement.setString(1, email);
    	statement.setString(2, password);
    	affected = statement.executeUpdate();
    	connection.close();
    	return affected >= 1;
    }
    
    public List<Lesson> getLessons(String ProfEmail) throws ClassNotFoundException, SQLException {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "select * from professor_lessons where Email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ProfEmail);
        ResultSet resultset = statement.executeQuery();

        while (resultset.next()) {
        	Lesson lesson = new Lesson();
        	lesson.setLessonName(resultset.getString(1));
        	lesson.setLessonCode(resultset.getString(2));
        	lessonList.add(lesson);
        }
        connection.close();       
        return lessonList;
    }
    
    public List<Student> getStudents(String ProfEmail) throws ClassNotFoundException, SQLException {
    	List<Student> studentList = new ArrayList<Student>();
    	Connection connection = DatabaseUtilities.getConnection();
    	String sql = "select students.Name, students.Surname, students.Number from students "
    			+ "inner join student_lessons on students.Number = student_lessons.Number "
    			+ "inner join professor_lessons on student_lessons.Code = professor_lessons.Code "
    			+ "where professor_lessons.Email = ?";
    	PreparedStatement statement = connection.prepareStatement(sql);
    	statement.setString(1, ProfEmail);
    	ResultSet resultset = statement.executeQuery();
    	
    	while (resultset.next()) {
    		Student student = new Student();
    		student.setName(resultset.getString(1));
    		student.setSurname(resultset.getString(2));
    		student.setStudentNumber(resultset.getString(3));
    		studentList.add(student);
    	}
    	connection.close();
    	return studentList;
    }
        
    public List<Exam> getExams(String ProfEmail) throws ClassNotFoundException, SQLException {
    	List<Exam> examList = new ArrayList<Exam>();
    	Connection connection = DatabaseUtilities.getConnection();
    	String sql = "select exams.Code, exams.Date from exams "
    			+ "inner join professor_lessons on exams.Code = professor_lessons.Code "
    			+ "inner join professors on professor_lessons.Email = professors.Email "
    			+ "where professors.Email = ?";
    	PreparedStatement statement = connection.prepareStatement(sql);
    	statement.setString(1, ProfEmail);
    	ResultSet resultset = statement.executeQuery();
    	
    	while (resultset.next()) {
    		Exam exam = new Exam();
    		
    	}
    	connection.close();
    	return examList;
    }
    
}	
