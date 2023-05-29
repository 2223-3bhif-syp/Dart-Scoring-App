package at.htl.dart.controller;

import at.htl.dart.entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

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

    // players move to the game window
    public void beginGameBtnOnClick(ActionEvent actionEvent) {
        Player player1 = new Player(playerOneName.getText());
        Player player2 = new Player(playerTwoName.getText());
        Integer points = startPointsChoiceBox.getValue();

        // all inputs are correct
        if(checkAllCreateGameInputs(player1.getName(), player2.getName(), points)){
            openPlayGameWindow(player1.getName(), player2.getName(), points);
        }
        else{
            // Message window that the entries are invalid
            showStartGameAlert();
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
    private void openPlayGameWindow(String playerNameOne, String playerNameTwo, Integer selectedPoints){
        try{
            URL fxmlPlayGameUrl = getClass().getResource("/playGame.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlPlayGameUrl);
            Parent root = loader.load();

            PlayGameController playGameController = loader.getController();
            playGameController.setPlayerNames(playerNameOne, playerNameTwo);
            playGameController.setPointsPlayer1(selectedPoints);
            playGameController.setPointsPlayer2(selectedPoints);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage createGameStage = (Stage) beginGameBtn.getScene().getWindow();
            createGameStage.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not open playGameWindow");
        }
    }

    public boolean checkAllCreateGameInputs(String name1, String name2, Integer startPoints){
        boolean isReadyToStart = false;

        if(validateName(name1) && validateName(name2) && startPoints != null){
            isReadyToStart = true;
        }
        return isReadyToStart;
    }

    // show start game alert
    public void showStartGameAlert(){
        boolean isValidPlayerOne = validateName(playerOneName.getText());
        boolean isValidPlayerTwo = validateName(playerTwoName.getText());
        boolean isSelectedChoiceBox = startPointsChoiceBox.getSelectionModel().getSelectedItem() != null;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Inputs");
        alert.setHeaderText("Not able to start game");
        alert.setContentText("The create game inputs are not valid. Please check your inputs and try again!");

        if(!isValidPlayerOne){
            nameInputPlayerOneNotCorrect.setTextFill(Color.RED);
            nameInputPlayerOneNotCorrect.setText("Name not valid!");
        }
        else{
            nameInputPlayerOneNotCorrect.setTextFill(Color.GREEN);
            nameInputPlayerOneNotCorrect.setText("Name is valid");
        }

        if(!isValidPlayerTwo){
            nameInputPlayerTwoNotCorrect.setTextFill(Color.RED);
            nameInputPlayerTwoNotCorrect.setText("Name not valid!");
        }
        else{
            nameInputPlayerTwoNotCorrect.setTextFill(Color.GREEN);
            nameInputPlayerTwoNotCorrect.setText("Name is valid");
        }

        if(!isSelectedChoiceBox){
            choiceBoxNotSelected.setTextFill(Color.RED);
            choiceBoxNotSelected.setText("No start points selected!");
        }
        else{
            choiceBoxNotSelected.setTextFill(Color.GREEN);
            choiceBoxNotSelected.setText("starting points are selected");
        }

        ButtonType closeButton = new ButtonType("Close");
        alert.getButtonTypes().setAll(closeButton);

        alert.setOnCloseRequest((DialogEvent event) -> {
            // Reset the text fields
            nameInputPlayerOneNotCorrect.setText("");
            nameInputPlayerTwoNotCorrect.setText("");
            choiceBoxNotSelected.setText("");
        });

        alert.showAndWait();
    }
}
