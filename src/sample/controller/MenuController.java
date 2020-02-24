package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class MenuController {

    @FXML
    private AnchorPane rootAnchorPane;

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

    @FXML
    private JFXButton closeButton;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public static int userId;

    public static void setUserId(int userId) {
        MenuController.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }


    @FXML
    void initialize() {
        rootAnchorPane.setBackground(Background.EMPTY);

        menuHomeButton.setOnMouseClicked(event -> {
            goToMenu();
        });

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

    public void goToPomodoroClock() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/clock.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
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
        stage.showAndWait();


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

    public void goToMenu(){
        try{
            AnchorPane menuAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/menu.fxml"));
            rootAnchorPane.getChildren().setAll(menuAnchorPane); //musiałem brać z root bo inaczej była pętla sam się do siebie musiałbym odwoływać

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

