package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private ArrayList<Monom> polynomial = new ArrayList<>();

    public Polynomial() {
    }

    public Polynomial(ArrayList<Monom> polynomial){
        this.polynomial = polynomial;
    }

    public ArrayList<Monom> getPolynomial() {
        return polynomial;
    }

    public void readPolynomial(String textFieldString){
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(textFieldString);
        int x = 0;
        while(matcher.find()){
            String val = matcher.group(1);
            int coeff;
            int power;
            int xPosition = val.indexOf('x');
            int powPosition = val.indexOf('^');

            if(powPosition == -1){
                if(xPosition != -1){
                    power = 1;
                }
                else
                    power = 0;
            }
            else{
                if(!val.substring(powPosition + 1).trim().equals("")){
                    power = Integer.parseInt(val.substring(powPosition + 1).trim());
                }
                else
                    power = 1;
            }

            if(xPosition == -1){
                coeff = Integer.parseInt(val.trim());
            }
            else{
                if(!val.substring(0, xPosition).trim().equals("")){
                    coeff = Integer.parseInt(val.substring(0, xPosition).trim());
                }
                else
                    coeff = 1;
            }

            if (coeff != 0) {
                Monom m = new Monom(power, coeff);
                this.polynomial.add(m);
            }

        }

    }

    public void sortPolynomial(){
        this.getPolynomial().sort((m1, m2) -> Integer.compare(m2.getPower(), m1.getPower()));
    }

    public void clearPolynomial(){
        this.getPolynomial().clear();
    }


    public Polynomial addPolynomials(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial resultPolynomial = new Polynomial(polynomial1.getPolynomial());

        for (Monom monom2 : polynomial2.getPolynomial()) {
            boolean foundPower = false;

            for (Monom monom1 : resultPolynomial.getPolynomial()) {
                if (monom1.getPower() == monom2.getPower()) {
                    monom1.setCoefficient(monom1.getCoefficient() + monom2.getCoefficient());
                    foundPower = true;
                }
            }

            if (!foundPower) {
                resultPolynomial.getPolynomial().add(monom2);
            }
        }
        return resultPolynomial;
    }

    public Polynomial substractPolynomials(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial resultPolynomial = new Polynomial(polynomial1.getPolynomial());

        for (Monom monom2 : polynomial2.getPolynomial()) {
            boolean foundPower = false;

            for (Monom monom1 : resultPolynomial.getPolynomial()) {
                if (monom1.getPower() == monom2.getPower()) {
                    monom1.setCoefficient(monom1.getCoefficient() - monom2.getCoefficient());
                    foundPower = true;
                }
            }

            if (!foundPower) {
                monom2.setCoefficient(monom2.getCoefficient() * -1);
                resultPolynomial.getPolynomial().add(monom2);
            }
        }
        return resultPolynomial;
    }

    public Polynomial derivatePolynomial(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for(Monom monom : polynomial.getPolynomial()){
            Monom resultMonom = new Monom();
            if(monom.getPower() > 0){
                resultMonom.setCoefficient(monom.getCoefficient() * monom.getPower());
                resultMonom.setPower(monom.getPower() - 1);
                result.getPolynomial().add(resultMonom);
            }
        }
        return result;
    }

    public Polynomial integratePolynomial(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for(Monom monom : polynomial.getPolynomial()){
            Monom resultMonom = new Monom();
            if(monom.getPower() > 0) {
                resultMonom.setCoefficient(monom.getCoefficient() / (monom.getPower() + 1));
                resultMonom.setPower(monom.getPower() + 1);
            }
            else{
                resultMonom.setCoefficient(monom.getCoefficient());
                resultMonom.setPower(1);
            }
            result.getPolynomial().add(resultMonom);
        }
        return result;
    }

    public Polynomial multiplicatePolynomials(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial resultPolynomial = new Polynomial();
        int maxDegree = 0;
        for(Monom monom1 : polynomial1.getPolynomial()){
            for(Monom monom2 : polynomial2.getPolynomial()){
                Monom resultMonom = new Monom();
                resultMonom.setPower(monom1.getPower() + monom2.getPower());
                resultMonom.setCoefficient(monom1.getCoefficient() * monom2.getCoefficient());
                resultPolynomial.getPolynomial().add(resultMonom);
                if(maxDegree < resultMonom.getPower())
                    maxDegree = resultMonom.getPower();
            }
        }
        Polynomial finalPolynomial = new Polynomial();
        for(int i = 0; i <= maxDegree; i++){
            double coeff = 0;
            for(Monom monom : resultPolynomial.getPolynomial()){
                if(monom.getPower() == i){
                    coeff = coeff + monom.getCoefficient();
                }
            }
            if(coeff > 0){
                Monom finalMonom = new Monom(i, coeff);
                finalPolynomial.getPolynomial().add(finalMonom);
            }
        }
        return finalPolynomial;
    }

    public ArrayList<Polynomial> dividePolynomials(Polynomial polynomial1, Polynomial polynomial2){
        ArrayList<Polynomial> result = new ArrayList<>();

        Polynomial quotient = new Polynomial();
        Polynomial remainder = polynomial1;

        Monom dMonom = polynomial2.getPolynomial().get(0);
        Monom rMonom = polynomial1.getFirstMonom(remainder);

        int rPower = rMonom.getPower();
        int dPower = dMonom.getPower();
        double rCoef = rMonom.getCoefficient();
        double dCoef = dMonom.getCoefficient();

        if(polynomial1.getPolynomial().get(0).getPower() < polynomial2.getPolynomial().get(0).getPower()){
            quotient.getPolynomial().add(new Monom(0, 0));
            result.add(quotient);
            result.add(remainder);
            return result;
        }

        while(rPower >= dPower){
            Monom qMonom = new Monom(rPower - dPower,rCoef / dCoef );
            quotient.getPolynomial().add(qMonom);

            Polynomial helper = new Polynomial();
            helper.getPolynomial().add(qMonom);

            remainder = remainder.substractPolynomials(remainder, helper.multiplicatePolynomials(helper, polynomial2));

            rMonom = remainder.getFirstMonom(remainder);
            rPower = rMonom.getPower();
            rCoef = rMonom.getCoefficient();
        }

        result.add(quotient);
        result.add(remainder);

        return result;
    }

    public Monom getFirstMonom(Polynomial p){
        Monom firstMonom = new Monom();

        for (Monom currentMonom : p.getPolynomial()) {
            if (currentMonom.getCoefficient() != 0) {
                firstMonom = currentMonom;
                break;
            }
        }
        return firstMonom;
    }

    public String displayedString(){
        StringBuilder resultString = new StringBuilder();
        for(Monom m : polynomial){
            if(m != polynomial.get(polynomial.size() - 1)){
                if(m.getCoefficient() != 1)
                    resultString.append(m.getCoefficient()).append("x^").append(m.getPower()).append("+");
                else
                    resultString.append("x^").append(m.getPower()).append("+");
            }
            else
                if(m.getCoefficient() != 1)
                    resultString.append(m.getCoefficient()).append("x^").append(m.getPower());
                else
                    resultString.append("x^").append(m.getPower());
        }
        return resultString.toString();
    }

}
