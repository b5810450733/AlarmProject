package Control;

import animatefx.animation.FadeOut;
import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class WakeController {
    @FXML
    private TextField answer;

    @FXML
    private Label question;

    @FXML
    private Button go;



    protected String theAnswer ;
    protected String signForSet ;

    private MainAlarmController parentController;

    public void setParentController(MainAlarmController parentController) {
        this.parentController = parentController;
    }

    public void Question (){
        Random random = new Random();
        int sign = random.nextInt(3);
        int first = random.nextInt(100)+1;
        int second = random.nextInt(100)+1;
        while (first < second){
            first = random.nextInt(100)+1;
        }
        switch (sign){
            case 0: signForSet="+";
                theAnswer = String.valueOf((first+second));
                System.out.println(theAnswer);
                break;
            case 1: signForSet="-";
                theAnswer = String.valueOf(first-second);
                System.out.println(theAnswer);
                break;
            case 2: signForSet="*";
                theAnswer = String.valueOf(first*second);
                System.out.println(theAnswer);
                break;
            default: signForSet="";
        }
        question.setText("What is : "+first+" "+signForSet+" "+second);
    }

    public void handleGoButton(ActionEvent e){
        try {
            Integer.parseInt(answer.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"", ButtonType.OK);
            alert.setHeaderText("");
            if (answer.getText().equals(theAnswer)){
                answer.setStyle("-fx-border-color: green");
                alert.setContentText("CORRECT, the alarm will stop now. WakeUp!!!");
                Optional optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)){
                    Stage stage = (Stage) question.getScene().getWindow();
                    parentController.exit.setDisable(false);
                    parentController.stopPlayer();
                    stage.close();
                }
            }else {
                wrongAnswer("WRONG, Rub the sleep your my eyes and try again.");
            }
        }catch (NumberFormatException exception){
            wrongAnswer("Wrong input, please fill positive integer.");
        }
    }

    public void wrongAnswer(String error){
        new Shake(answer.getScene().getRoot()).play();
        answer.setStyle("-fx-border-color: red");
        Alert alert = new Alert(Alert.AlertType.WARNING,error);
        alert.setHeaderText("");
        alert.show();
        System.out.println("Wrong input");
        answer.clear();
    }


}
