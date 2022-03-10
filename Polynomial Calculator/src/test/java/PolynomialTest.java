import model.Monom;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {

    @Test
    public void addTest(){

        Polynomial p1 = createPolynomial1();
        Polynomial p2 = createPolynomial2();
        Polynomial sum = new Polynomial();
        sum = sum.addPolynomials(p1, p2);
        assertEquals("x^2+3.0x^1+2.0x^0", sum.displayedString());

        Polynomial p3 = createPolynomial1();
        Polynomial p4 = createPolynomial2();
        Polynomial sub = new Polynomial();
        sub = sub.substractPolynomials(p3, p4);
        assertEquals("x^2+x^1+0.0x^0", sub.displayedString());

        Polynomial p5 = createPolynomial1();
        Polynomial p6 = createPolynomial2();
        Polynomial mul = new Polynomial();
        mul = mul.multiplicatePolynomials(p5, p6);
        mul.sortPolynomial();
        assertEquals("x^3+3.0x^2+3.0x^1+x^0", mul.displayedString());

        Polynomial p7 = createPolynomial1();
        Polynomial der = new Polynomial();
        der = der.derivatePolynomial(p7);
        assertEquals("2.0x^1+2.0x^0", der.displayedString());

        Polynomial intt = new Polynomial();
        intt = intt.integratePolynomial(p7);
        assertEquals("0.3333333333333333x^3+x^2+x^1", intt.displayedString());

        Polynomial p8 = createPolynomial1();
        Polynomial p9 = createPolynomial2();
        Polynomial div = new Polynomial();
        div = div.dividePolynomials(p8, p9).get(0);
        assertEquals("x^1+x^0", div.displayedString());

        Polynomial mod = new Polynomial();
        mod = mod.dividePolynomials(p8, p9).get(1);
        assertEquals("x^1+x^0", div.displayedString());



    }

    public Polynomial createPolynomial1(){
        Monom m1 = new Monom(2, 1);
        Monom m2 = new Monom(1, 2);
        Monom m3 = new Monom(0, 1);

        Polynomial polynomial = new Polynomial();
        polynomial.getPolynomial().add(m1);
        polynomial.getPolynomial().add(m2);
        polynomial.getPolynomial().add(m3);
        return polynomial;
    }


    public Polynomial createPolynomial2(){
        Monom m4 = new Monom(1, 1);
        Monom m5 = new Monom(0,1);

        Polynomial polynomial = new Polynomial();
        polynomial.getPolynomial().add(m4);
        polynomial.getPolynomial().add(m5);
        return polynomial;
    }
}
