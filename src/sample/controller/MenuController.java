package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class MenuController extends GoTo {

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private Button menuHomeButton;

    @FXML
    private Button menuToDoButton;

    @FXML
    private Button menuPomodoroButton;

    @FXML
    private Button menuInfoButton;


    public static int userId;

    public static void setUserId(int userId) {
        MenuController.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }


    @FXML
    void initialize() {

        menuToDoButton.setOnMouseClicked(event -> {
            goToToDoList();
        });

        menuPomodoroButton.setOnMouseClicked(event -> {
            goToPomodoroClock();
        });

        menuInfoButton.setOnMouseClicked(event -> {
            goToInfo();
        });
    }

    public void goToToDoList() {
        try {
            MenuController.userId = getUserId();
            ToDoListController.userId = getUserId();
            AnchorPane toDoListpane = new FXMLLoader().load(getClass().getResource("/sample/view/toDoList.fxml"));
            menuAnchorPane.getChildren().setAll(toDoListpane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToMenu(){ } // z klasy abstrakcyjnej trzeba wszystkie metody zaimplementować

    public void goToInfo(){
        try {
            MenuController.userId = getUserId();
            InfoController.userId = getUserId();
            AnchorPane infoAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/Info.fxml"));
            menuAnchorPane.getChildren().setAll(infoAnchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

