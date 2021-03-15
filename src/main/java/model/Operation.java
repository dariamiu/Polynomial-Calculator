package model;
import java.util.Comparator;
import java.util.Iterator;

public class Operation {


    /**
     * method to sort the polynomial by degree after different operations are applied on the polynomial
     * @param polynomial to be sorted
     */
    public void sortPolynomial(Polynomial polynomial){
        polynomial.getTerms().sort(Comparator.comparing(Monomial::getDegree).reversed());
    }


    /**
     *  addition method
     *  the first polynomial is taken term by term,search for term of same degree in the second polynomial,
     *  add their sum to sum, delete the term from the second polynomial;
     *  if polynomial2 doesn't have that degree, add the term from polynomial1 in sum;
     *  if there are terms of different degrees from polynomial1 in polynomial2, add them to sum;
     * @param polynomial1
     * @param polynomial2
     * @return sum
     */
    public Polynomial add( Polynomial polynomial1, Polynomial polynomial2){
        Polynomial sum = new Polynomial();
        Polynomial copy ;
        copy = polynomial2;
        for( Monomial monomial1 : polynomial1.getTerms()){
            Iterator<Monomial> iterator = copy.getTerms().iterator();
            boolean check = false;
            while (iterator.hasNext() && check == false) {
                Monomial monomial2 = iterator.next();
                if (monomial1.getDegree() == monomial2.getDegree()) {
                    Monomial monomial = new Monomial(monomial1.getDegree(), monomial1.getCoefficient() + monomial2.getCoefficient());
                    if (monomial.getCoefficient() != 0) sum.addMonomial(monomial);
                    check = true;
                    iterator.remove();
                }
            }
            if (check == false){
                sum.addMonomial(monomial1);
            }
        }
        if(!copy.getTerms().isEmpty()) sum.addPolynomial(copy.getTerms());
        sortPolynomial(sum);
        return sum;
    }

    /**
     * subtract method
     * the same idea as add method;
     * at the end, change the sign if there are terms to be added from polynomial2 and add them to subtract
     * @param polynomial1
     * @param polynomial2
     * @return result of subtraction
     */
    public Polynomial subtract( Polynomial polynomial1, Polynomial polynomial2){
        Polynomial dif = new Polynomial();
        Polynomial copy ;
        copy = polynomial2;
        for( Monomial monomial1 : polynomial1.getTerms()){
            Iterator<Monomial> iterator = copy.getTerms().iterator();
            boolean check = false;
            while (iterator.hasNext() && check == false) {
                Monomial monomial2= iterator.next();
                if (monomial1.getDegree() == monomial2.getDegree()) {
                    Monomial monomial = new Monomial(monomial1.getDegree(),
                            monomial1.getCoefficient() - monomial2.getCoefficient());
                    if(monomial.getCoefficient() != 0) dif.addMonomial(monomial);
                    check = true;
                    iterator.remove();
                }
            }
            if (check == false){
                dif.addMonomial(monomial1);
            }
        }
        if(!copy.getTerms().isEmpty()) {
            for( Monomial monomial : copy.getTerms()) monomial.changeSign();
            dif.addPolynomial(copy.getTerms());}
        sortPolynomial(dif);
        return dif;
    }

    /**
     * method for derivation
     * @param polynomial
     * @return result of derivation
     */
    public Polynomial derivative(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for( Monomial monomial : polynomial.getTerms()){
            if(monomial.getDegree() > 0){
            Monomial monomial1 = new Monomial(monomial.getDegree() - 1, monomial.getCoefficient()* monomial.getDegree());
            result.addMonomial(monomial1);
            }
        }
        return result;
    }


    /**
     * method for integration
     * @param polynomial
     * @return result of integration
     */
    public Polynomial integrate(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for( Monomial monomial : polynomial.getTerms()){
            if(monomial.getDegree() != 0){
                Monomial monomial1 = new Monomial(monomial.getDegree() + 1, monomial.getCoefficient()/ (monomial.getDegree()+1));
                result.addMonomial(monomial1);
            }else{
                Monomial monomial1 = new Monomial(1, monomial.getCoefficient());
                result.addMonomial(monomial1);
            }
        }
        return result;
    }

