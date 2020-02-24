package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;
import java.io.IOException;


public class SignUpController {


    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private JFXTextField  signUpLastName;

    @FXML
    private JFXTextField  signUpFirstName;

    @FXML
    private Button signUpButton;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private Button signUpBackButton;

    @FXML
    private AnchorPane signUpAnchorPane;

    @FXML
    private Label wrongUsernameLabel;

    @FXML
    private JFXButton closeButton;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            signUpUser();
        });

        signUpBackButton.setOnAction(event -> { //powrót do ekranu logowania
            AnchorPane loginAnchorPane = null;
            try {
                loginAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/login.fxml"));
                signUpAnchorPane.getChildren().setAll(loginAnchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
        private void signUpUser () {
            DatabaseHandler dbhandler = new DatabaseHandler();
            User user1 = new User(signUpFirstName.getText(), signUpLastName.getText(), signUpUsername.getText(), signUpPassword.getText());
            wrongUsernameLabel.setVisible(!dbhandler.createUser(user1)); //createUser zwróci false jeśli nie udało się utworzyć użytkownika
            signUpFirstName.setText("");
            signUpLastName.setText("");
            signUpPassword.setText("");
            signUpUsername.setText("");

            };


        }


