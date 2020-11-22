package com.freedempire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The JavaFX controller based on MVC design pattern.
 */
public class Controller {
    @FXML
    private Label labelDisplay;

    private String digits;
    private int pointCount;
    private Double firstNum;
    private Double secondNum;
    private String mathematicalOperation;
    private boolean operatorProvided;

    /**
     * Initialize the calculator to the initial state.
     */
    @FXML
    public void initialize() {
        digits = "0";
        pointCount = 0;
        firstNum = null;
        secondNum = null;
        mathematicalOperation = null;
        operatorProvided = false;
    }

    /**
     * Handler for all the digit buttons.
     * @param actionEvent
     */
    @FXML
    private void handlerDigitalAction(ActionEvent actionEvent) {
        String newDigit = ((Button) actionEvent.getSource()).getText();
        digits = digits + newDigit;

        // remove leading 0
        if (digits.charAt(0) == '0' && digits.charAt(1) != '.') {
            digits = digits.substring(1);
        }

        labelDisplay.setText(digits);
        operatorProvided = false;
    }

    /**
     * Handler for the point button.
     * @param actionEvent
     */
    @FXML
    private void handlerPointAction(ActionEvent actionEvent) {
        if (pointCount == 0) {
            digits = digits + ".";
            labelDisplay.setText(digits);
            pointCount++;
        }
        operatorProvided = false;
    }

    /**
     * Handler for buttons: "AC", "±", "%".
     * @param actionEvent
     */
    @FXML
    private void handlerGeneralAction(ActionEvent actionEvent) {
        String generalOperation = ((Button) actionEvent.getSource()).getText();

        switch (generalOperation) {
            case "AC":
                initialize();
                labelDisplay.setText(digits);
                break;
            case "±":
                if (digits.charAt(0) == '-') {
                    digits = digits.substring(1);
                } else {
                    digits = "-" + digits;
                }
                labelDisplay.setText(digits);
                operatorProvided = false;
                break;
            case "%":
                if (firstNum == null) {
                    digits = "0";
                    labelDisplay.setText(digits);
                } else {
                    double num = Double.parseDouble(digits);
                    secondNum = firstNum * num / 100;
                    labelDisplay.setText(String.valueOf(secondNum));
                    digits = "0";
                }
                operatorProvided = false;
                break;
            default:
                labelDisplay.setText("ERROR!");
                break;
        }
    }

    /**
     * Handler for mathematical operation buttons.
     * @param actionEvent
     */
    @FXML
    private void handlerMathematicalAction(ActionEvent actionEvent) {
        String newMathematicalOperation = ((Button) actionEvent.getSource()).getText();

        if (!operatorProvided) {
            if (firstNum == null) {
                firstNum = Double.parseDouble(digits);
                digits = "0";
            } else {
                calculate();
                labelDisplay.setText(String.valueOf(firstNum));
            }
        }

        mathematicalOperation = newMathematicalOperation;
        operatorProvided = true;
        pointCount = 0;
    }

    /**
     * Handler for the "equal" button.
     */
    @FXML
    private void handlerEqualAction() {
        if (firstNum != null && mathematicalOperation != null) {
            calculate();
            labelDisplay.setText(String.valueOf(firstNum));
        }
        initialize();
    }

    /**
     * Execute the mathematical operation whenever it's possible.
     */
    private void calculate() {
        if (secondNum == null) {
            secondNum = Double.parseDouble(digits);
        }
        switch (mathematicalOperation) {
            case "÷":
                firstNum = firstNum / secondNum;
                break;
            case "×":
                firstNum = firstNum * secondNum;
                break;
            case "–":
                firstNum = firstNum - secondNum;
                break;
            case "+":
                firstNum = firstNum + secondNum;
                break;
            default:
                labelDisplay.setText("ERROR!");
                break;
        }
        secondNum = null;
        digits = "0";
    }
}
