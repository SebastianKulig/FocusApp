package sample.controller;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import javafx.stage.Stage;

public class InfoController {


    @FXML
    private JFXButton closeButton;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public static int userId;

    public static int getUserId() {
        return userId;
    }


    @FXML
    void initialize() {

    }


}

