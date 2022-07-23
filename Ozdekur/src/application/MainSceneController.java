package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import dbops.ProfessorManager;
import dbops.StudentManager;
import entities.Exam;
import entities.Lesson;
import entities.Student;
import dbops.DataSingleton;
import dbops.ExamManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class MainSceneController implements Initializable {

	@FXML
	private Label labelWrongExam;
	@FXML
	private Text textWelcome;
	@FXML
	private Text textLoggedInAs;
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
	private Button buttonCreateExam;
	@FXML
	private Button buttonDeleteExam;
	@FXML
	private TextField tfExamLessonName;
	@FXML
	private TextField tfStudName;
	@FXML
	private TextField tfStudSurname;
	@FXML
	private TextField tfStudNumber;
	@FXML
	private TextField tfStudLesson;
	@FXML
	private DatePicker dpExamDate;
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

	DataSingleton data = DataSingleton.getInstance();

	StudentManager sm = new StudentManager();
	ProfessorManager pm = new ProfessorManager();
	ExamManager em = new ExamManager();

	public void Logout() throws IOException {
		Main m = new Main();
		m.changeScene("LoginScene.fxml");
	}

	public void CreateNewExam() throws ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		List<Exam> tempExamList = pm.getExams(profMail);

		String ExamName = tfExamLessonName.getText().toString();
		java.util.Date date = java.util.Date
				.from(dpExamDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		if (pm.checkLessons(ExamName, profMail) == true) {
			em.insert(ExamName, sqlDate);
		} else {
			labelWrongExam.setText("You don't teach a lesson called " + ExamName);
		}

		ObservableList<Exam> oListExam = FXCollections.observableArrayList(tempExamList);
		columnExamDate.setCellValueFactory(new PropertyValueFactory<Exam, Date>("date"));
		columnExamLesson.setCellValueFactory(new PropertyValueFactory<Exam, String>("LessonCode"));
		columnExamNote.setCellValueFactory(new PropertyValueFactory<Exam, Double>("MeanNote"));
		tableExams.setItems(oListExam);
	}
	
	public void DeleteExam() {
		
	}

	public void InsertStudentToLesson() throws ClassNotFoundException, SQLException {

	}

	public void DeleteStudentFromLesson() throws ClassNotFoundException, SQLException {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textLoggedInAs.setText("You are logged in as: " + data.getEmail());
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];

		try {
			List<Student> tempStudList = pm.getStudents(profMail);
			List<Lesson> tempLessonList = pm.getLessons(profMail);
			List<Exam> tempExamList = pm.getExams(profMail);
			ObservableList<Student> oListStudent = FXCollections.observableArrayList(tempStudList);
			ObservableList<Lesson> oListLesson = FXCollections.observableArrayList(tempLessonList);
			ObservableList<Exam> oListExam = FXCollections.observableArrayList(tempExamList);

			columnStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
			columnStudentSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
			columnStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
			tableStudents.setItems(oListStudent);

			columnLessonNames.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonName"));
			columnLessonCodes.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonCode"));
			tableLessons.setItems(oListLesson);

			columnExamDate.setCellValueFactory(new PropertyValueFactory<Exam, Date>("date"));
			columnExamLesson.setCellValueFactory(new PropertyValueFactory<Exam, String>("LessonCode"));
			columnExamNote.setCellValueFactory(new PropertyValueFactory<Exam, Double>("MeanNote"));
			tableExams.setItems(oListExam);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
