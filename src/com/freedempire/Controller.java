package com.freedempire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label labelDisplay;

    private String digits;
    private int pointCount;
    private Double firstNum;
    private Double secondNum;
    private String mathematicalOperation;
    private boolean operatorProvided;

    @FXML
    public void initialize() {
        digits = "0";
        pointCount = 0;
        firstNum = null;
        secondNum = null;
        mathematicalOperation = null;
        operatorProvided = false;
    }

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

    @FXML
    private void handlerPointAction(ActionEvent actionEvent) {
        if (pointCount == 0) {
            digits = digits + ".";
            labelDisplay.setText(digits);
            pointCount++;
        }
        operatorProvided = false;
    }

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

    @FXML
    private void handlerEqualAction() {
        if (firstNum != null && mathematicalOperation != null) {
            calculate();
            labelDisplay.setText(String.valueOf(firstNum));
        }
        initialize();
    }

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
