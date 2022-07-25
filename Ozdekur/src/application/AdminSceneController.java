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
	private Label labelCreateLesson;
	@FXML
	private Label labelCreateStudent;
	@FXML
	private Label labelCreateProfessor;
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
	private Button buttonDeleteLesson;
	@FXML
	private Button buttonCreateNewLesson;
	@FXML
	private Button buttonDeleteStudent;
	@FXML
	private Button buttonCreateStudent;
	@FXML
	private Button buttonAlterStudent;
	@FXML
	private TextField tfExamLessonName;
	@FXML
	private TextField tfStudentInsertLesson;
	@FXML
	private TextField tfChangeExamResults;
	@FXML
	private TextField tfNewLessonName;
	@FXML
	private TextField tfNewLessonCode;
	@FXML
	private TextField tfNewStudentName;
	@FXML
	private TextField tfNewStudentSurname;
	@FXML
	private TextField tfNewStudentNumber;
	@FXML
	private TextField tfNewProfessorName;
	@FXML
	private TextField tfNewProfessorSurname;
	@FXML
	private TextField tfNewProfessorID;
	@FXML
	private TextField tfNewProfessorAge;
	@FXML
	private TextField tfNewProfessorEmail;
	@FXML
	private TextField tfNewProfessorGender;
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
	@FXML
	private TableView<Lesson> tableMyLessons;
	@FXML
	private TableColumn<Lesson, String> columnMyLessonName;
	@FXML
	private TableColumn<Lesson, String> columnMyLessonCode;
	@FXML
	private TableView<Professor> tableProfessors;
	@FXML
	private TableColumn<Professor, String> columnProfessorName;
	@FXML
	private TableColumn<Professor, String> columnProfessorSurname;
	@FXML
	private TableColumn<Professor, String> columnProfessorID;
	@FXML
	private TableColumn<Professor, Integer> columnProfessorAge;
	@FXML
	private TableColumn<Professor, String> columnProfessorMail;
	@FXML
	private TableColumn<Professor, String> columnProfessorGender;
	@FXML
	private TableColumn<Professor, Integer> columnProfessorAdminStatus;

	DataSingleton data = DataSingleton.getInstance();

	StudentManager sm = new StudentManager();
	ProfessorManager pm = new ProfessorManager();
	ExamManager em = new ExamManager();
	LessonManager lm = new LessonManager();

	public void Logout() throws IOException {
		Main m = new Main();
		m.changeScene("LoginScene.fxml");
	}
	
	public void DeleteLesson() throws ClassNotFoundException, SQLException {
		Lesson lesson = tableLessons.getSelectionModel().getSelectedItem();
		if (lesson != null) {
			lm.delete(lesson.getLessonCode());
			tableLessons.getItems().clear();
			ObservableList<Lesson> oListLesson = lm.list();
			tableLessons.setItems(oListLesson);
		}
	}
	
	public void CreateNewLesson() throws ClassNotFoundException, SQLException {
		Lesson lesson = new Lesson();
		lesson.setLessonCode(tfNewLessonCode.getText().toString());
		lesson.setLessonName(tfNewLessonName.getText().toString());
		if (lesson.getLessonCode().length() < 7) {
			if (lm.exists(lesson) == false) {
				lm.insert(lesson);
				tableLessons.getItems().clear();
				ObservableList<Lesson> oListLesson = lm.list();
				tableLessons.setItems(oListLesson);
			} else {
				labelCreateLesson.setTextFill(Color.RED);
				labelCreateLesson.setText("Lesson already exists!");
			}
		} else {
			labelCreateLesson.setTextFill(Color.RED);
			labelCreateLesson.setText("Lesson code should have 6 digits!");
		}
	}
	
	public void DeleteStudent() throws ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		
		Student student = tableStudents.getSelectionModel().getSelectedItem();
		sm.delete(student.getStudentNumber());
		
		tableStudents.getItems().clear();
		ObservableList<Student> studentList = sm.list();
		tableStudents.setItems(studentList);
		
		tableMyStudents.getItems().clear();
		ObservableList<Student> myStudentList = pm.getStudents(profMail);
		tableMyStudents.setItems(myStudentList);
	}
	
	public void CreateStudent() throws ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		Student student = new Student();
		student.setName(tfNewStudentName.getText().toString());
		student.setSurname(tfNewStudentSurname.getText().toString());
		student.setStudentNumber(tfNewStudentNumber.getText().toString());
		
		if (sm.find(student.getStudentNumber()) == null) {
			if (student.checkStudentNumber(student.getStudentNumber()) == true) {
				sm.insert(student);
				labelCreateStudent.setText("Student was created");
				labelCreateStudent.setTextFill(Color.GREEN);
			} else {
				labelCreateStudent.setText("Student number must have 9 digits!");
				labelCreateStudent.setTextFill(Color.RED);
			}
		} else {
			labelCreateStudent.setText("Student already exists!");
			labelCreateStudent.setTextFill(Color.RED);
		}
		
		tableStudents.getItems().clear();
		ObservableList<Student> studentList = sm.list();
		tableStudents.setItems(studentList);
		
		tableMyStudents.getItems().clear();
		ObservableList<Student> myStudentList = pm.getStudents(profMail);
		tableMyStudents.setItems(myStudentList);
	}
	
	public void AlterStudent() throws ClassNotFoundException, SQLException {
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];
		Student student = new Student();
		student.setName(tfNewStudentName.getText().toString());
		student.setSurname(tfNewStudentSurname.getText().toString());
		student.setStudentNumber(tfNewStudentNumber.getText().toString());
		
		if (sm.find(student.getStudentNumber()) != null) {
			if (student.checkStudentNumber(student.getStudentNumber()) == true) {
				sm.alter(student.getStudentNumber(), student);
				labelCreateStudent.setText("Student was updated");
				labelCreateStudent.setTextFill(Color.GREEN);
			} else {
				labelCreateStudent.setText("Student number must have 9 digits!");
				labelCreateStudent.setTextFill(Color.RED);
			}
		} else {
			labelCreateStudent.setText("Student doesn't exist!");
			labelCreateStudent.setTextFill(Color.RED);
		}
		
		tableStudents.getItems().clear();
		ObservableList<Student> studentList = sm.list();
		tableStudents.setItems(studentList);
		
		tableMyStudents.getItems().clear();
		ObservableList<Student> myStudentList = pm.getStudents(profMail);
		tableMyStudents.setItems(myStudentList);
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
	
	public void CreateProfessor() throws ClassNotFoundException, SQLException {
		Professor professor = new Professor();
		professor.setName(tfNewProfessorName.getText().toString());
		professor.setSurname(tfNewProfessorSurname.getText().toString());
		professor.setAge(Integer.parseInt(tfNewProfessorAge.getText().toString()));
		professor.setEmail(tfNewProfessorEmail.getText().toString());
		professor.setGender(tfNewProfessorGender.getText().toString());
		professor.setId(tfNewProfessorID.getText().toString());
		professor.setIsAdmin(0);
		if (professor.checkId(professor.getId())) {
			if (professor.getGender().toLowerCase().equals("male") || professor.getGender().toLowerCase().equals("female")) {
				pm.insert(professor);
				labelCreateProfessor.setText("Professor has created!");
				labelCreateProfessor.setTextFill(Color.GREEN);
			} else {
				labelCreateProfessor.setText("Please enter a valid gender!");
				labelCreateProfessor.setTextFill(Color.RED);
			}
		} else {
			labelCreateProfessor.setText("ID should have 11 digits!");
			labelCreateProfessor.setTextFill(Color.RED);
		}

	}
	
	public void AlterProfessor() throws ClassNotFoundException, SQLException {
		Professor professor = new Professor();
		professor.setName(tfNewProfessorName.getText().toString());
		professor.setSurname(tfNewProfessorSurname.getText().toString());
		professor.setAge(Integer.parseInt(tfNewProfessorAge.getText().toString()));
		professor.setEmail(tfNewProfessorEmail.getText().toString());
		professor.setGender(tfNewProfessorGender.getText().toString());
		professor.setId(tfNewProfessorID.getText().toString());
		professor.setIsAdmin(0);
		if (pm.find(professor.getEmail()) != null) {
			if (professor.checkId(professor.getId())) {
				if (professor.getGender().toLowerCase().equals("male") || professor.getGender().toLowerCase().equals("female")) {
					//pm.insert(professor);
					labelCreateProfessor.setText("Professor informations are updated!");
					labelCreateProfessor.setTextFill(Color.GREEN);
				} else {
					labelCreateProfessor.setText("Please enter a valid gender!");
					labelCreateProfessor.setTextFill(Color.RED);
				}
			} else {
				labelCreateProfessor.setText("ID should have 11 digits!");
				labelCreateProfessor.setTextFill(Color.RED);
			}
		} else {
			labelCreateProfessor.setText("This professor doesn't exist!");
			labelCreateProfessor.setTextFill(Color.RED);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textLoggedInAs.setText("You are logged in as: " + data.getEmail());
		String[] parts = textLoggedInAs.getText().toString().split(": ");
		String profMail = parts[1];

		try {
			ObservableList<Student> oListStudent = sm.list();
			ObservableList<Lesson> oListLesson = lm.list();
			ObservableList<Exam> oListExam = pm.getExams(profMail);
			ObservableList<Student> oListMyStudent = pm.getStudents(profMail);
			ObservableList<Student> oListStudResults = pm.listStudentResults(profMail);
			ObservableList<Lesson> oListMyLesson = pm.getLessons(profMail);
			ObservableList<Professor> oListProfessors = pm.list();

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
			
			columnMyLessonName.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonName"));
			columnMyLessonCode.setCellValueFactory(new PropertyValueFactory<Lesson, String>("LessonCode"));
			tableMyLessons.setItems(oListMyLesson);
			
			columnProfessorName.setCellValueFactory(new PropertyValueFactory<Professor, String>("Name"));
			columnProfessorSurname.setCellValueFactory(new PropertyValueFactory<Professor, String>("Surname"));
			columnProfessorMail.setCellValueFactory(new PropertyValueFactory<Professor, String>("Email"));
			columnProfessorID.setCellValueFactory(new PropertyValueFactory<Professor, String>("Id"));
			columnProfessorGender.setCellValueFactory(new PropertyValueFactory<Professor, String>("Gender"));
			columnProfessorAge.setCellValueFactory(new PropertyValueFactory<Professor, Integer>("Age"));
			columnProfessorAdminStatus.setCellValueFactory(new PropertyValueFactory<Professor, Integer>("IsAdmin"));
			tableProfessors.setItems(oListProfessors);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
