package sample.controller;

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
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.javaws.ui.SplashScreen.hide;

public class SignUpController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpBackButton;

    @FXML
    private AnchorPane signUpAnchorPane;

    @FXML
    private Label wrongUsernameLabel;


    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            //dbhandler.createUser(signUpFirstName.getText(), signUpLastName.getText(), signUpUsername.getText(), signUpPassword.getText());
            signUpUser();
        });
        signUpBackButton.setOnAction(event -> {
            AnchorPane loginAnchorPane = null;
            try {
                loginAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/login.fxml"));
                signUpAnchorPane.getChildren().setAll(loginAnchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }


            /*
            signUpBackButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

             */


        });

    }

        private void signUpUser () {
            DatabaseHandler dbhandler = new DatabaseHandler();
            User user1 = new User(signUpFirstName.getText(), signUpLastName.getText(), signUpUsername.getText(), signUpPassword.getText());
            wrongUsernameLabel.setVisible(!dbhandler.createUser(user1)); //piekne :D konstruktorem se wpisuje wszystkie dane
            signUpFirstName.setText("");
            signUpLastName.setText("");
            signUpPassword.setText("");
            signUpUsername.setText("");

            };

   /* private void goBackToLogin() {
        signUpBackButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
        try {

            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    */


        }


