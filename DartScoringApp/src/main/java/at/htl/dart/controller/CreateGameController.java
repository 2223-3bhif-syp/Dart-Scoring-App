package at.htl.dart.controller;

import at.htl.dart.entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

import static java.awt.Color.RED;
import static java.awt.Color.red;

public class CreateGameController {
    @FXML
    public TextField playerOneName;
    public TextField playerTwoName;
    public ChoiceBox<Integer> startPointsChoiceBox;
    public Button beginGameBtn;
    public Label nameInputPlayerOneNotCorrect;
    public Label nameInputPlayerTwoNotCorrect;
    public Label choiceBoxNotSelected;

    public void initialize(){
        ObservableList<Integer> choices = FXCollections.observableArrayList();
        choices.add(501);
        choices.add(301);
        choices.add(201);

        startPointsChoiceBox.setItems(choices);
    }

    // players enter their names and select from which score they want to start
    public void beginGameBtnOnClick(ActionEvent actionEvent) {
        Player player1 = new Player(playerOneName.getText());
        Player player2 = new Player(playerTwoName.getText());
        int selectedPoints = startPointsChoiceBox.getValue();

        boolean isValidPlayerOne = validateName(player1.getName());
        boolean isValidPlayerTwo = validateName(player2.getName());
        boolean isSelectedChoiceBox = startPointsChoiceBox.getSelectionModel().getSelectedItem() != null;

        if(!isValidPlayerOne){
            nameInputPlayerOneNotCorrect.setTextFill(Color.RED);
            nameInputPlayerOneNotCorrect.setText("Name not valid!");
        }

        if(!isValidPlayerTwo){
            nameInputPlayerTwoNotCorrect.setTextFill(Color.RED);
            nameInputPlayerTwoNotCorrect.setText("Name not valid!");
        }

        if(isValidPlayerOne && isValidPlayerTwo && isSelectedChoiceBox){
            openPlayGameWindow(player1.getName(), player2.getName(), selectedPoints);
        }
    }

    // Verification of the name entries
    // an entry containing only numbers is not allowed
    // the name should have at least 3 letters
    // a mixture of numbers and letters is allowed
    private boolean validateName(String name) {
        boolean containsOnlyNumbers = name.matches("[0-9]+");
        boolean containsOnlyLetters = name.matches("[a-zA-Z]+");
        boolean hasValidLength = name.length() >= 3;

        return (!containsOnlyNumbers || containsOnlyLetters) && hasValidLength;
    }

    // open playGame.fxml
    private void openPlayGameWindow(String playerNameOne, String playerNameTwo, int selectedPoints){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/playGame.fxml"));
            Parent root = loader.load();

            PlayGameController playGameController = loader.getController();
            playGameController.setPlayerNames(playerNameOne, playerNameTwo);
            playGameController.setSelectedPoints(selectedPoints);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not open playGameWindow");
        }
    }
}
