package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import dbops.*;
import entities.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class AdminSceneController implements Initializable {

	@FXML
	private Label labelWrongExam;
	@FXML
	private Label labelStudentAlreadyInLesson;
	@FXML
	private Text textWelcome;
	@FXML
	private Text textLoggedInAs;
	@FXML
	private Button buttonLogout;
	@FXML
	private Button buttonInsertStudent;
	@FXML
	private Button buttonRemoveStudent;
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
	private TextField tfStudentInsertLesson;
	@FXML
	private TextField tfChangeExamResults;
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
	private TableColumn<Student, String> columnStudentName;
	@FXML
	private TableColumn<Student, String> columnStudentSurname;
	@FXML
	private TableColumn<Student, String> columnStudentNumber;
	@FXML
	private TableColumn<Student, String> columnMyStudentLesson;
	@FXML
	private TableColumn<Student, String> columnMyStudentName;
	@FXML
	private TableColumn<Student, String> columnMyStudentSurname;
	@FXML
	private TableColumn<Student, String> columnMyStudentNumber;
	@FXML
	private TableColumn<Student, Double> columnStudentNote;
	@FXML
	private TableView<Exam> tableExams;
	@FXML
	private TableView<Student> tableMyStudents;
	@FXML
	private TableColumn<Exam, String> columnExamLesson;
	@FXML
	private TableColumn<Exam, Date> columnExamDate;
	@FXML
	private TableView<Student> tableIndividualExams;
	@FXML
	private TableColumn<Student, String> columnIndividualNumber;
	@FXML
	private TableColumn<Student, String> columnIndividualLesson;
	@FXML
	private TableColumn<Student, Date> columnIndividualDate;
	@FXML
	private TableColumn<Student, Double> columnIndividualNote;

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

		String ExamName = tfExamLessonName.getText().toString();
		java.util.Date date = java.util.Date
				.from(dpExamDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		if (pm.checkLessons(ExamName, profMail) == true) {
			em.insert(ExamName, sqlDate);
		} else {
			labelWrongExam.setText("You don't teach a lesson called " + ExamName);
		}
		tableExams.getItems().clear();
		ObservableList<Exam> oListExam = pm.getExams(profMail);
		tableExams.setItems(oListExam);
	}

	public void DeleteExam() throws ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		Exam exam = tableExams.getSelectionModel().getSelectedItem();
		String ExamName = exam.getLessonCode();
		Date sqlDate = exam.getDate();
		em.delete(ExamName, sqlDate);
		tableExams.refresh();
		tableExams.getItems().clear();
		ObservableList<Exam> oListExam = pm.getExams(profMail);
		tableExams.setItems(oListExam);
	}

	public void InsertStudentToLesson() throws ClassNotFoundException, SQLException {
		labelStudentAlreadyInLesson.setText("");
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		Student student = tableStudents.getSelectionModel().getSelectedItem();
		student.setTempLesson(tfStudentInsertLesson.getText().toString());
		if (student != null) {
			if (sm.checkStudentInLesson(student.getStudentNumber(), student.getTempLesson()) == false) {
				if (pm.checkLessons(student.getTempLesson(), profMail) == true) {
					sm.insertStudentToLesson(student.getStudentNumber(), student.getTempLesson());
				} else {
					labelStudentAlreadyInLesson.setTextFill(Color.RED);
					labelStudentAlreadyInLesson.setText("You don't teach " + student.getTempLesson());
				}
			} else {
				labelStudentAlreadyInLesson.setTextFill(Color.RED);
				labelStudentAlreadyInLesson.setText("This student is already in lesson");
			}
			tableMyStudents.getItems().clear();
			ObservableList<Student> oListMyStuds = pm.getStudents(profMail);
			tableMyStudents.setItems(oListMyStuds);
		}
	}

	public void DeleteStudentFromLesson() throws ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		Student student = tableMyStudents.getSelectionModel().getSelectedItem();
		if (student != null) {
			sm.removeStudentFromLesson(student.getStudentNumber(), student.getTempLesson());
			tableMyStudents.getItems().clear();
			ObservableList<Student> oListMyStuds = pm.getStudents(profMail);
			tableMyStudents.setItems(oListMyStuds);
		}
	}
	
	public void ChangeExamResults() throws NumberFormatException, ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		Student student = tableIndividualExams.getSelectionModel().getSelectedItem();
		if (student != null) {
			sm.changeStudentNotes(Double.parseDouble(tfChangeExamResults.getText().toString()), student.getStudentNumber());
		}
		tableIndividualExams.getItems().clear();
		ObservableList<Student> oListStudResults = pm.listStudentResults(profMail);
		tableIndividualExams.setItems(oListStudResults);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textLoggedInAs.setText("You are logged in as: " + data.getEmail());
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];

		try {
			ObservableList<Student> oListStudent = sm.list();
			ObservableList<Lesson> oListLesson = pm.getLessons(profMail);
			ObservableList<Exam> oListExam = pm.getExams(profMail);
			ObservableList<Student> oListMyStudent = pm.getStudents(profMail);
			ObservableList<Student> oListStudResults = pm.listStudentResults(profMail);

			columnStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
			columnStudentSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
			columnStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
			tableStudents.setItems(oListStudent);

			columnMyStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
			columnMyStudentSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
			columnMyStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
			columnMyStudentLesson.setCellValueFactory(new PropertyValueFactory<Student, String>("TempLesson"));
			tableMyStudents.setItems(oListMyStudent);

			columnLessonNames.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonName"));
			columnLessonCodes.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonCode"));
			tableLessons.setItems(oListLesson);

			columnExamDate.setCellValueFactory(new PropertyValueFactory<Exam, Date>("date"));
			columnExamLesson.setCellValueFactory(new PropertyValueFactory<Exam, String>("LessonCode"));
			tableExams.setItems(oListExam);
			
			columnIndividualDate.setCellValueFactory(new PropertyValueFactory<Student, Date>("TempExamDate"));
			columnIndividualNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("StudentNumber"));
			columnIndividualLesson.setCellValueFactory(new PropertyValueFactory<Student, String>("TempLesson"));
			columnIndividualNote.setCellValueFactory(new PropertyValueFactory<Student, Double>("TotalNote"));
			tableIndividualExams.setItems(oListStudResults);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
