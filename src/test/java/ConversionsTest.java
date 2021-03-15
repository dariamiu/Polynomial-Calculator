import controller.Controller;
import model.Monomial;
import model.Operation;
import model.Polynomial;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConversionsTest {
    @Test
    public void stringToPolynomial(){

        String test = "x^3+3x^2+2x+6";

        Monomial monomialResult1 = new Monomial(3,1);
        Monomial monomialResult2 = new Monomial(2,3);
        Monomial monomialResult3 = new Monomial(1, 2);
        Monomial monomialResult4 = new Monomial(0, 6);

        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);
        resultOk.addMonomial(monomialResult3);
        resultOk.addMonomial(monomialResult4);

        Polynomial resultTest =  new Polynomial();
        resultTest = resultTest.stringToPolynomial(test);
        Boolean check = resultOk.equalsPolynomial(resultTest);

        assertTrue(check);

    }

    @Test
    public void polynomialToString(){

        String test = "x^3+3x^2+2x+6";

        Monomial monomialResult1 = new Monomial(3,1);
        Monomial monomialResult2 = new Monomial(2,3);
        Monomial monomialResult3 = new Monomial(1, 2);
        Monomial monomialResult4 = new Monomial(0, 6);

        Polynomial polynomialTest = new Polynomial();
        polynomialTest.addMonomial(monomialResult1);
        polynomialTest.addMonomial(monomialResult2);
        polynomialTest.addMonomial(monomialResult3);
        polynomialTest.addMonomial(monomialResult4);

        String testResult = polynomialTest.polynomialToString();


        assertTrue(test.equals(testResult));

    }

}
