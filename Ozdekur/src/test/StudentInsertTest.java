package test;

import java.sql.SQLException;
import entities.Student;
import dbops.StudentManager;

public class StudentInsertTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StudentManager manager = new StudentManager();        
        
        Student s1 = new Student("Frank", "Mustermann", "111111111");
        boolean inserted = manager.insert(s1);
        System.out.println("Inserted: " + inserted);
        
		/*
		 * Student s2 = new Student("Richard", "Rich", "170501033"); boolean inserted2 =
		 * manager.insert(s2); System.out.println("Inserted: " + inserted2);
		 * 
		 * Student s3 = new Student("Lebron", "James", "170501038"); boolean inserted3 =
		 * manager.insert(s3); System.out.println("Inserted: " + inserted3);
		 */
    }
}
