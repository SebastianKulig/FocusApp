package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MenuController {



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

        menuHomeButton.setOnMouseClicked(event -> {
            goToMenu();
        });

        menuToDoButton.setOnMouseClicked(event -> {
            System.out.println("klik");
            goToToDoList();
        });
    }

    private void goToToDoList() {
        //addButton.relocate(0,20);
        //addButton.setOpacity(0);
        try {
            MenuController.userId = getUserId();
            ToDoListController.userId = getUserId();
            AnchorPane toDoListpane = new FXMLLoader().load(getClass().getResource("/sample/view/toDoList.fxml"));
            menuAnchorPane.getChildren().setAll(toDoListpane);
            //FadeTransition rootTransition = new FadeTransition(Duration.millis(4000), toDoListpane);
           // rootTransition.setFromValue(0f);
           // rootTransition.setToValue(1f);
           // rootTransition.setCycleCount(1);
            //rootTransition.setAutoReverse(false);
            //rootTransition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        menuToDoButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/toDoList.fxml"));
        try {

            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        ToDoListController toDoListController = loader.getController();
        System.out.println(userId);
        toDoListController.setUserId(userId);
        stage.showAndWait(); //nie ruszaj kurwa kolejności
    }

 */
    }

    public void goToMenu(){
        try {
            ToDoListController.userId = getUserId();
            MenuController.userId = getUserId();
            AnchorPane menuAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/menu.fxml"));
            menuAnchorPane.getChildren().setAll(menuAnchorPane);
            //FadeTransition rootTransition = new FadeTransition(Duration.millis(4000), toDoListpane);
            // rootTransition.setFromValue(0f);
            // rootTransition.setToValue(1f);
            // rootTransition.setCycleCount(1);
            //rootTransition.setAutoReverse(false);
            //rootTransition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
