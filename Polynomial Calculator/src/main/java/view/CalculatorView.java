package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame{
    private JTextField firstPolynomialTextField;
    private JTextField secondPolynomialTextField;
    private JButton subtractButton;
    private JButton addButton;
    private JButton divideButton;
    private JButton moduloButton;
    private JButton multiplicateButton;
    private JButton delButton;
    private JPanel calculatorPanel;
    private JTextField resultTextField;
    private JButton derivateButton;
    private JButton integrateButton;

    public CalculatorView(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(calculatorPanel);
        this.pack();
    }

    public void setFirstPolynomialTextField(String firstPolynomialTextField) {
        this.firstPolynomialTextField.setText(firstPolynomialTextField);
    }

    public void setSecondPolynomialTextField(String secondPolynomialTextField) {
        this.secondPolynomialTextField.setText(secondPolynomialTextField);
    }

    public void setResultTextField(String resultTextField) {
        this.resultTextField.setText(resultTextField);
    }

    public String getFirstPolynomialTextField() {
        return firstPolynomialTextField.getText();
    }

    public String getSecondPolynomialTextField() {
        return secondPolynomialTextField.getText();
    }

    public void addMulListener(ActionListener listener){
        multiplicateButton.addActionListener(listener);
    }

    public void addSubListener(ActionListener listener){
        subtractButton.addActionListener(listener);
    }

    public void addDivListener(ActionListener listener){
        divideButton.addActionListener(listener);
    }

    public void addModListener(ActionListener listener){
        moduloButton.addActionListener(listener);
    }

    public void addAddListener(ActionListener listener){
        addButton.addActionListener(listener);
    }

    public void addDelListener(ActionListener listener){
        delButton.addActionListener(listener);
    }

    public void addDerListener(ActionListener listener){
        derivateButton.addActionListener(listener);
    }

    public void addIntListener(ActionListener listener){
        integrateButton.addActionListener(listener);
    }






}
