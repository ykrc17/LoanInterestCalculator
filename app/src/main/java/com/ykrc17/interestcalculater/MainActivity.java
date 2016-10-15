package com.ykrc17.interestcalculater;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, MainPresenter.View {
    private enum Mode {
        PRINCIPAL, INTEREST_RATE, PAYMENT_AMOUNT, PAYMENT_NUMBER;

    }
    private MainPresenter mPresenter;

    private EditText et_principal, et_interest_rate, et_payment, et_payment_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        et_principal = (EditText) findViewById(R.id.et_principal);
        et_interest_rate = (EditText) findViewById(R.id.et_interest_rate);
        et_payment = (EditText) findViewById(R.id.et_payment);
        et_payment_number = (EditText) findViewById(R.id.et_payment_number);
        findViewById(R.id.btn_go).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go: {
                Mode mode = null;
                if (TextUtils.isEmpty(et_principal.getText())) {
                    mode = Mode.PRINCIPAL;
                }
                if (TextUtils.isEmpty(et_interest_rate.getText())) {
                    if (mode == null) {
                        mode = Mode.INTEREST_RATE;
                    } else {
                        showError();
                        return;
                    }
                }
                if (TextUtils.isEmpty(et_payment.getText())) {
                    if (mode == null) {
                        mode = Mode.PAYMENT_AMOUNT;
                    } else {
                        showError();
                        return;
                    }
                }
                if (TextUtils.isEmpty(et_payment_number.getText())) {
                    if (mode == null) {
                        mode = Mode.PAYMENT_NUMBER;
                    } else {
                        showError();
                        return;
                    }
                }
                if (mode == null) {
                    showError();
                }
                switch (mode) {
                    case PAYMENT_AMOUNT: {
                        double principal = Double.parseDouble(et_principal.getText().toString());
                        double rate = Double.parseDouble(et_interest_rate.getText().toString()) / 100;
//                        double payment = Double.parseDouble(et_payment.getText().toString());
                        int number = Integer.parseInt(et_payment_number.getText().toString());
                        mPresenter.calcPayment(principal, rate, number);
                        break;
                    }
                    case PAYMENT_NUMBER: {
                        showError("期数都不知道，搞毛啊");
                        break;
                    }
                }
                break;
            }
        }
    }

    private void showError() {
        showError("你到底想求什么？");
    }

    private void showError(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPrincipal() {

    }

    @Override
    public void showRate() {

    }

    @Override
    public void showPayment(double payment) {
        et_payment.setText(String.valueOf(payment));
    }

    @Override
    public void showNumber() {

    }
}
