package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import dbops.ProfessorManager;
import dbops.StudentManager;
import entities.Exam;
import entities.Lesson;
import entities.Professor;
import entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;


public class MainSceneController implements Initializable{

	@FXML
	private Button buttonLogout;
	
	@FXML
	private Button buttonInsertStudent;
	
	@FXML
	private Button buttonDeleteStudent;
	
	@FXML
	private Button buttonCreateNewExam;
	
	@FXML
	private Button buttonAlterResults;
	
	@FXML
	private TextField tfStudName;
	
	@FXML
	private TextField tfStudSurname;
	
	@FXML
	private TextField tfStudNumber;
	
	@FXML
	private TextField tfStudLesson;
	
	@FXML
	private TableView<Lesson> tableLessons;
	
	@FXML
	private TableColumn<Lesson, String> columnLessonNames;
	
	@FXML
	private TableColumn<Lesson, String> columnLessonCodes;
	
	@FXML
	private TableView<Student> tableStudents;
	
	@FXML
	private TableColumn<Student, String> columnStudentLesson;
	
	@FXML
	private TableColumn<Student, String> columnStudentName;
	
	@FXML
	private TableColumn<Student, String> columnStudentSurname;
	
	@FXML
	private TableColumn<Student, String> columnStudentNumber;
	
	@FXML
	private TableColumn<Student, Double> columnStudentNote;
	
	@FXML
	private TableView<Exam> tableExams;
	
	@FXML
	private TableColumn<Exam, String> columnExamLesson;
	
	@FXML
	private TableColumn<Exam, Date> columnExamDate;
	
	@FXML
	private TableColumn<Exam, Double> columnExamNote;
	
	@FXML
	private TableView<Student> individualExamTable;
		
	
	public void Logout(ActionEvent e) throws IOException {
		Main m = new Main();
		m.changeScene("LoginScene.fxml");
	}
	
	
	public void setUser(String Email) throws ClassNotFoundException, SQLException {
		
	}
		
	public void CreateNewExam() {
		
	}
	
	public void InsertStudentToLesson(ActionEvent e) throws ClassNotFoundException, SQLException {
		Student student = new Student();
		student.setName(tfStudName.getText().toString());
		student.setSurname(tfStudSurname.getText().toString());
		student.setLesson(tfStudLesson.getText().toString());
		student.setStudentNumber(tfStudNumber.getText().toString());
		StudentManager s = new StudentManager();
		s.insert(student);
		s.insertStudentToLesson(student.getStudentNumber(), student.getLesson());
		ProfessorManager m = new ProfessorManager();
		Professor p = new Professor();
		p.setEmail("john@ozdekur.com");
		List<Student> studentList = m.getStudents(p.getEmail());
		ObservableList<Student> observableStudentList = FXCollections.observableArrayList(studentList);
		columnStudentLesson.setCellValueFactory(new PropertyValueFactory<Student, String>("Lesson"));
		columnStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
		columnStudentSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
		columnStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
		tableStudents.setItems(observableStudentList);
	}
	
	public void DeleteStudentFromLesson(ActionEvent e) throws ClassNotFoundException, SQLException {
		Student student = new Student();
		student.setName(tfStudName.getText().toString());
		student.setSurname(tfStudSurname.getText().toString());
		student.setLesson(tfStudLesson.getText().toString());
		student.setStudentNumber(tfStudNumber.getText().toString());
		ProfessorManager m = new ProfessorManager();
		Professor p = new Professor();
		p.setEmail("john@ozdekur.com");
		m.removeStudentFromLesson(tfStudNumber.getText().toString());
		List<Student> studentList = m.getStudents(p.getEmail());
		ObservableList<Student> observableStudentList = FXCollections.observableArrayList(studentList);
		columnStudentLesson.setCellValueFactory(new PropertyValueFactory<Student, String>("Lesson"));
		columnStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
		columnStudentSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
		columnStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
		tableStudents.setItems(observableStudentList);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProfessorManager m = new ProfessorManager();
		Professor p = null;
		try {
			p = m.find("john@ozdekur.com");
			List<Lesson> lessonList;
			lessonList = m.getLessons(p.getEmail());
			ObservableList<Lesson> observableLessonList = FXCollections.observableArrayList(lessonList);
			columnLessonNames.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonName"));
			columnLessonCodes.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonCode"));
			tableLessons.setItems(observableLessonList);
			
			List<Student> studentList = m.getStudents(p.getEmail());
			ObservableList<Student> observableStudentList = FXCollections.observableArrayList(studentList);
			columnStudentLesson.setCellValueFactory(new PropertyValueFactory<Student, String>("Lesson"));
			columnStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
			columnStudentSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
			columnStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
			tableStudents.setItems(observableStudentList);
			
			List<Exam> examList = m.getExams(p.getEmail());
			ObservableList<Exam> observableExamList = FXCollections.observableArrayList(examList);
			columnExamLesson.setCellValueFactory(new PropertyValueFactory<Exam, String>("lesson"));
			columnExamDate.setCellValueFactory(new PropertyValueFactory<Exam, Date>("date"));
			columnExamNote.setCellValueFactory(new PropertyValueFactory<Exam, Double>("MeanNote"));
			tableExams.setItems(observableExamList);
			

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
		
}
