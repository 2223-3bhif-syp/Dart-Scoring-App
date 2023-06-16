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
        currentPlayerNameLabel = namePlayer1;
        currentPlayerPointsLabel = player1Points;
    }

    public void setPointsPlayer1(Integer points){
        player1Points.setText(points.toString());
        currentPlayerPoints = points;
    }

    public void setPointsPlayer2(Integer points){
        player2Points.setText(points.toString());
        currentPlayerPoints = points;
    }

    public void handleButtonClick(ActionEvent actionEvent){
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonText = clickedButton.getText();
        System.out.println(buttonText);
        int multiplier = 1;

        if(buttonText.equals("2x")){
            multiplier = 2;
            return;
        } else if (buttonText.equals("3x")) {
            multiplier = 3;
            return;
        }

        int points = Integer.parseInt(buttonText) * multiplier;
        currentPlayerPoints -= points;
        dartCounter++;

        if (currentPlayerNameLabel == namePlayer1) {
            updatePlayer1DartLabels(buttonText, dartCounter);
        } else if (currentPlayerNameLabel == namePlayer2) {
            updatePlayer2DartLabels(buttonText, dartCounter);
        }

        updatePointsDisplayed();

        if(currentPlayerPoints == 0){
            showGameResult(currentPlayerNameLabel.getText() + " has won the game!");
        }

        if(dartCounter == 3){
            switchPlayers();
            dartCounter = 0; // Reset Dart Counter for the new player
            resetDartLabels(player1FirstDart, player1SecondDart, player1ThirdDart); // Reset dart labels for player 1
            resetDartLabels(player2FirstDart, player2SecondDart, player2ThirdDart);
        }
    }

    private void updatePointsDisplayed(){
        currentPlayerPointsLabel.setText(Integer.toString(currentPlayerPoints));

        // Color
        currentPlayerPointsLabel.setBackground(null);
        currentPlayerPointsLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }


    private void switchPlayers(){
        currentPlayerPointsLabel.setBackground(null);

        if(currentPlayerNameLabel.getText().equals(namePlayer1.getText())){
            currentPlayerNameLabel = namePlayer2;
            currentPlayerPointsLabel = player2Points;
        } else {
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

    private void updatePlayer1DartLabels(String score, int dartCounter) {
        if (dartCounter == 1) {
            player1FirstDart.setText(score);
        } else if (dartCounter == 2) {
            player1SecondDart.setText(score);
        } else if (dartCounter == 3) {
            player1ThirdDart.setText(score);
        }
    }

    private void updatePlayer2DartLabels(String score, int dartCounter) {
        if (dartCounter == 1) {
            player2FirstDart.setText(score);
        } else if (dartCounter == 2) {
            player2SecondDart.setText(score);
        } else if (dartCounter == 3) {
            player2ThirdDart.setText(score);
        }
    }

    private void resetDartLabels(Label... labels) {
        for (Label label : labels) {
            label.setText("0");
        }
    }

}
