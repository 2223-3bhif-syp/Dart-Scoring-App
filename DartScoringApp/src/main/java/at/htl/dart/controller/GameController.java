package at.htl.dart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameController {
    @FXML
    public TextField playerOneName;
    public TextField playerTwoName;
    public ChoiceBox<Integer> startPointsChoiceBox;
    public Button beginGameBtn;
    public Label nameInputPlayerOneNotCorrect;
    public Label nameInputPlayerTwoNotCorrect;
    public Label choiceBoxNotSelected;


    // players enter their names and select from which score they want to start
    public void beginGameBtnOnClick(ActionEvent actionEvent) {

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
}
