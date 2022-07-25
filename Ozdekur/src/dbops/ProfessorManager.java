package dbops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Exam;
import entities.Lesson;
import entities.Professor;
import entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfessorManager {

	public int checkAdminStatus(String email) throws ClassNotFoundException, SQLException {
		int admin = 0;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select IsAdmin from professors where Email=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
			admin = resultset.getInt("IsAdmin");
		}
		connection.close();
		return admin;
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
		connection.close();
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

	public ObservableList<Professor> list() throws ClassNotFoundException, SQLException {
		ObservableList<Professor> profList = FXCollections.observableArrayList();
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
	
	public boolean alter(Professor professor) throws SQLException, ClassNotFoundException {
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "update professors "
				+ "set Name = ?, Surname = ?, ID = ?, Age = ?, Email = ?, Gender = ? "
				+ "where Email = ?";
		PreparedStatement statement = connection.prepareCall(sql);
		statement.setString(1, professor.getName());
		statement.setString(2, professor.getSurname());
		statement.setString(3, professor.getId());
		statement.setInt(4, professor.getAge());
		statement.setString(5, professor.getEmail());
		statement.setString(6, professor.getGender());
		statement.setString(7, professor.getEmail());
		int affected = statement.executeUpdate();
		connection.close();
		return affected >= 1;
	}

	public boolean checkEmailInProfessors(String email) throws ClassNotFoundException, SQLException {
		boolean check = false;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select Email from professors";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultset = statement.executeQuery();

		while (resultset.next()) {
			if (email.equals(resultset.getString(1))) {
				check = true;
			}
		}
		connection.close();
		return check;
	}

	public boolean checkEmailInCredentials(String email) throws ClassNotFoundException, SQLException {
		boolean check = false;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select Email from credentials";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultset = statement.executeQuery();

		while (resultset.next()) {
			if (email.equals(resultset.getString(1))) {
				check = true;
			}
		}
		connection.close();
		return check;
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

	public ObservableList<Lesson> getLessons(String ProfEmail) throws ClassNotFoundException, SQLException {
		ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select lessons.Code, lessons.Name from professor_lessons "
				+ "inner join lessons on professor_lessons.Code = lessons.Code " + "where Email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, ProfEmail);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
			Lesson lesson = new Lesson();
			lesson.setLessonCode(resultset.getString(1));
			lesson.setLessonName(resultset.getString(2));
			lessonList.add(lesson);
		}
		connection.close();
		return lessonList;
	}

	public ObservableList<Student> getStudents(String ProfEmail) throws ClassNotFoundException, SQLException {
		ObservableList<Student> studentList = FXCollections.observableArrayList();
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select students.Name, students.Surname, students.Number, student_lessons.Code from students "
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
			student.setTempLesson(resultset.getString(4));
			studentList.add(student);
		}
		connection.close();
		return studentList;
	}

	public ObservableList<Exam> getExams(String ProfEmail) throws ClassNotFoundException, SQLException {
		ObservableList<Exam> examList = FXCollections.observableArrayList();
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select exams.Code, exams.Date, lessons.Name from exams "
				+ "inner join professor_lessons on exams.Code = professor_lessons.Code "
				+ "inner join professors on professor_lessons.Email = professors.Email "
				+ "inner join lessons on exams.Code = lessons.Code " + "where professors.Email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, ProfEmail);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
			Exam exam = new Exam();
			Lesson lesson = new Lesson();
			lesson.setLessonCode(resultset.getString(1));
			lesson.setLessonName(resultset.getString(3));
			exam.setLesson(lesson);
			exam.setDate(resultset.getDate(2));
			exam.setLessonName(lesson.getLessonName());
			exam.setLessonCode(lesson.getLessonCode());
			examList.add(exam);
		}
		connection.close();
		return examList;
	}

	public boolean checkLessons(String Code, String Email) throws ClassNotFoundException, SQLException {
		boolean check = false;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select Code from professor_lessons where Email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, Email);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
			if (Code.equals(resultset.getString(1))) {
				check = true;
			}

		}
		connection.close();
		return check;
	}
	
	public ObservableList<Student> listStudentResults(String ProfMail) throws ClassNotFoundException, SQLException {
		ObservableList<Student> studLessonList = FXCollections.observableArrayList();
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select exam_results.Number, professor_lessons.Code, exam_results.Date, exam_results.Result "
				+ "from professor_lessons inner join exam_results on professor_lessons.Code = exam_results.Code "
				+ "where professor_lessons.Email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, ProfMail);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
			Student s = new Student();
			s.setStudentNumber(resultset.getString(1));
			s.setTempLesson(resultset.getString(2));
			s.setTempExamDate(resultset.getDate(3));
			s.setTotalNote(resultset.getDouble(4));
			studLessonList.add(s);
		}
		connection.close();
		return studLessonList;
	}

}
