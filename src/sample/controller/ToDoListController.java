package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.ImageView;
public class ToDoListController {
    public static  int userId;

    @FXML
    private AnchorPane toDoListAnchorPane;

    @FXML
    private Button addButton;

    @FXML
    private ListView<Task> toDoListListView;

    @FXML
    private ImageView menuPomodoroButton;

    @FXML
    private Button menuStatisticsButton;

    @FXML
    private ImageView menuHomeButton;

    @FXML
    private ImageView menuToDoButton;
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
            //System.out.println("user tasks: " + resultSet.getString("task"));
        }

        toDoListListView.setItems(tasks);
        toDoListListView.setCellFactory(RowCellController ->new RowCellController()); //do naszej customowej zamiast defaultowej


        menuHomeButton.setOnMouseClicked(event -> {
            goToMenu();
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
            //AddFormController addFormController = loader.getController();
            //addFormController.setUserId(userId);
            //AddFormController.userId = getUserId();
            //addButton.relocate(0,20);
            //addButton.setOpacity(0);
            /*
            try {
                AnchorPane paneaddForm = new FXMLLoader().load(getClass().getResource("/sample/view/addForm.fxml"));

                ToDoListController.userId = getUserId();

                toDoListAnchorPane.getChildren().setAll(paneaddForm);
                FadeTransition rootTransition = new FadeTransition(Duration.millis(4000), paneaddForm);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);

                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

            } catch (IOException e) {
                e.printStackTrace();
            }
*/
        });

    }

    public void setUserId(int userId){
        this.userId = userId;
        System.out.println("id " + this.userId);
    }
    public int getUserId(){
        return this.userId;
    }

    public void goToMenu(){
        try {

            ToDoListController.userId = getUserId();
            MenuController.userId = getUserId();
            System.out.println("from toDoList" + userId);
            AnchorPane MenuPane = new FXMLLoader().load(getClass().getResource("/sample/view/menu.fxml"));
            toDoListAnchorPane.getChildren().setAll(MenuPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



