package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import dbops.ProfessorManager;

public class CreateAccountController {

	public CreateAccountController() {

	}

	@FXML
	private Button buttonConfirm;
	@FXML
	private Button buttonAlreadyHave;
	@FXML
	private Label labelWrongCredentials;
	@FXML
	private TextField tfEmail;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private PasswordField tfPassword2;
	
	public void createAccount() throws ClassNotFoundException, SQLException {
		ProfessorManager m = new ProfessorManager();
		String email = tfEmail.getText().toString();
		String p1 = tfPassword.getText().toString();
		String p2 = tfPassword2.getText().toString();
		if (m.checkEmailInProfessors(email) == true) {
			if (m.checkEmailInCredentials(email) == false) {
				if (checkPasswords(p1, p2) == true) {
					m.createProfessorAccount(email, p1);
					labelWrongCredentials.setTextFill(Color.GREEN);
					labelWrongCredentials.setText("Account Created");
				} else {
					labelWrongCredentials.setTextFill(Color.RED);
					labelWrongCredentials.setText("Passwords don't match!");
				}
			} else {
				labelWrongCredentials.setTextFill(Color.RED);
				labelWrongCredentials.setText("Account already exist!");
			}
		} else {
			labelWrongCredentials.setTextFill(Color.RED);
			labelWrongCredentials.setText("Account doesn't exist!");
		}
	}
	
	public void goBack(ActionEvent e) throws IOException {
		Main m = new Main();
		m.changeScene("LoginScene.fxml");
	}
	
	private boolean checkPasswords(String p1, String p2) {
		if (p1.equals(p2)) {
			return true;
		}
		return false;
	}
		
}
