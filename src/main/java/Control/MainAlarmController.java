package Control;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;


public class MainAlarmController {

    @FXML
    protected Label clockShow, datefield;

    @FXML
    protected Button exit;

    @FXML
    protected TextField setHour;

    @FXML
    protected TextField setMinute;

    @FXML
    protected AnchorPane mainPane;

    @FXML
    protected Label colorC1;

    @FXML
    protected Label textC1;

    @FXML
    protected Button buttonC1;

    @FXML
    protected TextField setClock;

    @FXML
    protected Button save;

    @FXML
    protected Label colorC2;

    @FXML
    protected Label textC2;

    @FXML
    protected Label colorC3;

    @FXML
    protected Label textC3;

    @FXML
    protected Label colorC4;

    @FXML
    protected Label textC4;

    @FXML
    protected Label colorC5;

    @FXML
    protected Label textC5;

    @FXML
    protected Label textC6;

    @FXML
    protected Label colorC6;

    @FXML
    protected Button buttonC2;

    @FXML
    protected Button buttonC3;

    @FXML
    protected Button buttonC4;

    @FXML
    protected Button buttonC5;

    @FXML
    protected Button buttonC6;

    protected MediaPlayer player;

    protected String clockNumber;
    protected String hour;
    protected String minute;

    protected String clock1 = "";
    protected String clock2 = "";
    protected String clock3 = "";
    protected String clock4 = "";
    protected String clock5 = "";
    protected String clock6 = "";

    protected Model.Button b1;
    protected Model.Button b2;
    protected Model.Button b3;
    protected Model.Button b4;
    protected Model.Button b5;
    protected Model.Button b6;

    public void initialize() {
        b1 = new Model.Button(1);
        b2 = new Model.Button(1);
        b3 = new Model.Button(1);
        b4 = new Model.Button(1);
        b5 = new Model.Button(1);
        b6 = new Model.Button(1);
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
            if (time.equals(clock1) || time.equals(clock2) || time.equals(clock3) || time.equals(clock4) || time.equals(clock5) || time.equals(clock6)) {
                exit.setDisable(true);
                nowWake();
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
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please check the field information.", ButtonType.OK);
        alert.setHeaderText("");
        if (event.getSource().equals(save)) {
            try {
                if (!setClock.getText().equals("") && !setHour.getText().equals("") && !setMinute.getText().equals("")) {
                    if ((Integer.parseInt(setClock.getText()) >= 1) && (Integer.parseInt(setClock.getText()) <= 6) && (Integer.parseInt(setHour.getText()) >= 0)
                            && (Integer.parseInt(setHour.getText()) <= 23) && (Integer.parseInt(setMinute.getText()) >= 0) && (Integer.parseInt(setClock.getText()) <= 60)) {
                        clockNumber = setClock.getText();
                        hour = setHour.getText();
                        minute = setMinute.getText();
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
                    }
                } else {
                    alert.show();
                }
            } catch (NumberFormatException e) {
                alert.show();
                return;
            }
        }
        setClock.clear();
        setHour.clear();
        setMinute.clear();
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


    @FXML
    public void handleOnOff(ActionEvent event) {
        Button buttonThatClick = (Button) event.getSource();
        String button = buttonThatClick.getId();
        switch (button) {
            case "buttonC1":
                if (b1.getStatus() == 1) {
                    colorC1.setStyle("-fx-background-color: #b8e994");
                    clock1 = hour + ":" + minute + ":00";
                    b1.setStatus(0);
                } else if (b1.getStatus() == 0) {
                    b1.setStatus(1);
                    colorC1.setStyle("-fx-background-color: #ff6b6b");
                    clock1 = "";
                }
                break;
            case "buttonC2":
                if (b2.getStatus() == 1) {
                    colorC2.setStyle("-fx-background-color: #b8e994");
                    clock2 = hour + ":" + minute + ":00";
                    b2.setStatus(0);
                } else if (b2.getStatus() == 0) {
                    b2.setStatus(1);
                    colorC2.setStyle("-fx-background-color: #ff6b6b");
                    clock2 = "";
                }
                break;
            case "buttonC3":
                if (b3.getStatus() == 1 && !textC3.getText().equals("-- : --")) {
                    colorC3.setStyle("-fx-background-color: #b8e994");
                    clock3 = hour + ":" + minute + ":00";
                    b3.setStatus(0);
                } else if (b3.getStatus() == 0) {
                    b3.setStatus(1);
                    colorC3.setStyle("-fx-background-color: #ff6b6b");
                    clock3 = "";
                }
                break;
            case "buttonC4":
                if (b4.getStatus() == 1 && !textC4.getText().equals("-- : --")) {
                    colorC4.setStyle("-fx-background-color: #b8e994");
                    clock4 = hour + ":" + minute + ":00";
                    b4.setStatus(0);
                } else if (b4.getStatus() == 0) {
                    b4.setStatus(1);
                    colorC4.setStyle("-fx-background-color: #ff6b6b");
                    clock4 = "";
                }
                break;
            case "buttonC5":
                if (b5.getStatus() == 1 && !textC5.getText().equals("-- : --")) {
                    colorC5.setStyle("-fx-background-color: #b8e994");
                    clock5 = hour + ":" + minute + ":00";
                    b5.setStatus(0);
                } else if (b5.getStatus() == 0) {
                    b5.setStatus(1);
                    colorC5.setStyle("-fx-background-color: #ff6b6b");
                    clock5 = "";
                }
                break;
            case "buttonC6":
                if (b6.getStatus() == 1 && !textC6.getText().equals("-- : --")) {
                    colorC6.setStyle("-fx-background-color: #b8e994");
                    clock6 = hour + ":" + minute + ":00";
                    b6.setStatus(0);
                } else if (b6.getStatus() == 0) {
                    b6.setStatus(1);
                    colorC6.setStyle("-fx-background-color: #ff6b6b");
                    clock6 = "";
                }
                break;
        }
    }

}


