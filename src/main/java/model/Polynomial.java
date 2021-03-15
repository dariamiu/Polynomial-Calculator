package model;

import Validator.InputValidator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private List<Monomial> terms = new ArrayList<Monomial>();


    public void addMonomial(Monomial monomial){
        terms.add(monomial);
    }

    public List<Monomial> getTerms() {
        return terms;
    }

    public void setTerms(List<Monomial> terms) {
        this.terms = terms;
    }

    //

    /**
     * method used to append the coefficient when converting polynomial to string
     * there a 2 different cases, when the coefficient is integer, there is no need to add the .0 decimal,
     * and when the coefficient is a real number, it is truncated such that it has only 2 decimals
     * @param myString
     * @param coefficientFloat
     */
    public void addCoefficient(StringBuilder myString, Float coefficientFloat){
        Integer coefficientInteger = coefficientFloat.intValue();
        if(coefficientFloat - coefficientInteger == 0){
            myString.append(coefficientInteger);
        }else{
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            coefficientFloat = Float.parseFloat(df.format(coefficientFloat));
            myString.append(coefficientFloat);
        }
    }

    /**
     * method for conversion from type polynomial to string
     * the polynomial is iterated, the degree and the coefficient are analysed and there are different scenarios
     * that variable i is used to check if the while is at the first iteration, then the string should not contain an
     * additional plus if the coefficient is positive (a polynomial shouldn't look like "+x^2+x")
     * if the coefficient is negative the "-" is added, then the method addCoefficient described above is called
     * depending on the degree "x" or "x^" or nothing is added
     * @return the polynomial as a string
     */
    public String polynomialToString(){
        Iterator<Monomial> iterator = this.getTerms().iterator();
        StringBuilder myString = new StringBuilder();
        int i =0;

        while (iterator.hasNext()){
            Monomial monomial = iterator.next();
            Integer degree = monomial.getDegree();
            Float coefficient = monomial.getCoefficient();

            if(i >0 && coefficient > 0)  myString.append("+");
            if(degree == 0) {
               addCoefficient(myString,coefficient);
            }else if (degree > 0){
                if (monomial.getCoefficient() != 1 && monomial.getCoefficient() != -1){
                    addCoefficient(myString,coefficient);
                } else if(monomial.getCoefficient() == -1) myString.append("-");
                if(monomial.getDegree()==1) myString.append("x");
                else if(monomial.getDegree() > 1 ) myString.append("x^" + monomial.getDegree());
            }

            i++;
        }
        String result = myString.toString();
        return result;
    }


    /**
     * method used to parse the polynomial from the input (from the user) to an Polynomial object
     * the pattern p is used to get the terms of the polynomial one by one
     * the pattern p1, with 3 groups is used to divide the term in coefficient, unknown variable and degree
     * each group is treated separately
     * there are several scenarios: coefficient is null that means that the coefficient is actually
     * 1 or -1, and the sign was treated together with "x", otherwise the actual value of the coefficient
     * is set to the polynomial; if x is null that means the degree is 0, if there is no "^" that means the degree is 1,
     * the degree is the one that was read
     * @param input
     * @return
     */
    public Polynomial stringToPolynomial(String input){
        InputValidator validator = new InputValidator();
        validator.validate(input);
        Pattern p = Pattern.compile( "([+-]?[^+-]+)");
        Matcher m = p.matcher( input );
        Polynomial result = new Polynomial();
        while (m.find()) {
            String term = m.group(1);
            Pattern p1 = Pattern.compile( "([+-]?\\d+)?([+-]?x)?(\\^\\d+)?");
            Matcher m1 = p1.matcher( term );
            if(m1.find()){
                String coefficient = m1.group(1);
                String x = m1.group(2);
                String degree = m1.group(3);
                Monomial monomial = new Monomial();
                if(coefficient == null) {
                    if(x.contains("-")) monomial.setCoefficient(-1);
                    else monomial.setCoefficient(1);
                } else {
                    monomial.setCoefficient(Float.parseFloat(coefficient));
                }
                if(x != null){
                    if(degree == null){
                        monomial.setDegree(1);
                    }else{
                        degree = degree.replaceAll("\\^","");
                        monomial.setDegree(Integer.valueOf(degree));
                    }
                }else{
                    monomial.setDegree(0);
                }
                result.addMonomial(monomial);
            }
        }
        return result;
    }

    //

    /**
     * method to add more terms at once to the polynomial
     * @param monomials
     */
    public void addPolynomial(List<Monomial> monomials){
        terms.addAll(monomials);
    }


    /**
     * method used to determine if two polynomials are the exact same
     * @param polynomial1
     * @return
     */
    public boolean equalsPolynomial(Polynomial polynomial1){

        Iterator<Monomial> iterator = this.getTerms().iterator();

        Integer i = 0;
        boolean check = true;

        while(iterator.hasNext()){
            Monomial monomialTest = iterator.next();
            if(monomialTest.getCoefficient() != polynomial1.getTerms().get(i).getCoefficient()
                    || monomialTest.getDegree() != polynomial1.getTerms().get(i).getDegree()){
                check = false;
            }
            i++;
        }
        return check;
    }
}
