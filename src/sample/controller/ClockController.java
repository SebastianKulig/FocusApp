package sample.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.event.TreeModelEvent;

public class ClockController {
    public static int userId;
    private int minuty = 25; //Domyślny czas skupienia
    private int przerwa = 5; //Domyślny czas przerwy
    private boolean check; //Do sprawdzania czy przycisk stop został naciśnięty
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
        int czas = getMinuty() * 60; //Zamiana podanych minut na sekundy
        clockTitle.setText("Skup się!");
        tickTock(czas); //Uruchomienie samego odliczania
        clockTitle.setText("Przerwa?");
    } //Metoda uruchamiająca część skupienia

    void czasPrzerwy () {
        int czas = getPrzerwa() * 60;
        clockTitle.setText("Przerwa :-)");
        tickTock(czas);
        clockTitle.setText("Do roboty!");
    } //Metoda uruchamiająca część przerwy

    void tickTock (int czas) {
        for (int i = czas; i >= 0; i--) {
            if (!check) { //Jeżeli nie został włączony przycisk przerwy
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {}
            clockDisplay.setText(i/60 + ":"+ i%60);
        }
    } //Metoda odliczająca

    private boolean isInt (TextField field) {
        try {
            Integer.parseInt(field.getText());
            return true;
        } catch (NumberFormatException e) { //Jezeli podany tekst nie jest liczbą, to pisze w polu, że ma być
            field.setText("Dane muszą być liczbą");
            return false;
        }
    } //Obsługa wyjątku
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

    @FXML
    private Button goToMenuButton;

    @FXML
    private Label skupienieTx;

    @FXML
    private Label przerwaTx;

    public void initialize () {
        clockPlayButton.setOnMouseClicked(e -> { //Po wciśnięciu myszką
            Thread thread = new Thread(() -> { //Startuje nowy wątek
                setCheck(true); //Reset przycisku stop
                clockPlayButton.setVisible(false);//Ukrywanie przycisku
                skupienieTx.setVisible(false);//I podpisu
                czasSkupienia();
                clockPlayButton.setVisible(true);//I po wykonanym odliczaniu pojawiają się znowu
                skupienieTx.setVisible(true);
            });
            thread.start();//Odpalenie wątku
        }); //Przycisk skupienia

        clockPlayButton1.setOnMouseClicked(e -> {
            Thread thread = new Thread(() -> {
                setCheck(true);
                clockPlayButton1.setVisible(false);
                przerwaTx.setVisible(false);
                czasPrzerwy();
                clockPlayButton1.setVisible(true);
                przerwaTx.setVisible(true);
            });
            thread.start();
        }); //Przycisk przerwy

        goToMenuButton.setOnAction(e -> {
            goToMenuButton.getScene().getWindow().hide();

        }); //Przycisk przejścia do menu

        clockStopButton.setOnMouseClicked(e -> {
            setCheck(false);
        }); //Przycisk wyłączający wątki

        setMinutyButton.setOnAction(e -> {
            if (isInt(setMinutyTx)) {
                setMinuty(Integer.parseInt(setMinutyTx.getText()));
            }
        }); //Przyjęcie czasu skupienia

        setPrzerwaButton.setOnAction(e -> {
            if (isInt(setPrzerwaTx)) {
                setPrzerwa(Integer.parseInt(setPrzerwaTx.getText()));
            }
        }); //Przyjęcie przerwy
    }


}




