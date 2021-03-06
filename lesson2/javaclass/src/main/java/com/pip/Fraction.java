package com.pip;

/**
 * Fraction class with gcd comparing
 */
public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public static int gcd(int m, int n) {
        if (m > n) {
            if (n == 0) {
                return m;
            } else {
                return gcd(n, m % n);
            }
        } else {
            if (m == 0) {
                return n;
            } else {
                return gcd(m, n % m);
            }
        }
    }

    public void simplify() {
        int common = gcd(numerator, denominator);

        if (common > 1) {
            numerator = numerator / common;
            denominator = denominator / common;
        }
    }

    public Fraction add(Fraction frac) {
        int top = 0;
        int bottom = 0;

        if (frac.getDenominator() == denominator) {
            top = frac.getNumerator() + numerator;
            bottom = denominator;
        } else {
            top = frac.getNumerator() * denominator + numerator * frac.getDenominator();
            bottom = frac.getDenominator() * denominator;
        }

        return new Fraction(top, bottom);
    }

    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }
}
