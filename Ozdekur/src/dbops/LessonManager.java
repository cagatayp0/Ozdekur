package dbops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LessonManager {
	
	public boolean insert(Lesson lesson) throws ClassNotFoundException, SQLException {
		int affected;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "insert into lessons (Code, Name) values (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, lesson.getLessonCode());
		statement.setString(2, lesson.getLessonName());
		affected = statement.executeUpdate();
		connection.close();
		return affected >= 1;
	}
	
	public boolean delete(String Code) throws ClassNotFoundException, SQLException {
		int affected;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "delete from lessons where Code = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, Code);
		affected = statement.executeUpdate();
		connection.close();
		return affected >= 1;
	}
	
	public ObservableList<Lesson> list() throws ClassNotFoundException, SQLException {
		ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select * from lessons";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultset = statement.executeQuery();

		while (resultset.next()) {
			Lesson l = new Lesson();
			l.setLessonCode(resultset.getString(1));
			l.setLessonName(resultset.getString(2));
			lessonList.add(l);
		}
		connection.close();
		return lessonList;
	}
	
	public boolean exists(Lesson lesson) throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "select * from lessons";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
				if (lesson.getLessonName().equals(resultset.getString(2)) && lesson.getLessonCode().equals(resultset.getString(1))) {
					return true;
			}
		}
		connection.close();
		return false;
	}
	
}
