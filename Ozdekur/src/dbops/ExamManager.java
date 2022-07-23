package dbops;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExamManager {

	public boolean insert(String Code, Date Date) throws ClassNotFoundException, SQLException {
		int affected;
		Connection connection = DatabaseUtilities.getConnection();
		String sql = "insert into exams values (?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, Code);
		statement.setDate(2, Date);
		affected = statement.executeUpdate();
		return affected >= 1;
	}
	
}
