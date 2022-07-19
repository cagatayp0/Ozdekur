package test;

import java.sql.SQLException;

import dbops.ProfessorManager;

public class ProfessorCredentialCheckTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean val;
		boolean val2;
		ProfessorManager m = new ProfessorManager();
		val = m.checkEmailInProfessors("john@ozdekur.com");
		val2 = m.checkEmailInCredentials("aaa");
		System.out.println(val);
		System.out.println(val2);
	}
}
