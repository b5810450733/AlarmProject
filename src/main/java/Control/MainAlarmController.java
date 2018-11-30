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
    protected Label clockShow,datefield;

    @FXML
    protected Button exit;

    @FXML
    protected TextField setHour;

    @FXML
    protected TextField setMinute;

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

    public void initialize(){
        b1 = new Model.Button(0);
        b2 = new Model.Button(0);
        b3 = new Model.Button(0);
        b4 = new Model.Button(0);
        b5 = new Model.Button(0);
        b6 = new Model.Button(0);
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
            if (time.equals(clock1) || time.equals(clock2) ||time.equals(clock3) ||time.equals(clock4) ||time.equals(clock5) ||time.equals(clock6)){
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

    public void handleSaveButton(ActionEvent event){
        if (event.getSource().equals(save)){
            try {
                if ((Integer.parseInt(setClock.getText()) >= 1) && (Integer.parseInt(setClock.getText()) <= 6 )){
                    if ((Integer.parseInt(setHour.getText()) >= 0) && (Integer.parseInt(setHour.getText()) <= 23 )){
                        if ((Integer.parseInt(setMinute.getText()) >= 0) && (Integer.parseInt(setClock.getText()) <= 60 )){
                            clockNumber = setClock.getText();
                            hour = setHour.getText();
                            minute = setMinute.getText();
                        }
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"Please check the field information.", ButtonType.OK);
                    alert.setHeaderText("");
                    alert.show();
                    return;
                }

            }catch (NumberFormatException e){
                e.getStackTrace();
            }
        }
        setClock.clear();
        setHour.clear();
        setMinute.clear();
        setClockShow(hour,minute);
        switch (clockNumber){
            case "1":
                textC1.setText(hour+" : "+minute);
                colorC1.setStyle("-fx-background-color: #ff6b6b");
                break;
            case "2":
                textC2.setText(hour+" : "+minute);
                colorC2.setStyle("-fx-background-color: #ff6b6b");
                break;
            case "3":
                textC3.setText(hour+" : "+minute);
                colorC3.setStyle("-fx-background-color: #ff6b6b");
                break;
            case "4":
                textC4.setText(hour+" : "+minute);
                colorC4.setStyle("-fx-background-color: #ff6b6b");
                break;
            case "5":
                textC5.setText(hour+" : "+minute);
                colorC5.setStyle("-fx-background-color: #ff6b6b");
                break;
            case "6":
                textC6.setText(hour+" : "+minute);
                colorC6.setStyle("-fx-background-color: #ff6b6b");
                break;
            default: return;
        }
    }

    public void setClockShow(String h,String m){
        if (h.length() == 1){
            hour = "0"+h;
        }else {
            hour = h;
        }
        if (m.length() == 1){
            minute = "0"+m;
        }else {
            minute = m;
        }
    }

    @FXML
    public void handleOnOff(ActionEvent event){ // ดักกรณีที่ไม่ได้ใส่อะไรแล้วกด onoff มันจะเป็นสีแดง
        Button button = (Button) event.getSource();
        if (button.equals(buttonC1)){
            if (b1.getStatus() == 0 && !textC1.getText().equals("-- : --")){
                colorC1.setStyle("-fx-background-color: #b8e994");
                clock1 = hour+":"+minute+":00";
                b1.setStatus(1);
            }else{
                b1.setStatus(0);
                colorC1.setStyle("-fx-background-color: #ff6b6b");
                clock1 = "";
            }

        }if (button.equals(buttonC2)){
            if (b2.getStatus() == 0 && !textC2.getText().equals("-- : --")){
                colorC2.setStyle("-fx-background-color: #b8e994");
                clock2 = hour+":"+minute+":00";
                b2.setStatus(1);
            }else{
                b2.setStatus(0);
                colorC2.setStyle("-fx-background-color: #ff6b6b");
                clock2 = "";
            }
        }if (button.equals(buttonC3)){
            if (b3.getStatus() == 0 && !textC3.getText().equals("-- : --")){
                colorC3.setStyle("-fx-background-color: #b8e994");
                clock3 = hour+":"+minute+":00";
                b3.setStatus(1);
            }else{
                b3.setStatus(0);
                colorC3.setStyle("-fx-background-color: #ff6b6b");
                clock3 = "";
            }
        }if (button.equals(buttonC4)){
            if (b4.getStatus() == 0 && !textC4.getText().equals("-- : --")){
                colorC4.setStyle("-fx-background-color: #b8e994");
                clock4 = hour+":"+minute+":00";
                b4.setStatus(1);
            }else{
                b4.setStatus(0);
                colorC4.setStyle("-fx-background-color: #ff6b6b");
                clock4 = "";
            }
        }if (button.equals(buttonC5)){
            if (b5.getStatus() == 0 && !textC5.getText().equals("-- : --")){
                colorC5.setStyle("-fx-background-color: #b8e994");
                clock5 = hour+":"+minute+":00";
                b5.setStatus(1);
            }else{
                b5.setStatus(0);
                colorC5.setStyle("-fx-background-color: #ff6b6b");
                clock5 = "";
            }
        }if (button.equals(buttonC6)){
            if (b6.getStatus() == 0 && !textC6.getText().equals("-- : --")){
                colorC6.setStyle("-fx-background-color: #b8e994");
                clock6 = hour+":"+minute+":00";
                b6.setStatus(1);
            }else{
                b6.setStatus(0);
                colorC6.setStyle("-fx-background-color: #ff6b6b");
                clock6 = "";
            }
        }
    }
}
