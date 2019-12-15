package sample.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class InfoController extends GoTo{


    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private Button menuToDoButton;

    @FXML
    private Button menuPomodoroButton;

    @FXML
    private Button menuInfoButton;

    @FXML
    private Button menuHomeButton;

    public static int userId;

    public static int getUserId() {
        return userId;
    }


    @FXML
    void initialize() {
        menuHomeButton.setOnMouseClicked(event -> {
            goToMenu();
        });
        menuPomodoroButton.setOnMouseClicked(event -> {
            goToPomodoroClock();
        });
        menuToDoButton.setOnMouseClicked(event -> {
            goToToDoList();
        });

    }

    public void goToMenu(){
        try {

            MenuController.userId = getUserId();
            AnchorPane menuAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/menu.fxml"));
            infoAnchorPane.getChildren().setAll(menuAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToToDoList() {
        try {

            ToDoListController.userId = getUserId();
            AnchorPane toDoListpane = new FXMLLoader().load(getClass().getResource("/sample/view/toDoList.fxml"));
            infoAnchorPane.getChildren().setAll(toDoListpane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToInfo(){ }
}

