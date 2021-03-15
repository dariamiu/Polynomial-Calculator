package Validator;

import model.Monomial;
import model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {


    /**
     * methode to validate the input introduced by the user
     * the user can not introduce real coefficients
     * if the input contains other characters than the ones needed to write a polynomial such as space or *
     * also throws exception if there are mistakes such as doubled characters ^^ or ++
     * @param input introduced by user
     * @throws RuntimeException
     */
    public void validate(String input){

        if(input.isEmpty()){
            throw new RuntimeException("input is empty!");
        }
        Pattern realNumber = Pattern.compile("(\\d[\\.,\\\\]\\d)");
        Matcher m = realNumber.matcher(input);
        if (m.find()){
            throw new RuntimeException("Coefficients must be integers!!");
        }
        Pattern otherCharacters = Pattern.compile("[^-+x\\d\\^]");
        Matcher m1 = otherCharacters.matcher(input);
        if(m1.find()){
            throw new RuntimeException("unknown characters in input");
        }
        Pattern wrong = Pattern.compile("([+-][^[a-z]\\d])|(x[^-+\\^])|(\\^[^\\d])");
        Matcher m2 = wrong.matcher(input);
        if(m2.find()){
            throw new RuntimeException("wrong input");
        }

    }


    /**
     * method used to validate the input introduced by user when the wanted operation is division
     * the division can be done if the numerator has a smaller degree than the denominator
     * @param polynomial1 the numerator for division
     * @param polynomial2 the denominator for division
     * @throws RuntimeException
     */
    public void validateDivision(Polynomial polynomial1,Polynomial polynomial2){
        Monomial firstMonomialD = polynomial2.getTerms().get(0);
        Monomial firstMonomialN = polynomial1.getTerms().get(0);
        Integer degreeN = firstMonomialN.getDegree();
        Integer degreeD = firstMonomialD.getDegree();
        if(firstMonomialD.getCoefficient()==0 && firstMonomialD.getDegree() == 0){
            throw new RuntimeException("DIVISION BY 0!");
        }
        if(degreeD >degreeN) {
            throw new RuntimeException("Degree of polynomial1 greater than degree of polynomial2 !\nCan not perform division!");
        }
    }
}
