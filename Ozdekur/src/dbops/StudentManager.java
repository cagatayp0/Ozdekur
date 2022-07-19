package dbops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Student;

public class StudentManager {

    public boolean insert(Student student) throws ClassNotFoundException, SQLException {
        int affected;
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "insert into Students (Number, Name, Surname) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getStudentNumber());
        statement.setString(2, student.getName());
        statement.setString(3, student.getSurname());
        affected = statement.executeUpdate();
        return affected >= 1;
    }

    public Student find(String number) throws ClassNotFoundException, SQLException {
        Student student = null;
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "select * from Students where Number=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, number);
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
            student = new Student();
            student.setName(resultset.getString("Name"));
            student.setSurname(resultset.getString("Surname"));
            student.setStudentNumber(resultset.getString("Number"));
        }
        connection.close();
        return student;
    }

    public boolean delete(String number) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "delete from Students where Number=?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, number);
        int affected = statement.executeUpdate();
        connection.close();
        return affected == 1;
    }
    
    public List<Student> list() throws ClassNotFoundException, SQLException {
        List<Student> studentList = new ArrayList<Student>();
        Connection connection = DatabaseUtilities.getConnection();
        String sql = "select * from Students";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultset = statement.executeQuery();

        while (resultset.next()) {
            Student student = new Student();
            student.setName(resultset.getString("Name"));
            student.setSurname(resultset.getString("Surname"));
            student.setStudentNumber(resultset.getString("Number"));
            studentList.add(student);
        }
        connection.close();       
        return studentList;
    }
    
    public boolean insertStudentToLesson(String StudentNumber, String LessonName) throws ClassNotFoundException, SQLException {
    	StudentManager man = new StudentManager();
    	Student s = man.find(StudentNumber);
    	String sql = "insert into BigTable values (?,?,?,?)";
    	Connection connection = DatabaseUtilities.getConnection();
    	PreparedStatement statement = connection.prepareStatement(sql);
    	statement.setString(1, s.getName());
    	statement.setString(2, s.getSurname());
    	statement.setString(3, s.getStudentNumber());
    	statement.setString(4, LessonName);
    	int affected = statement.executeUpdate();
    	connection.close();
    	return affected == 1;
    }
    
}