package test;

import java.sql.SQLException;
import entities.Student;
import dbops.StudentManager;

public class StudentFindTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StudentManager manager = new StudentManager();
        Student s1 = manager.find("175502302");
        if (s1 != null) {
            System.out.println(s1.getName() + s1.getSurname()
                    + s1.getStudentNumber());
        }

        Student s2 = manager.find("170501033");
        if (s2 != null) {
            System.out.println(s2.getName() + " " +  s2.getSurname()
                    + " " + s2.getStudentNumber());
        }
    }
}
