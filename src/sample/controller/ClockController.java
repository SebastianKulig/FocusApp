package sample.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClockController {


    @FXML
    private Text clockMinutes;

    @FXML
    private Text clockSeconds;

    @FXML
    private JFXButton clockExitButton;

    @FXML
    private JFXButton clockWorkButton;

    @FXML
    private JFXButton cloclPauseButton;

    @FXML
    private JFXButton clockBreakButton;
    @FXML
    private void clockExitButtonAction(){
        Stage stage = (Stage) clockExitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert clockMinutes != null : "fx:id=\"clockMinutes\" was not injected: check your FXML file 'clock.fxml'.";
        assert clockSeconds != null : "fx:id=\"clockSeconds\" was not injected: check your FXML file 'clock.fxml'.";
        assert clockExitButton != null : "fx:id=\"clockExitButton\" was not injected: check your FXML file 'clock.fxml'.";
        assert clockWorkButton != null : "fx:id=\"clockWorkButton\" was not injected: check your FXML file 'clock.fxml'.";
        assert cloclPauseButton != null : "fx:id=\"cloclPauseButton\" was not injected: check your FXML file 'clock.fxml'.";
        assert clockBreakButton != null : "fx:id=\"clockBreakButton\" was not injected: check your FXML file 'clock.fxml'.";

    }
}
