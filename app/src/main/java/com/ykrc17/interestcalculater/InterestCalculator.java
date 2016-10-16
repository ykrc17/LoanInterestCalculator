package com.ykrc17.interestcalculater;

/**
 * Created by YK-MSI-GE60 on 2016/10/15 015.
 */

public class InterestCalculator {
    public static double calcRate(double principal, double payment, int number) {
        double min = 0;
        double max = 0.01;
        while (payment > calcPayment(principal, max, number)) {
            min = max;
            max *= 2;
        }
        return recursiveCalcRate(principal, payment, number, min, max);
    }

    private static double recursiveCalcRate(double principal, double payment, int number, double minRate, double maxRate) {
        double rate = (minRate + maxRate) / 2;
        // 精确到0.01%
        if ((maxRate - minRate) < (0.01 / 100 / 2)) {
            return rate;
        }
        if (payment < calcPayment(principal, rate, number)) {
            return recursiveCalcRate(principal, payment, number, minRate, rate);
        } else {
            return recursiveCalcRate(principal, payment, number, rate, maxRate);
        }
    }

    public static double calcPayment(double principal, double rate, int number) {
        double pow = Math.pow(rate + 1, number);
        return principal * rate * pow / (pow - 1);
    }
}
