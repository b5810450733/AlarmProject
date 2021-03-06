import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/MainAlarm.fxml"));
        primaryStage.setTitle("Alarm");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.setResizable(false);
        primaryStage.show();
        new Pulse(primaryStage.getScene().getRoot()).play();
    }
}
