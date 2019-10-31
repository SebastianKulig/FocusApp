package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;

import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
        primaryStage.setTitle("FOCUS");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