    /**
     *  method used to add the terms of the same degree from the same polynomial,
     *  and to reduce the terms if the coefficient is 0
     *  used in the multiply method
     * @param polynomial
     * @return result after reduction
     */
    public Polynomial reduceTerms(Polynomial polynomial) {
        Iterator<Monomial> iterator1 = polynomial.getTerms().listIterator();

        Integer i = 0;
        while (iterator1.hasNext()) {
            Monomial monomial1 = iterator1.next();

            while (iterator1.hasNext()) {
                Monomial monomial2 = iterator1.next();
                if (monomial1.getDegree() == monomial2.getDegree()) {
                    monomial1.setCoefficient(monomial1.getCoefficient() + monomial2.getCoefficient());
                    iterator1.remove();
                }
            }
            i++;
            iterator1 = polynomial.getTerms().listIterator(i);

        }
        iterator1 = polynomial.getTerms().listIterator(0);
        while(iterator1.hasNext()){
            if(iterator1.next().getCoefficient() == 0) iterator1.remove();
        }
        return polynomial;

    }

    /**
     * method for multiplication of polynomials
     * @param polynomial1
     * @param polynomial2
     * @return result of multiplication
     */
    public Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial result = new Polynomial();
        for( Monomial monomial1 : polynomial1.getTerms()){
            for(Monomial monomial2 : polynomial2.getTerms()){
                Monomial monomial = new Monomial(monomial1.getDegree() + monomial2.getDegree(),
                        monomial1.getCoefficient()* monomial2.getCoefficient());
                result.addMonomial(monomial);
            }
        }
        sortPolynomial(result);
        reduceTerms(result);
        return result;
    }

    /**
     * method to multiply a polynomial with a single monomial
     * used in division method
     * @param polynomial
     * @param monomial
     * @return the resulted polynomial
     */
    public Polynomial multiplyPolyMonom(Polynomial polynomial, Monomial monomial){
        Polynomial result = new Polynomial();
        for( Monomial monomial1 : polynomial.getTerms()){
                Monomial monomialNew = new Monomial(monomial1.getDegree() + monomial.getDegree(),
                        monomial1.getCoefficient()* monomial.getCoefficient());
                result.addMonomial(monomialNew);
            }
        return result;
    }

    /**method for division
     * first save the degrees of the polynomials in variables degreeN, degreeD
     * while the 2 polynomials ca be divided
     * first term of the remainder numerator is divided by the first term of the denominator
     * add to quotient that resulted term
     * create the new numerator
     * get the new degree of numerator
     * @param numerator first polynomial
     * @param denominator second polynomial
     * @return result an array containing the remainder and the quotient
     * take the degree of the polynomials
     */
    public Polynomial[] division( Polynomial numerator, Polynomial denominator){

        Monomial firstMonomialD = denominator.getTerms().get(0);
        Monomial firstMonomialN = numerator.getTerms().get(0);
        Integer degreeN = firstMonomialN.getDegree();
        Integer degreeD = firstMonomialD.getDegree();
        Polynomial quotient = new Polynomial();


        while (degreeN >= degreeD) {
            Monomial divide = new Monomial(firstMonomialN.getDegree()- firstMonomialD.getDegree(),
                    firstMonomialN.getCoefficient()/firstMonomialD.getCoefficient());
            quotient.addMonomial(divide);
            numerator = subtract(numerator, multiplyPolyMonom(denominator,divide));
           if(!numerator.getTerms().isEmpty()) {
                 firstMonomialN = numerator.getTerms().get(0);
                 degreeN = firstMonomialN.getDegree();

            }else {
                degreeN = 0;
            }

        }
        Polynomial[] result = new Polynomial[2];
        /**
         *  after all the subtractions the numerator became the remainder
         */
        result[1] = numerator; //remainder
        result[0] = quotient;
        return  result;
    }
}



