package sample.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.event.TreeModelEvent;

public class ClockController {
    public static  int userId;
    private int minuty = 1;
    private int przerwa = 5;
    private boolean check;
    public int getUserId(){
        return this.userId;
    }


    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getMinuty() {
        return minuty;
    }

    public int getPrzerwa() {
        return przerwa;
    }

    void setMinuty (int n) {
        this.minuty = n;
    }

    void setPrzerwa (int n) {
        this.przerwa = n;
    }

    void czasSkupienia () {
        int czas = getMinuty() * 60;
        clockTitle.setText("Skup się!");
        tickTock(czas);
        clockTitle.setText("Przerwa?");
    }


    void czasPrzerwy () {
        int czas = getPrzerwa() * 60;
        clockTitle.setText("Przerwa :-)");
        tickTock(czas);
        clockTitle.setText("Do roboty!");
    }


    void tickTock (int czas) {
        for (int i = czas; i >= 0; i--) {
            if (!check) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {}
            //System.out.println(czas/60 + ":"+ czas%60);
            clockDisplay.setText(i/60 + ":"+ i%60);
        }
    }

    private boolean isInt (TextField field) {
        try {
            Integer.parseInt(field.getText());
            return true;
        } catch (NumberFormatException e) {
            field.setText("Dane muszą być liczbą");
            return false;
        }
    }
    @FXML
    private AnchorPane clockAnchorPane;

    @FXML
    private ImageView clockStopButton;

    @FXML
    private ImageView clockPlayButton;

    @FXML
    private ImageView clockPlayButton1;

    @FXML
    private ImageView clockPauseButton;

    @FXML
    private TextField clockDisplay;

    @FXML
    private TextField clockTitle;

    @FXML
    private TextField setMinutyTx;

    @FXML
    private TextField setPrzerwaTx;

    @FXML
    private Button setMinutyButton;

    @FXML
    private Button setPrzerwaButton;

    public void initialize () {
        clockPlayButton1.setVisible(false);

        clockPlayButton.setOnMouseClicked(e -> {
            Thread thread = new Thread(() -> {
                setCheck(true);
                clockPlayButton.setVisible(false);
                clockPlayButton1.setVisible(true);
                czasSkupienia();
            });
            thread.start();
        });

        clockPlayButton1.setOnMouseClicked(e -> {
            Thread thread = new Thread(() -> {
                setCheck(true);
                clockPlayButton.setVisible(true);
                clockPlayButton1.setVisible(false);
                czasPrzerwy();
            });
            thread.start();
        });

        clockPauseButton.setOnMouseClicked(e -> {
         clockPauseButton.getScene().getWindow().hide();
        });

        clockStopButton.setOnMouseClicked(e -> {
            setCheck(false);
        });

        setMinutyButton.setOnAction(e -> {
            if (isInt(setMinutyTx)) {
                setMinuty(Integer.parseInt(setMinutyTx.getText()));
            }
        });

        setPrzerwaButton.setOnAction(e -> {
            if (isInt(setPrzerwaTx)) {
                setPrzerwa(Integer.parseInt(setPrzerwaTx.getText()));
            }
        });
    }


}

