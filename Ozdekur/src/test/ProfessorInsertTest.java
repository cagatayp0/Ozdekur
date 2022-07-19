package test;

import java.sql.SQLException;

import dbops.ProfessorManager;
import entities.Professor;

public class ProfessorInsertTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ProfessorManager m = new ProfessorManager();
		Professor p = new Professor("Jim", "Clark", "12345678526", 65, "jimbo@ozdekur.com", "Male");
		boolean inserted = m.insert(p);
		System.out.println("Inserted: " + inserted);
	}
    
}
