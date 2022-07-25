package dbops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentManager {

	public boolean insert(Student student) throws ClassNotFoundException, SQLException {
		int affected;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "insert into students (Number, Name, Surname) values (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, student.getStudentNumber());
		statement.setString(2, student.getName());
		statement.setString(3, student.getSurname());
		affected = statement.executeUpdate();
		connection.close();
		return affected >= 1;
	}

	public Student find(String number) throws ClassNotFoundException, SQLException {
		Student student = null;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select * from students where Number=?";
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
		String sql = "delete from students where Number=?";
		PreparedStatement statement = connection.prepareCall(sql);
		statement.setString(1, number);
		int affected = statement.executeUpdate();
		connection.close();
		return affected == 1;
	}

	public ObservableList<Student> list() throws ClassNotFoundException, SQLException {
		ObservableList<Student> studentList = FXCollections.observableArrayList();
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select * from students";
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

	public boolean insertStudentToLesson(String StudentNumber, String LessonName)
			throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "insert into student_lessons values (?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, LessonName);
		statement.setString(2, StudentNumber);
		int affected = statement.executeUpdate();
		connection.close();
		return affected >= 1;
	}

	public boolean removeStudentFromLesson(String StudentNumber, String LessonCode) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "delete from student_lessons where Number = ? and Code = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, StudentNumber);
		statement.setString(2, LessonCode);
		int affected = statement.executeUpdate();
		connection.close();
		return affected >= 1;
	}
	
	public boolean checkStudentInLesson(String StudentNumber, String LessonCode) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select * from student_lessons where Number = ? and Code = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, StudentNumber);
		statement.setString(2, LessonCode);
		ResultSet resultset = statement.executeQuery();
		
		while (resultset.next()) {
			Student student = new Student();
			student.setStudentNumber(resultset.getString(1));
			student.setTempLesson(resultset.getString(2));
			if (student.getStudentNumber().equals(StudentNumber) && student.getTempLesson().equals(LessonCode)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean changeStudentNotes(Double NewResult, String StudentNumber) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "update exam_results set Result = ? where Number = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, NewResult);
		statement.setString(2, StudentNumber);
		int affected = statement.executeUpdate();
		return affected >= 1;
	}

}
