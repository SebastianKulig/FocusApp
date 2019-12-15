package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class GoTo {
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
        stage.showAndWait();
    }

    public abstract void goToMenu();
    public abstract void goToToDoList();
    public abstract void goToInfo();
}