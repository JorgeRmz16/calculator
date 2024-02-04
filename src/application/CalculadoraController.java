package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class CalculadoraController implements Initializable{

    @FXML
    private TextField txtSuperior;

    private StringBuilder inputBuffer = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private double result = 0;

    @FXML
    private void handleNumericButton(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        inputBuffer.append(sourceButton.getText());
        updateDisplay();
    }

    @FXML
    private void handleOperatorButton(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        if (!inputBuffer.toString().isEmpty()) {
            firstOperand = Double.parseDouble(inputBuffer.toString());
            operator = sourceButton.getText();
            inputBuffer.setLength(0);
        }else {
        	firstOperand = result;
        	operator = sourceButton.getText();
            inputBuffer.setLength(0);
        }
    }

    @FXML
    private void handleEqualsButton() {
    	if (!inputBuffer.toString().isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(inputBuffer.toString());
            result = performOperation(firstOperand, secondOperand, operator);
            txtSuperior.setText(String.valueOf(result));
            inputBuffer.setLength(0);
            operator = "";
            
        }
    }

    private double performOperation(double operand1, double operand2, String operation) {
        switch (operation) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "X":
                return operand1 * operand2;
            case "%":
            	return (operand1 * operand2) / 100;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return 0;
                }
            default:
                return 0;
        }
    }
    
    @FXML
    private void handleBackButton() {
        if (inputBuffer.length() > 0) {
            inputBuffer.deleteCharAt(inputBuffer.length() - 1);
            updateDisplay();
        }
    }
    
    @FXML
    private void handleClearButton() {
        inputBuffer.setLength(0);
        firstOperand = 0;
        operator = "";
        updateDisplay();
    }

    private void updateDisplay() {
        txtSuperior.setText(inputBuffer.toString());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        txtSuperior.setEditable(false);
    }
    
}