import model.Monomial;
import model.Operation;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationTest {

    @Test
    public void add(){

        Operation operation = new Operation();
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        Monomial monomial = new Monomial(2,3);
        Monomial monomial1 = new Monomial(1,1);
        Monomial monomial2 = new Monomial(0, 2);

        Monomial monomial3 = new Monomial(3, 1);
        Monomial monomial4 = new Monomial(1,1);
        Monomial monomial5 = new Monomial(0,4);

        polynomial.addMonomial(monomial);
        polynomial.addMonomial(monomial1);
        polynomial.addMonomial(monomial2);

        polynomial1.addMonomial(monomial3);
        polynomial1.addMonomial(monomial4);
        polynomial1.addMonomial(monomial5);

        Monomial monomialResult1 = new Monomial(3,1);
        Monomial monomialResult2 = new Monomial(2,3);
        Monomial monomialResult3 = new Monomial(1, 2);
        Monomial monomialResult4 = new Monomial(0, 6);

        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);
        resultOk.addMonomial(monomialResult3);
        resultOk.addMonomial(monomialResult4);

        Polynomial resultTest;
        resultTest = operation.add(polynomial1,polynomial);
        Boolean check = resultOk.equalsPolynomial(resultTest);

        assertTrue(check);
    }

    @Test
    public void subtract(){

        Operation operation = new Operation();
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        Monomial monomial = new Monomial(2,3);
        Monomial monomial1 = new Monomial(1,1);
        Monomial monomial2 = new Monomial(0, 2);

        Monomial monomial3 = new Monomial(3, 1);
        Monomial monomial4 = new Monomial(1,1);
        Monomial monomial5 = new Monomial(0,4);

        polynomial.addMonomial(monomial);
        polynomial.addMonomial(monomial1);
        polynomial.addMonomial(monomial2);

        polynomial1.addMonomial(monomial3);
        polynomial1.addMonomial(monomial4);
        polynomial1.addMonomial(monomial5);

        Monomial monomialResult1 = new Monomial(3,-1);
        Monomial monomialResult2 = new Monomial(2,3);
        Monomial monomialResult3 = new Monomial(0, -2);

        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);
        resultOk.addMonomial(monomialResult3);

        Polynomial resultTest;
        resultTest = operation.subtract(polynomial,polynomial1);
        Boolean check = resultOk.equalsPolynomial(resultTest);

        assertTrue(check);
    }
    @Test
    public void multiply(){

        Operation operation = new Operation();
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        Monomial monomial = new Monomial(2,1);
        Monomial monomial1 = new Monomial(1,1);
        Monomial monomial2 = new Monomial(0, 1);

        Monomial monomial3 = new Monomial(1, 1);
        Monomial monomial4 = new Monomial(0,-1);


        polynomial.addMonomial(monomial);
        polynomial.addMonomial(monomial1);
        polynomial.addMonomial(monomial2);

        polynomial1.addMonomial(monomial3);
        polynomial1.addMonomial(monomial4);


        Monomial monomialResult1 = new Monomial(3,1);
        Monomial monomialResult2 = new Monomial(0,-1);


        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);


        Polynomial resultTest;
        resultTest = operation.multiply(polynomial,polynomial1);
        Boolean check = resultOk.equalsPolynomial(resultTest);

        assertTrue(check);
    }
    @Test
    public void integrate(){

        Operation operation = new Operation();
        Polynomial polynomial = new Polynomial();

        Monomial monomial = new Monomial(2,1);
        Monomial monomial1 = new Monomial(1,1);
        Monomial monomial2 = new Monomial(0, 1);

        polynomial.addMonomial(monomial);
        polynomial.addMonomial(monomial1);
        polynomial.addMonomial(monomial2);


        Monomial monomialResult1 = new Monomial(3,0.3333333333f);
        Monomial monomialResult2 = new Monomial(2,0.5f);
        Monomial monomialResult3 = new Monomial(1,1);

        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);
        resultOk.addMonomial(monomialResult3);

        Polynomial resultTest;
        resultTest = operation.integrate(polynomial);
        Boolean check = resultOk.equalsPolynomial(resultTest);

        assertTrue(check);
    }

    @Test
    public void derivative(){

        Operation operation = new Operation();
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        Monomial monomial = new Monomial(2,1);
        Monomial monomial1 = new Monomial(1,1);
        Monomial monomial2 = new Monomial(0, 1);

        polynomial.addMonomial(monomial);
        polynomial.addMonomial(monomial1);
        polynomial.addMonomial(monomial2);

        Monomial monomialResult1 = new Monomial(1,2);
        Monomial monomialResult2 = new Monomial(0,1);


        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);


        Polynomial resultTest;
        resultTest = operation.derivative(polynomial);
        Boolean check = resultOk.equalsPolynomial(resultTest);

        assertTrue(check);
    }

    @Test
    public void division(){

        Operation operation = new Operation();
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        Monomial monomial = new Monomial(2,3);
        Monomial monomial1 = new Monomial(1,5);
        Monomial monomial2 = new Monomial(0, 2);

        Monomial monomial3 = new Monomial(1, 2);
        Monomial monomial4 = new Monomial(0,1);


        polynomial.addMonomial(monomial);
        polynomial.addMonomial(monomial1);
        polynomial.addMonomial(monomial2);

        polynomial1.addMonomial(monomial3);
        polynomial1.addMonomial(monomial4);


        Monomial monomialResult1 = new Monomial(1,1.5f);
        Monomial monomialResult2 = new Monomial(0,1.75f);
        Monomial remainder = new Monomial(0, 0.25f);

        Polynomial resultOk = new Polynomial();
        resultOk.addMonomial(monomialResult1);
        resultOk.addMonomial(monomialResult2);

        Polynomial remainderOk = new Polynomial();
        remainderOk.addMonomial(remainder);


        Polynomial resultTest[];
        resultTest = operation.division(polynomial,polynomial1);

        Boolean check = resultOk.equalsPolynomial(resultTest[0]);
        Boolean check2 = remainderOk.equalsPolynomial(resultTest[1]);

        assertTrue(check && check2);
    }

}
