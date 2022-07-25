package test;

import java.sql.SQLException;
import entities.Student;
import javafx.collections.ObservableList;
import dbops.StudentManager;

public class StudentListTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StudentManager manager = new StudentManager();
        ObservableList<Student> studentList = manager.list();
        for (Student student : studentList) {
            System.out.println("");
            System.out.println(student.getName()
                    + " " + student.getSurname()
                    + " " + student.getStudentNumber());
        }
    }
}
