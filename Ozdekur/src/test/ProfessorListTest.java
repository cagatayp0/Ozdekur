package test;

import java.sql.SQLException;
import java.util.List;

import dbops.ProfessorManager;
import entities.Professor;

public class ProfessorListTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProfessorManager m = new ProfessorManager();
        List<Professor> profList = m.list();
        for (Professor prof : profList) {
            System.out.println("");
            System.out.println(prof.getName()
                    + " " + prof.getSurname()
                    + " " + prof.getId());
        }
	}
}
