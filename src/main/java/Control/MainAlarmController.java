package Control;


import Model.Alarm;
import animatefx.animation.Shake;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class MainAlarmController {

    @FXML
    protected Label clockShow, datefield;

    @FXML
    protected Button exit;

    @FXML
    protected ComboBox<String> setHour;

    @FXML
    protected ComboBox<String> setMinute;

    @FXML
    protected AnchorPane mainPane;

    @FXML
    protected Label colorC1,colorC2,colorC3,colorC4,colorC5,colorC6;

    @FXML
    protected Label textC1,textC2,textC3,textC4,textC5,textC6;

    @FXML
    protected Button buttonC1,buttonC2,buttonC3,buttonC4,buttonC5,buttonC6;

    @FXML
    protected ComboBox<String> setClock;

    @FXML
    protected Button save;


    protected MediaPlayer player;

    protected String clockNumber;
    protected String hour;
    protected String minute;

    protected Alarm[] alarmsList = new Alarm[6];

    protected ObservableList<String> alarmNumber = FXCollections.observableArrayList();
    protected ObservableList<String> hourNumber = FXCollections.observableArrayList();
    protected ObservableList<String> minuteNumber = FXCollections.observableArrayList();

    public void initialize() {
        setClearComboValue();
        for (int i = 0; i < 6; i++) {
            Alarm alarm = new Alarm(1,"");
            alarmsList[i] = alarm;
            alarmNumber.add(i+1+"");
        }
        for (int i = 0; i < 24; i++) {
            hourNumber.add(i+"");
        }
        for (int i = 0; i < 60; i++) {
            minuteNumber.add(i+"");
        }
        setClock.setItems(alarmNumber);
        setHour.setItems(hourNumber);
        setMinute.setItems(minuteNumber);
        System.out.println(Arrays.toString(alarmsList));
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMM YYYY");
        datefield.setText(format.format(today));
        System.out.println(format.format(today));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = now.format(formatter);
            System.out.println(time);
            clockShow.setText(time);
            //datefield.setText(format.format(today)); ถ้าจะเปิดข้ามวันต้องมา setText ในนี้เพื่อให้อัพเดทเวลา
            for (Alarm alarm : alarmsList) {
                if (alarm.getTimeSet().equals(time)){
                    exit.setDisable(true);
                    nowWake();
                }
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) exit.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void nowWake() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Wake.fxml"));
        try {
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(loader.load(), 480, 280));
            playSound();
            stage.setTitle("!!! WAKE UP !!!");
            stage.setResizable(false);
            WakeController controller = (WakeController) loader.getController();
            controller.setParentController(this);
            controller.Question();
            stage.show();
            new Shake(stage.getScene().getRoot()).play();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void playSound() {
        Random random = new Random();
        int ran = random.nextInt(2) + 1;
        String musicFile = "/Sound/" + "alarm" + ran + ".mp3";
        try {
            Media sound = new Media(getClass().getResource(musicFile).toURI().toString());
            this.player = new MediaPlayer(sound);
            player.play();
            player.setCycleCount(MediaPlayer.INDEFINITE);

        } catch (URISyntaxException e) {
            e.getStackTrace();
        }
    }

    public void stopPlayer() {
        player.stop();
    }


    public void handleSaveButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please complete the information field.", ButtonType.OK);
        alert.setHeaderText("");
        if (event.getSource().equals(save)) {
            try {
                if (!setClock.getValue().equals("-") && !setHour.getValue().equals("-") && !setMinute.getValue().equals("-")){
                    clockNumber = setClock.getValue();
                    hour = setHour.getValue();
                    minute = setMinute.getValue();
                    setClockShow(hour, minute);
                    switch (clockNumber) {
                        case "1":
                            textC1.setText(hour + " : " + minute);
                            colorC1.setStyle("-fx-background-color: #ff6b6b");
                            buttonC1.setDisable(false);
                            break;
                        case "2":
                            textC2.setText(hour + " : " + minute);
                            colorC2.setStyle("-fx-background-color: #ff6b6b");
                            buttonC2.setDisable(false);
                            break;
                        case "3":
                            textC3.setText(hour + " : " + minute);
                            colorC3.setStyle("-fx-background-color: #ff6b6b");
                            buttonC3.setDisable(false);
                            break;
                        case "4":
                            textC4.setText(hour + " : " + minute);
                            colorC4.setStyle("-fx-background-color: #ff6b6b");
                            buttonC4.setDisable(false);
                            break;
                        case "5":
                            textC5.setText(hour + " : " + minute);
                            colorC5.setStyle("-fx-background-color: #ff6b6b");
                            buttonC5.setDisable(false);
                            break;
                        case "6":
                            textC6.setText(hour + " : " + minute);
                            colorC6.setStyle("-fx-background-color: #ff6b6b");
                            buttonC6.setDisable(false);
                            break;
                        default:
                            return;
                    }
                } else {
                    alert.show();
                    return;
                }
            } catch (NumberFormatException e) {
                alert.show();
                return;
            }
        }
        setClearComboValue();
    }

    public void setClearComboValue(){
        setClock.setValue("-");
        setHour.setValue("-");
        setMinute.setValue("-");
    }

    public void setClockShow(String h, String m) {
        if (h.length() == 1) {
            hour = "0" + h;
        } else {
            hour = h;
        }
        if (m.length() == 1) {
            minute = "0" + m;
        } else if (m.equals("60")) {
            minute = "00";
        } else {
            minute = m;
        }
    }

    public int setOnOff(Alarm alarm, Label label) {
        if (alarm.getStatus() == 1) {
            label.setStyle("-fx-background-color: #b8e994");
            alarm.setStatus(0);
            return alarm.getStatus();
        }
        label.setStyle("-fx-background-color: #ff6b6b");
        alarm.setStatus(1);
        return alarm.getStatus();
    }

    public String splitToSet(String clock){
        String turn = "";
        String[] split = clock.split(" ");
        for (String s : split) {
            turn += s;
        }
        return turn;
    }

    @FXML
    public void handleOnOff(ActionEvent event) {
        Button incomeMing = (Button) event.getSource();
        String buttonName = incomeMing.getId();
        int status;

        switch (buttonName) {
            case "buttonC1":
                status = setOnOff(alarmsList[0], colorC1);
                if (status == 0) alarmsList[0].setTimeSet(splitToSet(textC1.getText())+ ":00");
                if (status == 1) alarmsList[0].setTimeSet("");
                break;
            case "buttonC2":
                status = setOnOff(alarmsList[1], colorC2);
                if (status == 0) alarmsList[1].setTimeSet(splitToSet(textC2.getText()) + ":00");
                if (status == 1) alarmsList[1].setTimeSet("");
                break;
            case "buttonC3":
                status = setOnOff(alarmsList[2], colorC3);
                if (status == 0) alarmsList[2].setTimeSet(splitToSet(textC3.getText()) + ":00");
                if (status == 1) alarmsList[2].setTimeSet("");
                break;
            case "buttonC4":
                status = setOnOff(alarmsList[3], colorC4);
                if (status == 0) alarmsList[3].setTimeSet(splitToSet(textC4.getText())+ ":00");
                if (status == 1) alarmsList[3].setTimeSet("");
                break;
            case "buttonC5":
                status = setOnOff(alarmsList[4], colorC5);
                if (status == 0) alarmsList[4].setTimeSet(splitToSet(textC5.getText()) + ":00");
                if (status == 1) alarmsList[4].setTimeSet("");
                break;
            case "buttonC6":
                status = setOnOff(alarmsList[5], colorC6);
                if (status == 0) alarmsList[5].setTimeSet(splitToSet(textC6.getText()) + ":00");
                if (status == 1) alarmsList[5].setTimeSet("");
                break;
        }
        System.out.println(Arrays.toString(alarmsList));
    }
}


