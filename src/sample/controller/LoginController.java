package sample.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.database.DatabaseHandler;
import sample.model.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {
    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXTextField loginUsernameField;

    @FXML
    private JFXPasswordField loginPasswordField;

    @FXML
    private Button loginSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private Label loginErrorLabel;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private DatabaseHandler db;

    private int userId;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    void initialize() {
        loginSignInButton.setDefaultButton(true);

        DatabaseHandler db = new DatabaseHandler();
        loginSignInButton.setOnAction(event -> {
                String loginText = loginUsernameField.getText().trim();
                String loginPassword = loginPasswordField.getText().trim();
                User user1 = new User();
                user1.setUserName(loginText);
                user1.setPassword(loginPassword);
                ResultSet userFromDB = db.returnUserFromDB(user1); //sprawdzamy czy baza danych zwroci nam jakiegoś użytkownika dla tego loginu i hasła
                try {
                    while (userFromDB.next()) { // jeśli zostanie zwrócony z bazy danych 1 użytkownik to zezwalamay na dostęp
                        String user_name = userFromDB.getString("first_name");
                        userId = userFromDB.getInt("id_user");
                        goToMenu();
                    }
                    loginErrorLabel.setVisible(true);
                    loginUsernameField.clear();
                    loginPasswordField.clear();
                }catch (SQLException e){
                   e.printStackTrace();

                }
        });


        loginSignUpButton.setOnAction(event -> {
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
            Scene scene = new Scene(root);
            //stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);

            MenuController menuController = loader.getController();
            menuController.setUserId(userId);



            stage.initStyle(StageStyle.UNDECORATED);


            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });


            stage.show();

        }
}
