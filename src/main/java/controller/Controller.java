package controller;

import Validator.InputValidator;
import model.Operation;
import model.Polynomial;
import view.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private UserInterface userInterface;
    private Polynomial polynomial1 ;
    private Polynomial polynomial2;
    private Polynomial result;
    private Operation operation;
    private InputValidator validator;

public Controller(){
    userInterface = new UserInterface();
    validator = new InputValidator();
    operation = new Operation();
    polynomial1 = new Polynomial();
    polynomial2 = new Polynomial();
    result = new Polynomial();
    setButtons();
}

    /**
     * method to activate all the action listeners of the buttons
     */
    public void setButtons(){


        /**
         * in all this methods, first the input is validated, it is parsed to type Polynomial, then the operation is performed
         */
        userInterface.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validator.validate(userInterface.getPolynomial1());
                    validator.validate(userInterface.getPolynomial2());
                    polynomial1 = polynomial1.stringToPolynomial(userInterface.getPolynomial1());
                    polynomial2 = polynomial2.stringToPolynomial(userInterface.getPolynomial2());
                    result =  operation.add(polynomial1,polynomial2);
                    userInterface.setResult2(result.polynomialToString());
                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }

            }
        });

        userInterface.multiplyButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validator.validate(userInterface.getPolynomial1());
                    validator.validate(userInterface.getPolynomial2());
                    polynomial1 = polynomial1.stringToPolynomial(userInterface.getPolynomial1());
                    polynomial2 = polynomial2.stringToPolynomial(userInterface.getPolynomial2());
                    result =  operation.multiply(polynomial1,polynomial2);
                    userInterface.setResult2(result.polynomialToString());

                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }

            }
        });
        userInterface.divideButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validator.validate(userInterface.getPolynomial1());
                    validator.validate(userInterface.getPolynomial2());
                    polynomial1 = polynomial1.stringToPolynomial(userInterface.getPolynomial1());
                    polynomial2 = polynomial2.stringToPolynomial(userInterface.getPolynomial2());
                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }
                try{
                    validator.validateDivision(polynomial1,polynomial2);
                    Polynomial[] divisionResult;
                    divisionResult =  operation.division(polynomial1,polynomial2);
                    result = divisionResult[0];
                    userInterface.setResult2(result.polynomialToString());
                    userInterface.setDivisionRemainderText(divisionResult[1].polynomialToString());

                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }
            }
        });
        userInterface.subtractButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validator.validate(userInterface.getPolynomial1());
                    validator.validate(userInterface.getPolynomial2());
                    polynomial1 = polynomial1.stringToPolynomial(userInterface.getPolynomial1());
                    polynomial2 = polynomial2.stringToPolynomial(userInterface.getPolynomial2());
                    result =  operation.subtract(polynomial1,polynomial2);
                    userInterface.setResult2(result.polynomialToString());

                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }


            }
        });

        userInterface.derivativeButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validator.validate(userInterface.getPolynomial());
                    polynomial1 = polynomial1.stringToPolynomial(userInterface.getPolynomial());
                    result =  operation.derivative(polynomial1);
                    userInterface.setResult1(result.polynomialToString());
                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }


            }
        });
        userInterface.integrateButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validator.validate(userInterface.getPolynomial());
                    polynomial1 = polynomial1.stringToPolynomial(userInterface.getPolynomial());
                    result =  operation.integrate(polynomial1);
                    userInterface.setResult1(result.polynomialToString());
                }catch (RuntimeException exception){
                    userInterface.displayErrorMessage(exception);
                }


            }
        });
    }


}
