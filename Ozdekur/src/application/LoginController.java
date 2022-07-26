package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbops.DataSingleton;
import dbops.DatabaseUtilities;
import dbops.ProfessorManager;

public class LoginController {

	public LoginController() {

	}

	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonLoginAdmin;
	@FXML
	private Button buttonForgotPassword;
	@FXML
	private Label labelWrongPassword;
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button buttonCreateAccount;
	@FXML
	private AnchorPane loginPane;
	
	DataSingleton data = DataSingleton.getInstance();

	public void normalLogin(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
		//checkLogin("MainScene.fxml");
		ProfessorManager m = new ProfessorManager();
		if (m.checkAdminStatus(tfUsername.getText().toString()) == 1) {
			openAdminScene(e);
		} else {
			openMainScene(e);
		}
	}
	
	public void openForgotPasswordScene() throws IOException {
		Main m = new Main();
		m.changeScene("ForgotPasswordScene.fxml");
	}
	
	public void createAccount(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
		Main m = new Main();
		m.changeScene("CreateAccountScene.fxml");
	}

	private void checkLogin(String fxmlToLoad) throws IOException, SQLException, ClassNotFoundException {
		Main m = new Main();
		Connection connection = DatabaseUtilities.getConnection();
        String verifyLogin = "select count(1) from Credentials where Email = '" +
        		tfUsername.getText().toString() + "'" + "and " +
        		"Password = '" + tfPassword.getText().toString() + "'";
        PreparedStatement statement = connection.prepareStatement(verifyLogin);
        ResultSet queryResult = statement.executeQuery(verifyLogin);
        while (queryResult.next()) {
        	if (queryResult.getInt(1) == 1) {
        		m.changeScene(fxmlToLoad);
        	} else {
        		labelWrongPassword.setText("Wrong username or password!");
        	}
        }
        connection.close();
	}
	
	private void openMainScene(ActionEvent event) throws IOException {
		data.setEmail(tfUsername.getText().toString());
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		loginPane.getChildren().removeAll();
		loginPane.getChildren().setAll(mainPane);
	}
	
	private void openAdminScene(ActionEvent event) throws IOException {
		data.setEmail(tfUsername.getText().toString());
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("AdminScene.fxml"));
		loginPane.getChildren().removeAll();
		loginPane.getChildren().setAll(mainPane);
	}
		
}
