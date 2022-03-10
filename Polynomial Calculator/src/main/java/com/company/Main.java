package com.company;

import model.Polynomial;
import presenter.CalculatorPresenter;
import view.CalculatorView;

public class Main {

    public static void main(String[] args) {
        CalculatorView calculatorView = new CalculatorView("Polynomial Calculator");
        calculatorView.setVisible(true);
        CalculatorPresenter calculatorPresenter = new CalculatorPresenter(calculatorView);
    }
}
