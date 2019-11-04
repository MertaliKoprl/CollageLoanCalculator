package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double TotalLoan;
    double loanTerm;
    double interestRate;
    TextView MonthlyResult;
    TextView totalInterestText;
    TextView totalLoanText;
    TextView LabelTerm;
    boolean operate;
    DecimalFormat afterDotDigitFormat = new DecimalFormat(".00");//Format
    DecimalFormat afterDotOneDigitFormat = new DecimalFormat(".0");//Format

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LabelTerm = (TextView) findViewById(R.id.LabelTerm);
        LabelTerm.setVisibility(View.INVISIBLE);
        MonthlyResult = (TextView) findViewById(R.id.resultMonthly);
        totalLoanText = (TextView) findViewById(R.id.totalLoanText);
        totalInterestText = (TextView) findViewById(R.id.totalInterest);
        EditText loanAmount = (EditText) findViewById(R.id.loanAmountInput);
        EditText interestRateInput = (EditText) findViewById(R.id.interestRate);
        EditText loanTermInput = (EditText) findViewById(R.id.loanTermInput);




        interestRateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {

                        interestRate = Double.parseDouble(s.toString());
                        operate = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Enter Valid Amound of Year(5,10,15,20,25 or 30)", Toast.LENGTH_SHORT).show();

                    operate = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loanTermInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    double termYear = Double.parseDouble(s.toString());

                        if (termYear == 5 || termYear == 10 || termYear == 15 || termYear == 20 || termYear == 25 || termYear == 30) {
                            operate = true;
                            loanTerm = termYear;//In years
                        } else {
                            operate = false;
                            Toast.makeText(getApplicationContext(), "Enter Valid Amound of Year(5,10,15,20,25 or 30)", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    Toast.makeText(getApplicationContext(), "Enter Valid Data.", Toast.LENGTH_SHORT).show();

                    operate = false;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loanAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                        TotalLoan = Double.parseDouble(s.toString());
                        operate = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Enter Valid Amound of Year(5,10,15,20,25 or 30)", Toast.LENGTH_SHORT).show();
                    operate = false;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void CalculatePayment(View view) {
        if (operate && interestRate != 0 && loanTerm != 0 && TotalLoan != 0) {
            LabelTerm.setText(afterDotOneDigitFormat.format(loanTerm)+ " Years For Loan");
            LabelTerm.setVisibility(View.VISIBLE);
            interestRate = interestRate / 100;//To make equation easier
            double MonthlyInteresRate = interestRate / 12;
            double loanTermMonth = loanTerm * 12;
            double MonthlyPaymentLoan = (TotalLoan * MonthlyInteresRate) / (1 - Math.pow(1 + MonthlyInteresRate, -loanTermMonth));
            double totalInterest = TotalLoan - (MonthlyPaymentLoan * loanTermMonth);
            double interest = totalInterest * -1;


            MonthlyResult.setText(afterDotDigitFormat.format(MonthlyPaymentLoan));
            totalInterestText.setText(afterDotDigitFormat.format(-totalInterest) + " $");
            totalLoanText.setText(afterDotDigitFormat.format(TotalLoan + interest) + " $");
        } else {
            Toast.makeText(getApplicationContext(), "Something is missing, Check Parameters again.", Toast.LENGTH_SHORT).show();

        }
    }


}
