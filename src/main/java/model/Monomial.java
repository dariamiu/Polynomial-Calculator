package model;

public class Monomial {

    private Integer degree;
    private Float coefficient;

    public Monomial(Integer degree, float coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }
    public Monomial() {

    }
    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }


    /**
     * change sign method used in the subtract methode
     */
    public void changeSign(){
        this.coefficient = -coefficient;
    }
}
