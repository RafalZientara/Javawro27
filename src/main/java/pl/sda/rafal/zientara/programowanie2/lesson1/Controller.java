package pl.sda.rafal.zientara.programowanie2.lesson1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {

    @FXML
    private TextField screen;

    private Operation currentOperation;

    private BigDecimal firstNumber;

    @FXML
    public void handlerNumberPressed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj instanceof Button) {
            Button button = (Button) obj;
            String text = button.getText();
            screen.appendText(text);
        }
    }

    @FXML
    public void handlePlusPressed() {
        changeOperation(currentOperation = Operation.PLUS);
    }

    @FXML
    public void handleMinusPressed() {
        changeOperation(currentOperation = Operation.MINUS);
    }

    @FXML
    public void handleMultiplyPressed() {
        changeOperation(currentOperation = Operation.MULTIPLY);
    }

    @FXML
    public void handleDividePressed() {
        changeOperation(currentOperation = Operation.DIVIDE);
    }

    @FXML
    public void handleClearPressed() {
        screen.clear();
    }

    @FXML
    public void handleComaPressed() {
        String text = screen.getText();
        if (!text.contains(".")) {
            screen.appendText(".");
        }
    }

    @FXML
    public void handleResultPressed() {
        BigDecimal secondNumber = getNumberFromScreen();
        BigDecimal result = getResult(secondNumber);
        screen.setText(String.format("%f", result));
    }

    private BigDecimal getResult(BigDecimal secondNumber) {
        try {
            switch (currentOperation) {
                case PLUS:
                    return firstNumber.add(secondNumber);
                case MINUS:
                    return firstNumber.subtract(secondNumber);
                case MULTIPLY:
                    return firstNumber.multiply(secondNumber);
                case DIVIDE:
                    return firstNumber.divide(secondNumber, RoundingMode.UNNECESSARY);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ArithmeticException");
//            alert.setHeaderText("nope1");
            alert.setContentText("Zjebałeś! Nie dziel przez 0!");

            alert.showAndWait();
        }
        return new BigDecimal(0);
    }

    private void changeOperation(Operation operation) {
        currentOperation = operation;
        firstNumber = getNumberFromScreen();
        screen.clear();
    }

    private BigDecimal getNumberFromScreen() {
        String text = screen.getText();
        return new BigDecimal(text.replace(",", "."));
    }


}
