package com.ykrc17.interestcalculater;

/**
 * Created by YK-MSI-GE60 on 2016/10/15 015.
 */

public class InterestCalculator {
    public static double calcPayment(double principal, double rate, int number) {
        double pow = Math.pow(rate + 1, number);
        return principal * rate * pow / (pow - 1);
    }
}
