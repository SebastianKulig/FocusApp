package sample.controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ToDoListController {
    public static  int userId;

    @FXML
    private AnchorPane toDoListAnchorPane;

    @FXML
    private Button addButton;

    @FXML
    private ListView<Task> toDoListListView;

    @FXML
    private JFXButton closeButton;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private DatabaseHandler databaseHandler;

    public static ObservableList<Task> tasks;

    private double xOffset = 0;
    private double yOffset = 0;



    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList(); //do wyświetlania zadań
        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getUserTasks(ToDoListController.userId); //zadania danego użytkownika
        while(resultSet.next()){
            Task task = new Task();
            task.setTaskId(resultSet.getInt("id_task"));
            task.setTask_name(resultSet.getString("task"));
            task.setDescription(resultSet.getString("description"));
            tasks.addAll(task);
        }

        toDoListListView.setItems(tasks);
        toDoListListView.setCellFactory(RowCellController ->new RowCellController()); //do naszej customowej zamiast defaultowej


        addButton.setOnAction(event -> {
            addButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addForm.fxml"));
            try {
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            AddFormController addFormController = loader.getController();
            addFormController.setUserId(userId); //nie ruszać kolejności, najpierw trzeba ustawić id w addForm

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

        });

    }

}



