package at.htl.dart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.awt.*;

public class PlayGameController {
    @FXML
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public Button btn10;
    public Button btn11;
    public Button btn12;
    public Button btn13;
    public Button btn14;
    public Button btn15;
    public Button btn16;
    public Button btn17;
    public Button btn18;
    public Button btn19;
    public Button btn20;
    public Button btn25;
    public Button btn50;
    public Button btn2x;
    public Button btn3x;
    public Label namePlayer1;
    public Label player1FirstDart;
    public Label player1SecondDart;
    public Label player1ThirdDart;
    public Label player1Points;
    public Label namePlayer2;
    public Label player2FirstDart;
    public Label player2SecondDart;
    public Label player2ThirdDart;
    public Label player2Points;
    public AnchorPane buttonPane;
    private int currentPlayerPoints;
    private Label currentPlayerPointsLabel;
    private Label currentPlayerNameLabel;
    private int muliplier = 1;
    private int dartCounter = 0;

    @FXML
    public void initialize(){
        buttonPane.getChildren().forEach(node -> {
            if(node instanceof Button){
                Button button = (Button) node;
                button.setOnAction(this::handleButtonClick);
            }
        });
    }

    public void setPlayerNames(String playerNameOne, String playerNameTwo){
        namePlayer1.setText(playerNameOne);
        namePlayer2.setText(playerNameTwo);
    }

    public void setPointsPlayer1(Integer points){
        player1Points.setText(points.toString());
    }

    public void setPointsPlayer2(Integer points){
        player2Points.setText(points.toString());
    }

    public void handleButtonClick(ActionEvent actionEvent){
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonText = clickedButton.getText();

        if(buttonText.equals("2x")){
            muliplier = 2;
            return;
        } else if (buttonText.equals("3x")) {
            muliplier = 3;
            return;
        }

        int points = Integer.parseInt(buttonText) * muliplier;
        currentPlayerPoints -= points;
        dartCounter++;

        updatePointsDisplayed();

        if(currentPlayerPoints == 0){
            showGameResult(currentPlayerNameLabel.getText() + " has won the game!");
        }

        if(dartCounter == 3){
            switchPlayers();
        }
    }

    private void updatePointsDisplayed(){
        currentPlayerPointsLabel.setText(Integer.toString(currentPlayerPoints));
    }

    private void switchPlayers(){
        currentPlayerPointsLabel.setBackground(null);

        if(currentPlayerNameLabel == namePlayer1){
            currentPlayerNameLabel = namePlayer2;
            currentPlayerPointsLabel = player2Points;
        } else{
            currentPlayerNameLabel = namePlayer1;
            currentPlayerPointsLabel = player1Points;
        }

        currentPlayerPoints = Integer.parseInt(currentPlayerPointsLabel.getText());
        currentPlayerPointsLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }

    private void showGameResult(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spiel beendet");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
