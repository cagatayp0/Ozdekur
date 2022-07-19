package test;

import java.sql.SQLException;
import java.util.List;
import entities.Student;
import dbops.StudentManager;

public class StudentListTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StudentManager manager = new StudentManager();
        List<Student> studentList = manager.list();
        for (Student student : studentList) {
            System.out.println("");
            System.out.println(student.getName()
                    + " " + student.getSurname()
                    + " " + student.getStudentNumber());
        }
    }
}
