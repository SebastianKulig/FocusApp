package sample.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import javax.xml.crypto.Data;

public class AddFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addFormTaskfield;

    @FXML
    private TextField addFormDescrytpionField;

    @FXML
    private Button addFormAddButton;



    @FXML
    private Label addFormSuccesfullyLabel;

    private DatabaseHandler dbhandler;

    private int userId;

    @FXML
    void initialize() {
        DatabaseHandler dbhandler =new DatabaseHandler();




        addFormAddButton.setOnAction(event -> {
            Task task = new Task();

            String task_name =addFormTaskfield.getText().trim();
            String task_descryption = addFormDescrytpionField.getText().trim();
            if(!task_descryption.equals("") || !task.equals("")) {

               // task.setUserId(ToDoListController.userId);
                System.out.println("w dodawaniu zadań"+userId);
                task.setUserId(getUserId());
                task.setTask_name(task_name);
                task.setDescription(task_descryption);

                addFormSuccesfullyLabel.setVisible(true);

                addFormTaskfield.setText("");
                addFormDescrytpionField.setText("");
            }else{
                System.out.println("nothing added");
            }


            dbhandler.addTask(task);



            ResultSet resultSet = dbhandler.getUserTasks(ToDoListController.userId);
            ToDoListController.tasks.clear();
                try {
                    while(resultSet.next()) {
                        Task myTask = new Task();
                        myTask.setTaskId(resultSet.getInt("id_task"));
                        myTask.setTask_name(resultSet.getString("task"));
                        myTask.setDescription(resultSet.getString("description"));
                        ToDoListController.tasks.add(myTask);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


        });


    }
    public int getUserId() {

        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}