package com.Fractions;

public class Main {

    public static void main(String[] args) {
        /*
	    Fractions f = new Fractions(0,12,6);
        Fractions f2 = new Fractions(0,1000,11);
        String s = f.multiply(f2);
        System.out.println(s);
        */
    }
}

class Fractions {

    private int mix = 0;
    private int numerator = 0;
    private int denominator = 1;

    //  a(b/c)  a->mix b->numerator c->denominator

    Fractions(int mix,int numerator,int denominator) {

        init(mix,numerator,denominator);
    }

    /*
    Fractions(String format) {


    }
    */

    private void init(int mix,int numerator,int denominator) {
        int arr[] = null;
        arr = simplify(mix,numerator,denominator);
        this.numerator = arr[0];
        this.denominator = arr[1];
    }//Initialize the object.


    //getter and setters:
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
    }//Return a String of the fraction after some operations (simplify...).

    private String returnResult(int numerator, int denominator) {
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
    }//Return final fractions in standard format, for inner methods only.

    //addition，subtraction，multiplication and division
    public String add(Fractions f) {
        int remainder = 0;
        int gcd = 0;
        int newDe = 0;
        int newNumer = 0;

        gcd = getGCD(denominator,f.getDenominator(),remainder,1);
        newDe = denominator*f.getDenominator()/gcd;
        newNumer = numerator*(f.getDenominator()/gcd)+f.getNumerator()*(denominator/gcd);
        return returnResult(newNumer,newDe);

    }

    public String subtract(Fractions f) {
        int remainder = 0;
        int gcd = 0;
        int newDe = 0;
        int newNumer = 0;

        gcd = getGCD(denominator,f.getDenominator(),remainder,1);
        newDe = denominator*f.getDenominator()/gcd;
        newNumer = numerator*(f.getDenominator()/gcd)-f.getNumerator()*(denominator/gcd);
        return returnResult(newNumer,newDe);
    }

    public String multiply(Fractions f) {
        int newNumer = numerator*f.getNumerator();
        int newDe = denominator*f.getDenominator();
        int []arr = simplify(0,newNumer,newDe);
        return returnResult(arr[0],arr[1]);
    }

    public String divide(Fractions f) {
        int newNumer = numerator*f.getDenominator();
        int newDe = denominator*f.getNumerator();
        int []arr = simplify(0,newNumer,newDe);
        return returnResult(arr[0],arr[1]);
    }

    private int[] simplify(int mix,int numerator,int denominator) {
        int[] arr = null;
        if(mix==0)
            arr = factorization(numerator,denominator);
        else {
            numerator = mix*denominator+numerator;
            arr = factorization(numerator,denominator);
        }
        return arr;
    }//Simplify a/b

    private int[] factorization(int numerator,int denominator) {

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
    }//Factor (a/b)

    private int getGCD (int numerator,int denominator,int remainder,int flag) {
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
    }// Get greatest common divisor between two numbers.

}
