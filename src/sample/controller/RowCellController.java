package sample.controller;

import java.io.IOException;


import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.Database.DatabaseHandler;
import sample.model.Task;

public class RowCellController extends JFXListCell<Task> {


    @FXML
    private AnchorPane rowCellAnchorPane;

    @FXML
    private ImageView rowCellImageView;

    @FXML
    private Label rowCellTaskLabel;

    @FXML
    private Label rowCellDescriptionLabel;
    @FXML
    private CheckBox checkBox;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler databaseHandler;
    @FXML
    void initialize() {


    }

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if(empty || task == null){
            setText(null);
            setGraphic(null);
        }else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/rowCell.fxml"));
                fxmlLoader.setController(this); //this class is controller to rowCell
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rowCellTaskLabel.setText(task.getTask_name());
            rowCellDescriptionLabel.setText(task.getDescription());
            int taskId = task.getTaskId();

            checkBox.setOnAction(event -> {
                removingTaskRow(taskId);

            });

            setText(null);
            setGraphic(rowCellAnchorPane); //umieszczamy wszystko w naszym anchorpane
        }
    }
    public void removingTaskRow(int taskId){ //usuwanie zadań z listy po zaznaczeni checkbox

        if(checkBox.isSelected()){
            databaseHandler = new DatabaseHandler();                             //delete from database
            databaseHandler.deleteTask(ToDoListController.userId, taskId);
            getListView().getItems().remove(getItem());

        }
    }
}