package sample.controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {


    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button loginSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private AnchorPane loginAnchorPane;

    private DatabaseHandler db;

    private int userId;
    @FXML
    void initialize() {
        DatabaseHandler db = new DatabaseHandler();
        loginSignInButton.setOnAction(event -> {
                String loginText = loginUsernameField.getText().trim();
                String loginPassword = loginPasswordField.getText().trim();
                User user1 = new User();
                user1.setUserName(loginText);
                user1.setPassword(loginPassword);
                ResultSet userFromDB = db.returnUserFromDB(user1);

                try {
                    while (userFromDB.next()) {
                        String user_name = userFromDB.getString("first_name");
                        userId = userFromDB.getInt("id_user");
                        goToMenu();
                    }
                }catch (SQLException e){
                   e.printStackTrace();
                }
        });


        loginSignUpButton.setOnAction(event -> {//lambda
            AnchorPane signUpAnchorPane = null;
            try {
                signUpAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/signUp.fxml"));
                loginAnchorPane.getChildren().setAll(signUpAnchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

        private void goToMenu() {
            loginSignInButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/menu.fxml"));
            try {
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            MenuController menuController = loader.getController();
            menuController.setUserId(userId);
            stage.setOnCloseRequest(e -> {       // wychodzenie, bez tego trzeba podwójnie zamykać
                Platform.exit();
            System.exit(0);
            });
            stage.setTitle("FOCUS");
            stage.showAndWait();


        }
}
