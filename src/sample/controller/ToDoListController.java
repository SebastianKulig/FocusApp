package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ToDoListController extends GoTo {
    public static  int userId;

    @FXML
    private AnchorPane toDoListAnchorPane;

    @FXML
    private Button addButton;

    @FXML
    private ListView<Task> toDoListListView;

    @FXML
    private Button menuToDoButton;

    @FXML
    private Button menuPomodoroButton;

    @FXML
    private Button menuInfoButton;

    @FXML
    private Button menuHomeButton;

    private DatabaseHandler databaseHandler;

    public static ObservableList<Task> tasks;



    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();
        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getUserTasks(ToDoListController.userId);
        while(resultSet.next()){
            Task task = new Task();
            task.setTaskId(resultSet.getInt("id_task"));
            task.setTask_name(resultSet.getString("task"));
            task.setDescription(resultSet.getString("description"));
            tasks.addAll(task);
        }

        toDoListListView.setItems(tasks);
        toDoListListView.setCellFactory(RowCellController ->new RowCellController()); //do naszej customowej zamiast defaultowej


        menuHomeButton.setOnMouseClicked(event -> {
            goToMenu();
        });

        menuPomodoroButton.setOnMouseClicked(event -> {
            goToPomodoroClock();
        });

        menuInfoButton.setOnMouseClicked(event -> {
            goToInfo();
        });

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
            stage.showAndWait();

        });

    }

    public int getUserId(){
        return this.userId;
    }

    public void goToMenu(){
        try {


            MenuController.userId = getUserId();
            AnchorPane MenuPane = new FXMLLoader().load(getClass().getResource("/sample/view/menu.fxml"));
            toDoListAnchorPane.getChildren().setAll(MenuPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToToDoList(){}
    public void goToInfo(){
        try {

            InfoController.userId = getUserId();
            AnchorPane infoAnchorPane = new FXMLLoader().load(getClass().getResource("/sample/view/Info.fxml"));
            toDoListAnchorPane.getChildren().setAll(infoAnchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



