package com.ykrc17.interestcalculater;

/**
 * Created by YK-MSI-GE60 on 2016/10/15 015.
 */

public class MainPresenter {
    private View mView;

    public MainPresenter(View view) {
        this.mView = view;
    }

    public void calcRate(double principal, double payment, int number) {
        mView.showRate(InterestCalculator.calcRate(principal, payment, number));
    }

    public void calcPayment(double principal, double rate, int number) {
        mView.showPayment(InterestCalculator.calcPayment(principal, rate, number));
    }

    public interface View {
        void showPrincipal();

        void showRate(double rate);

        void showPayment(double payment);

        void showNumber();
    }
}
