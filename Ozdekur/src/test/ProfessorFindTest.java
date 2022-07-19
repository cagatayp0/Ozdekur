package test;

import java.sql.SQLException;

import dbops.ProfessorManager;

public class ProfessorFindTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ProfessorManager m = new ProfessorManager();
		System.out.println(m.find("jimbo@ozdekur.com").getName());
	}
}
