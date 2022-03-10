package presenter;

import model.Polynomial;
import view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPresenter {
    private final CalculatorView calculatorView;
    private final Polynomial polynomial1;
    private final Polynomial polynomial2;
    private Polynomial result;

    public CalculatorPresenter(CalculatorView calculatorView){
        this.calculatorView = calculatorView;
        this.polynomial1 = new Polynomial();
        this.polynomial2 = new Polynomial();
        this.result = new Polynomial();

        calculatorView.addAddListener(new addButtonListener());
        calculatorView.addDelListener(new delButtonListener());
        calculatorView.addSubListener(new subButtonListener());
        calculatorView.addDerListener(new derButtonListener());
        calculatorView.addIntListener(new intButtonListener());
        calculatorView.addMulListener(new mulButtonListener());
        calculatorView.addDivListener(new divButtonListener());
        calculatorView.addModListener(new modButtonListener());
    }

    private class addButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            polynomial2.readPolynomial(calculatorView.getSecondPolynomialTextField());
            result = result.addPolynomials(polynomial1, polynomial2);
            result.sortPolynomial();
            calculatorView.setResultTextField(result.displayedString());
        }
    }

    private class subButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            polynomial2.readPolynomial(calculatorView.getSecondPolynomialTextField());
            result = result.substractPolynomials(polynomial1, polynomial2);
            result.sortPolynomial();
            calculatorView.setResultTextField(result.displayedString());
        }
    }

    private class derButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            result = result.derivatePolynomial(polynomial1);
            calculatorView.setResultTextField(result.displayedString());

        }
    }

    private class intButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            result = result.integratePolynomial(polynomial1);
            calculatorView.setResultTextField(result.displayedString());

        }
    }

    private class mulButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            polynomial2.readPolynomial(calculatorView.getSecondPolynomialTextField());
            result = result.multiplicatePolynomials(polynomial1, polynomial2);
            result.sortPolynomial();
            calculatorView.setResultTextField(result.displayedString());
        }
    }

    private class divButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            polynomial2.readPolynomial(calculatorView.getSecondPolynomialTextField());
            result = result.dividePolynomials(polynomial1, polynomial2).get(0);
            result.sortPolynomial();
            calculatorView.setResultTextField(result.displayedString());
        }
    }

    private class modButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            polynomial1.readPolynomial(calculatorView.getFirstPolynomialTextField());
            polynomial2.readPolynomial(calculatorView.getSecondPolynomialTextField());
            result = result.dividePolynomials(polynomial1, polynomial2).get(1);
            result.sortPolynomial();
            calculatorView.setResultTextField(result.displayedString());
        }
    }

    private class delButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method is called when the doctor button is clicked
            calculatorView.setFirstPolynomialTextField("");
            calculatorView.setSecondPolynomialTextField("");
            calculatorView.setResultTextField("");
            result.clearPolynomial();
            polynomial1.clearPolynomial();
            polynomial2.clearPolynomial();
        }
    }


}
