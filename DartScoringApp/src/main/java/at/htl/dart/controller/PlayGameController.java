package at.htl.dart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public void btn1OnClick(ActionEvent actionEvent) {
    }

    public void btn2OnClick(ActionEvent actionEvent) {
    }

    public void btn3OnClick(ActionEvent actionEvent) {
    }

    public void btn4OnClick(ActionEvent actionEvent) {
    }

    public void btn5OnClick(ActionEvent actionEvent) {
    }

    public void btn6OnClick(ActionEvent actionEvent) {
    }

    public void btn7OnClick(ActionEvent actionEvent) {
    }

    public void btn8OnClick(ActionEvent actionEvent) {
    }

    public void btn9OnClick(ActionEvent actionEvent) {
    }

    public void btn10OnClick(ActionEvent actionEvent) {
    }

    public void btn11OnClick(ActionEvent actionEvent) {
    }

    public void btn12OnClick(ActionEvent actionEvent) {
    }

    public void btn13OnClick(ActionEvent actionEvent) {
    }

    public void btn14OnClick(ActionEvent actionEvent) {
    }

    public void btn15OnClick(ActionEvent actionEvent) {
    }

    public void btn16OnClick(ActionEvent actionEvent) {
    }

    public void btn17OnClick(ActionEvent actionEvent) {
    }

    public void btn18OnClick(ActionEvent actionEvent) {
    }

    public void btn19OnClick(ActionEvent actionEvent) {
    }

    public void btn20OnClick(ActionEvent actionEvent) {
    }

    public void btn25OnClick(ActionEvent actionEvent) {
    }

    public void btn50OnClick(ActionEvent actionEvent) {
    }

    public void btn2xOnClick(ActionEvent actionEvent) {
    }

    public void btn3xOnClick(ActionEvent actionEvent) {
    }
}
