package com.Fractions;

public class Main {

    public static void main(String[] args) {
	    Fractions f = new Fractions(0,12,6);
        System.out.println(f.returnResult());
    }
}

class Fractions {

    private int mix = 0;
    private int numerator = 0;
    private int denominator = 1;

    Fractions(int mix,int numerator,int denominator) {

        int arr[] = null;
        arr = simplify(mix,numerator,denominator);
        this.numerator = arr[0];
        this.denominator = arr[1];
    }

    /*
    Fractions(String format) {


    }
    */

    public int getMix() {
        return mix;
    }

    public void setMix(int mix) {
        this.mix = mix;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public String returnResult() {
        String result = numerator +"/"+denominator;
        String result2 = "";
        if(numerator<=denominator) {
            return "Result: "+result;//Proper fraction improper fraction mixed number
        }
        else {
            int tempRemainder = numerator%denominator;
            int tempMix = (numerator-tempRemainder)/denominator;

            if(tempRemainder == 0)
                result2 = tempMix+"";
            else {
                result2 = tempMix + "(" + tempRemainder + "/" + denominator + ")";
            }
            return "Result: "+result+" or "+result2;
        }
    }
    public String returnResult(int numerator, int denominator) {
        String result = numerator +"/"+denominator;
        if(numerator<=denominator) {
            return "Result: "+result;//Proper fraction improper fraction mixed number
        }
        else {
            int tempRemainder = numerator%denominator;
            int tempMix = (numerator-tempRemainder)/denominator;
            String result2 = tempMix+"("+tempRemainder+"/"+denominator+")";
            return "Result: "+result+" or "+result2;
        }
    }

    //addition，subtraction，multiplication and division
    public void add(Fractions f) {
        int remainder = 0;
        int gcd = 0;
        int newDe = 0;
        int newNumer = 0;

        gcd = getGCD(denominator,f.getDenominator(),remainder,1);
        newDe = denominator*f.getDenominator()/gcd;
        newNumer = numerator*(f.getDenominator()/gcd)+f.getNumerator()*(denominator/gcd);
        returnResult(newNumer,newDe);

    }

    public void subtract(Fractions f) {
        int remainder = 0;
        int gcd = 0;
        int newDe = 0;
        int newNumer = 0;

        gcd = getGCD(denominator,f.getDenominator(),remainder,1);
        newDe = denominator*f.getDenominator()/gcd;
        newNumer = numerator*(f.getDenominator()/gcd)-f.getNumerator()*(denominator/gcd);
        returnResult(newNumer,newDe);
    }

    public void multiply(Fractions f) {
        int newNumer = numerator*f.getNumerator();
        int newDe = denominator*f.getDenominator();
        int []arr = simplify(0,newNumer,newDe);
        returnResult(arr[0],arr[1]);
    }

    public void divide(Fractions f) {
        int newNumer = numerator*f.getDenominator();
        int newDe = denominator*f.getNumerator();
        int []arr = simplify(0,newNumer,newDe);
        returnResult(arr[0],arr[1]);
    }

    public int[] simplify(int mix,int numerator,int denominator) {
        int[] arr = null;
        if(mix==0)
            arr = factorization(numerator,denominator);
        else {
            numerator = mix*denominator+numerator;
            arr = factorization(numerator,denominator);
        }
        return arr;
    }

    public int[] factorization(int numerator,int denominator) {

        int remainder = 0;
        int gcd = 0;

        if(numerator<=denominator) {
            gcd = getGCD(denominator,numerator,remainder,1);
        }
        else{
            gcd = getGCD(numerator,denominator,remainder,1);
        }
        int [] arr = {numerator/gcd,denominator/gcd};
        return arr;
    }

    public int getGCD (int numerator,int denominator,int remainder,int flag) {
        remainder = numerator%denominator;
        if(remainder == 0) {
            flag = denominator;
        }
        else if(remainder == 1) {
            flag = 1;
        }
        else {
            numerator = denominator;
            denominator = remainder;
            flag = getGCD(numerator,denominator,remainder,flag);
        }
        return flag;
    }

}
