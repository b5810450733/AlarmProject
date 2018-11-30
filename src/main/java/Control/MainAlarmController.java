package Control;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class MainAlarmController {

    @FXML
    protected Label clockShow,datefield;

    @FXML
    protected Button exit;

    protected String setWake = "00:25:50";

    protected MediaPlayer player;


    public void initialize(){
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
            if (time.equals(setWake)){
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

    public void nowWake(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Wake.fxml")) ;
        try {
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(loader.load(),480,280));
            playSound();
            stage.setTitle("!!! WAKE UP !!!");
            stage.setResizable(false);
            WakeController controller = (WakeController) loader.getController();
            controller.setParentController(this);
            controller.Question();
            stage.show();
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }

    @FXML
    public void playSound(){
        Random random = new Random();
        int ran = random.nextInt(2)+1;
        String musicFile = "/Sound/"+"alarm"+ran+".mp3";
        try {
            Media sound = new Media(getClass().getResource(musicFile).toURI().toString());
            this.player = new MediaPlayer(sound);
            player.play();
            player.setCycleCount(MediaPlayer.INDEFINITE);

        }catch (URISyntaxException e){
            e.getStackTrace();
        }
    }

    public void stopPlayer(){
        player.stop();
        System.out.println("test");
    }
}
